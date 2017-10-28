package com.hermit.iii.teamArea.model;

public class TeamAreaService {
	TeamAreaDAO_interface_original ta = new TeamAreaDAO_JNDI();
	public Integer insert(TeamAreaVO_original taVO){
		return ta.insert(taVO);
	};
	public Integer delete(Integer businNO){
		return ta.delete(businNO);
	};
	public TeamAreaVO_original select(Integer businNO){
		return ta.select(businNO);
	};
	public Integer update(TeamAreaVO_original taVO){
		return ta.update(taVO);
	};
}
