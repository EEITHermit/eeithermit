package com.hermit.iii.housepicture.model;

import java.util.List;

public class HousePictureService {
	private HousePictureDAO_interface dao;
	
	public HousePictureService(){
		dao = new HousePictureDAO_JNDI();
	}
	
	public void insertHousePicture(String hPicture,Integer houseNO){
		HousePictureVO vo = new HousePictureVO();
		vo.sethPicture(hPicture);
		vo.setHouseNO(houseNO);
		dao.insert(vo);
	}
	
	public void updateHousePicture(Integer housePictureNO ,String hPicture , Integer houseNO){
		HousePictureVO vo = new HousePictureVO();
		vo.setHouseNO(houseNO);
		vo.setHousePictureNO(housePictureNO);
		vo.sethPicture(hPicture);
		dao.update(vo);
	}
	
	public void deletePicture(Integer housePictureNO){
		dao.delete(housePictureNO);
	}
	
	public HousePictureVO getOneHousePicture(Integer housePictureNO){
		return dao.findByPrimaryKey(housePictureNO);
	}
	
	public List<HousePictureVO> getAllHousePicture(){
		return dao.getAll();
	}
}
