package com.hermit.iii.admanager.model;
import java.util.List;

public interface ADManagerDAO_interface {	
	public void insert(ADManagerVO_original ad);
	public void update(ADManagerVO_original ad);
	public void delete(int adNo);
	public ADManagerVO_original findByPrimaryKey(Integer adNo);
	public List<ADManagerVO_original> getAll();
	public String getAllForJson();
	

}
