package com.hermit.iii.housetype.model;

import java.util.List;


public interface HouseTypeDAO_interface_hibernate {
	
	 public void insert(HouseTypeVO HouseTypeVO);
	 
     public void update(HouseTypeVO HouseTypeVO);
     
     public void delete(Integer typeNO);
     
     public HouseTypeVO findByPrimaryKey(Integer typeNO);
     
     public List<HouseTypeVO> getAll();
}
