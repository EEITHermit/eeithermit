package com.hermit.iii.infraction.model;

import java.sql.*;
import java.util.*;

public class InfractionJDBCDAO implements InfractionDAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Hermit";
	String userid = "sa";
	String passwd = "P@ssw0rd";

	// inNO在資料庫為自動流水號免新增，順序同資料庫表格(與VO/Bean)設定
	// (memNO,reason,inDate,empNO)
	private static final String INSERT_STMT = "INSERT INTO Infraction VALUES (?, ?, getDate(), ?)";
	// 同上順序，全改通吃法(PK流水號當條件)
	private static final String UPDATE_STMT = "UPDATE Infraction SET memNO=?, reason=?, inDate=?, empNO=? WHERE inNO = ?";
	private static final String DELETE_STMT = "DELETE FROM Infraction WHERE inNO = ?";
	// (含PK流水號)全選，避免用＊(PK流水號當條件)
	private static final String GET_ONE_STMT = "SELECT inNO,memNO,reason,inDate,empNO FROM Infraction WHERE inNO = ?";
	// (含PK流水號)全選，避免用＊(免PK流水號當條件全打包，PK流水號當排序使得每次結果顯示方式統一)
	private static final String GET_ALL_STMT = "SELECT inNO,memNO,reason,inDate,empNO FROM Infraction ORDER BY inNO";

	@Override
	public Integer insert(InfractionVO infractionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer result = 0;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, infractionVO.getMemNO());
			pstmt.setString(2, infractionVO.getReason());
			pstmt.setInt(3, infractionVO.getEmpNO());

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
		return result;
	}

	@Override
	public void update(InfractionVO infractionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, infractionVO.getMemNO());
			pstmt.setString(2, infractionVO.getReason());
			pstmt.setDate(3, infractionVO.getInDate());
			pstmt.setInt(4, infractionVO.getEmpNO());
			pstmt.setInt(5, infractionVO.getInNO());

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
	public void delete(Integer inNO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, inNO);

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
	public InfractionVO findByPrimaryKey(Integer inNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InfractionVO infractionVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

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

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return infractionVO;
	}

	@Override
	public Set<InfractionVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InfractionVO infractionVO = null;
		Set<InfractionVO> set = new LinkedHashSet<InfractionVO>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

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

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		InfractionJDBCDAO dao = new InfractionJDBCDAO();

		// 新增
		InfractionVO infractionVO1 = new InfractionVO();
		infractionVO1.setMemNO(40001);
		infractionVO1.setReason("reason123");
		infractionVO1.setInDate(java.sql.Date.valueOf("2017-10-08"));
		infractionVO1.setEmpNO(30001);
		dao.insert(infractionVO1);

		// 修改初始資料第一筆
		InfractionVO infractionVO2 = new InfractionVO();
		infractionVO2.setInNO(40001);
		infractionVO2.setMemNO(40001);
		infractionVO2.setReason("reason456");
		infractionVO2.setInDate(java.sql.Date.valueOf("2017-07-08"));
		infractionVO2.setEmpNO(30001);
		dao.update(infractionVO2);

		// 查詢初始資料第一筆
		InfractionVO infractionVO3 = dao.findByPrimaryKey(40001);
		System.out.print(infractionVO3.getInNO() + ",");
		System.out.print(infractionVO3.getMemNO() + ",");
		System.out.print(infractionVO3.getReason() + ",");
		System.out.print(infractionVO3.getInDate() + ",");
		System.out.println(infractionVO3.getEmpNO());
		System.out.println("---------------------");

		// 查詢全部
		Set<InfractionVO> set = dao.getAll();
		for (InfractionVO Infraction : set) {
			System.out.print(Infraction.getInNO() + ",");
			System.out.print(Infraction.getMemNO() + ",");
			System.out.print(Infraction.getReason() + ",");
			System.out.print(Infraction.getInDate() + ",");
			System.out.println(Infraction.getEmpNO());
		}

		// 刪除初始資料一筆
		dao.delete(40001);

		System.out.println("Done");
	}
}