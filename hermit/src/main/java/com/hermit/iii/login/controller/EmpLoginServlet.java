package com.hermit.iii.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.login.model.EmpLoginService;

@WebServlet("/Login/emplogin.do")
public class EmpLoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		String errMsg = "";// 儲存錯誤訊息
		String account = request.getParameter("account");
		String pwd = request.getParameter("pwd");
		String flag = request.getParameter("remember");

		if (account == null || account.trim().length() == 0) {
			errMsg += "1.帳號必須輸入;";
		}

		if (pwd == null || pwd.trim().length() == 0) {
			errMsg += "2.密碼必須輸入;";
		}

		if (errMsg.length() != 0) {
			out.print(errMsg);
			return;
		}

		// LoginService類別new為物件，存放參考的變數為ls
		EmpLoginService ls = new EmpLoginService();
		// 呼叫 ls物件的 check()，要記得傳入accont與pwd兩個參數
		// 同時將傳回值放入EmpVO型別的變數vo之內。
		EmpVO vo = ls.check(account, pwd);
		if (vo != null) {
			session.setAttribute("LoginOK", vo);
		} else {
			errMsg += "3.該帳號不存在或密碼錯誤;";
		}

		if (errMsg.length() == 0) {
			if ("on".equals(flag)) {
				account = java.net.URLEncoder.encode(account, "UTF-8");
				Cookie accountCookie = new Cookie("account", account);
				Cookie pwdCookie = new Cookie("pwd", pwd);
				Cookie flagCookie = new Cookie("flag", "checked");

				// cookie存活時間
				accountCookie.setMaxAge(5 * 60);
				pwdCookie.setMaxAge(5 * 60);
				flagCookie.setMaxAge(5 * 60);
				// cookie儲存的位置
				accountCookie.setPath(request.getContextPath());
				pwdCookie.setPath(request.getContextPath());
				flagCookie.setPath(request.getContextPath());
				// 儲存cookie
				response.addCookie(accountCookie);
				response.addCookie(pwdCookie);
				response.addCookie(flagCookie);
			} else {
				account = java.net.URLEncoder.encode(account, "UTF-8");
				Cookie accountCookie = new Cookie("account", account);
				Cookie pwdCookie = new Cookie("pwd", pwd);
				Cookie flagCookie = new Cookie("flag", "checked");

				// cookie存活時間
				accountCookie.setMaxAge(0);
				pwdCookie.setMaxAge(0);
				flagCookie.setMaxAge(0);
				// cookie儲存的位置
				accountCookie.setPath(request.getContextPath());
				pwdCookie.setPath(request.getContextPath());
				flagCookie.setPath(request.getContextPath());
				// 儲存cookie
				response.addCookie(accountCookie);
				response.addCookie(pwdCookie);
				response.addCookie(flagCookie);
			}
			out.print("ok");
			return;
		} else {
			out.print(errMsg);
			return;
		}
	}
}
