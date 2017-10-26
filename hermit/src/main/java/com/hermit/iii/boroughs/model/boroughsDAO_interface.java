package com.hermit.iii.boroughs.model;

import java.util.List;


public interface boroughsDAO_interface {
	 public void insert(boroughsVO boroughsVO);
     public void update(boroughsVO boroughsVO);
     public void delete(Integer boroughNO);
     public boroughsVO findByPrimaryKey(Integer boroughNO);
     public List<boroughsVO> getAll();
     public String getAllForJson();
}
