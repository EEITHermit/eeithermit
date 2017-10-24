package com.hermit.iii.businTeam.model;

import java.io.Serializable;

public class BusinTeamVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer businNO;
	private String businName;
	private Integer manager;
	public Integer getBusinNO() {
		return businNO;
	}
	public void setBusinNO(Integer businNO) {
		this.businNO = businNO;
	}
	public String getBusinName() {
		return businName;
	}
	public void setBusinName(String businName) {
		this.businName = businName;
	}
	public Integer getManager() {
		return manager;
	}
	public void setManager(Integer manager) {
		this.manager = manager;
	}
	
}
