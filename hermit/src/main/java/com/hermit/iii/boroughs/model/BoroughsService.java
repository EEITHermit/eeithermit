package com.hermit.iii.boroughs.model;

import java.util.List;

public class BoroughsService {

	private BoroughsDAO_interface dao;
	
	public BoroughsService(){
		dao = new BoroughsDAO_JNDI();
	}
	
	public void insertBoroughs(String boroughName, Integer cityNO ){
		BoroughsVO vo = new BoroughsVO();
		vo.setBoroughName(boroughName);
		vo.setCityNO(cityNO);
		dao.insert(vo);
	}
	
	public void updateBoroughs(Integer boroughNO,String boroughName,Integer cityNO){
		BoroughsVO vo = new BoroughsVO();
		vo.setBoroughNO(boroughNO);
		vo.setBoroughName(boroughName);
		vo.setCityNO(cityNO);
		dao.update(vo);
	}
	
	public void deleteBoroughs(Integer boroughNO){
		dao.delete(boroughNO);
	}
	public BoroughsVO getOne(Integer boroughNO){
		return dao.findByPrimaryKey(boroughNO);
	}
	public List<BoroughsVO> getAll(){
		return dao.getAll();
	}
	public List<BoroughsVO> getAll_cityNO(Integer cityNO){
		return dao.getAll_cityNO(cityNO);
	} 
}
