package com.hermit.iii.houseform.model;

import java.util.List;

public interface HouseFormDAO_interface {
	 public void insert(HouseFormVO_original houseFormVO);
     public void update(HouseFormVO_original houseFormVO);
     public void delete(Integer formNO);
     public HouseFormVO_original findByPrimaryKey(Integer formNO);
     public List<HouseFormVO_original> getAll();
}
