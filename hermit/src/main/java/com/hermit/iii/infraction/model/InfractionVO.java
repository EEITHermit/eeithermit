package com.hermit.iii.infraction.model;

import java.io.Serializable;
import java.sql.Date;

import com.hermit.iii.member.model.MemberVO;

public class InfractionVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer inNO;
	private MemberVO memberVO;
	private String reason;
	private Date inDate;
	private Integer empNO;
	public Integer getInNO() {
		return inNO;
	}
	public void setInNO(Integer inNO) {
		this.inNO = inNO;
	}
	
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public Integer getEmpNO() {
		return empNO;
	}
	public void setEmpNO(Integer empNO) {
		this.empNO = empNO;
	}
	
	
}
