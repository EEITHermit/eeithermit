package com.hermit.iii.housetype.model;

import java.util.List;


public interface HouseTypeDAO_interface_hibernate {
	
	 public void insert(HouseTypeVO_hibernate HouseTypeVO_hibernate);
	 
     public void update(HouseTypeVO_hibernate HouseTypeVO_hibernate);
     
     public void delete(Integer typeNO);
     
     public HouseTypeVO_hibernate findByPrimaryKey(Integer typeNO);
     
     public List<HouseTypeVO_hibernate> getAll();
}
