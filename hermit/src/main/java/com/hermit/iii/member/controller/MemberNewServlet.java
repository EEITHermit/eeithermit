package com.hermit.iii.member.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.hermit.iii.member.model.*;
import com.hermit.iii.util.*;

@Controller
@SessionAttributes({ "SMScode", "telholder" })
public class MemberNewServlet {

	@RequestMapping(method = RequestMethod.POST, value = "/MemberNewServlet/registerInsert.do")
	public String method1(@ModelAttribute("myForm") MemberVO memberVO, ModelMap model,
			@RequestParam(value = "repwd", required=false) String repwd, @RequestParam(value = "agree", required=false) String agree, HttpSession session) {
		System.out.println("method1被呼叫!");
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
		model.addAttribute("ErrorMsgKey", errorMsgMap);
		String smscode = "";

		try {
			/**** 1.接收請求參數 - 輸入格式的錯誤處理 ****/
			String memTel = memberVO.getMemTel();
			if (memTel == null || memTel.trim().length() == 0) {
				errorMsgMap.put("TelEmptyError", "請勿空白");
			}

			String telReg = "^[(0-9)]{10}$";
			if (!memTel.trim().matches(telReg)) {
				errorMsgMap.put("TelFormatError", "請輸入正確格式");
			}

			String memAccount = memberVO.getMemAccount();
			if (memAccount == null || memAccount.trim().length() == 0) {
				errorMsgMap.put("AccountEmptyError", "請勿空白");
			}

			String accountReg = "^[(a-zA-Z0-9)]{6,12}$";
			if (!memAccount.trim().matches(accountReg)) {
				errorMsgMap.put("AccountFormatError", "請輸入英文、數字 ,且長度必需在6到12之間");
			}

			String memPwd = memberVO.getMemPwd();
			if (memPwd == null || memPwd.trim().length() == 0) {
				errorMsgMap.put("PwdEmptyError", "請勿空白");
			}

			String pwdReg = "^.{6,12}$";
			if (!memPwd.trim().matches(pwdReg)) {
				errorMsgMap.put("PwdFormatError", "長度必需在6到12之間");
			}

			if (repwd == null || repwd.trim().length() == 0) {
				errorMsgMap.put("RepwdEmptyError", "請勿空白");
			}

			if (memPwd.trim().length() > 0 && repwd.trim().length() > 0) {
				if (!memPwd.trim().equals(repwd.trim())) {
					errorMsgMap.put("RepwdEmptyError", "密碼欄必須與確認欄一致");
					errorMsgMap.put("PwdEmptyError", "*");
				}
			}

			String memName = memberVO.getMemName();
			if (memName == null || memName.trim().length() == 0) {
				errorMsgMap.put("NameEmptyError", "請勿空白");
			}

			String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (!memName.trim().matches(nameReg)) {
				errorMsgMap.put("NameFormatError", "只能是中、英文字母、數字和_,且長度必需在2到10之間");
			}

			String memGender = "";
			if ("female".equals(memberVO.getMemGender()))
				memGender = "女";
			else
				memGender = "男";

			String memEmail = memberVO.getMemEmail();
			if (memEmail == null || memEmail.trim().length() == 0) {
				errorMsgMap.put("EmailEmptyError", "支援找回密碼功能，請勿空白");
			}

			String emailReg = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
			if (!memEmail.trim().matches(emailReg)) {
				errorMsgMap.put("EmailFormatError", "請輸入正確格式");
			}

			String memImage = memberVO.getMemImage(); // base64字串
			if (memImage == null || memImage.trim().length() == 0) {
				memImage = new DefaultImage().THE_HEAD;
			}

			if (agree == null) {
				errorMsgMap.put("AgreeEmptyError", "請仔細閱讀並明瞭各條款規定");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgMap.isEmpty()) {
				return "register_page";
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

			return "redirect:/register/register_notice_page.jsp";
		} catch (Exception e) {
			errorMsgMap.put("Exception", e.getMessage());
			return "register_page";
		}
	}
}
