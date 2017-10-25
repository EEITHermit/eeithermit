package com.hermit.iii.teammemberlist.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

public class TeamMemberListJNDIDAO implements TeamMemberListDAO_interface {
	DataSource ds = null;

	public TeamMemberListJNDIDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/JNDI");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// memberListNO在資料庫為自動流水號免新增，順序同資料庫表格(與VO/Bean)設定
	// (empNO,businNO,tmlStartTime,tmlEndTime,tmlStatus)
	private static final String INSERT_STMT = "INSERT INTO TeamMemberList VALUES (?, ?, ?, ?, ?)";
	// 同上順序，全改通吃法(PK流水號當條件)
	private static final String UPDATE_STMT = "UPDATE TeamMemberList SET empNO=?, businNO=?, tmlStartTime=?, tmlEndTime=?, tmlStatus=? WHERE memberListNO = ?";
	private static final String DELETE_STMT = "DELETE FROM TeamMemberList WHERE memberListNO = ?";
	// (含PK流水號)全選，避免用＊(PK流水號當條件)
	private static final String GET_ONE_STMT = "SELECT memberListNO,empNO,businNO,tmlStartTime,tmlEndTime,tmlStatus FROM TeamMemberList WHERE memberListNO = ?";
	// (含PK流水號)全選，避免用＊(免PK流水號當條件全打包，PK流水號當排序使得每次結果顯示方式統一)
	private static final String GET_ALL_STMT = "SELECT memberListNO,empNO,businNO,tmlStartTime,tmlEndTime,tmlStatus FROM TeamMemberList ORDER BY memberListNO";

	@Override
	public void insert(TeamMemberListVO teamMemberListVO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);) {

			pstmt.setInt(1, teamMemberListVO.getEmpNO());
			pstmt.setInt(2, teamMemberListVO.getBusinNO());
			pstmt.setDate(3, teamMemberListVO.getTmlStartTime());
			pstmt.setDate(4, teamMemberListVO.getTmlEndTime());
			pstmt.setByte(5, teamMemberListVO.getTmlStatus());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(TeamMemberListVO teamMemberListVO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_STMT);) {

			pstmt.setInt(1, teamMemberListVO.getEmpNO());
			pstmt.setInt(2, teamMemberListVO.getBusinNO());
			pstmt.setDate(3, teamMemberListVO.getTmlStartTime());
			pstmt.setDate(4, teamMemberListVO.getTmlEndTime());
			pstmt.setByte(5, teamMemberListVO.getTmlStatus());
			pstmt.setInt(6, teamMemberListVO.getMemberListNO());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer memberListNO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_STMT);) {

			pstmt.setInt(1, memberListNO);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TeamMemberListVO findByPrimaryKey(Integer memberListNO) {
		ResultSet rs = null;
		TeamMemberListVO teamMemberListVO = null;

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ONE_STMT);) {

			pstmt.setInt(1, memberListNO);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 確定有資料才開始new TeamMemberListVO物件
				// teamMemberListVO = Domain objects
				teamMemberListVO = new TeamMemberListVO();
				teamMemberListVO.setMemberListNO(rs.getInt("memberListNO"));
				teamMemberListVO.setEmpNO(rs.getInt("empNO"));
				teamMemberListVO.setBusinNO(rs.getInt("businNO"));
				teamMemberListVO.setTmlStartTime(rs.getDate("tmlStartTime"));
				teamMemberListVO.setTmlEndTime(rs.getDate("tmlEndTime"));
				teamMemberListVO.setTmlStatus(rs.getByte("tmlStatus"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teamMemberListVO;
	}

	@Override
	public Set<TeamMemberListVO> getAll() {
		ResultSet rs = null;
		TeamMemberListVO teamMemberListVO = null;
		Set<TeamMemberListVO> set = new LinkedHashSet<TeamMemberListVO>();

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ALL_STMT);) {

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 確定有資料才開始new TeamMemberListVO物件
				// teamMemberListVO = Domain objects
				teamMemberListVO = new TeamMemberListVO();
				teamMemberListVO.setMemberListNO(rs.getInt("memberListNO"));
				teamMemberListVO.setEmpNO(rs.getInt("empNO"));
				teamMemberListVO.setBusinNO(rs.getInt("businNO"));
				teamMemberListVO.setTmlStartTime(rs.getDate("tmlStartTime"));
				teamMemberListVO.setTmlEndTime(rs.getDate("tmlEndTime"));
				teamMemberListVO.setTmlStatus(rs.getByte("tmlStatus"));
				set.add(teamMemberListVO); // Store the row in the list
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}
}