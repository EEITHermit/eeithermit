package com.hermit.iii.boroughs.model;

import java.util.List;

public interface BoroughsDAO_interface {
	 public void insert(BoroughsVO boroughsVO);
     public void update(BoroughsVO boroughsVO);
     public void delete(Integer boroughNO);
     public BoroughsVO findByPrimaryKey(Integer boroughNO);
     public List<BoroughsVO> getAll();
     public String getAllWhereCity(Integer cityNO);
     
}
