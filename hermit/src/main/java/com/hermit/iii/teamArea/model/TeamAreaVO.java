package com.hermit.iii.teamArea.model;

import java.io.Serializable;

import com.hermit.iii.boroughs.model.BoroughsVO;
import com.hermit.iii.teammemberlist.model.TeamMemberListVO;

public class TeamAreaVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private TeamMemberListVO teamMemberListVO;
	private Integer cityNO;
	private BoroughsVO BoroughVO;
	public TeamMemberListVO getTeamMemberListVO() {
		return teamMemberListVO;
	}
	public void setTeamMemberListVO(TeamMemberListVO teamMemberListVO) {
		this.teamMemberListVO = teamMemberListVO;
	}
	public Integer getCityNO() {
		return cityNO;
	}
	public void setCityNO(Integer cityNO) {
		this.cityNO = cityNO;
	}
	public BoroughsVO getBoroughVO() {
		return BoroughVO;
	}
	public void setBoroughVO(BoroughsVO boroughVO) {
		BoroughVO = boroughVO;
	}
	
}
