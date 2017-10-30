package com.hermit.iii.teamArea.model;

public class TeamAreaService {
	TeamAreaDAO_interface ta = new TeamAreaDAO_hibernate();
	public void insert(TeamAreaVO taVO){
		ta.insert(taVO);
	};
	public void delete(Integer businNO){
		ta.delete(businNO);
	};
	public TeamAreaVO select(Integer businNO){
		return ta.findByPrimaryKey(businNO);
	};
	public void update(TeamAreaVO taVO){
		ta.update(taVO);
	};
}
