package com.hermit.iii.reservation.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.member.model.MemberVO;

public class ReservationVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer reservationNo;
	private MemberVO memberVO = new MemberVO();
	private HouseVO houseVO = new HouseVO();
	private EmpVO empVO = new EmpVO();
	private Integer areaNO;
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
	public EmpVO getEmpVO() {
		return empVO;
	}
	public void setEmpVO(EmpVO empVO) {
		this.empVO = empVO;
	}
	public Integer getAreaNO() {
		return areaNO;
	}
	public void setAreaNO(Integer areaNO) {
		this.areaNO = areaNO;
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
