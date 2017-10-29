package com.hermit.iii.post.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.hermit.iii.emp.model.EmpVO;

public class PostVO implements Serializable {

	private Integer postNO;
	private String postName;
	private Set<EmpVO> emps = new HashSet<EmpVO>();

	public Integer getPostNO() {
		return postNO;
	}

	public void setPostNO(Integer postNO) {
		this.postNO = postNO;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public Set<EmpVO> getEmps() {
		return emps;
	}

	public void setEmps(Set<EmpVO> emps) {
		this.emps = emps;
	}

}
