package com.hermit.iii.login.model;

import com.hermit.iii.member.model.MemberVO;
import com.hermit.iii.util.CipherUtils;

import sun.misc.BASE64Encoder;

public class GeneratePwdLinkUtils {

	// 生成重設密碼連結
	public static String generateResetPwdLink(MemberVO vo) {
		return "http://localhost:8081/hermit/Login/checkaccount.do?account=" + EncryptAccount(vo);
	}

	private static String EncryptAccount(MemberVO vo) {

		String account = vo.getMemAccount(); // 要加密的帳號
		String key = "comhermitiiieeit"; // 加密的key
		String encryptAccount = ""; // 加密後的帳號
		String encryptBASE64 = ""; // 將加密後的帳號轉為BASE64編碼

		try {
			encryptAccount = CipherUtils.encryptString(key, account);
			encryptBASE64 = GeneratePwdLinkUtils.encryptBASE64(encryptAccount.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return encryptBASE64;
	}

	public static String encryptBASE64(byte[] text) {
		return (new BASE64Encoder()).encode(text);
	}
}
