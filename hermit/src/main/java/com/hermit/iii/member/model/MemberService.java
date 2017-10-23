package com.hermit.iii.member.model;

import java.sql.Blob;
import java.util.ArrayList;

public class MemberService {
	private MemberDAO_Interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO insert(String memTel, String memAccount, String memPwd, String memName, String memGender,
			String memEmail, String MemStatus, String memStatus, Integer memInfract) {
		MemberVO memberVO1 = new MemberVO();
		memberVO1.setMemTel(memTel);
		memberVO1.setMemAccount(memAccount);
		memberVO1.setMemPwd(memPwd);
		memberVO1.setMemName(memName);
		memberVO1.setMemGender(memGender);
		memberVO1.setMemEmail(memEmail);
		memberVO1.setMemStatus(memStatus);
		memberVO1.setMemInfract(memInfract);
		// memberVO1.setMemImage(memImage);
		dao.insert(memberVO1);
		return memberVO1;
	}

	public MemberVO update(Integer memNO, String memTel, String memAccount, String memPwd, String memName,
			String memGender, String memEmail, String memStatus, Integer memInfract,String memImage) {
		MemberVO memberVO1 = new MemberVO();
		memberVO1.setMemNO(memNO);
		memberVO1.setMemTel(memTel);
		memberVO1.setMemAccount(memAccount);
		memberVO1.setMemPwd(memPwd);
		memberVO1.setMemName(memName);
		memberVO1.setMemGender(memGender);
		memberVO1.setMemEmail(memEmail);
		memberVO1.setMemStatus(memStatus);
		memberVO1.setMemInfract(memInfract);
		 memberVO1.setMemImage(memImage);
		dao.update(memberVO1);
		return dao.findByPrimaryKey(memNO);
	}

	public MemberVO findByPrimaryKey(Integer memNO) {

		return dao.findByPrimaryKey(memNO);

	}
	
	public ArrayList<MemberVO> autoCompleteM(String name){
		return dao.autoCompleteM(name);
	}
	
	public void checkInfraction(Integer memNO){
		dao.checkInfraction(memNO);
	}
	
	public Integer infractPlus1(Integer memNO){
		return dao.infractPlus1(memNO);
	}
}
