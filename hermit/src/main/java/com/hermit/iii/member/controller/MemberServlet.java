package com.hermit.iii.member.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hermit.iii.member.model.*;
import com.hermit.iii.util.*;

public class MemberServlet extends HttpServlet {
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
		PrintWriter out = response.getWriter();
		// session物件來存放共用資料。
		HttpSession session = request.getSession();

		String action = request.getParameter("action");

		if ("register_insert_Action".equals(action)) {
			// 準備存放錯誤訊息的Map物件
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
			request.setAttribute("ErrorMsgKey", errorMsgMap);

			try {
				/**** 1.接收請求參數 - 輸入格式的錯誤處理 ****/
				String memTel = request.getParameter("memTel");
				if (memTel == null || memTel.trim().length() == 0) {
					errorMsgMap.put("TelEmptyError", "請勿空白");
				}

				String telReg = "^[(0-9)]{10}$";
				if (!memTel.trim().matches(telReg)) {
					errorMsgMap.put("TelFormatError", "請輸入正確格式");
				}

				String memAccount = request.getParameter("memAccount");
				if (memAccount == null || memAccount.trim().length() == 0) {
					errorMsgMap.put("AccountEmptyError", "請勿空白");
				}

				String accountReg = "^[(a-zA-Z0-9)]{6,12}$";
				if (!memAccount.trim().matches(accountReg)) {
					errorMsgMap.put("AccountFormatError", "請輸入英文、數字 ,且長度必需在6到12之間");
				}

				String memPwd = request.getParameter("memPwd");
				if (memPwd == null || memPwd.trim().length() == 0) {
					errorMsgMap.put("PwdEmptyError", "請勿空白");
				}

				String pwdReg = "^.{6,12}$";
				if (!memPwd.trim().matches(pwdReg)) {
					errorMsgMap.put("PwdFormatError", "長度必需在6到12之間");
				}

				String repwd = request.getParameter("repwd");
				if (repwd == null || repwd.trim().length() == 0) {
					errorMsgMap.put("RepwdEmptyError", "請勿空白");
				}

				if (memPwd.trim().length() > 0 && repwd.trim().length() > 0) {
					if (!memPwd.trim().equals(repwd.trim())) {
						errorMsgMap.put("RepwdEmptyError", "密碼欄必須與確認欄一致");
						errorMsgMap.put("PwdEmptyError", "*");
					}
				}

				String memName = request.getParameter("memName");
				if (memName == null || memName.trim().length() == 0) {
					errorMsgMap.put("NameEmptyError", "請勿空白");
				}

				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (!memName.trim().matches(nameReg)) {
					errorMsgMap.put("NameFormatError", "只能是中、英文字母、數字和_,且長度必需在2到10之間");
				}

				String memGender = "";
				if ("female".equals(request.getParameter("memGender")))
					memGender = "女";
				else
					memGender = "男";

				String memEmail = request.getParameter("memEmail");
				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgMap.put("EmailEmptyError", "支援找回密碼功能，請勿空白");
				}

				String emailReg = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
				if (!memEmail.trim().matches(emailReg)) {
					errorMsgMap.put("EmailFormatError", "請輸入正確格式");
				}

				String memImage = request.getParameter("memImage"); // base64字串
				if (memImage == null || memImage.trim().length() == 0) {
					memImage = new DefaultImage().THE_HEAD;
				}

				if (request.getParameter("agree") == null) {
					errorMsgMap.put("AgreeEmptyError", "請仔細閱讀並明瞭各條款規定");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgMap.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/register/register_page.jsp");
					failureView.forward(request, response);
					return;
				}
				/**** 2.開始新增資料 ****/
				MemberService memberSvc = new MemberService();
				// 直接設定通過檢查的輸入值至MemberService處理
				java.sql.Date memRegister = null;
				String memStatus = "未驗證會員";
				Integer memInfract = 0;
				memberSvc.addMember(memTel, memAccount, memPwd, memName, memGender, memEmail, memRegister, memStatus,
						memInfract, memImage);

				/**** 3.新增完成 ****/
				smscode = new SendBySMS().Process(memTel); // 傳送簡訊驗證
				session.setAttribute("SMScode", smscode);
				session.setAttribute("telholder", memTel);

				response.sendRedirect("register/register_notice_page.jsp");
			} catch (Exception e) {
				errorMsgMap.put("Exception", e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/register/register_page.jsp");
				failureView.forward(request, response);
			}
		}

