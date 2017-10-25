package com.hermit.iii.lease.model;

import java.io.*;
import java.sql.*;

public class LeaseVO implements Serializable {
	private Integer leaseNO;
	private Integer houseNO;
	private Date leaseBeginDate;
	private Date leaseEndDate;
	private Integer memNO;
	private Integer empNO;
	private Integer Rent;
	private Integer Deposit;
	private Integer Relief;
	private Date leaseDate;
	private String leasePic;
	private String houseNote;
	private Byte Refund;

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

	public Integer getRent() {
		return Rent;
	}

	public void setRent(Integer rent) {
		Rent = rent;
	}

	public Integer getDeposit() {
		return Deposit;
	}

	public void setDeposit(Integer deposit) {
		Deposit = deposit;
	}

	public Integer getRelief() {
		return Relief;
	}

	public void setRelief(Integer relief) {
		Relief = relief;
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

	public Byte getRefund() {
		return Refund;
	}

	public void setRefund(Byte refund) {
		Refund = refund;
	}
}