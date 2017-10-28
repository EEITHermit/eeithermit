package com.hermit.iii.lease.model;

import java.io.Serializable;
import java.sql.*;

public class LeaseVO_hibernate implements Serializable {
	private Integer leaseNO;
	private Integer houseNO;
	private Date leaseBeginDate;
	private Date leaseEndDate;
	private Integer memNO;
	private Integer empNO;
	private Integer leaseRent;
	private Integer leaseDeposit;
	private Integer leaseRelief;
	private Date leaseDate;
	private String leasePic;
	private String houseNote;
	private Byte leaseRefund;

	public Integer getLeaseNO() {
		return leaseNO;
	}

	public void setLeaseNO(Integer leaseNO) {
		this.leaseNO = leaseNO;
	}

	public Integer getHouseNO() {
		return houseNO;
	}

	public void setHouseNO(Integer houseNO) {
		this.houseNO = houseNO;
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

	public Integer getMemNO() {
		return memNO;
	}

	public void setMemNO(Integer memNO) {
		this.memNO = memNO;
	}

	public Integer getEmpNO() {
		return empNO;
	}

	public void setEmpNO(Integer empNO) {
		this.empNO = empNO;
	}

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