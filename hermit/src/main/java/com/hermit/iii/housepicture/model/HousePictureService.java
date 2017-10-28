package com.hermit.iii.housepicture.model;

import java.util.List;

public class HousePictureService {
	private HousePictureDAO_interface_original dao;
	
	public HousePictureService(){
		dao = new HousePictureDAO_JNDI();
	}
	
	public void insertHousePicture(String hPicture,Integer houseNO){
		HousePictureVO_original vo = new HousePictureVO_original();
		vo.sethPicture(hPicture);
		vo.setHouseNO(houseNO);
		dao.insert(vo);
	}
	
	public void updateHousePicture(Integer housePictureNO ,String hPicture , Integer houseNO){
		HousePictureVO_original vo = new HousePictureVO_original();
		vo.setHouseNO(houseNO);
		vo.setHousePictureNO(housePictureNO);
		vo.sethPicture(hPicture);
		dao.update(vo);
	}
	
	public void deletePicture(Integer housePictureNO){
		dao.delete(housePictureNO);
	}
	
	public HousePictureVO_original getOneHousePicture(Integer housePictureNO){
		return dao.findByPrimaryKey(housePictureNO);
	}
	
	public List<HousePictureVO_original> getAllHousePicture(){
		return dao.getAll();
	}
}
