package com.hermit.iii.houseform.model;

import java.util.List;

public interface HouseFormDAO_interface_hibernate {
	
	 public void insert(HouseFormVO_hibernate HouseFormVO_hibernate);
	 
     public void update(HouseFormVO_hibernate HouseFormVO_hibernate);
     
     public void delete(Integer formNO);
     
     public HouseFormVO_hibernate findByPrimaryKey(Integer formNO);
     
     public List<HouseFormVO_hibernate> getAll();
     
}
