package com.hermit.iii.infraction.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

public class InfractionJNDIDAO implements InfractionDAO_interface {
	DataSource ds = null;

	public InfractionJNDIDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/JNDI");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	String insert = "insert into Infraction values(?,?,getDate(),?)";
	// 同上順序，全改通吃法(PK流水號當條件)
	private static final String UPDATE_STMT = "UPDATE Infraction SET memNO=?, reason=?, inDate=?, empNO=? WHERE inNO = ?";
	private static final String DELETE_STMT = "DELETE FROM Infraction WHERE inNO = ?";
	// (含PK流水號)全選，避免用＊(PK流水號當條件)
	private static final String GET_ONE_STMT = "SELECT inNO,memNO,reason,inDate,empNO FROM Infraction WHERE inNO = ?";
	// (含PK流水號)全選，避免用＊(免PK流水號當條件全打包，PK流水號當排序使得每次結果顯示方式統一)
	private static final String GET_ALL_STMT = "SELECT inNO,memNO,reason,inDate,empNO FROM Infraction ORDER BY inNO";

	// 新增，已測試
	@Override
	public Integer insert(InfractionVO inVO) {
		Integer result = 0;
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(insert);) {
			ps.setInt(1, inVO.getMemNO());
			ps.setString(2, inVO.getReason());
			ps.setInt(3, inVO.getEmpNO());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void update(InfractionVO infractionVO) {
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_STMT);) {

			pstmt.setInt(1, infractionVO.getMemNO());
			pstmt.setString(2, infractionVO.getReason());
			pstmt.setDate(3, infractionVO.getInDate());
			pstmt.setInt(4, infractionVO.getEmpNO());
			pstmt.setInt(5, infractionVO.getInNO());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer inNO) {
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_STMT);) {

			pstmt.setInt(1, inNO);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public InfractionVO findByPrimaryKey(Integer inNO) {
		ResultSet rs = null;
		InfractionVO infractionVO = null;

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ONE_STMT);) {

			pstmt.setInt(1, inNO);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 確定有資料才開始new InfractionVO物件
				// infractionVO = Domain objects
				infractionVO = new InfractionVO();
				infractionVO.setInNO(rs.getInt("inNO"));
				infractionVO.setMemNO(rs.getInt("memNO"));
				infractionVO.setReason(rs.getString("reason"));
				infractionVO.setInDate(rs.getDate("inDate"));
				infractionVO.setEmpNO(rs.getInt("empNO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Clean up resources
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return infractionVO;
	}

	@Override
	public Set<InfractionVO> getAll() {
		ResultSet rs = null;
		InfractionVO infractionVO = null;
		Set<InfractionVO> set = new LinkedHashSet<InfractionVO>();

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ALL_STMT);) {

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 確定有資料才開始new InfractionVO物件
				// infractionVO = Domain objects
				infractionVO = new InfractionVO();
				infractionVO.setInNO(rs.getInt("inNO"));
				infractionVO.setMemNO(rs.getInt("memNO"));
				infractionVO.setReason(rs.getString("reason"));
				infractionVO.setInDate(rs.getDate("inDate"));
				infractionVO.setEmpNO(rs.getInt("empNO"));
				set.add(infractionVO); // Store the row in the list
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Clean up resources
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return set;
	}
}