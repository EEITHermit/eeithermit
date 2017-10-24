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

			pstmt.setInt(1, favoriteVO.getHouseNO());
			pstmt.setInt(2, favoriteVO.getMemNO());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer memNO, Integer houseNO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_STMT);) {

			pstmt.setInt(1, memNO);
			pstmt.setInt(2, houseNO);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public FavoriteVO findByKey(Integer memNO, Integer houseNO) {
		ResultSet rs = null;
		FavoriteVO favoriteVO = null;

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ONE_STMT);) {

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
				favoriteVO.setMemNO(rs.getInt("memNO"));
				favoriteVO.setHouseNO(rs.getInt("houseNO"));
				set.add(favoriteVO); // Store the row in the list
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}
}