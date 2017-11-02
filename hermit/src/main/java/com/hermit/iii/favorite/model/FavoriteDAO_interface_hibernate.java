package com.hermit.iii.favorite.model;

import java.util.*;

public interface FavoriteDAO_interface_hibernate {
	public void insert(FavoriteVO favoriteVO);

	public void update(FavoriteVO favoriteVO);

	public void delete(Integer favNO);

	public FavoriteVO findByPrimaryKey(Integer favNO);

	public Set<FavoriteVO> getAll();

	/**** 自訂指令 ****/
	// AJAX 會員編號查詢
	public Set<FavoriteVO> find_MemNO_AJAX(Integer memNO);
	// AJAX 會員房屋編號查詢
	public Integer check_MemHouseNO_AJAX(Integer memNO, Integer houseNO);
}