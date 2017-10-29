package com.hermit.iii.housetype.model;

import java.util.List;


public interface HouseTypeDAO_interface {
	 public void insert(HouseTypeVO_original houseTypeVO);
     public void update(HouseTypeVO_original houseTypeVO);
     public void delete(Integer typeNO);
     public HouseTypeVO_original findByPrimaryKey(Integer typeNO);
     public List<HouseTypeVO_original> getAll();
}
