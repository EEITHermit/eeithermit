package com.hermit.iii.post.model;

import java.io.Serializable;

public class PostVO implements Serializable {

	private Integer postNO;
	private String postName;

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

}
