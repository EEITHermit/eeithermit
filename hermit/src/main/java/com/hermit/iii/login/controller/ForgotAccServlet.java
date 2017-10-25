package com.hermit.iii.login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hermit.iii.login.model.EmailUtils;
import com.hermit.iii.member.model.*;

@WebServlet("/Login/forgotacc.do")
public class ForgotAccServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String findByTel = request.getParameter("findByTel");
		String findByEmail = request.getParameter("findByEmail");
		MemberJNDIDAO dao = new MemberJNDIDAO();
		MemberVO vo = dao.findByTel(findByTel);

		if (vo == null) {
			request.setAttribute("TelerrorMsg", findByTel + "不是註冊的電話！");
			request.getRequestDispatcher("/MemberLogin/ForgotAcc.jsp").forward(request, response);
			return;
		}

		if (vo.getMemEmail() == null) {
			request.setAttribute("MailerrorMsg", findByEmail + "不存在！");
			request.getRequestDispatcher("/MemberLogin/ForgotAcc.jsp").forward(request, response);
			return;
		}

		// 發送帳號資訊到會員信箱
		EmailUtils.sendAccountInformationEmail(vo);
		request.setAttribute("sendMailMsg", "已成功發送到" + vo.getMemEmail() + "信箱裡面，請您到此信箱收信。");
		request.getRequestDispatcher("/MemberLogin/ForgotPwdSuccess.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest requeset, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(requeset, response);
	}

}
