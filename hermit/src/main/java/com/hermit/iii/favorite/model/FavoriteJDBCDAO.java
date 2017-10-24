package com.hermit.iii.favorite.model;

import java.sql.*;
import java.util.*;

public class FavoriteJDBCDAO implements FavoriteDAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Hermit";
	String userid = "sa";
	String passwd = "P@ssw0rd";

	// (memNO,houseNO)
	private static final String INSERT_STMT = "INSERT INTO Favorite VALUES (?, ?)";
	// (memNO當條件)
	private static final String UPDATE_STMT = "UPDATE Favorite SET houseNO=? WHERE memNO = ?";
	// (memNO,houseNO當條件)
	private static final String DELETE_STMT = "DELETE FROM Favorite WHERE memNO = ?  AND houseNO = ?";
	// 全選，避免用＊(memNO,houseNO當條件)
	private static final String GET_ONE_STMT = "SELECT memNO,houseNO FROM Favorite WHERE memNO = ? AND houseNO = ?";
	// 全選，避免用＊(免條件全打包，memNO當排序使得每次結果顯示方式統一)
	private static final String GET_ALL_STMT = "SELECT memNO,houseNO FROM Favorite ORDER BY memNO";

	@Override
	public void insert(FavoriteVO favoriteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, favoriteVO.getMemNO());
			pstmt.setInt(2, favoriteVO.getHouseNO());

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
	public void update(FavoriteVO favoriteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, favoriteVO.getHouseNO());
			pstmt.setInt(2, favoriteVO.getMemNO());

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
	public void delete(Integer memNO, Integer houseNO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, memNO);
			pstmt.setInt(2, houseNO);

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
	public FavoriteVO findByKey(Integer memNO, Integer houseNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FavoriteVO favoriteVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memNO);
			pstmt.setInt(2, houseNO);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 確定有資料才開始new FavoriteVO物件
				// favoriteVO = Domain objects
				favoriteVO = new FavoriteVO();
				favoriteVO.setMemNO(rs.getInt("memNO"));
				favoriteVO.setHouseNO(rs.getInt("houseNO"));
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
		return favoriteVO;
	}

	@Override
	public Set<FavoriteVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FavoriteVO favoriteVO = null;
		Set<FavoriteVO> set = new LinkedHashSet<FavoriteVO>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 確定有資料才開始new FavoriteVO物件
				// favoriteVO = Domain objects
				favoriteVO = new FavoriteVO();
				favoriteVO.setMemNO(rs.getInt("memNO"));
				favoriteVO.setHouseNO(rs.getInt("houseNO"));
				set.add(favoriteVO); // Store the row in the list
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
		FavoriteJDBCDAO dao = new FavoriteJDBCDAO();

		// 新增
		FavoriteVO favoriteVO1 = new FavoriteVO();
		favoriteVO1.setMemNO(40001);
		favoriteVO1.setHouseNO(20001);
		dao.insert(favoriteVO1);

		// 修改初始資料第一筆
		FavoriteVO favoriteVO2 = new FavoriteVO();
		favoriteVO2.setMemNO(40001);
		favoriteVO2.setHouseNO(20002);
		dao.update(favoriteVO2);

		// 查詢初始資料第一筆
		FavoriteVO favoriteVO3 = dao.findByKey(40001, 20002);
		System.out.print(favoriteVO3.getMemNO() + ",");
		System.out.println(favoriteVO3.getHouseNO());
		System.out.println("---------------------");

		// 查詢全部
		Set<FavoriteVO> set = dao.getAll();
		for (FavoriteVO favorite : set) {
			System.out.print(favorite.getMemNO() + ",");
			System.out.println(favorite.getHouseNO());
		}

		// 刪除初始資料一筆
		dao.delete(40001, 20002);

		System.out.println("Done");
	}
}