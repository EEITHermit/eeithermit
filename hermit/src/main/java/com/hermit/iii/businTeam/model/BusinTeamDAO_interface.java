package com.hermit.iii.businTeam.model;

public interface BusinTeamDAO_interface {
	public Integer delete(Integer businNO);
	public Integer insert(BusinTeamVO btVO);
	public Integer update(BusinTeamVO btVO);
	public BusinTeamVO select(Integer businNO);
}
