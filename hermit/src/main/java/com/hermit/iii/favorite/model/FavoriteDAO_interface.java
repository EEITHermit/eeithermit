package com.hermit.iii.favorite.model;

import java.util.*;

public interface FavoriteDAO_interface {
	public void insert(FavoriteVO favoriteVO);

	public void update(FavoriteVO favoriteVO);

	public void delete(Integer memNO, Integer houseNO);

	public FavoriteVO findByKey(Integer memNO, Integer houseNO);

	public Set<FavoriteVO> getAll();
}