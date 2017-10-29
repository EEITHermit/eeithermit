package com.hermit.iii.teamArea.model;

import java.io.Serializable;

public class TeamAreaVO_original implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer businNO;
	private Integer cityNO;
	private Integer boroughNO;
	public Integer getBusinNO() {
		return businNO;
	}
	public void setBusinNO(Integer businNO) {
		this.businNO = businNO;
	}
	public Integer getCityNO() {
		return cityNO;
	}
	public void setCityNO(Integer cityNO) {
		this.cityNO = cityNO;
	}
	public Integer getBoroughNO() {
		return boroughNO;
	}
	public void setBoroughNO(Integer boroughNO) {
		this.boroughNO = boroughNO;
	}
	
}
