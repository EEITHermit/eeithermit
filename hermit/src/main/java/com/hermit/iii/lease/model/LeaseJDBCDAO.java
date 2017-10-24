package com.hermit.iii.lease.model;

import java.io.*;
import java.sql.*;
import java.util.*;

public class LeaseJDBCDAO implements LeaseDAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Hermit";
	String userid = "sa";
	String passwd = "P@ssw0rd";

	// leaseNO在資料庫為自動流水號免新增，順序同資料庫表格(與VO/Bean)設定
	// (houseNO,leaseBeginDate,leaseEndDate,memNO,empNO,Rent,Deposit,Relief,leaseDate,leasePic,houseNote,Refund)
	private static final String INSERT_STMT = "INSERT INTO Lease VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	// 同上順序，全改通吃法(PK流水號當條件)
	private static final String UPDATE_STMT = "UPDATE Lease SET houseNO=?, leaseBeginDate=?, leaseEndDate=?, memNO=?, empNO=?, Rent=?, Deposit=?, Relief=?, leaseDate=?, leasePic=?, houseNote=?, Refund=? WHERE leaseNO = ?";
	private static final String DELETE_STMT = "DELETE FROM Lease WHERE leaseNO = ?";
	// (含PK流水號)全選，避免用＊(PK流水號當條件)
	private static final String GET_ONE_STMT = "SELECT leaseNO,houseNO,leaseBeginDate,leaseEndDate,memNO,empNO,Rent,Deposit,Relief,leaseDate,leasePic,houseNote,Refund FROM Lease WHERE leaseNO = ?";
	// (含PK流水號)全選，避免用＊(免PK流水號當條件全打包，PK流水號當排序使得每次結果顯示方式統一)
	private static final String GET_ALL_STMT = "SELECT leaseNO,houseNO,leaseBeginDate,leaseEndDate,memNO,empNO,Rent,Deposit,Relief,leaseDate,leasePic,houseNote,Refund FROM Lease ORDER BY leaseNO";

	@Override
	public void insert(LeaseVO leaseVO, InputStream is, long size) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, leaseVO.getHouseNO());
			pstmt.setDate(2, leaseVO.getLeaseBeginDate());
			pstmt.setDate(3, leaseVO.getLeaseEndDate());
			pstmt.setInt(4, leaseVO.getMemNO());
			pstmt.setInt(5, leaseVO.getEmpNO());
			pstmt.setInt(6, leaseVO.getRent());
			pstmt.setInt(7, leaseVO.getDeposit());
			pstmt.setInt(8, leaseVO.getRelief());
			pstmt.setDate(9, leaseVO.getLeaseDate());
			pstmt.setBinaryStream(10, is, size);
			pstmt.setString(11, leaseVO.getHouseNote());
			pstmt.setByte(12, leaseVO.getRefund());

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
	public void update(LeaseVO leaseVO, InputStream is, long size) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, leaseVO.getHouseNO());
			pstmt.setDate(2, leaseVO.getLeaseBeginDate());
			pstmt.setDate(3, leaseVO.getLeaseEndDate());
			pstmt.setInt(4, leaseVO.getMemNO());
			pstmt.setInt(5, leaseVO.getEmpNO());
			pstmt.setInt(6, leaseVO.getRent());
			pstmt.setInt(7, leaseVO.getDeposit());
			pstmt.setInt(8, leaseVO.getRelief());
			pstmt.setDate(9, leaseVO.getLeaseDate());
			pstmt.setBinaryStream(10, is, size);
			pstmt.setString(11, leaseVO.getHouseNote());
			pstmt.setByte(12, leaseVO.getRefund());
			pstmt.setInt(13, leaseVO.getLeaseNO());

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
	public void delete(Integer leaseNO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, leaseNO);

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
	public LeaseVO findByPrimaryKey(Integer leaseNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LeaseVO leaseVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, leaseNO);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 確定有資料才開始new LeaseVO物件
				// leaseVO = Domain objects
				leaseVO = new LeaseVO();
				leaseVO.setLeaseNO(rs.getInt("leaseNO"));
				leaseVO.setHouseNO(rs.getInt("houseNO"));
				leaseVO.setLeaseBeginDate(rs.getDate("leaseBeginDate"));
				leaseVO.setLeaseEndDate(rs.getDate("leaseEndDate"));
				leaseVO.setMemNO(rs.getInt("memNO"));
				leaseVO.setEmpNO(rs.getInt("empNO"));
				leaseVO.setRent(rs.getInt("Rent"));
				leaseVO.setDeposit(rs.getInt("Deposit"));
				leaseVO.setRelief(rs.getInt("Relief"));
				leaseVO.setLeaseDate(rs.getDate("leaseDate"));
				leaseVO.setLeasePic(rs.getBlob("leasePic"));
				leaseVO.setHouseNote(rs.getString("houseNote"));
				leaseVO.setRefund(rs.getByte("Refund"));
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
		return leaseVO;
	}

	@Override
	public Set<LeaseVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LeaseVO leaseVO = null;
		Set<LeaseVO> set = new LinkedHashSet<LeaseVO>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 確定有資料才開始new LeaseVO物件
				// leaseVO = Domain objects
				leaseVO = new LeaseVO();
				leaseVO.setLeaseNO(rs.getInt("leaseNO"));
				leaseVO.setHouseNO(rs.getInt("houseNO"));
				leaseVO.setLeaseBeginDate(rs.getDate("leaseBeginDate"));
				leaseVO.setLeaseEndDate(rs.getDate("leaseEndDate"));
				leaseVO.setMemNO(rs.getInt("memNO"));
				leaseVO.setEmpNO(rs.getInt("empNO"));
				leaseVO.setRent(rs.getInt("Rent"));
				leaseVO.setDeposit(rs.getInt("Deposit"));
				leaseVO.setRelief(rs.getInt("Relief"));
				leaseVO.setLeaseDate(rs.getDate("leaseDate"));
				leaseVO.setLeasePic(rs.getBlob("leasePic"));
				leaseVO.setHouseNote(rs.getString("houseNote"));
				leaseVO.setRefund(rs.getByte("Refund"));
				set.add(leaseVO); // Store the row in the list
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
		LeaseJDBCDAO dao = new LeaseJDBCDAO();

		// 新增
		LeaseVO leaseVO1 = new LeaseVO();
		leaseVO1.setHouseNO(20001);
		leaseVO1.setLeaseBeginDate(java.sql.Date.valueOf("2015-10-10"));
		leaseVO1.setLeaseEndDate(java.sql.Date.valueOf("2017-01-01"));
		leaseVO1.setMemNO(40001);
		leaseVO1.setEmpNO(30001);
		leaseVO1.setRent(1000);
		leaseVO1.setDeposit(1000);
		leaseVO1.setRelief(1000);
		leaseVO1.setLeaseDate(java.sql.Date.valueOf("2017-04-01"));
		// leaseVO1.setLeasePic(null); // not use
		leaseVO1.setHouseNote("備註123");
		leaseVO1.setRefund((byte) 10);
		dao.insert(leaseVO1, null, 0);

		// 修改初始資料第一筆
		LeaseVO leaseVO2 = new LeaseVO();
		leaseVO2.setLeaseNO(200001);
		leaseVO2.setHouseNO(20001);
		leaseVO2.setLeaseBeginDate(java.sql.Date.valueOf("2014-10-10"));
		leaseVO2.setLeaseEndDate(java.sql.Date.valueOf("2016-01-01"));
		leaseVO2.setMemNO(40001);
		leaseVO2.setEmpNO(30001);
		leaseVO2.setRent(1000);
		leaseVO2.setDeposit(1000);
		leaseVO2.setRelief(1000);
		leaseVO2.setLeaseDate(java.sql.Date.valueOf("2018-04-01"));
		// leaseVO2.setLeasePic(null); // not use
		leaseVO2.setHouseNote("備註123");
		leaseVO2.setRefund((byte) 10);
		dao.update(leaseVO2, null, 0);

		// 查詢初始資料第一筆
		LeaseVO leaseVO3 = dao.findByPrimaryKey(200001);
		System.out.print(leaseVO3.getLeaseNO() + ",");
		System.out.print(leaseVO3.getHouseNO() + ",");
		System.out.print(leaseVO3.getLeaseBeginDate() + ",");
		System.out.print(leaseVO3.getLeaseEndDate() + ",");
		System.out.print(leaseVO3.getMemNO() + ",");
		System.out.print(leaseVO3.getEmpNO() + ",");
		System.out.print(leaseVO3.getRent() + ",");
		System.out.print(leaseVO3.getDeposit() + ",");
		System.out.print(leaseVO3.getRelief() + ",");
		System.out.print(leaseVO3.getLeaseDate() + ",");
		System.out.print(leaseVO3.getLeasePic() + ",");
		System.out.print(leaseVO3.getHouseNote() + ",");
		System.out.println(leaseVO3.getRefund());
		System.out.println("---------------------");

		// 查詢全部
		Set<LeaseVO> set = dao.getAll();
		for (LeaseVO leaseVO : set) {
			System.out.print(leaseVO.getLeaseNO() + ",");
			System.out.print(leaseVO.getHouseNO() + ",");
			System.out.print(leaseVO.getLeaseBeginDate() + ",");
			System.out.print(leaseVO.getLeaseEndDate() + ",");
			System.out.print(leaseVO.getMemNO() + ",");
			System.out.print(leaseVO.getEmpNO() + ",");
			System.out.print(leaseVO.getRent() + ",");
			System.out.print(leaseVO.getDeposit() + ",");
			System.out.print(leaseVO.getRelief() + ",");
			System.out.print(leaseVO.getLeaseDate() + ",");
			System.out.print(leaseVO.getLeasePic() + ",");
			System.out.print(leaseVO.getHouseNote() + ",");
			System.out.println(leaseVO.getRefund());
			System.out.println();
		}
		// 刪除初始資料一筆
		dao.delete(200001);

		System.out.println("Done");
	}
}