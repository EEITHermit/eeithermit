package com.hermit.iii.calendar.controller;

import java.io.Serializable;
import java.sql.Timestamp;

public class eventShow implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer empNo;
	private String title;
	private Timestamp start;
	private Timestamp end;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	
	
}
