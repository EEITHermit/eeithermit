package com.hermit.iii.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hermit.iii.member.model.*;

@WebServlet("/Login/resetpwd.do")
// 重新設置新密碼
public class ResetPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String memAccount = request.getParameter("account");// 從checkaccount接收帳號資訊
		String newPassword = request.getParameter("newPassword");
		String newPassword2 = request.getParameter("newPassword2");
		Map<String, String> errors = new HashMap<String, String>();

		if (newPassword == null || "".equals(newPassword)) {
			errors.put("newPassword", "新密碼不可為空白！");
		}
		if (newPassword2 == null || "".equals(newPassword)) {
			errors.put("newPassword2", "確認新密碼不可為空白！");
		}
		if (!newPassword.equals(newPassword2)) {
			errors.put("passwordError", "兩次輸入的密碼不一致！");
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/MemberLogin/ResetPassword.jsp?account=" + memAccount).forward(request,
					response);
			return;
		}

		MemberJNDIDAO dao = new MemberJNDIDAO();
		MemberVO memVO = dao.findByAccount(memAccount);// 取得帳號資訊
		memVO.setMemPwd(newPassword);
		dao.update(memVO);

		request.setAttribute("pwdModifyMsg", "密碼修改成功！");
		request.getRequestDispatcher("/MemberLogin/ResetPasswordSuccess.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
