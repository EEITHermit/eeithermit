package com.hermit.iii.login.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hermit.iii.member.model.*;
import com.hermit.iii.util.*;

public class MemLoginService {

	private MemberDAO_interface_hibernate dao;
	private MemberDAO_interface_hibernate daoSP;

	public MemLoginService() {
		dao = new MemberDAO_hibernate();

		// 為方便一般應用程式main方的測試,所以底下的Spring-model-JDBCcfg內部dataSource設定是採用org.springframework.jdbc.datasource.DriverManagerDataSource
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-model-JDBCcfg.xml");
		daoSP = (MemberDAO_interface_hibernate) context.getBean("memDAO");
	}

	public MemberVO check(String account, String pwd) {
		MemberVO vo = daoSP.findByAccount(account);
		// 將取得密碼加密後跟資料庫內的密碼進行比對
		String ercryptPwd = SecurityCipher.encryptString(pwd);

		if (vo != null && ercryptPwd.equals(vo.getMemPwd())) {
			return vo;
		}
		return null;
	}

	public MemberVO OtherCheck(String account, String name) {
		MemberVO vo = daoSP.findByAccount(account);
		if (vo != null && name.equals(vo.getMemName())) {
			return vo;
		}
		return null;
	}
}