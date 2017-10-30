package com.hermit.iii.favorite.model;

import java.io.Serializable;
import java.sql.*;

import com.hermit.iii.house.model.*;
import com.hermit.iii.member.model.*;

public class FavoriteVO implements Serializable {
	private Integer favNO;
	// JDBC與JNDI無法使用
	private MemberVO memberVO;
	// JDBC與JNDI無法使用
	private HouseVO houseVO;
	private Date favDate;

	public Integer getFavNO() {
		return favNO;
	}

	public void setFavNO(Integer favNO) {
		this.favNO = favNO;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public HouseVO getHouseVO() {
		return houseVO;
	}

	public void setHouseVO(HouseVO houseVO) {
		this.houseVO = houseVO;
	}

	public Date getFavDate() {
		return favDate;
	}

	public void setFavDate(Date favDate) {
		this.favDate = favDate;
	}
}