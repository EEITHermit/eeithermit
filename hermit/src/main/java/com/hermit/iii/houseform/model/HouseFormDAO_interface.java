package com.hermit.iii.houseform.model;

import java.util.List;

public interface HouseFormDAO_interface {
	 public void insert(HouseFormVO houseFormVO);
     public void update(HouseFormVO houseFormVO);
     public void delete(Integer formNO);
     public HouseFormVO findByPrimaryKey(Integer formNO);
     public List<HouseFormVO> getAll();
}
