package com.hermit.iii.emp.model;

import java.io.Serializable;

public class EmpVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer empNO;
	private String empAccount;
	private String empPwd;
	private String empPhone;
	private String empName;
	private Integer postNO;
	private boolean empStatus;
	public Integer getEmpNO() {
		return empNO;
	}
	public void setEmpNO(Integer empNO) {
		this.empNO = empNO;
	}
	public String getEmpAccount() {
		return empAccount;
	}
	public void setEmpAccount(String empAccount) {
		this.empAccount = empAccount;
	}
	public String getEmpPwd() {
		return empPwd;
	}
	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getPostNO() {
		return postNO;
	}
	public void setPostNO(Integer postNO) {
		this.postNO = postNO;
	}
	public boolean isEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(boolean empStatus) {
		this.empStatus = empStatus;
	}
	
	
}