		if ("register_easy_insert_Action".equals(action)) {
			// 準備存放錯誤訊息的Map物件
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
			request.setAttribute("ErrorMsgKey", errorMsgMap);

			try {
				/**** 1.接收請求參數 - 輸入格式的錯誤處理 ****/
				String memTel = request.getParameter("memTel");
				if (memTel == null || memTel.trim().length() == 0) {
					errorMsgMap.put("TelEmptyError", "請勿空白");
				}

				String telReg = "^[(0-9)]{10}$";
				if (!memTel.trim().matches(telReg)) {
					errorMsgMap.put("TelFormatError", "請輸入正確格式");
				}

				Map<String, String> bearerMsg = (Map<String, String>) session.getAttribute("BearerMsgKey");

				String memAccount = bearerMsg.get("memAccount");

				String memPwd = null;

				String memName = bearerMsg.get("memName");

				String memGender = "";
				if ("female".equals(request.getParameter("memGender")))
					memGender = "女";
				else
					memGender = "男";

				String memEmail = bearerMsg.get("memEmail");
				if (memEmail == null) {
					memEmail = request.getParameter("memEmail");
					if (memEmail == null || memEmail.trim().length() == 0) {
						errorMsgMap.put("EmailEmptyError", "支援找回密碼功能，請勿空白");
					}

					String emailReg = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
					if (!memEmail.trim().matches(emailReg)) {
						errorMsgMap.put("EmailFormatError", "請輸入正確格式");
					}
				}

				String memImage = request.getParameter("memImage"); // base64字串
				if (memImage == null || memImage.trim().length() == 0) {
					memImage = new DefaultImage().THE_HEAD;
				}

				if (request.getParameter("agree") == null) {
					errorMsgMap.put("AgreeEmptyError", "請仔細閱讀並明瞭各條款規定");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgMap.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/register/register_easy_page.jsp");
					failureView.forward(request, response);
					return;
				}
				/**** 2.開始新增資料 ****/
				MemberService memberSvc = new MemberService();
				// 直接設定通過檢查的輸入值至MemberService處理
				java.sql.Date memRegister = null;
				String memStatus = "未驗證會員";
				Integer memInfract = 0;
				memberSvc.addMember(memTel, memAccount, memPwd, memName, memGender, memEmail, memRegister, memStatus,
						memInfract, memImage);
				/**** 3.新增完成 ****/
				smscode = new SendBySMS().Process(memTel); // 傳送簡訊驗證
				session.setAttribute("SMScode", smscode);
				session.setAttribute("telholder", memTel);

				response.sendRedirect("register/register_notice_page.jsp");
			} catch (Exception e) {
				errorMsgMap.put("Exception", e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/register/register_easy_page.jsp");
				failureView.forward(request, response);
			}
		}

		if ("register_check_account_Action".equals(action)) {
			// 準備存放錯誤訊息的Map物件
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
			request.setAttribute("ErrorMsgKey", errorMsgMap);

			try {
				/**** 1.接收請求參數 - 輸入格式的錯誤處理 ****/
				String memAccount = request.getParameter("memAccount");
				/**** 2.開始查詢資料 ****/
				MemberService memberSvc = new MemberService();
				String checkResult = memberSvc.checkAccountAJAX(memAccount);
				/**** 3.查詢完成 ****/
				out.write(checkResult);
			} catch (Exception e) {
				errorMsgMap.put("Exception", e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/register/register_page.jsp");
				failureView.forward(request, response);
			}
		}

		if ("management_insert_Action".equals(action)) {
			// 準備存放錯誤訊息的Map物件
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
			request.setAttribute("ErrorMsgKey", errorMsgMap);

			try {
				/**** 1.接收請求參數 - 輸入格式的錯誤處理 ****/
				String memTel = request.getParameter("memTel");
				if (memTel == null || memTel.trim().length() == 0) {
					errorMsgMap.put("TelEmptyError", "請勿空白");
				}

				String telReg = "^[(0-9)]{10}$";
				if (!memTel.trim().matches(telReg)) {
					errorMsgMap.put("TelFormatError", "請輸入正確格式");
				}

				String memAccount = request.getParameter("memAccount");
				if (memAccount == null || memAccount.trim().length() == 0) {
					errorMsgMap.put("AccountEmptyError", "請勿空白");
				}

				String accountReg = "^[(a-zA-Z0-9)]{6,12}$";
				if (!memAccount.trim().matches(accountReg)) {
					errorMsgMap.put("AccountFormatError", "請輸入英文、數字 ,且長度必需在6到12之間");
				}

				String memPwd = request.getParameter("memPwd");
				if (memPwd == null || memPwd.trim().length() == 0) {
					errorMsgMap.put("PwdEmptyError", "請勿空白");
				}

				String pwdReg = "^.{6,12}$";
				if (!memPwd.trim().matches(pwdReg)) {
					errorMsgMap.put("PwdFormatError", "長度必需在6到12之間");
				}

				String memName = request.getParameter("memName");
				if (memName == null || memName.trim().length() == 0) {
					errorMsgMap.put("NameEmptyError", "請勿空白");
				}

				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (!memName.trim().matches(nameReg)) {
					errorMsgMap.put("NameFormatError", "只能是中、英文字母、數字和_,且長度必需在2到10之間");
				}

				String memGender = "";
				if ("female".equals(request.getParameter("memGender")))
					memGender = "女";
				else
					memGender = "男";

				String memEmail = request.getParameter("memEmail");
				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgMap.put("EmailEmptyError", "支援找回密碼功能，請勿空白");
				}

				String emailReg = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
				if (!memEmail.trim().matches(emailReg)) {
					errorMsgMap.put("EmailFormatError", "請輸入正確格式");
				}

				java.sql.Date memRegister = null;
				try {
					memRegister = java.sql.Date.valueOf(request.getParameter("memRegister").trim());
				} catch (IllegalArgumentException e) {
					errorMsgMap.put("RegisterEmptyError", "請勿空白");
				}

				String memStatus = "";
				if ("general".equals(request.getParameter("memStatus")))
					memStatus = "一般會員驗證";
				else if ("facebook".equals(request.getParameter("memStatus")))
					memStatus = "FB驗證";
				else if ("google".equals(request.getParameter("memStatus")))
					memStatus = "Google驗證";
				else if ("infraction".equals(request.getParameter("memStatus")))
					memStatus = "黑名單會員";
				else
					memStatus = "未驗證會員";

				Integer memInfract = 0;
				try {
					memInfract = new Integer(request.getParameter("memInfract").trim());
				} catch (NumberFormatException e) {
					errorMsgMap.put("InfractFormatError", "請輸入正確格式");
				}

				String memImage = request.getParameter("memImage"); // base64字串
				if (memImage == null || memImage.trim().length() == 0) {
					memImage = new DefaultImage().THE_HEAD;
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgMap.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/management/manage_member_page.jsp");
					failureView.forward(request, response);
					return;
				}
				/**** 2.開始新增資料 ****/
				MemberService memberSvc = new MemberService();
				memberSvc.addMember(memTel, memAccount, memPwd, memName, memGender, memEmail, memRegister, memStatus,
						memInfract, memImage);
				/**** 3.新增完成 ****/
				response.sendRedirect("management/manage_member_page.jsp");
			} catch (Exception e) {
				errorMsgMap.put("Exception", e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/management/manage_member_page.jsp");
				failureView.forward(request, response);
			}
		}
		// 變更密碼為真實密碼
		if ("getOneMember".equals(action)) {
			MemberVO vo = (MemberVO) session.getAttribute("LoginOK");
			String Pwd = new SecurityCipher().decryptString(vo.getMemPwd());
			session.setAttribute("realPwd", Pwd);
			RequestDispatcher rd = request.getRequestDispatcher("/memberbackstage/mem_back_reset.jsp");
			rd.forward(request, response);
			return;
		}
		if ("update".equals(action)) {
			Map<String, String> errorMsg = new HashMap<String, String>();

			MemberVO memberVO = new MemberVO();
			String memTel = request.getParameter("memTel");

			String memPwd = request.getParameter("memPwd");
			String memPwdReg = "^[(a-zA-Z0-9@)]{2,10}$";
			if (memPwd.trim().length() == 0) {
				errorMsg.put("memPwd", "請輸入密碼");
			} else if (!memPwd.trim().matches(memPwdReg)) {
				errorMsg.put("memPwd", "格式不符合");
			}
			String memName = request.getParameter("memName");
			String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
			if (memName.trim().length() == 0) {
				errorMsg.put("memName", "請輸入姓名");
			} else if (!memName.trim().matches(memNameReg)) {
				errorMsg.put("memName", "格式不符合");
			}
			String memEmail = request.getParameter("memEmail");
			String memEmailReg = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
			if (memEmail.trim().length() == 0) {
				errorMsg.put("memEmail", "請輸入信箱");
			} else if (!memEmail.trim().matches(memEmailReg)) {
				errorMsg.put("memEmail", "格式不符合");
			}
			if (!errorMsg.isEmpty()) {
				request.setAttribute("MsgMap", errorMsg);
				RequestDispatcher rd = request.getRequestDispatcher("/memberbackstage/mem_back_reset.jsp");
				rd.forward(request, response);
				return;
			}
			MemberService memSvc = new MemberService();

			Integer memNO = Integer.valueOf(request.getParameter("memNO"));
			String memAccount = request.getParameter("memAccount");
			String memGender = request.getParameter("memGender");
			String memStatus = request.getParameter("memStatus");
			Integer memInfract = Integer.valueOf(request.getParameter("memInfract"));
			String memImage = request.getParameter("memImage");
			// System.out.println(memImage);
			memberVO = memSvc.update(memNO, memTel, memAccount, memPwd, memName, memGender, memEmail, memStatus,
					memInfract, memImage);
			session.setAttribute("realPwd", memPwd);
			session.setAttribute("LoginOK", memberVO);
			// request.setAttribute("memberVO", memberVO);
			request.setAttribute("Msg", "修改成功");
			response.sendRedirect("/hermit/memberbackstage/mem_back_index.jsp");
			return;
		}

		if ("management_update_Action".equals(action)) {
			// 準備存放錯誤訊息的Map物件
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
			request.setAttribute("ErrorMsgKey", errorMsgMap);

			try {
				/**** 1.接收請求參數 - 輸入格式的錯誤處理 ****/
				Integer memNO = new Integer(request.getParameter("memNO").trim());

				String memTel = request.getParameter("memTel");
				if (memTel == null || memTel.trim().length() == 0) {
					errorMsgMap.put("TelEmptyError", "請勿空白");
				}

				String telReg = "^[(0-9)]{10}$";
				if (!memTel.trim().matches(telReg)) {
					errorMsgMap.put("TelFormatError", "請輸入正確格式");
				}

//				String memAccount = request.getParameter("memAccount");
//				if (memAccount == null || memAccount.trim().length() == 0) {
//					errorMsgMap.put("AccountEmptyError", "請勿空白");
//				}
//
//				String accountReg = "^[(a-zA-Z0-9)]{6,12}$";
//				if (!memAccount.trim().matches(accountReg)) {
//					errorMsgMap.put("AccountFormatError", "請輸入英文、數字 ,且長度必需在6到12之間");
//				}

//				String memPwd = request.getParameter("memPwd");
//				if (memPwd == null || memPwd.trim().length() == 0) {
//					errorMsgMap.put("PwdEmptyError", "請勿空白");
//				}
//
//				String pwdReg = "^.{6,12}$";
//				if (!memPwd.trim().matches(pwdReg)) {
//					errorMsgMap.put("PwdFormatError", "長度必需在6到12之間");
//				}

				String memName = request.getParameter("memName");
				if (memName == null || memName.trim().length() == 0) {
					errorMsgMap.put("NameEmptyError", "請勿空白");
				}

				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (!memName.trim().matches(nameReg)) {
					errorMsgMap.put("NameFormatError", "只能是中、英文字母、數字和_,且長度必需在2到10之間");
				}

				String memGender = "";
				if ("female".equals(request.getParameter("memGender")))
					memGender = "女";
				else
					memGender = "男";

				String memEmail = request.getParameter("memEmail");
				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgMap.put("EmailEmptyError", "支援找回密碼功能，請勿空白");
				}

				String emailReg = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
				if (!memEmail.trim().matches(emailReg)) {
					errorMsgMap.put("EmailFormatError", "請輸入正確格式");
				}

				String memStatus = "";
				if ("general".equals(request.getParameter("memStatus")))
					memStatus = "一般會員驗證";
				else if ("facebook".equals(request.getParameter("memStatus")))
					memStatus = "FB驗證";
				else if ("google".equals(request.getParameter("memStatus")))
					memStatus = "Google驗證";
				else if ("infraction".equals(request.getParameter("memStatus")))
					memStatus = "黑名單會員";
				else
					memStatus = "未驗證會員";

				Integer memInfract = 0;
				try {
					memInfract = new Integer(request.getParameter("memInfract").trim());
				} catch (NumberFormatException e) {
					errorMsgMap.put("InfractFormatError", "請輸入正確格式");
				}

				String memImage = request.getParameter("memImage"); // base64字串
				if (memImage == null || memImage.trim().length() == 0) {
					memImage = new DefaultImage().THE_HEAD;
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgMap.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/management/manage_member_page.jsp");
					failureView.forward(request, response);
					return;
				}
				/**** 2.開始修改資料 ****/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.findByPrimaryKey(memNO);
				String memAccount = memberVO.getMemAccount();
				String memPwd = new SecurityCipher().decryptString(memberVO.getMemPwd());
				memberSvc.updateMember(memNO, memTel, memAccount, memPwd, memName, memGender, memEmail, memStatus,
						memInfract, memImage);
				/**** 3.修改完成 ****/
				response.sendRedirect("management/manage_member_page.jsp");
			} catch (Exception e) {
				errorMsgMap.put("Exception", e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/management/manage_member_page.jsp");
				failureView.forward(request, response);
			}
		}

		if ("management_delete_Action".equals(action)) {
			// 準備存放錯誤訊息的Map物件
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
			request.setAttribute("ErrorMsgKey", errorMsgMap);

			try {
				/**** 1.接收請求參數 - 輸入格式的錯誤處理 ****/
				Integer memNO = new Integer(request.getParameter("memNO").trim());
				/**** 2.開始刪除資料 ****/
				MemberService memberSvc = new MemberService();
				memberSvc.deleteMember(memNO);
				/**** 3.刪除完成 ****/
				response.sendRedirect("management/manage_member_page.jsp");
			} catch (Exception e) {
				errorMsgMap.put("Exception", e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/management/manage_member_page.jsp");
				failureView.forward(request, response);
			}
		}

		if ("member_search".equals(action)) {
			Integer MemberNo = Integer.valueOf(request.getParameter("member"));

			MemberService dao = new MemberService();
			MemberVO memberVO = dao.findByPrimaryKey(MemberNo);
			try {
				request.setAttribute("memNO", memberVO.getMemNO());
				request.setAttribute("memTel", memberVO.getMemTel());
				request.setAttribute("memAccount", memberVO.getMemAccount());
				request.setAttribute("memPwd", new SecurityCipher().decryptString(memberVO.getMemPwd()));
				request.setAttribute("memName", memberVO.getMemName());
				request.setAttribute("memGender", memberVO.getMemGender());
				request.setAttribute("memEmail", memberVO.getMemEmail());
				request.setAttribute("memRegister", memberVO.getMemRegister());
				request.setAttribute("memStatus", memberVO.getMemStatus());
				request.setAttribute("memInfract", memberVO.getMemInfract());
				request.setAttribute("memImage", memberVO.getMemImage());
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/Member/member.jsp");
			rd.forward(request, response);

			return;
		}

		if ("management_getAll_Action".equals(action)) {
			// 準備存放錯誤訊息的Map物件
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
			request.setAttribute("ErrorMsgKey", errorMsgMap);

			try {
				/**** 1.接收請求參數 - 輸入格式的錯誤處理 ****/
				// 無參數
				/**** 2.開始查詢資料 ****/
				MemberService memberSvc = new MemberService();
				Set<MemberVO> set = memberSvc.getAll();
				/**** 3.查詢完成 ****/
				// 準備JSON包裝
				List<Object> jsonList = new ArrayList<Object>();
				for (MemberVO member : set) {
					Map<String, Object> jm = new HashMap<String, Object>();
					jm.put("memNO", member.getMemNO());
					jm.put("memTel", member.getMemTel());
					jm.put("memAccount", member.getMemAccount());
					jm.put("memPwd", new SecurityCipher().decryptString(member.getMemPwd()));
					jm.put("memName", member.getMemName());
					jm.put("memGender", member.getMemGender());
					jm.put("memEmail", member.getMemEmail());
					jm.put("memRegister", member.getMemRegister());
					jm.put("memStatus", member.getMemStatus());
					jm.put("memInfract", member.getMemInfract());
					if (member.getMemImage() == null)
						jm.put("memImage", new DefaultImage().THE_HEAD);
					else
						jm.put("memImage", member.getMemImage());
					jsonList.add(jm);
				}
				String jsonString = new JSONArray(jsonList).toString();
				// System.out.println(jsonString);
				out.write(jsonString);
			} catch (Exception e) {
				errorMsgMap.put("Exception", e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/management/manage_member_page.jsp");
				failureView.forward(request, response);
			}
		}
		if ("checkAgain".equals(action)) {
			// 為方便一般應用程式main方的測試,所以底下的model-config1內部dataSource設定是採用org.springframework.jdbc.datasource.DriverManagerDataSource
			ApplicationContext context = new ClassPathXmlApplicationContext("Spring-model-JDBCcfg.xml");
			// 建立DAO物件
			MemberDAO_interface_hibernate daSP = (MemberDAO_interface_hibernate) context.getBean("memDAO");
			String memAccount = request.getParameter("memAccount");
			String memTel = (daSP.findByAccount(memAccount)).getMemTel();

			smscode = new SendBySMS().Process(memTel); // 傳送簡訊驗證
			session.setAttribute("SMScode", smscode);
			session.setAttribute("telholder", memTel);
			response.sendRedirect("register/register_notice_page.jsp?memAccount=" + memAccount);
		}
	}
}