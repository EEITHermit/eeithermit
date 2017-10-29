package com.hermit.iii.admanager.model;
import java.util.List;

public interface ADManagerDAO_interface_hibernate {	
	
	public void insert(ADManagerVO ad);
	
	public void update(ADManagerVO ad);
	
	public void delete(int adNO);
	
	public ADManagerVO findByPrimaryKey(int adNO);
	
	public List<ADManagerVO> getAll();
	
	public String getAllForJson();
	
}
