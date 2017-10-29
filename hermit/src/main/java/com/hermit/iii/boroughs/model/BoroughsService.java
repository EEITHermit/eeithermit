package com.hermit.iii.boroughs.model;

import java.util.List;

public class BoroughsService {

	private BoroughsDAO_interface_original dao;
	
	public BoroughsService(){
		dao = new BoroughsDAO_JNDI();
	}
	
	public void insertBoroughs(String boroughName, Integer cityNO ){
		BoroughsVO_original vo = new BoroughsVO_original();
		vo.setBoroughName(boroughName);
		vo.setCityNO(cityNO);
		dao.insert(vo);
	}
	
	public void updateBoroughs(Integer boroughNO,String boroughName,Integer cityNO){
		BoroughsVO_original vo = new BoroughsVO_original();
		vo.setBoroughNO(boroughNO);
		vo.setBoroughName(boroughName);
		vo.setCityNO(cityNO);
		dao.update(vo);
	}
	
	public void deleteBoroughs(Integer boroughNO){
		dao.delete(boroughNO);
	}
	public BoroughsVO_original getOne(Integer boroughNO){
		return dao.findByPrimaryKey(boroughNO);
	}
	public List<BoroughsVO_original> getAll(){
		return dao.getAll();
	}
	public String getAllWhereCityForJson(Integer cityNO){
		return dao.getAllWhereCity(cityNO);
	}

	public List<BoroughsVO_original> getAll_cityNO(Integer cityNO){
		return dao.getAll_cityNO(cityNO);
	} 

}
