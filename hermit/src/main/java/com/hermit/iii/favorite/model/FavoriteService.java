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
	public FavoriteVO updateFavorite(Integer memNO, Integer houseNO) {
		FavoriteVO favoriteVO = new FavoriteVO();

		favoriteVO.setMemNO(memNO);
		favoriteVO.setHouseNO(houseNO);

		dao.update(favoriteVO);

		return favoriteVO;
	}

	// 修改service Struts 2可 用(預留)
	public void updateFavorite(FavoriteVO favoriteVO) {
		dao.update(favoriteVO);
	}

	// 刪除service
	public void deleteFavorite(Integer memNO, Integer houseNO) {
		dao.delete(memNO, houseNO);
	}

	// 查詢一筆service
	public FavoriteVO getOneFavorite(Integer memNO, Integer houseNO) {
		return dao.findByKey(memNO, houseNO);
	}

	// 查詢全部service
	public Set<FavoriteVO> getAll() {
		return dao.getAll();
	}
}