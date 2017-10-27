package com.hermit.iii.favorite.model;

import java.util.*;

public class FavoriteService {
	private FavoriteDAO_interface dao;

	public FavoriteService() {
		dao = new FavoriteJDBCDAO();
	}

	// 新增service
	public FavoriteVO addFavorite(Integer memNO, Integer houseNO) {
		FavoriteVO favoriteVO = new FavoriteVO();

		favoriteVO.setMemNO(memNO);
		favoriteVO.setHouseNO(houseNO);

		dao.insert(favoriteVO);

		return favoriteVO;
	}

	// 新增service Struts 2可 用(預留)
	public void addFavorite(FavoriteVO favoriteVO) {
		dao.insert(favoriteVO);
	}

	// 修改service
	public FavoriteVO updateFavorite(Integer favNO, Integer memNO, Integer houseNO, java.sql.Date favDate) {
		FavoriteVO favoriteVO = new FavoriteVO();

		favoriteVO.setFavNO(favNO);
		favoriteVO.setMemNO(memNO);
		favoriteVO.setHouseNO(houseNO);
		favoriteVO.setFavDate(favDate);

		dao.update(favoriteVO);

		return favoriteVO;
	}

	// 修改service Struts 2可 用(預留)
	public void updateFavorite(FavoriteVO favoriteVO) {
		dao.update(favoriteVO);
	}

	// 刪除service
	public void deleteFavorite(Integer favNO) {
		dao.delete(favNO);
	}

	// 查詢一筆service
	public FavoriteVO getOneFavorite(Integer favNO) {
		return dao.findByPrimaryKey(favNO);
	}

	// 查詢全部service
	public Set<FavoriteVO> getAll() {
		return dao.getAll();
	}
}