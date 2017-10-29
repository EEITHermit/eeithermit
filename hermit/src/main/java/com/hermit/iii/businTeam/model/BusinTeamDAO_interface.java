package com.hermit.iii.businTeam.model;

public interface BusinTeamDAO_interface {
	public Integer delete(Integer businNO);
	public Integer insert(BusinTeamVO_original btVO);
	public Integer update(BusinTeamVO_original btVO);
	public BusinTeamVO_original select(Integer businNO);
}
