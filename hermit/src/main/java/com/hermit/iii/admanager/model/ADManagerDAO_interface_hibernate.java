package com.hermit.iii.admanager.model;
import java.util.List;

public interface ADManagerDAO_interface_hibernate {	
	
	public void insert(ADManagerVO_hibernate ad);
	
	public void update(ADManagerVO_hibernate ad);
	
	public void delete(int adNo);
	
	public ADManagerVO_hibernate findByPrimaryKey(int adNo);
	
	public List<ADManagerVO_hibernate> getAll();
	
	public String getAllForJson();
	
}
