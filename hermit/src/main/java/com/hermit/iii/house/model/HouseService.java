package com.hermit.iii.house.model;

import java.util.ArrayList;
import java.util.List;

public class HouseService {

	private HouseDAO_interface dao;
	
	public HouseService(){
		dao=new HouseDAO_JDBC();
	}
	public HouseVO insert(String houseTitle,Integer cityNO,Integer boroughNO,Integer highestFloor,Integer nowFloor,String houseStatus,Integer houseRent,Integer houseCharge,String waterRate,String powerRate,String houseVideo,Integer typeNO,Integer formNO,String houseAddr,Double houseSize){
		HouseVO houseVO=new HouseVO();
		houseVO.setHouseTitle(houseTitle);
		houseVO.setCityNO(cityNO);
		houseVO.setBoroughNO(boroughNO);
		houseVO.setHighestFloor(highestFloor);
		houseVO.setNowFloor(nowFloor);
		houseVO.setHouseStatus(houseStatus);
		houseVO.setHouseRent(houseRent);
		houseVO.setHouseCharge(houseCharge);
		houseVO.setWaterRate(waterRate);
		houseVO.setPowerRate(powerRate);
		houseVO.setHouseVideo(houseVideo);
		houseVO.setTypeNO(typeNO);
		houseVO.setFormNO(formNO);
		houseVO.setHouseAddr(houseAddr);
		houseVO.setHouseSize(houseSize);
		dao.insert(houseVO);
		return houseVO;
	}
	
	public HouseVO update(String houseTitle,Integer cityNO,Integer boroughNO,Integer highestFloor,Integer nowFloor,String houseStatus,Integer houseRent,Integer houseCharge,String waterRate,String powerRate,String houseVideo,Integer typeNO,Integer formNO,String houseAddr,Double houseSize){
		HouseVO houseVO =new HouseVO();
		houseVO.setHouseTitle(houseTitle);
		houseVO.setCityNO(cityNO);
		houseVO.setBoroughNO(boroughNO);
		houseVO.setHighestFloor(highestFloor);
		houseVO.setNowFloor(nowFloor);
		houseVO.setHouseStatus(houseStatus);
		houseVO.setHouseRent(houseRent);
		houseVO.setHouseCharge(houseCharge);
		houseVO.setWaterRate(waterRate);
		houseVO.setPowerRate(powerRate);
		houseVO.setHouseVideo(houseVideo);
		houseVO.setTypeNO(typeNO);
		houseVO.setFormNO(formNO);
		houseVO.setHouseAddr(houseAddr);
		houseVO.setHouseSize(houseSize);
		dao.update(houseVO);
		return houseVO;
	}
	
	public HouseVO findByPrimaryKey(Integer houseNO){
		return dao.findByPrimaryKey(houseNO);
	}
	
	
}
