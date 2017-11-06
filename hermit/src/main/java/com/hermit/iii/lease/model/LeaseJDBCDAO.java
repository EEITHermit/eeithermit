package com.hermit.iii.lease.model;

import java.sql.*;
import java.util.*;

import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.housepicture.model.HousePictureVO;

public class LeaseJDBCDAO implements LeaseDAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Hermit";
	String userid = "sa";
	String passwd = "P@ssw0rd";

	// leaseNO在資料庫為自動流水號免新增，順序同資料庫表格(與VO/Bean)設定
	// (houseNO,leaseBeginDate,leaseEndDate,memNO,empNO,Rent,Deposit,Relief,leaseDate,leasePic,houseNote,Refund)
	private static final String INSERT_STMT = "INSERT INTO Lease VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	// 同上順序，全改通吃法(PK流水號當條件)
	private static final String UPDATE_STMT = "UPDATE Lease SET houseNO=?, leaseBeginDate=?, leaseEndDate=?, memNO=?, empNO=?, leaseRent=?, leaseDeposit=?, leaseRelief=?, leaseDate=?, leasePic=?, houseNote=?, leaseRefund=? WHERE leaseNO = ?";
	private static final String DELETE_STMT = "DELETE FROM Lease WHERE leaseNO = ?";
	// (含PK流水號)全選，避免用＊(PK流水號當條件)
	private static final String GET_ONE_STMT = "SELECT leaseNO,houseNO,leaseBeginDate,leaseEndDate,memNO,empNO,leaseRent,leaseDeposit,leaseRelief,leaseDate,leasePic,houseNote,leaseRefund FROM Lease WHERE leaseNO = ?";
	// (含PK流水號)全選，避免用＊(免PK流水號當條件全打包，PK流水號當排序使得每次結果顯示方式統一)
	private static final String GET_ALL_STMT = "SELECT leaseNO,houseNO,leaseBeginDate,leaseEndDate,memNO,empNO,leaseRent,leaseDeposit,leaseRelief,leaseDate,leasePic,houseNote,leaseRefund FROM Lease ORDER BY leaseNO";

	@Override
	public void insert(LeaseVO leaseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, leaseVO.getHouseVO().getHouseNO());
			pstmt.setDate(2, leaseVO.getLeaseBeginDate());
			pstmt.setDate(3, leaseVO.getLeaseEndDate());
//			pstmt.setInt(4, leaseVO.getMemNO());
//			pstmt.setInt(5, leaseVO.getEmpNO());
			pstmt.setInt(6, leaseVO.getLeaseRent());
			pstmt.setInt(7, leaseVO.getLeaseDeposit());
			pstmt.setInt(8, leaseVO.getLeaseRelief());
			pstmt.setDate(9, leaseVO.getLeaseDate());
			pstmt.setString(10, leaseVO.getLeasePic());
			pstmt.setString(11, leaseVO.getHouseNote());
			pstmt.setByte(12, leaseVO.getLeaseRefund());

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
	public void update(LeaseVO leaseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, leaseVO.getHouseVO().getHouseNO());
			pstmt.setDate(2, leaseVO.getLeaseBeginDate());
			pstmt.setDate(3, leaseVO.getLeaseEndDate());
//			pstmt.setInt(4, leaseVO.getMemNO());
//			pstmt.setInt(5, leaseVO.getEmpNO());
			pstmt.setInt(6, leaseVO.getLeaseRent());
			pstmt.setInt(7, leaseVO.getLeaseDeposit());
			pstmt.setInt(8, leaseVO.getLeaseRelief());
			pstmt.setDate(9, leaseVO.getLeaseDate());
			pstmt.setString(10, leaseVO.getLeasePic());
			pstmt.setString(11, leaseVO.getHouseNote());
			pstmt.setByte(12, leaseVO.getLeaseRefund());
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
				HouseVO houseVO = new HouseVO();
				houseVO.setHouseNO(rs.getInt("houseNO"));
				leaseVO.setHouseVO(houseVO);
				leaseVO.setLeaseBeginDate(rs.getDate("leaseBeginDate"));
				leaseVO.setLeaseEndDate(rs.getDate("leaseEndDate"));
//				leaseVO.setMemNO(rs.getInt("memNO"));
//				leaseVO.setEmpNO(rs.getInt("empNO"));
				leaseVO.setLeaseRent(rs.getInt("leaseRent"));
				leaseVO.setLeaseDeposit(rs.getInt("leaseDeposit"));
				leaseVO.setLeaseRelief(rs.getInt("leaseRelief"));
				leaseVO.setLeaseDate(rs.getDate("leaseDate"));
				leaseVO.setLeasePic(rs.getString("leasePic"));
				leaseVO.setHouseNote(rs.getString("houseNote"));
				leaseVO.setLeaseRefund(rs.getByte("leaseRefund"));
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
				HouseVO houseVO = new HouseVO();
				houseVO.setHouseNO(rs.getInt("houseNO"));
				leaseVO.setHouseVO(houseVO);
				leaseVO.setLeaseBeginDate(rs.getDate("leaseBeginDate"));
				leaseVO.setLeaseEndDate(rs.getDate("leaseEndDate"));
