package com.hermit.iii.calendar.model;

import java.io.Serializable;
import java.sql.Timestamp;
import com.hermit.iii.emp.model.EmpVO_original;
import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.member.model.MemberVO;

public class CalendarEventVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer eventNO;
	private Integer empNO;
	private Integer memNO;
	private Integer houseNO;
	private Timestamp eventStartTime;
	private Timestamp eventEndTime;
	private String ps;
	public Integer getEventNO() {
		return eventNO;
	}
	public void setEventNO(Integer eventNO) {
		this.eventNO = eventNO;
	}
	public Integer getEmpNO() {
		return empNO;
	}
	public void setEmpNO(Integer empNO) {
		this.empNO = empNO;
	}
	public Integer getMemNO() {
		return memNO;
	}
	public void setMemNO(Integer memNO) {
		this.memNO = memNO;
	}
	public Integer getHouseNO() {
		return houseNO;
	}
	public void setHouseNO(Integer houseNO) {
		this.houseNO = houseNO;
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
