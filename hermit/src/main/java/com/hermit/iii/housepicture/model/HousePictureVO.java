package com.hermit.iii.housepicture.model;

public class HousePictureVO {
	private Integer housePictureNO;
	private Byte[] hPicture;
	private Integer houseNO;
	public Integer getHousePictureNO() {
		return housePictureNO;
	}
	public void setHousePictureNO(Integer housePictureNO) {
		this.housePictureNO = housePictureNO;
	}
	public Byte[] gethPicture() {
		return hPicture;
	}
	public void sethPicture(Byte[] hPicture) {
		this.hPicture = hPicture;
	}
	public Integer getHouseNO() {
		return houseNO;
	}
	public void setHouseNO(Integer houseNO) {
		this.houseNO = houseNO;
	}
}
