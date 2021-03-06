package com.hermit.iii.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hermit.iii.login.model.MemLoginService;
import com.hermit.iii.member.model.*;

@WebServlet("/Login/memlogin.do")
public class MemLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		String errMsg = "";// 儲存錯誤訊息
		String account = request.getParameter("account");
		String pwd = request.getParameter("pwd");
		String flag = request.getParameter("remember");
		String action = request.getParameter("action");
		String requestHeader = request.getHeader("Referer");

		if ("login".equals(action)) {
			// 驗證碼
			String rand = session.getAttribute("code").toString();
			String input = request.getParameter("code");

			if (account == null || account.trim().length() == 0) {
				errMsg += "1.*帳號必須輸入;";
			} else {
				request.setAttribute("setacc", account);
			}

			if (pwd == null || pwd.trim().length() == 0) {
				errMsg += "2.*密碼必須輸入;";
			} else {
				request.setAttribute("setpwd", pwd);
			}

			if (input.length() == 0) {
				errMsg += "3.*驗證碼必須輸入;";

			} else {
				if (!rand.equals(input)) {
					errMsg += "4.*驗證碼錯誤;";
				}
			}

			if (errMsg.length() != 0) {
				out.print(errMsg);
				return;
			}

			// LoginService類別new為物件，存放參考的變數為ls
			MemLoginService ls = new MemLoginService();
			// 呼叫 ls物件的 check()，要記得傳入accont與pwd兩個參數
			// 同時將傳回值放入MemVO型別的變數vo之內。
			MemberVO vo = ls.check(account, pwd);
			if (vo != null) {
				if (vo.getMemStatus().equals("黑名單會員")) {
					errMsg += "5.*您的帳號已達違規次數不能使用;";
					out.println(errMsg);
					return;
				} else if (vo.getMemStatus().equals("未驗證會員")) {
					errMsg += ("6.您的帳號未完成手機驗證，現在會再傳一組新的驗證碼至您的手機，請先完成驗證。");
					out.println(errMsg);
					return;
				}
				session.setAttribute("LoginOK", vo);
			} else {
				errMsg += "5.*該帳號不存在或密碼錯誤;";
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
				out.print("ok*" + requestHeader);
				return;
			} else {
				out.print(errMsg);
				return;
			}
		} else if ("check".equals(action)) {
			Object s = session.getAttribute("LoginOK");
			if (s != null) {
				out.print("OK");
				return;
			} else {
				out.print("NO");
				return;
			}
		}
	}
}
