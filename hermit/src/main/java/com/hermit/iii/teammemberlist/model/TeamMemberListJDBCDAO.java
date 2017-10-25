package com.hermit.iii.teammemberlist.model;

import java.sql.*;
import java.util.*;

public class TeamMemberListJDBCDAO implements TeamMemberListDAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Hermit";
	String userid = "sa";
	String passwd = "P@ssw0rd";

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
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, teamMemberListVO.getEmpNO());
			pstmt.setInt(2, teamMemberListVO.getBusinNO());
			pstmt.setDate(3, teamMemberListVO.getTmlStartTime());
			pstmt.setDate(4, teamMemberListVO.getTmlEndTime());
			pstmt.setByte(5, teamMemberListVO.getTmlStatus());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(TeamMemberListVO teamMemberListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, teamMemberListVO.getEmpNO());
			pstmt.setInt(2, teamMemberListVO.getBusinNO());
			pstmt.setDate(3, teamMemberListVO.getTmlStartTime());
			pstmt.setDate(4, teamMemberListVO.getTmlEndTime());
			pstmt.setByte(5, teamMemberListVO.getTmlStatus());
			pstmt.setInt(6, teamMemberListVO.getMemberListNO());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer memberListNO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, memberListNO);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public TeamMemberListVO findByPrimaryKey(Integer memberListNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TeamMemberListVO teamMemberListVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

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

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return teamMemberListVO;
	}

	@Override
	public Set<TeamMemberListVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TeamMemberListVO teamMemberListVO = null;
		Set<TeamMemberListVO> set = new LinkedHashSet<TeamMemberListVO>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

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

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}

	public static void main(String[] args) {
		TeamMemberListJDBCDAO dao = new TeamMemberListJDBCDAO();

		// 新增
		TeamMemberListVO teamMemberListVO1 = new TeamMemberListVO();
		teamMemberListVO1.setEmpNO(30001);
		teamMemberListVO1.setBusinNO(30010);
		teamMemberListVO1.setTmlStartTime(java.sql.Date.valueOf("2015-10-10"));
		teamMemberListVO1.setTmlEndTime(java.sql.Date.valueOf("2015-10-11"));
		teamMemberListVO1.setTmlStatus((byte) 1);
		dao.insert(teamMemberListVO1);

		// 修改初始資料第一筆
		TeamMemberListVO teamMemberListVO2 = new TeamMemberListVO();
		teamMemberListVO2.setMemberListNO(30001);
		teamMemberListVO2.setEmpNO(30001);
		teamMemberListVO2.setBusinNO(30010);
		teamMemberListVO2.setTmlStartTime(java.sql.Date.valueOf("2017-10-15"));
		teamMemberListVO2.setTmlEndTime(java.sql.Date.valueOf("2017-10-14"));
		teamMemberListVO2.setTmlStatus((byte) 0);
		dao.update(teamMemberListVO2);

		// 查詢初始資料第一筆
		TeamMemberListVO teamMemberListVO3 = dao.findByPrimaryKey(30001);
		System.out.print(teamMemberListVO3.getMemberListNO() + ",");
		System.out.print(teamMemberListVO3.getEmpNO() + ",");
		System.out.print(teamMemberListVO3.getBusinNO() + ",");
		System.out.print(teamMemberListVO3.getTmlStartTime() + ",");
		System.out.print(teamMemberListVO3.getTmlEndTime() + ",");
		System.out.println(teamMemberListVO3.getTmlStatus());
		System.out.println("---------------------");

		// 查詢全部
		Set<TeamMemberListVO> set = dao.getAll();
		for (TeamMemberListVO teamMemberListVO : set) {
			System.out.print(teamMemberListVO.getMemberListNO() + ",");
			System.out.print(teamMemberListVO.getEmpNO() + ",");
			System.out.print(teamMemberListVO.getBusinNO() + ",");
			System.out.print(teamMemberListVO.getTmlStartTime() + ",");
			System.out.print(teamMemberListVO.getTmlEndTime() + ",");
			System.out.println(teamMemberListVO.getTmlStatus());
			System.out.println();
		}

		// 刪除初始資料一筆
		dao.delete(30002);

		System.out.println("Done");
	}
}