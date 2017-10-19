package com.hermit.iii.house.model;

import java.io.Serializable;

public class HouseVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer houseNO;
	private String houseAddr;
	private String houseTitle;
	public Integer getHouseNO() {
		return houseNO;
	}
	public void setHouseNO(Integer houseNO) {
		this.houseNO = houseNO;
	}
	public String getHouseAddr() {
		return houseAddr;
	}
	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}
	public String getHouseTitle() {
		return houseTitle;
	}
	public void setHouseTitle(String houseTitle) {
		this.houseTitle = houseTitle;
	}
	
	
	
}
