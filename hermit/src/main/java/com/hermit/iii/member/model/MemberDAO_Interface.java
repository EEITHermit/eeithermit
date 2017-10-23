package com.hermit.iii.member.model;

import java.io.*;
import java.util.*;

public interface MemberDAO_Interface {
	
	public void insert(MemberVO memberVO);

	public void update(MemberVO memberVO);

	public void delete(Integer mseqno);

	public MemberVO findByPrimaryKey(Integer memNO);

	public List<MemberVO> getAll();
	
	public ArrayList<MemberVO> autoCompleteM(String name);
	
	public void checkInfraction(Integer memNO);
	
	public Integer infractPlus1(Integer memNO);

}
