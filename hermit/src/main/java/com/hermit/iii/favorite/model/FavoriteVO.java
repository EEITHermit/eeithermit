package com.hermit.iii.favorite.model;

public class FavoriteVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer memNO;
	private Integer houseNO;

	public Integer getMemNO() {
		return memNO;
	}

	public void setMemNO(Integer memNO) {
		this.memNO = memNO;
	}

	public Integer getHouseNO() {
		return houseNO;
	}

	public void setHouseNO(Integer houseNO) {
		this.houseNO = houseNO;
	}
}