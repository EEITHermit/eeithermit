package com.hermit.iii.businTeam.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.hermit.iii.teammemberlist.model.TeamMemberListVO;

public class BusinTeamVO implements Serializable {

	private Integer businNO;
	private String businName;
	private Integer manager;
	private Set<TeamMemberListVO> teammemberlists = new HashSet<TeamMemberListVO>();

	public Integer getBusinNO() {
		return businNO;
	}

	public void setBusinNO(Integer businNO) {
		this.businNO = businNO;
	}

	public String getBusinName() {
		return businName;
	}

	public void setBusinName(String businName) {
		this.businName = businName;
	}

	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	public Set<TeamMemberListVO> getTeammemberlists() {
		return teammemberlists;
	}

	public void setTeammemberlists(Set<TeamMemberListVO> teammemberlists) {
		this.teammemberlists = teammemberlists;
	}

}
