package com.hermit.iii.member.model;

import java.io.Serializable;
import java.sql.*;

public class MemberVO_original implements Serializable {
	private Integer memNO;
	private String memTel;
	private String memAccount;
	private String memPwd;
	private String memName;
	private String memGender;
	private String memEmail;
	private Date memRegister;
	private String memStatus;
	private Integer memInfract;
	private String memImage;

	public Integer getMemNO() {
		return memNO;
	}

	public void setMemNO(Integer memNO) {
		this.memNO = memNO;
	}

	public String getMemTel() {
		return memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

	public String getMemAccount() {
		return memAccount;
	}

	public void setMemAccount(String memAccount) {
		this.memAccount = memAccount;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemGender() {
		return memGender;
	}

	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public Date getMemRegister() {
		return memRegister;
	}

	public void setMemRegister(Date memRegister) {
		this.memRegister = memRegister;
	}

	public String getMemStatus() {
		return memStatus;
	}

	public void setMemStatus(String memStatus) {
		this.memStatus = memStatus;
	}

	public Integer getMemInfract() {
		return memInfract;
	}

	public void setMemInfract(Integer memInfract) {
		this.memInfract = memInfract;
	}

	public String getMemImage() {
		return memImage;
	}

	public void setMemImage(String memImage) {
		this.memImage = memImage;
	}
}