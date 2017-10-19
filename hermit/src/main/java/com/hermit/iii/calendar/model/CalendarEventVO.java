package com.hermit.iii.calendar.model;

import java.io.Serializable;
import java.sql.Timestamp;
import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.member.model.MemberVO;

public class CalendarEventVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer eventNO;
	private EmpVO empVO = new EmpVO();
	private MemberVO memberVO = new MemberVO();
	private HouseVO houseVO = new HouseVO();
	private Timestamp eventStartTime;
	private Timestamp eventEndTime;
	private String ps;
	public Integer getEventNO() {
		return eventNO;
	}
	public void setEventNO(Integer eventNO) {
		this.eventNO = eventNO;
	}
	public EmpVO getEmpVO() {
		return empVO;
	}
	public void setEmpVO(EmpVO empVO) {
		this.empVO = empVO;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public HouseVO getHouseVO() {
		return houseVO;
	}
	public void setHouseVO(HouseVO houseVO) {
		this.houseVO = houseVO;
	}
	public Timestamp getEventStartTime() {
		return eventStartTime;
	}
	public void setEventStartTime(Timestamp eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	public Timestamp getEventEndTime() {
		return eventEndTime;
	}
	public void setEventEndTime(Timestamp eventEndTime) {
		this.eventEndTime = eventEndTime;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	
	
	
}
