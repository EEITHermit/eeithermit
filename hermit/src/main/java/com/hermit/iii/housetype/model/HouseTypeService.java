package com.hermit.iii.housetype.model;

import java.util.List;

public class HouseTypeService {
	private HouseTypeDAO_interface dao;
	
	public HouseTypeService(){
		dao = new HouseTypeDAO_JNDI();
	}
	
	public void insertHouseForm(String hType){
		HouseTypeVO vo = new HouseTypeVO();
		vo.sethType(hType);
		dao.insert(vo);
	}
	
	public void updateHouseForm(Integer typeNO,String hType){
		HouseTypeVO vo = new HouseTypeVO();
		vo.setTypeNO(typeNO);
		vo.sethType(hType);
		dao.update(vo);
	}
	
	public void deleteHouseForm(Integer typeNO){
		dao.delete(typeNO);
	}
	
	public HouseTypeVO getOneHouseForm(Integer typeNO){
		return dao.findByPrimaryKey(typeNO);
	}
	
	public List<HouseTypeVO> getAllHouseForm(){
		return dao.getAll();
	}
}
