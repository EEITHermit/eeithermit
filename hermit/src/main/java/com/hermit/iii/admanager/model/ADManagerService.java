package com.hermit.iii.admanager.model;

import java.sql.Date;
import java.util.List;

public class ADManagerService {
	private ADManagerDAO_interface dao;
	
	public ADManagerService(){
		dao = new ADManagerDAO_JNDI();
	}
	
	public void insertADManager(String adImage, String adLink, String adMessage, Date adTimeStart , Date adTimeEnd , Boolean adStatus, int adBrowse , int adModify){
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
	
	public void updateADManager(String adImage , String adLink, String adMessage, Date adTimeStart , Date adTimeEnd , Boolean adStatus, int adBrowse , int adModify){
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
	
	public void deleteADManager(int adNo){
		dao.delete(adNo);
	}
	
	public ADManagerVO getOneADManager(int adNo){
		return dao.findByPrimaryKey(adNo);
		
	}
	
	public List<ADManagerVO> getAllADManager(){
		return dao.getAll();
	}
	public String getAllForJson(){
		return dao.getAllForJson();
	}
}

