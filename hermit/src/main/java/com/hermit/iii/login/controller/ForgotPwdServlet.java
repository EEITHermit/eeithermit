package com.hermit.iii.login.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hermit.iii.login.model.EmailUtils;
import com.hermit.iii.member.model.*;

@WebServlet("/Login/forgotpwd.do")
public class ForgotPwdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String findByAccount = request.getParameter("account");
		PrintWriter out = response.getWriter();

		// 為方便一般應用程式main方的測試,所以底下的model-config1內部dataSource設定是採用org.springframework.jdbc.datasource.DriverManagerDataSource
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-model-JDBCcfg.xml");
		// 建立DAO物件
		MemberDAO_interface_hibernate daSP = (MemberDAO_interface_hibernate) context.getBean("memDAO");
		MemberVO vo = daSP.findByAccount(findByAccount);

		if (vo.getMemEmail() == null) {
			out.print("此帳號不存在！");
			return;
		} else {
			// 發送重置密碼的連結
			EmailUtils.sendResetPasswordEmail(vo);
			out.print("已成功發送到" + vo.getMemEmail() + "信箱裡面，請您到此信箱收信。");
			return;
		}
	}

	protected void doPost(HttpServletRequest requeset, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(requeset, response);
	}
}
