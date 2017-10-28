package com.hermit.iii.favorite.model;

import java.util.*;

public interface FavoriteDAO_interface_hibernate {
	public void insert(FavoriteVO favoriteVO);

	public void update(FavoriteVO favoriteVO);

	public void delete(Integer favNO);

	public FavoriteVO findByPrimaryKey(Integer favNO);

	public Set<FavoriteVO> getAll();
}
