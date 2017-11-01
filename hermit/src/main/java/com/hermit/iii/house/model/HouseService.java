package com.hermit.iii.house.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.hermit.iii.boroughs.model.BoroughsVO;
import com.hermit.iii.city.model.CityVO;
import com.hermit.iii.houseform.model.HouseFormVO;
import com.hermit.iii.housepicture.model.HousePictureVO;
import com.hermit.iii.housetype.model.HouseTypeVO;

public class HouseService {
	private HouseDAO_interface_hibernate dao ;
	
	public HouseService(){
		dao = new HouseDAO_hibernate();
	}
	
	public HouseVO insertHouse(String houseTitle,Integer cityNO,Integer boroughNO,String previewPic,Integer highestFloor,Integer nowFloor,String houseStatus,Integer houseRent,Integer houseCharge,String waterRate,String powerRate,String houseVideo,Integer typeNO,Integer formNO,String houseAddr,Double houseSize){
		HouseVO vo = new HouseVO();
		vo.setHouseTitle(houseTitle);
		
		CityVO cityVO=new CityVO();
		cityVO.setCityNO(cityNO);
		vo.setCityVO(cityVO);
		BoroughsVO boroughsVO=new BoroughsVO();
		boroughsVO.setBoroughNO(boroughNO);
		vo.setBoroughsVO(boroughsVO);
		vo.setPreviewPic(previewPic);
		vo.setHighestFloor(highestFloor);
		vo.setNowFloor(nowFloor);
		vo.setHouseStatus(houseStatus);
		vo.setHouseRent(houseRent);
		vo.setHouseCharge(houseCharge);
		vo.setWaterRate(waterRate);
		vo.setPowerRate(powerRate);
		vo.setHouseVideo(houseVideo);
		HouseTypeVO houseTypeVO=new HouseTypeVO();
		houseTypeVO.setTypeNO(typeNO);
		vo.setHouseTypeVO(houseTypeVO);
		HouseFormVO houseFormVO=new HouseFormVO();
		houseFormVO.setFormNO(formNO);
		vo.setHouseFormVO(houseFormVO);
		vo.setHouseAddr(houseAddr);
		vo.setHouseSize(houseSize);
		dao.insert(vo);
		return vo;
	}
	
	public HouseVO updateHouse(Integer houseNO,String houseTitle,Integer cityNO,Integer boroughNO,String previewPic,Integer highestFloor,Integer nowFloor,String houseStatus,Integer houseRent,Integer houseCharge,String waterRate,String powerRate,String houseVideo,Integer typeNO,Integer formNO,String houseAddr,Double houseSize,String houseInfo){
		HouseVO vo = new HouseVO();
		vo.setHouseNO(houseNO);
		vo.setHouseTitle(houseTitle);
		CityVO cityVO=new CityVO();
		cityVO.setCityNO(cityNO);
		vo.setCityVO(cityVO);
		BoroughsVO boroughsVO=new BoroughsVO();
		boroughsVO.setBoroughNO(boroughNO);
		vo.setBoroughsVO(boroughsVO);
		vo.setPreviewPic(previewPic);
		vo.setHighestFloor(highestFloor);
		vo.setNowFloor(nowFloor);
		vo.setHouseStatus(houseStatus);
		vo.setHouseRent(houseRent);
		vo.setHouseCharge(houseCharge);
		vo.setWaterRate(waterRate);
		vo.setPowerRate(powerRate);
		vo.setHouseVideo(houseVideo);
		HouseTypeVO houseTypeVO=new HouseTypeVO();
		houseTypeVO.setTypeNO(typeNO);
		vo.setHouseTypeVO(houseTypeVO);
		HouseFormVO houseFormVO=new HouseFormVO();
		houseFormVO.setFormNO(formNO);
		vo.setHouseFormVO(houseFormVO);
		vo.setHouseAddr(houseAddr);
		vo.setHouseSize(houseSize);
		vo.setHouseInfo(houseInfo);
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
	//漢勳加，autocomplete功能用
    public ArrayList<HouseVO> autoCompleteH(String address){
    	return dao.autoCompleteH(address);
    };
    //漢勳加，搜尋所負責鄉鎮區用
    public Integer findAreaNoByHouseNo(Integer houseNo){
    	return dao.findAreaNoByHouseNo(houseNo);
    }; 
    //子傑加，House加入子表，查全部
    public List<HouseVO> getAllHouse_FK(){
    	return dao.GET_ALL_JOIN_FK();
    }
    //子傑加，House加入子表，查單一
    public HouseVO GET_ONE_HOUSE_FK(Integer houseNO){
    	return dao.GET_ONE_HOUSE_FK(houseNO);
    }
    public String advencedSearch(String searchStr){
    	return dao.advencedSearch(searchStr);
    }
    public HouseVO getPic(Integer houseNO){
    	return dao.getPic(houseNO);
    }
    public void insertHouseAndHousePicture(HouseVO houseVO,Set<HousePictureVO> set){
    	dao.insertHouseAndHousePicture(houseVO, set);
    }
}
