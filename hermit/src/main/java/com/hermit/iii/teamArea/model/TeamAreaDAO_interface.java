package com.hermit.iii.teamArea.model;

public interface TeamAreaDAO_interface {
	public Integer insert(TeamAreaVO taVO);
	public Integer delete(Integer businNO);
	public TeamAreaVO select(Integer businNO);
	public Integer update(TeamAreaVO taVO);
}
