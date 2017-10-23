package com.hermit.iii.house.model;

import java.util.ArrayList;
import java.util.List;


public interface HouseDAO_interface {
	 public void insert(HouseVO houseVO);
     public void update(HouseVO houseVO);
     public void delete(Integer houseNO);
     public HouseVO findByPrimaryKey(Integer houseNO);
     public List<HouseVO> getAll();
     //漢勳加，autocomplete功能用
     public ArrayList<HouseVO> autoCompleteH(String address);
     //漢勳加，搜尋所負責鄉鎮區用
     public Integer findAreaNoByHouseNo(Integer houseNo);
}
