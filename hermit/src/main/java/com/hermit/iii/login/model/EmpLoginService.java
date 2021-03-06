package com.hermit.iii.login.model;

import com.hermit.iii.emp.model.*;
import com.hermit.iii.util.SecurityCipher;

public class EmpLoginService {

	private EmpDAO_interface_hibernate dao;
	private EmpDAO_interface_hibernate daoSP;

	public EmpLoginService() {
		dao = new EmpDAO_hibernate();
	}

	public EmpVO check(String account, String pwd) {
		EmpVO vo = dao.findByAccount(account);
		// 將取得密碼加密後跟資料庫內的密碼進行比對
		String ercryptPwd = SecurityCipher.encryptString(pwd);
		if (vo != null && ercryptPwd.equals(vo.getEmpPwd())) {
			return vo;
		}
		return null;
	}
}
