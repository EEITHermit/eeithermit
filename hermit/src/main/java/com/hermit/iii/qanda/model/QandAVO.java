package com.hermit.iii.qanda.model;

import java.io.Serializable;
import java.sql.*;

public class QandAVO implements Serializable {
	private Integer qaNO;
	private Integer memNO;
	private Integer empNO;
	private Integer houseNO;
	private Date qTime;
	private Date aTime;
	private Byte qaType;
	private String qDetail;
	private String aDetail;

	public Integer getQaNO() {
		return qaNO;
	}

	public void setQaNO(Integer qaNO) {
		this.qaNO = qaNO;
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

	public Integer getHouseNO() {
		return houseNO;
	}

	public void setHouseNO(Integer houseNO) {
		this.houseNO = houseNO;
	}

	public Date getqTime() {
		return qTime;
	}

	public void setqTime(Date qTime) {
		this.qTime = qTime;
	}

	public Date getaTime() {
		return aTime;
	}

	public void setaTime(Date aTime) {
		this.aTime = aTime;
	}

	public Byte getQaType() {
		return qaType;
	}

	public void setQaType(Byte qaType) {
		this.qaType = qaType;
	}

	public String getqDetail() {
		return qDetail;
	}

	public void setqDetail(String qDetail) {
		this.qDetail = qDetail;
	}

	public String getaDetail() {
		return aDetail;
	}

	public void setaDetail(String aDetail) {
		this.aDetail = aDetail;
	}
}