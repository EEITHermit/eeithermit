package com.hermit.iii.house.model;

import java.util.ArrayList;
import java.util.List;


public interface HouseDAO_interface {
	 public void insert(HouseVO_orignal HouseVO_orignal);
     public void update(HouseVO_orignal HouseVO_orignal);
     public void delete(Integer houseNO);
     public HouseVO_orignal findByPrimaryKey(Integer houseNO);
     public List<HouseVO_orignal> getAll();
     //漢勳加，autocomplete功能用
     public ArrayList<HouseVO_orignal> autoCompleteH(String address);
     //漢勳加，搜尋所負責鄉鎮區用
     public Integer findAreaNoByHouseNo(Integer houseNo);
     
     //子傑加，House表格JOIN houseType與houseForm
     public List<HouseVO_orignal> GET_ALL_JOIN_FK();
     public HouseVO_orignal GET_ONE_HOUSE_FK(Integer houseNO);
     public String advencedSearch(String searchStr);
     
}
