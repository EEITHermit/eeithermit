package com.hermit.iii.city.model;

import java.util.List;
public interface CityDAO_interface_hibernate {
	 public void insert(CityVO_hibernate cityVO_hibernate);
     public void update(CityVO_hibernate cityVO_hibernate);
     public void delete(Integer cityNO);
     public CityVO findByPrimaryKey(Integer cityNO);
     public List<CityVO> getAll();
     public String getAllForJson();
     
}
