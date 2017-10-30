package com.hermit.iii.house.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import com.hermit.iii.boroughs.model.BoroughsVO;
import com.hermit.iii.city.model.CityVO;
import com.hermit.iii.favorite.model.FavoriteVO;
import com.hermit.iii.houseform.model.HouseFormVO;
import com.hermit.iii.housetype.model.HouseTypeVO;

public class HouseVO  implements Serializable{
	private Integer houseNO;
	private String houseTitle;
	private String previewPic;
	private Integer highestFloor;
	private Integer nowFloor;
	private String houseStatus;
	private Integer houseRent;
	private Integer houseCharge;
	private String waterRate;
	private String powerRate;
	private String houseVideo;
	private String houseAddr;
	private Double houseSize;
	//子傑加
	private CityVO cityVO;
	private BoroughsVO boroughsVO;
	private HouseTypeVO houseTypeVO;
	private HouseFormVO houseFormVO;
	
	private Set<FavoriteVO> favs =new LinkedHashSet<FavoriteVO>();
	
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
	public CityVO getCityVO() {
		return cityVO;
	}
	public void setCityVO(CityVO cityVO) {
		this.cityVO = cityVO;
	}
	public BoroughsVO getBoroughsVO() {
		return boroughsVO;
	}
	public void setBoroughsVO(BoroughsVO boroughsVO) {
		this.boroughsVO = boroughsVO;
	}
	public HouseTypeVO getHouseTypeVO() {
		return houseTypeVO;
	}
	public void setHouseTypeVO(HouseTypeVO houseTypeVO) {
		this.houseTypeVO = houseTypeVO;
	}
	public HouseFormVO getHouseFormVO() {
		return houseFormVO;
	}
	public void setHouseFormVO(HouseFormVO houseFormVO) {
		this.houseFormVO = houseFormVO;
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
	public Set<FavoriteVO> getFavs() {
		return favs;
	}
	public void setFavs(Set<FavoriteVO> favs) {
		this.favs = favs;
	}
}