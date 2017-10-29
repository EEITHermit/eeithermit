package com.hermit.iii.post.model;

import java.util.List;

public interface PostDAO_interface {
	 public void insert(PostVO_original postVO);
     public void update(PostVO_original postVO);
     public void delete(Integer postNO);
     public PostVO_original findByPrimaryKey(Integer postNO);
     public List<PostVO_original> getAll();
	
}
