package com.hermit.iii.businTeam.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.teamArea.model.TeamAreaVO;
import com.hermit.iii.teammemberlist.model.TeamMemberListVO;

public class BusinTeamVO implements Serializable {

	private Integer businNO;
	private String businName;
	private EmpVO managerVO;
	private Set<TeamMemberListVO> teammemberlists = new HashSet<TeamMemberListVO>();
	private Set<TeamAreaVO> teamAreaVOs = new HashSet<TeamAreaVO>();
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

	public EmpVO getManagerVO() {
		return managerVO;
	}

	public void setManagerVO(EmpVO managerVO) {
		this.managerVO = managerVO;
	}

	public Set<TeamMemberListVO> getTeammemberlists() {
		return teammemberlists;
	}

	public void setTeammemberlists(Set<TeamMemberListVO> teammemberlists) {
		this.teammemberlists = teammemberlists;
	}

	public Set<TeamAreaVO> getTeamAreaVOs() {
		return teamAreaVOs;
	}

	public void setTeamAreaVOs(Set<TeamAreaVO> teamAreaVOs) {
		this.teamAreaVOs = teamAreaVOs;
	}
	
}
