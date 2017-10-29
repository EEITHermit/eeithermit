package com.hermit.iii.reservation.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hermit.iii.emp.model.EmpVO_original;
import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.member.model.MemberVO;

public class ReservationVO_original implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer reservationNo;
	private MemberVO memberVO = new MemberVO();
	private HouseVO houseVO = new HouseVO();
	private EmpVO_original empVO = new EmpVO_original();
	private Integer boroughNO;
	private String exceptTime;
	private Timestamp applyTime;
	public Integer getReservationNo() {
		return reservationNo;
	}
	public void setReservationNo(Integer reservationNo) {
		this.reservationNo = reservationNo;
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
	public EmpVO_original getEmpVO() {
		return empVO;
	}
	public void setEmpVO(EmpVO_original empVO) {
		this.empVO = empVO;
	}
	public Integer getBoroughNO() {
		return boroughNO;
	}
	public void setBoroughNO(Integer boroughNO) {
		this.boroughNO = boroughNO;
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
