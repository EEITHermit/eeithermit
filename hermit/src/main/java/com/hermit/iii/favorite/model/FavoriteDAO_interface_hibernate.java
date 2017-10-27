package com.hermit.iii.favorite.model;

import java.util.*;

public interface FavoriteDAO_interface_hibernate {
	public void insert(FavoriteVO_hibernate favoriteVO_hibernate);

	public void update(FavoriteVO_hibernate favoriteVO_hibernate);

	public void delete(Integer favNO);

	public FavoriteVO_hibernate findByPrimaryKey(Integer favNO);

	public Set<FavoriteVO_hibernate> getAll();
}
