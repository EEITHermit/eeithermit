package com.hermit.iii.favorite.model;

import java.sql.*;
import java.util.*;

public class FavoriteJDBCDAO implements FavoriteDAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Hermit";
	String userid = "sa";
	String passwd = "P@ssw0rd";

	// favNO在資料庫為自動流水號免新增，順序同資料庫表格(與VO/Bean)設定
	// (memNO,houseNO,favDate)
	private static final String INSERT_STMT = "INSERT INTO Favorite VALUES (?, ?, getDate())";
	// 上順序，全改通吃法(PK流水號當條件)
	private static final String UPDATE_STMT = "UPDATE Favorite SET memNO=?, houseNO=?, favDate=? WHERE favNO = ?";
	private static final String DELETE_STMT = "DELETE FROM Favorite WHERE favNO = ?";
	// (含PK流水號)全選，避免用＊(PK流水號當條件)
	private static final String GET_ONE_STMT = "SELECT favNO,memNO,houseNO,favDate FROM Favorite WHERE favNO = ?";
	// (含PK流水號)全選，避免用＊(免PK流水號當條件全打包，PK流水號當排序使得每次結果顯示方式統一)
	private static final String GET_ALL_STMT = "SELECT favNO,memNO,houseNO,favDate FROM Favorite ORDER BY favNO";

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

			pstmt.setInt(1, favoriteVO.getMemNO());
			pstmt.setInt(2, favoriteVO.getHouseNO());
			pstmt.setDate(3, favoriteVO.getFavDate());
			pstmt.setInt(4, favoriteVO.getFavNO());
			
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
	public void delete(Integer favNO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, favNO);

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
	public FavoriteVO findByPrimaryKey(Integer favNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FavoriteVO favoriteVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, favNO);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 確定有資料才開始new FavoriteVO物件
				// favoriteVO = Domain objects
				favoriteVO = new FavoriteVO();
				favoriteVO.setFavNO(rs.getInt("favNO"));
				favoriteVO.setMemNO(rs.getInt("memNO"));
				favoriteVO.setHouseNO(rs.getInt("houseNO"));
				favoriteVO.setFavDate(rs.getDate("favDate"));
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
				favoriteVO.setFavNO(rs.getInt("favNO"));
				favoriteVO.setMemNO(rs.getInt("memNO"));
				favoriteVO.setHouseNO(rs.getInt("houseNO"));
				favoriteVO.setFavDate(rs.getDate("favDate"));
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
		favoriteVO1.setFavDate(java.sql.Date.valueOf("2017-10-08"));
		dao.insert(favoriteVO1);

		// 修改初始資料第一筆
		FavoriteVO favoriteVO2 = new FavoriteVO();
		favoriteVO2.setFavNO(40001);
		favoriteVO2.setMemNO(40001);
		favoriteVO2.setHouseNO(20001);
		favoriteVO2.setFavDate(java.sql.Date.valueOf("2015-02-02"));
		dao.update(favoriteVO2);

		// 查詢初始資料第一筆
		FavoriteVO favoriteVO3 = dao.findByPrimaryKey(40001);
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
		dao.delete(40001);

		System.out.println("Done");
	}
}