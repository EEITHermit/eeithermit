package com.hermit.iii.teamArea.model;

import java.io.Serializable;
//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;

import com.hermit.iii.boroughs.model.BoroughsVO;
import com.hermit.iii.teammemberlist.model.TeamMemberListVO;

public class TeamAreaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private TeamMemberListVO teamMemberListVO;
	private Integer cityNO;
	private BoroughsVO BoroughVO;

	public TeamMemberListVO getTeamMemberListVO() {
		return teamMemberListVO;
	}

	public void setTeamMemberListVO(TeamMemberListVO teamMemberListVO) {
		this.teamMemberListVO = teamMemberListVO;
	}

	public Integer getCityNO() {
		return cityNO;
	}

	public void setCityNO(Integer cityNO) {
		this.cityNO = cityNO;
	}
	public BoroughsVO getBoroughVO() {
		return BoroughVO;
	}
	public void setBoroughVO(BoroughsVO boroughVO) {
		BoroughVO = boroughVO;
	}

	// 重新定義equal()、hasCode()
	// public boolean equals(Object obj){
	// if(obj==this){
	// return true;
	// }
	// if(!(obj instanceof TeamAreaVO)){
	// return false;
	// }
	// TeamAreaVO vo=(TeamAreaVO)obj;
	// return new EqualsBuilder()
	// .append(this.businNO,vo.getBusinNO())
	// .append(this.cityNO,vo.getCityNO())
	// .isequals();
	// }
	// public int hashCode(){
	// return new HashCodeBuilder()
	// .append(this.businNO)
	// .append(this.cityNO)
	// .toHashCode();
	// }
	//
}
