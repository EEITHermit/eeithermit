package com.hermit.iii.businTeam.model;

import java.util.Set;

public interface BusinTeamDAO_interface_hibernate {

	public void insert(BusinTeamVO businTeamVO);

	public void update(BusinTeamVO businTeamVO);

	public void delete(Integer businNO);

	public BusinTeamVO findByPrimaryKey(Integer businNO);

	public Set<BusinTeamVO> getAll();
}
