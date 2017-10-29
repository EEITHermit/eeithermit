package com.hermit.iii.admanager.model;

import java.sql.Date;

public class ADManagerVO_original {
	private Integer adNO; //廣告編號
	private String adImage; //廣告圖片
	private String adLink; //廣告超連結
	private String adMessage; //廣告訊息
	private Date adTimeStart; //廣告起始時間
	private Date adTimeEnd; //廣告結束時間
	private boolean adStatus; //廣告狀態(已上架,已下架)
	private Integer adBrowse; //廣告瀏覽次數
	private Integer adModify; //廣告最後修改人
	public Integer getAdNO() {
		return adNO;
	}
	public void setAdNO(Integer adNO) {
		this.adNO = adNO;
	}
	public String getAdImage() {
		return adImage;
	}
	public void setAdImage(String adImage) {
		this.adImage = adImage;
	}
	public String getAdLink() {
		return adLink;
	}
	public void setAdLink(String adLink) {
		this.adLink = adLink;
	}
	public String getAdMessage() {
		return adMessage;
	}
	public void setAdMessage(String adMessage) {
		this.adMessage = adMessage;
	}
	public Date getAdTimeStart() {
		return adTimeStart;
	}
	public void setAdTimeStart(Date adTimeStart) {
		this.adTimeStart = adTimeStart;
	}
	public Date getAdTimeEnd() {
		return adTimeEnd;
	}
	public void setAdTimeEnd(Date adTimeEnd) {
		this.adTimeEnd = adTimeEnd;
	}
	public boolean getAdStatus() {
		return adStatus;
	}
	public void setAdStatus(boolean adStatus) {
		this.adStatus = adStatus;
	}
	public Integer getAdBrowse() {
		return adBrowse;
	}
	public void setAdBrowse(Integer adBrowse) {
		this.adBrowse = adBrowse;
	}
	public Integer getAdModify() {
		return adModify;
	}
	public void setAdModify(Integer adModify) {
		this.adModify = adModify;
	}
	
}