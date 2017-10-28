package com.hermit.iii.teamArea.model;

import java.util.List;

public interface TeamAreaDAO_interface {
	public void insert(TeamAreaVO taVO);
	public void delete(Integer businNO);
	public TeamAreaVO findByPrimaryKey(Integer businNO);
	public void update(TeamAreaVO taVO);
	public List<TeamAreaVO>getAll();
}
