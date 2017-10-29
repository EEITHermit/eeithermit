package com.hermit.iii.emp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import com.hermit.iii.post.model.PostVO;
import com.hermit.iii.teammemberlist.model.TeamMemberListVO;

public class EmpVO implements Serializable {

	private Integer empNO;
	private String empAccount;
	private String empPwd;
	private String empPhone;
	private String empName;
	private PostVO postVO;
	private Boolean empStatus;
	private Set<TeamMemberListVO> teammemberlists = new HashSet<TeamMemberListVO>();

	public Integer getEmpNO() {
		return empNO;
	}

	public void setEmpNO(Integer empNO) {
		this.empNO = empNO;
	}

	public String getEmpAccount() {
		return empAccount;
	}

	public void setEmpAccount(String empAccount) {
		this.empAccount = empAccount;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public PostVO getPostVO() {
		return postVO;
	}

	public void setPostVO(PostVO postVO) {
		this.postVO = postVO;
	}

	public Boolean getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(Boolean empStatus) {
		this.empStatus = empStatus;
	}

	public Set<TeamMemberListVO> getTeammemberlists() {
		return teammemberlists;
	}

	public void setTeammemberlists(Set<TeamMemberListVO> teammemberlists) {
		this.teammemberlists = teammemberlists;
	}

}
