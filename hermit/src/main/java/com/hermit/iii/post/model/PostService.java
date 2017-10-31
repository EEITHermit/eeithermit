package com.hermit.iii.post.model;

import java.util.Set;

public class PostService {
	
	private PostDAO_interface_hibernate dao;
	
	public PostService(){
		dao = new PostDAO_hibernate();
	}
	
	public void insertPost(String postName){
		PostVO postVO = new PostVO();
		postVO.setPostName(postName);
		dao.insert(postVO);
	}
	
	public void updatePost(Integer postNO , String postName){
		PostVO postVO = new PostVO();
		postVO.setPostNO(postNO);
		postVO.setPostName(postName);
		dao.update(postVO);
	}
	
	public void deletePost(Integer postNO){
		dao.delete(postNO);
	}
	
	public PostVO getOnePost(Integer postNO){
		return dao.findByPrimaryKey(postNO);
	}
	
	public Set<PostVO> getALL(){
		return dao.getAll();
	}
}
