package com.hermit.iii.favorite.model;

import java.sql.*;

public class FavoriteVO implements java.io.Serializable {
	private Integer favNO;
	private Integer memNO;
	private Integer houseNO;
	private Date favDate;

	public Integer getFavNO() {
		return favNO;
	}

	public void setFavNO(Integer favNO) {
		this.favNO = favNO;
	}

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

	public Date getFavDate() {
		return favDate;
	}

	public void setFavDate(Date favDate) {
		this.favDate = favDate;
	}
}