package com.hermit.iii.mention.model;

public class MentionService {
	MentionDAO_Interface menDAO = null;
	public MentionService(){
		menDAO = new MentionDAO_JNDI();
	}
	
	public Integer getBoroughNOByEmpNO(Integer empNO){
		return menDAO.getBoroughNOByEmpNO(empNO);
	}
}
