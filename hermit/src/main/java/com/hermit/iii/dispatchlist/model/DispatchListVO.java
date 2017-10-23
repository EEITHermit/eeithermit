package com.hermit.iii.dispatchlist.model;
import java.sql.Date;

public class DispatchListVO {

	private Integer dlNo;
	private Integer dempNo;
	private Integer aempNo;
	private Integer qaNo;
	private Date dlStime;
	private Date dlEtime;
	private String elesign;
	private String dlNote;
	public Integer getDlNo() {
		return dlNo;
	}
	public void setDlNo(Integer dlNo) {
		this.dlNo = dlNo;
	}
	public Integer getDempNo() {
		return dempNo;
	}
	public void setDempNo(Integer dempNo) {
		this.dempNo = dempNo;
	}
	public Integer getAempNo() {
		return aempNo;
	}
	public void setAempNo(Integer aempNo) {
		this.aempNo = aempNo;
	}
	public Integer getQaNo() {
		return qaNo;
	}
	public void setQaNo(Integer qaNo) {
		this.qaNo = qaNo;
	}
	public Date getDlStime() {
		return dlStime;
	}
	public void setDlStime(Date dlStime) {
		this.dlStime = dlStime;
	}
	public Date getDlEtime() {
		return dlEtime;
	}
	public void setDlEtime(Date dlEtime) {
		this.dlEtime = dlEtime;
	}
	public String getElesign() {
		return elesign;
	}
	public void setElesign(String elesign) {
		this.elesign = elesign;
	}
	public String getDlNote() {
		return dlNote;
	}
	public void setDlNote(String dlNote) {
		this.dlNote = dlNote;
	}
	
}
