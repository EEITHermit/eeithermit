package com.hermit.iii.admanager.model;
import java.util.List;

public interface ADManagerDAO_interface {	
	public void insert(ADManagerVO ad);
	public void update(ADManagerVO ad);
	public void delete(int adNo);
	public ADManagerVO findByPrimaryKey(int adNo);
	public List<ADManagerVO> getAll();
	public String getAllForJson();
	

}
