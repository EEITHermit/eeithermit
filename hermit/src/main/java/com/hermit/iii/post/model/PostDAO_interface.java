package com.hermit.iii.post.model;

import java.util.List;

public interface PostDAO_interface {
	 public void insert(PostVO postVO);
     public void update(PostVO postVO);
     public void delete(Integer postNO);
     public PostVO findByPrimaryKey(Integer postNO);
     public List<PostVO> getAll();
	
}
