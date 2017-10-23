package com.hermit.iii.house.model;

import java.util.List;

public class HouseService {
	private HouseDAO_interface dao ;
	
	public HouseService(){
		dao = new HouseDAO_JNDI();
	}
	
	public HouseVO insertHouse(String houseTitle,Integer cityNO,Integer boroughNO,Integer highestFloor,Integer nowFloor,String houseStatus,Integer houseRent,Integer houseCharge,String waterRate,String powerRate,String houseVideo,Integer typeNO,Integer formNO,String houseAddr,Double houseSize){
		HouseVO vo = new HouseVO();
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
	
	public HouseVO updateHouse(Integer houseNO,String houseTitle,Integer cityNO,Integer boroughNO,Integer highestFloor,Integer nowFloor,String houseStatus,Integer houseRent,Integer houseCharge,String waterRate,String powerRate,String houseVideo,Integer typeNO,Integer formNO,String houseAddr,Double houseSize){
		HouseVO vo = new HouseVO();
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
	
	public HouseVO getOneHouse(Integer houseNO){
		return dao.findByPrimaryKey(houseNO);
	}
	public List<HouseVO> getAllHouse(){
		return dao.getAll();
	}

}
