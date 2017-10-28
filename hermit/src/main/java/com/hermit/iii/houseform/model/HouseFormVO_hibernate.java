package com.hermit.iii.houseform.model;

import java.io.Serializable;

public class HouseFormVO_hibernate implements Serializable {
	private Integer formNO;
	private String hForm;
	public Integer getFormNO() {
		return formNO;
	}
	public void setFormNO(Integer formNO) {
		this.formNO = formNO;
	}
	public String gethForm() {
		return hForm;
	}
	public void sethForm(String hForm) {
		this.hForm = hForm;
	}
}
