package com.hermit.iii.city.model;

import java.util.List;

public class CityService {
	private CityDAO_interface_original dao;
	
	public CityService(){
		dao = new CityDAO_JNDI();
	}
	
	public void insertCity(String cityName){
		CityVO_original cityVO = new CityVO_original();
		cityVO.setCityName(cityName);
		dao.insert(cityVO);
	}
	
	public void updateCity(Integer cityNO,String cityName){
		CityVO_original cityVO = new CityVO_original();
		cityVO.setCityNO(cityNO);
		cityVO.setCityName(cityName);
		dao.update(cityVO);
	}
	
	public void deleteCity(Integer cityNO){
		dao.delete(cityNO);
	}
	
	public CityVO_original getONECity(Integer cityNO){
		return dao.findByPrimaryKey(cityNO); 
	}
	public List<CityVO_original> getAllCity(){
		return dao.getAll();
	}
	public String getAllForJSON(){
		return dao.getAllForJson();
	}
}
