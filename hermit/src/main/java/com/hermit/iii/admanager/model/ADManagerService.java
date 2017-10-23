package com.hermit.iii.admanager.model;

import java.sql.Date;
import java.util.List;

public class ADManagerService {
	private ADManagerDAO_interface dao;
	
	public ADManagerVO addADManager(String adImage, String adLink, String adMessage, Date adTimeStart , Date adTimeEnd , Boolean adStatus, int adBrowse , int adModify){
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
		return adVO;
		
	}
	
	public ADManagerVO updateADManager(String adImage , String adLink, String adMessage, Date adTimeStart , Date adTimeEnd , Boolean adStatus, int adBrowse , int adModify){
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
		return adVO;
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

	
}

