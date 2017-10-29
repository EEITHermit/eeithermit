package com.hermit.iii.reservation.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hermit.iii.boroughs.model.BoroughsVO;
import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.emp.model.EmpVO_original;
import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.member.model.MemberVO;

public class ReservationVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer reservationNO;
	private MemberVO memberVO = new MemberVO();
	private HouseVO houseVO = new HouseVO();
	private EmpVO empVO;
	private BoroughsVO boroughsVO = new BoroughsVO();
	private Boolean takedOver;
	private String exceptTime;
	private Timestamp applyTime;
	public Integer getReservationNO() {
		return reservationNO;
	}
	public void setReservationNO(Integer reservationNO) {
		this.reservationNO = reservationNO;
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
	public EmpVO getEmpVO() {
		return empVO;
	}
	public void setEmpVO(EmpVO empVO) {
		this.empVO = empVO;
	}
	public BoroughsVO getBoroughsVO() {
		return boroughsVO;
	}
	public void setBoroughsVO(BoroughsVO boroughsVO) {
		this.boroughsVO = boroughsVO;
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
