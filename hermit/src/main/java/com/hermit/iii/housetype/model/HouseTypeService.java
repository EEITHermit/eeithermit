package com.hermit.iii.housetype.model;

import java.util.List;

public class HouseTypeService {
	private HouseTypeDAO_interface_hibernate dao;
	
	public HouseTypeService(){
		dao = new HouseTypeDAO_hibernate();
	}
	
	public void insertHouseForm(String hType){
		HouseTypeVO vo = new HouseTypeVO();
		vo.sethType(hType);
		dao.insert(vo);
	}
	
	public void updateHouseType(Integer typeNO,String hType){
		HouseTypeVO vo = new HouseTypeVO();
		vo.setTypeNO(typeNO);
		vo.sethType(hType);
		dao.update(vo);
	}
	
	public void deleteHouseType(Integer typeNO){
		dao.delete(typeNO);
	}
	
	public HouseTypeVO getOneHouseType(Integer typeNO){
		return dao.findByPrimaryKey(typeNO);
	}
	
	public List<HouseTypeVO> getAllHouseType(){
		return dao.getAll();
	}
}
