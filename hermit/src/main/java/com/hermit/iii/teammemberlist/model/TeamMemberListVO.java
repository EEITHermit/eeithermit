package com.hermit.iii.teammemberlist.model;

import java.sql.Date;

import com.hermit.iii.businTeam.model.BusinTeamVO;
import com.hermit.iii.emp.model.EmpVO;

public class TeamMemberListVO {
	private Integer memberListNO;
	private EmpVO empVO;
	private BusinTeamVO businTeamVO;
	private Date tmlStartTime;
	private Date tmlEndTime;
	private Byte tmlStatus;

	public Integer getMemberListNO() {
		return memberListNO;
	}

	public void setMemberListNO(Integer memberListNO) {
		this.memberListNO = memberListNO;
	}

	public EmpVO getEmpVO() {
		return empVO;
	}

	public void setEmpVO(EmpVO empVO) {
		this.empVO = empVO;
	}

	public BusinTeamVO getBusinTeamVO() {
		return businTeamVO;
	}

	public void setBusinTeamVO(BusinTeamVO businTeamVO) {
		this.businTeamVO = businTeamVO;
	}

	public Date getTmlStartTime() {
		return tmlStartTime;
	}

	public void setTmlStartTime(Date tmlStartTime) {
		this.tmlStartTime = tmlStartTime;
	}

	public Date getTmlEndTime() {
		return tmlEndTime;
	}

	public void setTmlEndTime(Date tmlEndTime) {
		this.tmlEndTime = tmlEndTime;
	}

	public Byte getTmlStatus() {
		return tmlStatus;
	}

	public void setTmlStatus(Byte tmlStatus) {
		this.tmlStatus = tmlStatus;
	}

}
