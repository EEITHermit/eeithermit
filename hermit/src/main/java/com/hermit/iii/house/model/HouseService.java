package com.hermit.iii.house.model;

import java.util.ArrayList;
import java.util.List;

public class HouseService {
	private HouseDAO_interface dao ;
	
	public HouseService(){
		dao = new HouseDAO_JNDI();
	}
	
	public HouseVO_orignal insertHouse(String houseTitle,Integer cityNO,Integer boroughNO,Integer highestFloor,Integer nowFloor,String houseStatus,Integer houseRent,Integer houseCharge,String waterRate,String powerRate,String houseVideo,Integer typeNO,Integer formNO,String houseAddr,Double houseSize){
		HouseVO_orignal vo = new HouseVO_orignal();
		vo.setHouseTitle(houseTitle);
		vo.setCityNO(cityNO);
		vo.setBoroughNO(boroughNO);
		vo.setHighestFloor(highestFloor);
		vo.setNowFloor(nowFloor);
		vo.setHouseStatus(houseStatus);
		vo.setHouseRent(houseRent);
		vo.setHouseCharge(houseCharge);
		vo.setWaterRate(waterRate);
		vo.setPowerRate(powerRate);
		vo.setHouseVideo(houseVideo);
		vo.setTypeNO(typeNO);
		vo.setFormNO(formNO);
		vo.setHouseAddr(houseAddr);
		vo.setHouseSize(houseSize);
		dao.insert(vo);
		return vo;
	}
	
	public HouseVO_orignal updateHouse(Integer houseNO,String houseTitle,Integer cityNO,Integer boroughNO,Integer highestFloor,Integer nowFloor,String houseStatus,Integer houseRent,Integer houseCharge,String waterRate,String powerRate,String houseVideo,Integer typeNO,Integer formNO,String houseAddr,Double houseSize){
		HouseVO_orignal vo = new HouseVO_orignal();
		vo.setHouseNO(houseNO);
		vo.setHouseTitle(houseTitle);
		vo.setCityNO(cityNO);
		vo.setBoroughNO(boroughNO);
		vo.setHighestFloor(highestFloor);
		vo.setNowFloor(nowFloor);
		vo.setHouseStatus(houseStatus);
		vo.setHouseRent(houseRent);
		vo.setHouseCharge(houseCharge);
		vo.setWaterRate(waterRate);
		vo.setPowerRate(powerRate);
		vo.setHouseVideo(houseVideo);
		vo.setTypeNO(typeNO);
		vo.setFormNO(formNO);
		vo.setHouseAddr(houseAddr);
		vo.setHouseSize(houseSize);
		dao.update(vo);
		return vo;
	}
	
	public void dateHouse(Integer houseNO){
		dao.delete(houseNO);
	}
	
	public HouseVO_orignal getOneHouse(Integer houseNO){
		return dao.findByPrimaryKey(houseNO);
	}
	public List<HouseVO_orignal> getAllHouse(){
		return dao.getAll();
	}
	//漢勳加，autocomplete功能用
    public ArrayList<HouseVO_orignal> autoCompleteH(String address){
    	return dao.autoCompleteH(address);
    };
    //漢勳加，搜尋所負責鄉鎮區用
    public Integer findAreaNoByHouseNo(Integer houseNo){
    	return dao.findAreaNoByHouseNo(houseNo);
    }; 
    //子傑加，House加入子表，查全部
    public List<HouseVO_orignal> getAllHouse_FK(){
    	return dao.GET_ALL_JOIN_FK();
    }
    //子傑加，House加入子表，查單一
    public HouseVO_orignal GET_ONE_HOUSE_FK(Integer houseNO){
    	return dao.GET_ONE_HOUSE_FK(houseNO);
    }
    public String advencedSearch(String searchStr){
    	return dao.advencedSearch(searchStr);
    }
}
