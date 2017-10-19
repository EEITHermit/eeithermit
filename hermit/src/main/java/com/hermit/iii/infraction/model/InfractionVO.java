package com.hermit.iii.infraction.model;

import java.io.Serializable;
import java.sql.Date;

public class InfractionVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer inNO;
	private Integer memNO;
	private String reason;
	private Date inDate;
	private Integer empNO;
	public Integer getInNO() {
		return inNO;
	}
	public void setInNO(Integer inNO) {
		this.inNO = inNO;
	}
	public Integer getMemNO() {
		return memNO;
	}
	public void setMemNO(Integer memNO) {
		this.memNO = memNO;
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
