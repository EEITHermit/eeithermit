package com.hermit.iii.teammemberlist.model;

import java.sql.Date;

public class TeamMemberListVO {
	private Integer memberListNO;
	private Integer empNO;
	private Integer businNO;
	private Date tmlStartTime;
	private Date tmlEndTime;
	private Byte tmlStatus;

	public Integer getMemberListNO() {
		return memberListNO;
	}

	public void setMemberListNO(Integer memberListNO) {
		this.memberListNO = memberListNO;
	}

	public Integer getEmpNO() {
		return empNO;
	}

	public void setEmpNO(Integer empNO) {
		this.empNO = empNO;
	}

	public Integer getBusinNO() {
		return businNO;
	}

	public void setBusinNO(Integer businNO) {
		this.businNO = businNO;
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
