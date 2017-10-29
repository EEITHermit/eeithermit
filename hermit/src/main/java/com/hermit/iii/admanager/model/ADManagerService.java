package com.hermit.iii.admanager.model;

import java.sql.Date;
import java.util.List;

public class ADManagerService {
	private ADManagerDAO_interface_hibernate dao;
	
	public ADManagerService(){
		dao = new ADManagerDAO_hebernate();
	}
	
	public void insertADManager(String adImage, String adLink, String adMessage, Date adTimeStart , Date adTimeEnd , Boolean adStatus, Integer adBrowse , Integer adModify){
		ADManagerVO adVO = new ADManagerVO();
		adVO.setAdImage(adImage);
		adVO.setAdLink(adLink);
		adVO.setAdMessage(adMessage);
		adVO.setAdTimeStart(adTimeStart);
		adVO.setAdTimeEnd(adTimeEnd);
		adVO.setAdStatus(adStatus);
		adVO.setAdBrowse(adBrowse);
		adVO.setAdModify(adModify);
		dao.insert(adVO);
	}
	
	public void updateADManager(Integer adNO, String adImage , String adLink, String adMessage, Date adTimeStart , Date adTimeEnd , Boolean adStatus, Integer adBrowse , Integer adModify){
		ADManagerVO adVO = new ADManagerVO();
		adVO.setAdImage(adImage);
		adVO.setAdLink(adLink);
		adVO.setAdMessage(adMessage);
		adVO.setAdTimeStart(adTimeStart);
		adVO.setAdTimeEnd(adTimeEnd);
		adVO.setAdStatus(adStatus);
		adVO.setAdBrowse(adBrowse);
		adVO.setAdModify(adModify);
		dao.update(adVO);
	}
	
	public void deleteADManager(int adNO){
		dao.delete(adNO);
	}
	
	public ADManagerVO getOneADManager(int adNO){
		return dao.findByPrimaryKey(adNO);
		
	}
	
	public List<ADManagerVO> getAllADManager(){
		return dao.getAll();
	}
	public String getAllForJson(){
		return dao.getAllForJson();
	}
}

