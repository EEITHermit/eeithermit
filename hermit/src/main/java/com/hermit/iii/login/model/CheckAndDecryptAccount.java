package com.hermit.iii.login.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hermit.iii.member.model.MemberJNDIDAO;
import com.hermit.iii.member.model.MemberVO;
import com.hermit.iii.util.CipherUtils;
import sun.misc.BASE64Decoder;

@WebServlet("/Login/checkaccount.do")
public class CheckAndDecryptAccount extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String account = request.getParameter("account"); // 接收到BASE64編碼的帳號資訊
		String memAccount = decryptAccount(account); // BASE64解碼後再解密的帳號

		MemberJNDIDAO dao = new MemberJNDIDAO();
		MemberVO memVO = dao.findByAccount(memAccount);

		if (memAccount.equals(memVO.getMemAccount())) {
			request.setAttribute("memAccount", memAccount);
			request.getRequestDispatcher("/MemberLogin/ResetPassword.jsp?account=" + memAccount).forward(request,
					response);
			return;
		}
		if (!memAccount.equals(memVO.getMemAccount())) {
			request.setAttribute("accountError", "此帳號不存在");
			request.getRequestDispatcher("/MemberLogin/AccountErr.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private static String decryptAccount(String account) {

		String encryptBASE64 = account;
		String key = "comhermitiiieeit";
		byte[] decryptBASE64 = null; // 進行BASE64解碼
		String decryptAccount = ""; // BASE64解碼後再解密

		try {
			decryptBASE64 = CheckAndDecryptAccount.decryptBASE64(encryptBASE64);
			decryptAccount = CipherUtils.decryptString(key, new String(decryptBASE64));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptAccount;
	}

	public static byte[] decryptBASE64(String text) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(text);
	}
}
