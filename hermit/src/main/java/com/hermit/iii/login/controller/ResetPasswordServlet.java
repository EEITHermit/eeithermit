package com.hermit.iii.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

		// 為方便一般應用程式main方的測試,所以底下的model-config1內部dataSource設定是採用org.springframework.jdbc.datasource.DriverManagerDataSource
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-model-JDBCcfg.xml");
		// 建立DAO物件
		MemberDAO_interface_hibernate daSP = (MemberDAO_interface_hibernate) context.getBean("memDAO");
		MemberVO memVO = daSP.findByAccount(memAccount);// 取得帳號資訊
		memVO.setMemPwd(newPassword);
		daSP.update(memVO);
		// request.setAttribute("pwdModifyMsg", "密碼修改成功！");
		// request.getRequestDispatcher("/index.jsp").forward(request,
		// response);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
