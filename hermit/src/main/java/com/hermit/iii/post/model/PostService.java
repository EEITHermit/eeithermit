package com.hermit.iii.post.model;

import java.util.List;

public class PostService {
	
	private PostDAO_interface dao;
	
	public PostService(){
		dao = new PostDAO_JNDI();
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
	
	public List<PostVO> getALL(){
		return dao.getAll();
	}
}
