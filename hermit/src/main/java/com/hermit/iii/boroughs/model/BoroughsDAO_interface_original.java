package com.hermit.iii.boroughs.model;

import java.util.List;

public interface BoroughsDAO_interface_original {
	 public void insert(BoroughsVO_original boroughsVO);
     public void update(BoroughsVO_original boroughsVO);
     public void delete(Integer boroughNO);
     public BoroughsVO_original findByPrimaryKey(Integer boroughNO);
     public List<BoroughsVO_original> getAll();
     public String getAllWhereCity(Integer cityNO);
     //子傑加
     public List<BoroughsVO_original> getAll_cityNO(Integer cityNO);
     
}
