package com.hermit.iii.housetype.model;

import java.io.Serializable;

public class HouseTypeVO implements Serializable {
	private Integer typeNO;
	private String hType;
	public Integer getTypeNO() {
		return typeNO;
	}
	public void setTypeNO(Integer typeNO) {
		this.typeNO = typeNO;
	}
	public String gethType() {
		return hType;
	}
	public void sethType(String hType) {
		this.hType = hType;
	}
}
