package com.hermit.iii.houseform.model;

import java.util.LinkedList;
import java.util.List;

public class HouseFormService {
	private HouseFormDAO_interface dao;
	
	public HouseFormService(){
		dao = new HouseFormDAO_JNDI();
	}
	
	public void insertHouseForm(String hForm){
		HouseFormVO vo = new HouseFormVO();
		vo.sethForm(hForm);
		dao.insert(vo);
	}
	
	public void updateHouseForm(Integer formNO,String hForm){
		HouseFormVO vo = new HouseFormVO();
		vo.setFormNO(formNO);
		vo.sethForm(hForm);
		dao.update(vo);
	}
	
	public void deleteHouseForm(Integer formNO){
		dao.delete(formNO);
	}
	
	public HouseFormVO getOneHouseForm(Integer formNO){
		return dao.findByPrimaryKey(formNO);
	}
	
	public List<HouseFormVO> getAllHouseForm(){
		return dao.getAll();
	}
}
