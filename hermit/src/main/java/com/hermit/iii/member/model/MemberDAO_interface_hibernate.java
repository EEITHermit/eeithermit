package com.hermit.iii.member.model;

import java.util.*;

public interface MemberDAO_interface_hibernate {

	public void insert(MemberVO_hibernate memberVO_hibernate);

	public void update(MemberVO_hibernate memberVO_hibernate);

	public void delete(Integer memNO);

	public MemberVO_hibernate findByPrimaryKey(Integer memNO);

	public Set<MemberVO_hibernate> getAll();

	/**** 自訂指令 ****/

	public ArrayList<MemberVO> autoCompleteM(String name);

	public void checkInfraction(Integer memNO);

	public Integer infractPlus1(Integer memNO);

	// SMS更新會員狀態
	public void update_MemStatusByMemTel(MemberVO memberVO);

	// Image查詢(讀取)
	public String find_MemImageByMemNO(Integer memNO);

	// AJAX 帳號檢查
	public String count_MemAccount_AJAX(String memAccount);

	// 驗證帳號是否存在
	public MemberVO findByAccount(String memAccount);

	// 忘記帳號查詢
	public MemberVO findByTel(String memTel);

	// 忘記密碼查詢
	public MemberVO findByEmail(String memEmail);
}