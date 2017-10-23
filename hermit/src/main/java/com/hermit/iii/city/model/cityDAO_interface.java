package com.hermit.iii.city.model;

import java.util.List;
public interface cityDAO_interface {
	 public void insert(cityVO cityVO);
     public void update(cityVO cityVO);
     public void delete(Integer cityNO);
     public cityVO findByPrimaryKey(Integer cityNO);
     public List<cityVO> getAll();
     public String getAllForJson();
}
