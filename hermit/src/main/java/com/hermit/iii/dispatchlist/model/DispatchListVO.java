package com.hermit.iii.dispatchlist.model;
import java.io.Serializable;
import java.sql.Date;

public class DispatchListVO implements Serializable{

	private Integer dlNO;
	private Integer dempNO;
	private Integer aempNO;
	private Integer qaNO;
	private Date dlStime;
	private Date dlEtime;
	private String elesign;
	private String dlNote;
	public Integer getDlNO() {
		return dlNO;
	}
	public void setDlNO(Integer dlNO) {
		this.dlNO = dlNO;
	}
	public Integer getDempNO() {
		return dempNO;
	}
	public void setDempNO(Integer dempNO) {
		this.dempNO = dempNO;
	}
	public Integer getAempNO() {
		return aempNO;
	}
	public void setAempNO(Integer aempNO) {
		this.aempNO = aempNO;
	}
	public Integer getQaNO() {
		return qaNO;
	}
	public void setQaNO(Integer qaNO) {
		this.qaNO = qaNO;
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
