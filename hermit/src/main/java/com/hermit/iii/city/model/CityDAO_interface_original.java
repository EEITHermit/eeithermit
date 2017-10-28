package com.hermit.iii.city.model;

import java.util.List;
public interface CityDAO_interface_original {
	 public void insert(CityVO_original cityVO);
     public void update(CityVO_original cityVO);
     public void delete(Integer cityNO);
     public CityVO_original findByPrimaryKey(Integer cityNO);
     public List<CityVO_original> getAll();
     public String getAllForJson();
     
}
