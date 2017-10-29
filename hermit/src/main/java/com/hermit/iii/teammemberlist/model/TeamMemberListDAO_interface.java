package com.hermit.iii.teammemberlist.model;

import java.util.*;

public interface TeamMemberListDAO_interface {

	public void insert(TeamMemberListVO_original teamMemberListVO);

	public void update(TeamMemberListVO_original teamMemberListVO);

	public void delete(Integer memberListNO);

	public TeamMemberListVO_original findByPrimaryKey(Integer memberListNO);

	public Set<TeamMemberListVO_original> getAll();
}
