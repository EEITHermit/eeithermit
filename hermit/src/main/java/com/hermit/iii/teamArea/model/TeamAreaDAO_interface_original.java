package com.hermit.iii.teamArea.model;

public interface TeamAreaDAO_interface_original {
	public Integer insert(TeamAreaVO_original taVO);
	public Integer delete(Integer businNO);
	public TeamAreaVO_original select(Integer businNO);
	public Integer update(TeamAreaVO_original taVO);
}