//				leaseVO.setMemNO(rs.getInt("memNO"));
//				leaseVO.setEmpNO(rs.getInt("empNO"));
				leaseVO.setLeaseRent(rs.getInt("leaseRent"));
				leaseVO.setLeaseDeposit(rs.getInt("leaseDeposit"));
				leaseVO.setLeaseRelief(rs.getInt("leaseRelief"));
				leaseVO.setLeaseDate(rs.getDate("leaseDate"));
				leaseVO.setLeasePic(rs.getString("leasePic"));
				leaseVO.setHouseNote(rs.getString("houseNote"));
				leaseVO.setLeaseRefund(rs.getByte("leaseRefund"));
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
		HouseVO houseVO = new HouseVO();
		houseVO.setHouseNO(20001);
		leaseVO1.setHouseVO(houseVO);
		leaseVO1.setLeaseBeginDate(java.sql.Date.valueOf("2015-10-10"));
		leaseVO1.setLeaseEndDate(java.sql.Date.valueOf("2017-01-01"));
//		leaseVO1.setMemNO(40001);
//		leaseVO1.setEmpNO(30001);
		leaseVO1.setLeaseRent(1000);
		leaseVO1.setLeaseDeposit(1000);
		leaseVO1.setLeaseRelief(1000);
		leaseVO1.setLeaseDate(java.sql.Date.valueOf("2017-04-01"));
		leaseVO1.setLeasePic(null); // not use
		leaseVO1.setHouseNote("備註123");
		leaseVO1.setLeaseRefund((byte) 0);
		dao.insert(leaseVO1);

		// 修改初始資料第一筆
		LeaseVO leaseVO2 = new LeaseVO();
		leaseVO2.setLeaseNO(200001);
		HouseVO houseVO1 = new HouseVO();
		houseVO.setHouseNO(20001);
		leaseVO2.setHouseVO(houseVO1);
		leaseVO2.setLeaseBeginDate(java.sql.Date.valueOf("2014-10-10"));
		leaseVO2.setLeaseEndDate(java.sql.Date.valueOf("2016-01-01"));
//		leaseVO2.setMemNO(40001);
//		leaseVO2.setEmpNO(30001);
		leaseVO2.setLeaseRent(1000);
		leaseVO2.setLeaseDeposit(1000);
		leaseVO2.setLeaseRelief(1000);
		leaseVO2.setLeaseDate(java.sql.Date.valueOf("2018-04-01"));
		// leaseVO2.setLeasePic(null); // not use
		leaseVO2.setHouseNote("備註123");
		leaseVO2.setLeaseRefund((byte) 10);
		dao.update(leaseVO2);

		// 查詢初始資料第一筆
		LeaseVO leaseVO3 = dao.findByPrimaryKey(200001);
		System.out.print(leaseVO3.getLeaseNO() + ",");
		System.out.print(leaseVO3.getHouseVO().getHouseNO() + ",");
		System.out.print(leaseVO3.getLeaseBeginDate() + ",");
		System.out.print(leaseVO3.getLeaseEndDate() + ",");
//		System.out.print(leaseVO3.getMemNO() + ",");
//		System.out.print(leaseVO3.getEmpNO() + ",");
		System.out.print(leaseVO3.getLeaseRent() + ",");
		System.out.print(leaseVO3.getLeaseDeposit() + ",");
		System.out.print(leaseVO3.getLeaseRelief() + ",");
		System.out.print(leaseVO3.getLeaseDate() + ",");
		System.out.print(leaseVO3.getLeasePic() + ",");
		System.out.print(leaseVO3.getHouseNote() + ",");
		System.out.println(leaseVO3.getLeaseRefund());
		System.out.println("---------------------");

		// 查詢全部
		Set<LeaseVO> set = dao.getAll();
		for (LeaseVO leaseVO : set) {
			System.out.print(leaseVO.getLeaseNO() + ",");
			System.out.print(leaseVO.getHouseVO().getHouseNO() + ",");
			System.out.print(leaseVO.getLeaseBeginDate() + ",");
			System.out.print(leaseVO.getLeaseEndDate() + ",");
//			System.out.print(leaseVO.getMemNO() + ",");
//			System.out.print(leaseVO.getEmpNO() + ",");
			System.out.print(leaseVO.getLeaseRent() + ",");
			System.out.print(leaseVO.getLeaseDeposit() + ",");
			System.out.print(leaseVO.getLeaseRelief() + ",");
			System.out.print(leaseVO.getLeaseDate() + ",");
			System.out.print(leaseVO.getLeasePic() + ",");
			System.out.print(leaseVO.getHouseNote() + ",");
			System.out.println(leaseVO.getLeaseRefund());
			System.out.println();
		}
		// 刪除初始資料一筆
		dao.delete(200001);

		System.out.println("Done");
	}


}