package com.hermit.iii.dispatchlist.model;

import java.util.List;
import java.sql.Date;


public class DispatchListService {
	private DispatchListDAO_interface dao;
	
	public DispatchListService(){
		dao = new DispatchListDAO_JNDI();
	}
	
	public DispatchListVO addDispatchList(int dempno , int aempno , int qano , Date dlstime){
		DispatchListVO dlVO = new DispatchListVO();
		dlVO.setDempNo(dempno);
		dlVO.setAempNo(aempno);
		dlVO.setQaNo(qano);
		dlVO.setDlStime(dlstime);
		dao.insert(dlVO);
		return dlVO;
		
	}
	
	public DispatchListVO updateDispatchList(int dlno, int dempno,int aempno,int qano,Date dlstime,Date dletime,String elesign,String dlnote){
		 DispatchListVO dlVO = new DispatchListVO();
		 dlVO.setDlNo(dlno);
		 dlVO.setDempNo(dempno);
		 dlVO.setAempNo(aempno);
		 dlVO.setQaNo(qano);
		 dlVO.setDlStime(dlstime);
		 dlVO.setDlEtime(dletime);
		 dlVO.setElesign(elesign);
		 dlVO.setDlNote(dlnote);
		 dao.update(dlVO);
		 return dlVO;
	}
	
	public void deleteDispatchList(int dlno){
		dao.delete(dlno);
	}
	
	public DispatchListVO getOneDispatchList(int dlno){
		return dao.findByPrimaryKey(dlno);
	}
	
	public List<DispatchListVO> getAllDispatchList(){
		return dao.getAll();
	}
	public String getAllForJson(){
		return dao.getAllForJson();
	}
	
}
