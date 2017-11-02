package com.hermit.iii.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmpServlet1 extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		
		
		
		if("insert_emp_action".equals(action)) {
			// 準備存放錯誤訊息的Map物件
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
			req.setAttribute("ErrorMsgKey", errorMsgMap);
			
			String empName = req.getParameter("empName");//員工姓名驗證
			if(empName == null || empName.trim().length() == 0){
				errorMsgMap.put("NameEmptyError", "請勿空白");
			}
			String empAccount = req.getParameter("empAccount");//員工帳號驗證
			if(empAccount ==null || empAccount.trim().length() == 0){
				errorMsgMap.put("empAccount", "請勿空白");
			}
			String accountReg = "^[a-zA-Z0-9]{6,12}$";//員工帳號驗證
			if(!empAccount.trim().matches(accountReg)){
				errorMsgMap.put("AccountFormatError", "請輸入英文、數字 ,且長度必需在6到12之間");
			}
			String empPwd = req.getParameter("empPwd");//員工密碼驗證
			if(empPwd == null || empPwd.trim().length() == 0){
				errorMsgMap.put("PwdEmptyError", "請勿空白");
			}
			String pedReg = "^.{6,12}$";//員工密碼驗證
			if(!empPwd.trim().matches(pedReg)){
				errorMsgMap.put("PwdFormatError", "長度必需在6到12之間");
			}
			String empPhone = req.getParameter("empPhone");//員工電話驗證
			if(empPhone == null || empPhone.trim().length() == 0){
				errorMsgMap.put("empPhoneError", "請勿空白");
			}
			String telReg = "^[(0-9)]{10}";//員工電話驗證{10位}
			if(!empPhone.trim().matches(telReg)){
				errorMsgMap.put("TelFormatError", "請輸入正確格式");
			}
			
//			Integer empStatus = Integer.valueOf(req.getParameter("empStatus"));//員工狀態
			
			String empStatus = req.getParameter("empStatus");
			 empStatus = "0".equals(empStatus) ? "可上班" : "已離職";
			
			
//			if("0".equals(req.getParameter("empStatus")))
//				empStatus = 0;
//			else
//				empStatus = 1;
			
			String postNO = req.getParameter("postNO");
			if("310".equals(postNO))
				postNO = "系統管理員";
			else if("320".equals(postNO))
				postNO = "業務人員";
			else if("330".equals(postNO))
				postNO = "客服人員";
			else
				postNO = "修繕人員";
			
			if (!errorMsgMap.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/empInsert_include.jsp");//尚未輸入
				failureView.forward(req, resp);
				return;
			}
			
		}	
		
		
	}
	
	
}
