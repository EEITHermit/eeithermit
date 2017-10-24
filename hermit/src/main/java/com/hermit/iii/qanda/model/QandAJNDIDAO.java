package com.hermit.iii.qanda.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

public class QandAJNDIDAO implements QandADAO_interface {
	DataSource ds = null;

	public QandAJNDIDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/JNDI");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// qaNO在資料庫為自動流水號免新增，順序同資料庫表格(與VO/Bean)設定
	// (memNO,empNO,houseNO,qTime,aTime,qaType,qDetail,aDetail)
	private static final String INSERT_STMT = "INSERT INTO QandA VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	// 同上順序，全改通吃法(PK流水號當條件)
	private static final String UPDATE_STMT = "UPDATE QandA SET memNO=?, empNO=?, houseNO=?, qTime=?, aTime=?, qaType=?, qDetail=?, aDetail=? WHERE qaNO = ?";
	private static final String DELETE_STMT = "DELETE FROM QandA WHERE qaNO = ?";
	// (含PK流水號)全選，避免用＊(PK流水號當條件)
	private static final String GET_ONE_STMT = "SELECT qaNO,memNO,empNO,houseNO,qTime,aTime,qaType,qDetail,aDetail FROM QandA WHERE qaNO = ?";
	// (含PK流水號)全選，避免用＊(免PK流水號當條件全打包，PK流水號當排序使得每次結果顯示方式統一)
	private static final String GET_ALL_STMT = "SELECT qaNO,memNO,empNO,houseNO,qTime,aTime,qaType,qDetail,aDetail FROM QandA ORDER BY qaNO";

	@Override
	public void insert(QandAVO qandaVO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);) {

			pstmt.setInt(1, qandaVO.getMemNO());
			pstmt.setInt(2, qandaVO.getEmpNO());
			pstmt.setInt(3, qandaVO.getHouseNO());
			pstmt.setDate(4, qandaVO.getqTime());
			pstmt.setDate(5, qandaVO.getaTime());
			pstmt.setByte(6, qandaVO.getQaType());
			pstmt.setString(7, qandaVO.getqDetail());
			pstmt.setString(8, qandaVO.getaDetail());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(QandAVO qandaVO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_STMT);) {

			pstmt.setInt(1, qandaVO.getMemNO());
			pstmt.setInt(2, qandaVO.getEmpNO());
			pstmt.setInt(3, qandaVO.getHouseNO());
			pstmt.setDate(4, qandaVO.getqTime());
			pstmt.setDate(5, qandaVO.getaTime());
			pstmt.setByte(6, qandaVO.getQaType());
			pstmt.setString(7, qandaVO.getqDetail());
			pstmt.setString(8, qandaVO.getaDetail());
			pstmt.setInt(9, qandaVO.getQaNO());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer qaNO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_STMT);) {

			pstmt.setInt(1, qaNO);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public QandAVO findByPrimaryKey(Integer qaNO) {
		ResultSet rs = null;
		QandAVO qandaVO = null;

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ONE_STMT);) {

			pstmt.setInt(1, qaNO);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 確定有資料才開始new QandAVO物件
				// qandaVO = Domain objects
				qandaVO = new QandAVO();
				qandaVO.setQaNO(rs.getInt("qaNO"));
				qandaVO.setMemNO(rs.getInt("memNO"));
				qandaVO.setEmpNO(rs.getInt("empNO"));
				qandaVO.setHouseNO(rs.getInt("houseNO"));
				qandaVO.setqTime(rs.getDate("qTime"));
				qandaVO.setaTime(rs.getDate("aTime"));
				qandaVO.setQaType(rs.getByte("qaType"));
				qandaVO.setqDetail(rs.getString("qDetail"));
				qandaVO.setaDetail(rs.getString("aDetail"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qandaVO;
	}

	@Override
	public Set<QandAVO> getAll() {
		ResultSet rs = null;
		QandAVO qandaVO = null;
		Set<QandAVO> set = new LinkedHashSet<QandAVO>();

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ALL_STMT);) {

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 確定有資料才開始new QandAVO物件
				// qandaVO = Domain objects
				qandaVO = new QandAVO();
				qandaVO.setQaNO(rs.getInt("qaNO"));
				qandaVO.setMemNO(rs.getInt("memNO"));
				qandaVO.setEmpNO(rs.getInt("empNO"));
				qandaVO.setHouseNO(rs.getInt("houseNO"));
				qandaVO.setqTime(rs.getDate("qTime"));
				qandaVO.setaTime(rs.getDate("aTime"));
				qandaVO.setQaType(rs.getByte("qaType"));
				qandaVO.setqDetail(rs.getString("qDetail"));
				qandaVO.setaDetail(rs.getString("aDetail"));
				set.add(qandaVO); // Store the row in the list
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}
}