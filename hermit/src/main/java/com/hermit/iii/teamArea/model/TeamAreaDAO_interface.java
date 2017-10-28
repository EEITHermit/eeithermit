package com.hermit.iii.teamArea.model;

public interface TeamAreaDAO_interface {
	public void insert(TeamAreaVO taVO);
	public void delete(Integer businNO);
	public TeamAreaVO select(Integer businNO);
	public void update(TeamAreaVO taVO);
}
