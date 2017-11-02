package com.hermit.iii.member.model;

import java.util.*;

public class MemberService {

	private MemberDAO_interface_hibernate dao ;

	public MemberService() {
		dao = new MemberDAO_hibernate();
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
			String memGender, String memEmail, String memStatus, Integer memInfract, String memImage) {
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

	// 新增service
	public MemberVO addMember(String memTel, String memAccount, String memPwd, String memName, String memGender,
			String memEmail, java.sql.Date memRegister, String memStatus, Integer memInfract, String memImage) {
		MemberVO memberVO = new MemberVO();

		memberVO.setMemTel(memTel);
		memberVO.setMemAccount(memAccount);
		memberVO.setMemPwd(memPwd);
		memberVO.setMemName(memName);
		memberVO.setMemGender(memGender);
		memberVO.setMemEmail(memEmail);
		memberVO.setMemRegister(memRegister);
		memberVO.setMemStatus(memStatus);
		memberVO.setMemInfract(memInfract);
		memberVO.setMemImage(memImage);

		dao.insert(memberVO);

		return memberVO;
	}

	// 修改service
	public MemberVO updateMember(Integer memNO, String memTel, String memAccount, String memPwd, String memName,
			String memGender, String memEmail, String memStatus, Integer memInfract, String memImage) {
		MemberVO memberVO = new MemberVO();

		memberVO.setMemNO(memNO);
		memberVO.setMemTel(memTel);
		memberVO.setMemAccount(memAccount);
		memberVO.setMemPwd(memPwd);
		memberVO.setMemName(memName);
		memberVO.setMemGender(memGender);
		memberVO.setMemEmail(memEmail);
		memberVO.setMemStatus(memStatus);
		memberVO.setMemInfract(memInfract);
		memberVO.setMemImage(memImage);

		dao.update(memberVO);

		return memberVO;
	}

	// 刪除service
	public void deleteMember(Integer memNO) {
		dao.delete(memNO);
	}

	// 查詢一筆service
	public MemberVO getOneMember(Integer memNO) {
		return dao.findByPrimaryKey(memNO);
	}

	// 查詢全部service
	public Set<MemberVO> getAll() {
		return dao.getAll();
	}

	/**** 自訂指令 ****/

	public ArrayList<MemberVO> autoCompleteM(String name) {
		return dao.autoCompleteM(name);
	}

	public void checkInfraction(Integer memNO) {
		dao.checkInfraction(memNO);
	}

	public Integer infractPlus1(Integer memNO) {
		return dao.infractPlus1(memNO);
	}

	// SMS更新會員狀態
	public MemberVO updateStatusByTel(String memTel, String memStatus) {
		MemberVO memberVO = new MemberVO();

		memberVO.setMemTel(memTel);
		memberVO.setMemStatus(memStatus);

		dao.update_MemStatusByMemTel(memberVO);

		return memberVO;
	}

	// Image查詢(讀取)
	public String findImageByNO(Integer memNO) {
		return dao.find_MemImageByMemNO(memNO);
	}

	// AJAX 帳號檢查
	public String checkAccountAJAX(String memAccount) {
		return dao.count_MemAccount_AJAX(memAccount);
	}
	
	
}
