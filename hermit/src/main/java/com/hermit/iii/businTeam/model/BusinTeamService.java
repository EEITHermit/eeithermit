package com.hermit.iii.businTeam.model;

import java.util.Set;

public class BusinTeamService {
	BusinTeamDAO_interface_hibernate btDAO = new BusinTeamDAO_hibernate();

	public void delete(Integer businNO) {
		btDAO.delete(businNO);
		return;
	};

	public void insert(BusinTeamVO btVO) {
		btDAO.insert(btVO);
		return;
	};

	public void update(BusinTeamVO btVO) {
		btDAO.update(btVO);
		return;
	};

	public BusinTeamVO select(Integer businNO) {

		return btDAO.findByPrimaryKey(businNO);
	};

	public Set<BusinTeamVO> getAll() {
		btDAO.getAll();
		return btDAO.getAll();
	}
}
