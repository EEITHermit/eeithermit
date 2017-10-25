package com.hermit.iii.teamArea.model;

public class TeamAreaService {
	TeamAreaDAO_interface ta = new TeamAreaDAO_JNDI();
	public Integer insert(TeamAreaVO taVO){
		return ta.insert(taVO);
	};
	public Integer delete(Integer businNO){
		return ta.delete(businNO);
	};
	public TeamAreaVO select(Integer businNO){
		return ta.select(businNO);
	};
	public Integer update(TeamAreaVO taVO){
		return ta.update(taVO);
	};
}
