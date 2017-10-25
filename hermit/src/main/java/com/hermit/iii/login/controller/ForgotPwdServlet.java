package com.hermit.iii.login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hermit.iii.login.model.EmailUtils;
import com.hermit.iii.member.model.*;

@WebServlet("/Login/forgotpwd.do")
public class ForgotPwdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String findByAccount = request.getParameter("findByAccount");
		MemberJNDIDAO dao = new MemberJNDIDAO();
		MemberVO vo = dao.findByAccount(findByAccount);

		if (vo == null) {
			request.setAttribute("errorMsg", "此帳號不存在！");
			request.getRequestDispatcher("/MemberLogin/ForgotPwd.jsp").forward(request, response);
			return;
		}
		// 發送重置密碼的連結
		EmailUtils.sendResetPasswordEmail(vo);
		request.setAttribute("sendMailMsg", "已成功發送到" + vo.getMemEmail() + "信箱裡面，請您到此信箱收信。");
		request.getRequestDispatcher("/MemberLogin/ForgotPwdSuccess.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest requeset, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(requeset, response);
	}

}
