package com.hermit.iii.admanager.model;
import java.util.List;

public interface ADManagerDAO_interface_hibernate {		
	public void insert(ADManagerVO adManagerVO);	
	public void update(ADManagerVO adManagerVO);	
	public void delete(Integer adNO);	
	public ADManagerVO findByPrimaryKey(Integer adNO);	
	public List<ADManagerVO> getAll();	
	public String getAllForJson();
	
}
