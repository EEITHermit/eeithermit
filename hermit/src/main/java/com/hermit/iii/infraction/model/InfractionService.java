package com.hermit.iii.infraction.model;

public class InfractionService {
	InfractionDAO_interface inDAO = new InfractionJNDIDAO();
	public Integer insert(InfractionVO inVO){
		return inDAO.insert(inVO);
	}
}
