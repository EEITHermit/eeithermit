package com.hermit.iii.teamArea.model;

import java.io.Serializable;
//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;

public class TeamAreaVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer businNO;
	private Integer cityNO;
	private Integer boroughNO;
	
	public TeamAreaVO(){
		
	}
	public Integer getBusinNO() {
		return businNO;
	}
	public void setBusinNO(Integer businNO) {
		this.businNO = businNO;
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
	//重新定義equal()、hasCode()
//	public boolean equals(Object obj){
//		if(obj==this){
//			return true;	
//		}
//		if(!(obj instanceof TeamAreaVO)){
//			return false;
//		}
//		TeamAreaVO vo=(TeamAreaVO)obj;
//		return new EqualsBuilder()
//				.append(this.businNO,vo.getBusinNO())
//				.append(this.cityNO,vo.getCityNO())
//				.isequals();
//	}
//	public int hashCode(){
//		return new HashCodeBuilder()
//				.append(this.businNO)
//				.append(this.cityNO)
//				.toHashCode();
//	}		
//	
}
