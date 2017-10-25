package com.hermit.iii.mention.model;

import java.util.ArrayList;

public class MentionService {
	MentionDAO_Interface menDAO ;
	public MentionService(){
		menDAO = new MentionDAO_JNDI();
	}
	
	public ArrayList<Integer> getBoroughNOByEmpNO(Integer empNO){
		return menDAO.getBoroughNOByEmpNO(empNO);
	}
}
