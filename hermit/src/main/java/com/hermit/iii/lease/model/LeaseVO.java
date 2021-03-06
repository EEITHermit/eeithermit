package com.hermit.iii.lease.model;

import java.io.Serializable;
import java.sql.*;

import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.member.model.MemberVO;

public class LeaseVO implements Serializable {
	private Integer leaseNO;
	private HouseVO houseVO;
	private Date leaseBeginDate;
	private Date leaseEndDate;
//	private Integer memNO;
//	private Integer empNO;
	private Integer leaseRent;
	private Integer leaseDeposit;
	private Integer leaseRelief;
	private Date leaseDate;
	private String leasePic;
	private String houseNote;
	private Byte leaseRefund;
	//子傑加
	private MemberVO memberVO;
	private EmpVO empVO;
	
	
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

	public Integer getLeaseNO() {
		return leaseNO;
	}

	public void setLeaseNO(Integer leaseNO) {
		this.leaseNO = leaseNO;
	}

	
	public HouseVO getHouseVO() {
		return houseVO;
	}

	public void setHouseVO(HouseVO houseVO) {
		this.houseVO = houseVO;
	}

	public Date getLeaseBeginDate() {
		return leaseBeginDate;
	}

	public void setLeaseBeginDate(Date leaseBeginDate) {
		this.leaseBeginDate = leaseBeginDate;
	}

	public Date getLeaseEndDate() {
		return leaseEndDate;
	}

	public void setLeaseEndDate(Date leaseEndDate) {
		this.leaseEndDate = leaseEndDate;
	}

//	public Integer getMemNO() {
//		return memNO;
//	}
//
//	public void setMemNO(Integer memNO) {
//		this.memNO = memNO;
//	}

//	public Integer getEmpNO() {
//		return empNO;
//	}
//
//	public void setEmpNO(Integer empNO) {
//		this.empNO = empNO;
//	}

	public Integer getLeaseRent() {
		return leaseRent;
	}

	public void setLeaseRent(Integer leaseRent) {
		this.leaseRent = leaseRent;
	}

	public Integer getLeaseDeposit() {
		return leaseDeposit;
	}

	public void setLeaseDeposit(Integer leaseDeposit) {
		this.leaseDeposit = leaseDeposit;
	}

	public Integer getLeaseRelief() {
		return leaseRelief;
	}

	public void setLeaseRelief(Integer leaseRelief) {
		this.leaseRelief = leaseRelief;
	}

	public Date getLeaseDate() {
		return leaseDate;
	}

	public void setLeaseDate(Date leaseDate) {
		this.leaseDate = leaseDate;
	}

	public String getLeasePic() {
		return leasePic;
	}

	public void setLeasePic(String leasePic) {
		this.leasePic = leasePic;
	}

	public String getHouseNote() {
		return houseNote;
	}

	public void setHouseNote(String houseNote) {
		this.houseNote = houseNote;
	}

	public Byte getLeaseRefund() {
		return leaseRefund;
	}

	public void setLeaseRefund(Byte leaseRefund) {
		this.leaseRefund = leaseRefund;
	}
}