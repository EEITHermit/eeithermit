package com.hermit.iii.city.model;

import java.util.List;
public interface CityDAO_interface {
	 public void insert(CityVO cityVO);
     public void update(CityVO cityVO);
     public void delete(Integer cityNO);
     public CityVO findByPrimaryKey(Integer cityNO);
     public List<CityVO> getAll();
}
