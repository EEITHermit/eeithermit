package com.hermit.iii.qanda.model;

import java.sql.*;
import java.util.*;

public class QandAJDBCDAO implements QandADAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Hermit";
	String userid = "sa";
	String passwd = "P@ssw0rd";

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
	//用memberNO查詢Q&A
	private static final String GET_ALL_BY_MEMBER_NO = "SELECT * FROM QandA Q JOIN house H ON Q.houseNO = H.houseNO where memNO = ?  ORDER BY qTime DESC";
	@Override
	public void insert(QandAVO qandaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, qandaVO.getMemNO());
			pstmt.setInt(2, qandaVO.getEmpNO());
			pstmt.setInt(3, qandaVO.getHouseVO().getHouseNO());
			pstmt.setDate(4, qandaVO.getqTime());
			pstmt.setDate(5, qandaVO.getaTime());
			pstmt.setByte(6, qandaVO.getQaType());
			pstmt.setString(7, qandaVO.getqDetail());
			pstmt.setString(8, qandaVO.getaDetail());

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
	public void update(QandAVO qandaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, qandaVO.getMemNO());
			pstmt.setInt(2, qandaVO.getEmpNO());
			pstmt.setInt(3, qandaVO.getHouseVO().getHouseNO());
			pstmt.setDate(4, qandaVO.getqTime());
			pstmt.setDate(5, qandaVO.getaTime());
			pstmt.setByte(6, qandaVO.getQaType());
			pstmt.setString(7, qandaVO.getqDetail());
			pstmt.setString(8, qandaVO.getaDetail());
			pstmt.setInt(9, qandaVO.getQaNO());

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
	public void delete(Integer qaNO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, qaNO);

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
	public QandAVO findByPrimaryKey(Integer qaNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QandAVO qandaVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, qaNO);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 確定有資料才開始new QandAVO物件
				// qandaVO = Domain objects
				qandaVO = new QandAVO();
				qandaVO.setQaNO(rs.getInt("qaNO"));
				qandaVO.setMemNO(rs.getInt("memNO"));
				qandaVO.setEmpNO(rs.getInt("empNO"));
				qandaVO.getHouseVO().setHouseNO(rs.getInt("houseNO"));
				qandaVO.setqTime(rs.getDate("qTime"));
				qandaVO.setaTime(rs.getDate("aTime"));
				qandaVO.setQaType(rs.getByte("qaType"));
				qandaVO.setqDetail(rs.getString("qDetail"));
				qandaVO.setaDetail(rs.getString("aDetail"));
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
		return qandaVO;
	}

	@Override
	public Set<QandAVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QandAVO qandaVO = null;
		Set<QandAVO> set = new LinkedHashSet<QandAVO>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 確定有資料才開始new QandAVO物件
				// qandaVO = Domain objects
				qandaVO = new QandAVO();
				qandaVO.setQaNO(rs.getInt("qaNO"));
				qandaVO.setMemNO(rs.getInt("memNO"));
				qandaVO.setEmpNO(rs.getInt("empNO"));
				qandaVO.getHouseVO().setHouseNO(rs.getInt("houseNO"));
				qandaVO.setqTime(rs.getDate("qTime"));
				qandaVO.setaTime(rs.getDate("aTime"));
				qandaVO.setQaType(rs.getByte("qaType"));
				qandaVO.setqDetail(rs.getString("qDetail"));
				qandaVO.setaDetail(rs.getString("aDetail"));
				set.add(qandaVO); // Store the row in the list
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
	public ArrayList<QandAVO> getAllByMemberNO(Integer memNO){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QandAVO> array = new ArrayList<QandAVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_MEMBER_NO);
			pstmt.setInt(1, memNO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 確定有資料才開始new QandAVO物件
				// qandaVO = Domain objects
				QandAVO qandaVO = new QandAVO();
				qandaVO.setQaNO(rs.getInt("qaNO"));
				qandaVO.setMemNO(rs.getInt("memNO"));
				qandaVO.setEmpNO(rs.getInt("empNO"));
				qandaVO.getHouseVO().setHouseNO(rs.getInt("houseNO"));
				qandaVO.getHouseVO().setHouseTitle(rs.getString("houseTitle"));
				qandaVO.setqTime(rs.getDate("qTime"));
				qandaVO.setaTime(rs.getDate("aTime"));
				qandaVO.setQaType(rs.getByte("qaType"));
				qandaVO.setqDetail(rs.getString("qDetail"));
				qandaVO.setaDetail(rs.getString("aDetail"));
				array.add(qandaVO); // Store the row in the list
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
		return array;
	}

//	public static void main(String[] args) {
//		QandAJDBCDAO dao = new QandAJDBCDAO();
//
//		// 新增
//		QandAVO qandaVO1 = new QandAVO();
//		qandaVO1.setMemNO(40001);
//		qandaVO1.setEmpNO(30001);
//		qandaVO1.setHouseNO(20001);
//		qandaVO1.setqTime(java.sql.Date.valueOf("2015-10-10"));
//		qandaVO1.setaTime(java.sql.Date.valueOf("2015-10-11"));
//		qandaVO1.setQaType((byte) 1);
//		qandaVO1.setqDetail("問題測試123");
//		qandaVO1.setaDetail("回答測試456");
//		dao.insert(qandaVO1);
//
//		// 修改初始資料第一筆
//		QandAVO qandaVO2 = new QandAVO();
//		qandaVO2.setQaNO(60000001);
//		qandaVO2.setMemNO(40001);
//		qandaVO2.setEmpNO(30001);
//		qandaVO2.setHouseNO(20001);
//		qandaVO2.setqTime(java.sql.Date.valueOf("2015-10-10"));
//		qandaVO2.setaTime(java.sql.Date.valueOf("2015-10-11"));
//		qandaVO2.setQaType((byte) 1);
//		qandaVO2.setqDetail("問題測試777");
//		qandaVO2.setaDetail("回答測試888");
//		dao.update(qandaVO2);
//
//		// 查詢初始資料第二筆
//		QandAVO qandaVO3 = dao.findByPrimaryKey(60000002);
//		System.out.print(qandaVO3.getQaNO() + ",");
//		System.out.print(qandaVO3.getMemNO() + ",");
//		System.out.print(qandaVO3.getEmpNO() + ",");
//		System.out.print(qandaVO3.getHouseNO() + ",");
//		System.out.print(qandaVO3.getqTime() + ",");
//		System.out.print(qandaVO3.getaTime() + ",");
//		System.out.print(qandaVO3.getQaType() + ",");
//		System.out.print(qandaVO3.getqDetail() + ",");
//		System.out.println(qandaVO3.getaDetail());
//		System.out.println("---------------------");
//
//		// 查詢全部
//		Set<QandAVO> set = dao.getAll();
//		for (QandAVO qandaVO : set) {
//			System.out.print(qandaVO.getQaNO() + ",");
//			System.out.print(qandaVO.getMemNO() + ",");
//			System.out.print(qandaVO.getEmpNO() + ",");
//			System.out.print(qandaVO.getHouseNO() + ",");
//			System.out.print(qandaVO.getqTime() + ",");
//			System.out.print(qandaVO.getaTime() + ",");
//			System.out.print(qandaVO.getQaType() + ",");
//			System.out.print(qandaVO.getqDetail() + ",");
//			System.out.println(qandaVO.getaDetail());
//			System.out.println();
//		}
//
//		// 刪除初始資料一筆
//		dao.delete(60000002);
//
//		System.out.println("Done");
//	}
}
