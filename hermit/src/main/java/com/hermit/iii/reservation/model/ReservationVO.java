package com.hermit.iii.reservation.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hermit.iii.emp.model.EmpVO_original;
import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.member.model.MemberVO;

public class ReservationVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer reservationNO;
	private Integer memNO;
	private Integer houseNO;
	private Integer empNO;
	private Integer boroughNO;
	private Boolean takedOver;
	private String exceptTime;
	private Timestamp applyTime;
	public Integer getReservationNO() {
		return reservationNO;
	}
	public void setReservationNO(Integer reservationNO) {
		this.reservationNO = reservationNO;
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
	public Integer getEmpNO() {
		return empNO;
	}
	public void setEmpNO(Integer empNO) {
		this.empNO = empNO;
	}
	public Integer getBoroughNO() {
		return boroughNO;
	}
	public void setBoroughNO(Integer boroughNO) {
		this.boroughNO = boroughNO;
	}
	public Boolean getTakedOver() {
		return takedOver;
	}
	public void setTakedOver(Boolean takedOver) {
		this.takedOver = takedOver;
	}
	public String getExceptTime() {
		return exceptTime;
	}
	public void setExceptTime(String exceptTime) {
		this.exceptTime = exceptTime;
	}
	public Timestamp getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}
	
	
}
