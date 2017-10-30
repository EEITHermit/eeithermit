package com.hermit.iii.houseform.model;

import java.util.List;

public interface HouseFormDAO_interface_hibernate {
	
	 public void insert(HouseFormVO HouseFormVO_hibernate);
	 
     public void update(HouseFormVO HouseFormVO_hibernate);
     
     public void delete(Integer formNO);
     
     public HouseFormVO findByPrimaryKey(Integer formNO);
     
     public List<HouseFormVO> getAll();
     
}
