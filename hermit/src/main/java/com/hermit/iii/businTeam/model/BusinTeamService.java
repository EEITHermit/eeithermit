package com.hermit.iii.businTeam.model;

public class BusinTeamService {
	BusinTeamDAO_interface btDAO = new BusinTeamDAO_JNDI();
	public Integer delete(Integer businNO){
		return btDAO.delete(businNO);
	};
	public Integer insert(BusinTeamVO btVO){
		return btDAO.insert(btVO);
	};
	public Integer update(BusinTeamVO btVO){
		return btDAO.update(btVO);
	};
	public BusinTeamVO select(Integer businNO){
		return btDAO.select(businNO);
	};
}