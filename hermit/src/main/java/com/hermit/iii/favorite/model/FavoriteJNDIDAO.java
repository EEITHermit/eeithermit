package com.hermit.iii.favorite.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

public class FavoriteJNDIDAO implements FavoriteDAO_interface {
	DataSource ds = null;

	public FavoriteJNDIDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/JNDI");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);) {

			pstmt.setInt(1, favoriteVO.getMemNO());
			pstmt.setInt(2, favoriteVO.getHouseNO());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(FavoriteVO favoriteVO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_STMT);) {

			pstmt.setInt(1, favoriteVO.getMemNO());
			pstmt.setInt(2, favoriteVO.getHouseNO());
			pstmt.setDate(3, favoriteVO.getFavDate());
			pstmt.setInt(4, favoriteVO.getFavNO());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer favNO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_STMT);) {

			pstmt.setInt(1, favNO);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public FavoriteVO findByPrimaryKey(Integer favNO) {
		ResultSet rs = null;
		FavoriteVO favoriteVO = null;

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ONE_STMT);) {

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

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return favoriteVO;
	}

	@Override
	public Set<FavoriteVO> getAll() {
		ResultSet rs = null;
		FavoriteVO favoriteVO = null;
		Set<FavoriteVO> set = new LinkedHashSet<FavoriteVO>();

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ALL_STMT);) {

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

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}
}