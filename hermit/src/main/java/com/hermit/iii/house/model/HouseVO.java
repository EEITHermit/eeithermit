package com.hermit.iii.house.model;

import java.io.Serializable;

public class HouseVO  implements Serializable{
	private Integer houseNO;
	private String houseTitle;
	private Integer cityNO;
	private Integer boroughNO;
	private String previewPic;
	private Integer highestFloor;
	private Integer nowFloor;
	private String houseStatus;
	private Integer houseRent;
	private Integer houseCharge;
	private String waterRate;
	private String powerRate;
	private String houseVideo;
	private Integer typeNO;
	private Integer formNO;
	private String houseAddr;
	private Double houseSize;

	public Integer getHouseNO() {
		return houseNO;
	}
	public void setHouseNO(Integer houseNO) {
		this.houseNO = houseNO;
	}
	public String getHouseTitle() {
		return houseTitle;
	}
	public void setHouseTitle(String houseTitle) {
		this.houseTitle = houseTitle;
	}
	public Integer getCityNO() {
		return cityNO;
	}
	public void setCityNO(Integer cityNO) {
		this.cityNO = cityNO;
	}
	public Integer getBoroughNO() {
		return boroughNO;
	}
	public void setBoroughNO(Integer boroughNO) {
		this.boroughNO = boroughNO;
	}
	public Integer getHighestFloor() {
		return highestFloor;
	}
	public void setHighestFloor(Integer highestFloor) {
		this.highestFloor = highestFloor;
	}
	public Integer getNowFloor() {
		return nowFloor;
	}
	public void setNowFloor(Integer nowFloor) {
		this.nowFloor = nowFloor;
	}
	public String getHouseStatus() {
		return houseStatus;
	}
	public void setHouseStatus(String houseStatus) {
		this.houseStatus = houseStatus;
	}
	public Integer getHouseRent() {
		return houseRent;
	}
	public void setHouseRent(Integer houseRent) {
		this.houseRent = houseRent;
	}
	public Integer getHouseCharge() {
		return houseCharge;
	}
	public void setHouseCharge(Integer houseCharge) {
		this.houseCharge = houseCharge;
	}
	public String getWaterRate() {
		return waterRate;
	}
	public void setWaterRate(String waterRate) {
		this.waterRate = waterRate;
	}
	public String getPowerRate() {
		return powerRate;
	}
	public void setPowerRate(String powerRate) {
		this.powerRate = powerRate;
	}
	public String getHouseVideo() {
		return houseVideo;
	}
	public void setHouseVideo(String houseVideo) {
		this.houseVideo = houseVideo;
	}
	public Integer getTypeNO() {
		return typeNO;
	}
	public void setTypeNO(Integer typeNO) {
		this.typeNO = typeNO;
	}
	public Integer getFormNO() {
		return formNO;
	}
	public void setFormNO(Integer formNO) {
		this.formNO = formNO;
	}
	public String getHouseAddr() {
		return houseAddr;
	}
	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}
	public Double getHouseSize() {
		return houseSize;
	}
	public void setHouseSize(Double houseSize) {
		this.houseSize = houseSize;
	}
	public String getPreviewPic() {
		return previewPic;
	}
	public void setPreviewPic(String previewPic) {
		this.previewPic = previewPic;
	}
}
