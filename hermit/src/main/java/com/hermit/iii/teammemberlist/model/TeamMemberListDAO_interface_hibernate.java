package com.hermit.iii.teammemberlist.model;

import java.util.Set;

public interface TeamMemberListDAO_interface_hibernate {

	public void insert(TeamMemberListVO teamMemberListVO);

	public void update(TeamMemberListVO teamMemberListVO);

	public void delete(Integer memberListNO);

	public TeamMemberListVO findByPrimaryKey(Integer memberListNO);

	public Set<TeamMemberListVO> getAll();
}
