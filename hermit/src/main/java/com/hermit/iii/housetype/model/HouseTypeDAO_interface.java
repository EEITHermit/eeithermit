package com.hermit.iii.housetype.model;

import java.util.List;


public interface HouseTypeDAO_interface {
	 public void insert(HouseTypeVO houseTypeVO);
     public void update(HouseTypeVO houseTypeVO);
     public void delete(Integer typeNO);
     public HouseTypeVO findByPrimaryKey(Integer typeNO);
     public List<HouseTypeVO> getAll();
}
