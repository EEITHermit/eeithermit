package com.hermit.iii.teamArea.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hermit.iii.boroughs.model.BoroughsVO;
import com.hermit.iii.businTeam.model.BusinTeamVO;

public class TeamAreaVO implements Serializable{
	private BusinTeamVO businTeamVO;
	private Integer cityNO;
	private BoroughsVO boroughsVO;
	
	public BusinTeamVO getBusinTeamVO() {
		return businTeamVO;
	}
	public void setBusinTeamVO(BusinTeamVO businTeamVO) {
		this.businTeamVO = businTeamVO;
	}

	public Integer getCityNO() {
		return cityNO;
	}

	public void setCityNO(Integer cityNO) {
		this.cityNO = cityNO;
	}
	public BoroughsVO getBoroughsVO() {
		return boroughsVO;
	}
	public void setBoroughsVO(BoroughsVO boroughsVO) {
		this.boroughsVO = boroughsVO;
	}
	
	
	//重新定義equal()、hasCode()
	public boolean equals(Object obj){
		if(obj==this){
			return true;	
		}
		if(!(obj instanceof TeamAreaVO)){
			return false;
		}
		TeamAreaVO vo=(TeamAreaVO)obj;
		return new EqualsBuilder()
				.append(this.businTeamVO,vo.getBusinTeamVO())
				.append(this.boroughsVO,vo.getBoroughsVO())
				.isEquals();
	}
	public int hashCode(){
		return new HashCodeBuilder()
				.append(this.businTeamVO)
				.append(this.boroughsVO)
				.toHashCode();
	}		
	
}
