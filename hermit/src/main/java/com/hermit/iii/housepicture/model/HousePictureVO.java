package com.hermit.iii.housepicture.model;

public class HousePictureVO {
	private Integer housePictureNO;
	private byte[] hPicture;
	private Integer houseNO;
	public Integer getHousePictureNO() {
		return housePictureNO;
	}
	public void setHousePictureNO(Integer housePictureNO) {
		this.housePictureNO = housePictureNO;
	}
	public byte[] gethPicture() {
		return hPicture;
	}
	public void sethPicture(byte[] hPicture) {
		this.hPicture = hPicture;
	}
	public Integer getHouseNO() {
		return houseNO;
	}
	public void setHouseNO(Integer houseNO) {
		this.houseNO = houseNO;
	}
}
