package com.hermit.iii.sms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hermit.iii.member.model.MemberDAO_hibernate;
import com.hermit.iii.member.model.MemberDAO_interface_hibernate;
import com.hermit.iii.member.model.MemberService;
import com.hermit.iii.member.model.MemberVO;
import com.hermit.iii.util.SendBySMS;

public class SMSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String smscode = "";

		HttpSession session = request.getSession();

		String action = request.getParameter("action");
		String memAccount = request.getParameter("memAccount");
		System.out.println(memAccount);
		if ("register_SMS_Action".equals(action)) {
			// 準備存放錯誤訊息的Map物件
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
			request.setAttribute("ErrorMsgKey", errorMsgMap);

			smscode = (String) session.getAttribute("SMScode");
			Map<String, String> BearerMsg = (Map<String, String>) session.getAttribute("BearerMsgKey");

			String identity = null;
			try {
				identity = BearerMsg.get("Identity");
			} catch (NullPointerException e) {
				identity = "無驗證";
			}

			try {
				/**** 1.接收請求參數 - 輸入格式的錯誤處理 ****/
				String code = request.getParameter("code");
				if (code == null || code.trim().length() == 0) {
					errorMsgMap.put("CodeEmptyError", "請勿空白");
				}

				String codeReg = "^[(a-zA-Z0-9)]{6}$";
				if (!code.trim().matches(codeReg)) {
					errorMsgMap.put("CodeFormatError", "請輸入正確驗證碼");
				} else {
					if (!code.trim().equals(smscode.trim())) {
						errorMsgMap.put("CodeFormatError", "請輸入正確驗證碼");
					}
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgMap.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/register/register_notice_page.jsp");
					failureView.forward(request, response);
					return;
				}
				/**** 2.開始處理資料 ****/
				String memTel = (String) session.getAttribute("telholder");
				MemberService memberSvc = new MemberService();
				if ("google".equals(identity))
					memberSvc.updateStatusByTel(memTel, "Google驗證");
				else if ("facebook".equals(identity))
					memberSvc.updateStatusByTel(memTel, "FB驗證");
				else
					memberSvc.updateStatusByTel(memTel, "一般會員驗證");

				// 註冊完直接登入
				// 為方便一般應用程式main方的測試,所以底下的model-config1內部dataSource設定是採用org.springframework.jdbc.datasource.DriverManagerDataSource
				ApplicationContext context = new ClassPathXmlApplicationContext("Spring-model-JDBCcfg.xml");
				// 建立DAO物件
				MemberDAO_interface_hibernate daSP = (MemberDAO_interface_hibernate) context.getBean("memDAO");
				MemberVO vo = daSP.findByAccount(memAccount);
				session.setAttribute("LoginOK", vo);

				/**** 3.處理完成 ****/
				response.sendRedirect("register/register_success_page.jsp");
			} catch (Exception e) {
				errorMsgMap.put("Exception", e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/register/register_notice_page.jsp");
				failureView.forward(request, response);
			}
		}

		if ("register_reSMS_Action".equals(action)) {
			// 準備存放錯誤訊息的Map物件
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
			request.setAttribute("ErrorMsgKey", errorMsgMap);

			try {
				/**** 1.接收請求參數 - 輸入格式的錯誤處理 ****/
				/**** 2.開始處理資料 ****/
				String memTel = (String) session.getAttribute("telholder");
				smscode = new SendBySMS().Process(memTel); // 傳送簡訊驗證
				session.setAttribute("SMScode", smscode);
				/**** 3.處理完成 ****/
				response.sendRedirect("register/register_notice_page.jsp");
			} catch (Exception e) {
				errorMsgMap.put("Exception", e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/register/register_notice_page.jsp");
				failureView.forward(request, response);
			}
		}
	}
}