package com.hermit.iii.member.model;

import java.io.Serializable;

public class MemberVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer memNO;
	private String memName;
	private String memTel;
	public Integer getMemNO() {
		return memNO;
	}
	public void setMemNO(Integer memNO) {
		this.memNO = memNO;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	
	
}
