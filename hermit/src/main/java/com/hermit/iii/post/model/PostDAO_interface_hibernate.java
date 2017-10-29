package com.hermit.iii.post.model;

import java.util.Set;

public interface PostDAO_interface_hibernate {

	public void insert(PostVO postVO);

	public void update(PostVO postVO);

	public void delete(Integer postNO);

	public PostVO findByPrimaryKey(Integer postNO);

	public Set<PostVO> getAll();

}
