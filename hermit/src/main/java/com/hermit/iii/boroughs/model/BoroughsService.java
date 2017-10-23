package com.hermit.iii.boroughs.model;

public class BoroughsService {

	private BoroughsDAO_interface dao;
	
	public BoroughsService(){
		dao = new BoroughsDAO_JNDI();
	}
	
	
	
}
