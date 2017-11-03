package com.hermit.iii.dispatchlist.model;
import java.io.Serializable;
import java.sql.Date;

import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.qanda.model.QandAVO;

public class DispatchListVO implements Serializable{

	private Integer dlNO;
	private EmpVO dempVO;
	private EmpVO aempVO;
	private QandAVO qaVO;
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
	public EmpVO getDempVO() {
		return dempVO;
	}
	public void setDempVO(EmpVO dempVO) {
		this.dempVO = dempVO;
	}
	public EmpVO getAempVO() {
		return aempVO;
	}
	public void setAempVO(EmpVO aempVO) {
		this.aempVO = aempVO;
	}
	public QandAVO getQaVO() {
		return qaVO;
	}
	public void setQaVO(QandAVO qaVO) {
		this.qaVO = qaVO;
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
