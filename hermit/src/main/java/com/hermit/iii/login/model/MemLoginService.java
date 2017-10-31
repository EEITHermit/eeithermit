package com.hermit.iii.login.model;

import com.hermit.iii.member.model.*;
import com.hermit.iii.util.*;

public class MemLoginService {

	public MemberVO check(String account, String pwd) {

		MemberDAO_hibernate dao = new MemberDAO_hibernate();
		MemberVO vo = dao.findByAccount(account);
		// 將取得密碼加密後跟資料庫內的密碼進行比對
		String ercryptPwd = SecurityCipher.encryptString(pwd);

		if (vo != null && ercryptPwd.equals(vo.getMemPwd())) {
			return vo;
		}
		return null;
	}

	public MemberVO OtherCheck(String account, String name) {

		MemberDAO_hibernate dao = new MemberDAO_hibernate();
		MemberVO vo = dao.findByAccount(account);

		if (vo != null && name.equals(vo.getMemName())) {
			return vo;
		}
		return null;
	}
}