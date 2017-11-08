package com.hermit.iii.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONValue;

import com.hermit.iii.emp.model.EmpService;
import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.mention.model.MentionService;
import com.hermit.iii.util.SecurityCipher;

@WebServlet("/emp/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// public EmpServlet() {
	// super();
	// }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		EmpService es = null;
		Integer empNO = null;
		String empAccount = null;
		String empPwd = null;
		String empPhone = null;
		String empName = null;
		Integer postNO = null;
		Boolean empStatus = null;
		
		System.out.println("action="+action);
		
		if("InsertEmp".equals(action)){
			es = new EmpService();
			
			Map<String, String> errorMsgMap = checkData(request);
			if (!errorMsgMap.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("empInsert_include.jsp");//尚未輸入
				failureView.forward(request, response);
				return;
			}
			
			empAccount = request.getParameter("empAccount");
//			empPwd = SecurityCipher.encryptString(request.getParameter("empPwd"));
			empPwd = SecurityCipher.encryptString(request.getParameter("empPwd"));
			empPhone = request.getParameter("empPhone");
			empName = request.getParameter("empName");
			postNO = Integer.valueOf(request.getParameter("empPostVO"));
			empStatus = "0".equals(request.getParameter("empStatus"));
			es.insertEmp(empAccount, empPwd, empPhone, empName, postNO, empStatus);
			System.out.println("Servlet Insert success");
			RequestDispatcher rd = request.getRequestDispatcher("empIndex_include.jsp");
			rd.forward(request, response);
		}
		
		if("UpdateEmp".equals(action)){
			System.out.println("00000000000000000000000000000");
			es = new EmpService();
//			Map<String, String> errorMsgMap = checkData(request);
//			if (!errorMsgMap.isEmpty()) {
//				RequestDispatcher failureView = request.getRequestDispatcher("empUpdate_include.jsp");//尚未輸入
//				failureView.forward(request, response);
//				return;
//			}
			empNO = Integer.valueOf(request.getParameter("empNO"));
			empAccount = request.getParameter("empAccount");
			empPwd = request.getParameter("empPwd");
			empPhone = request.getParameter("empPhone");
			empName = request.getParameter("empName");
			postNO = Integer.valueOf(request.getParameter("empPostVO")); 
			empStatus = "0".equals(request.getParameter("empStatus"));
			es.updateEmp(empNO, empAccount, empPwd, empPhone, empName, postNO, empStatus);
			System.out.println("Update success");
			
			response.sendRedirect(request.getContextPath()+"/emp/empIndex_include.jsp");

		}
		
		if("deleteEmp".equals(action)){
			es = new EmpService();
			es.deleteEmp(Integer.valueOf(request.getParameter("empNO")));
			System.out.println("Servlet delete success");
		}
		
		if("getOneEmp".equals(action)){
			System.out.println("Get One success");
			EmpVO empVO = new EmpVO();
			es = new EmpService();
			empVO = es.getOneEmp(Integer.valueOf(request.getParameter("empNO")));
			request.setAttribute("empVO", empVO);
			RequestDispatcher rd =request.getRequestDispatcher("empUpdate_include.jsp");//尚未輸入
			rd.forward(request, response);
		}
		
		if("getAllEmp".equals(action)){
			System.out.println("test getall");
			es = new EmpService();
			List<EmpVO> list = es.getAllEmp();
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("empIndex_include.jsp");//尚未輸入
			rd.forward(request, response);
		}
		if("getAllEmpJson".equals(action)){
			System.out.println("Get All For JSON");
			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			es = new EmpService();
			String stringjson = es.getAllForJson();
			out.println(stringjson);
			out.flush();
			out.close();
			
		}
		
		if("findByAccount".equals(action)){
			EmpVO empVO = new EmpVO();
			es = new EmpService();
			empVO = es.findByAccount(request.getParameter("empAccount"));
			request.setAttribute("empVO", empVO);
			RequestDispatcher rd = request.getRequestDispatcher("empIndex_include.jsp");//尚未輸入
			rd.forward(request, response);
			

		}
		if("findByFixPostAndBorough".equals(action)){
			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			EmpVO empVO = new EmpVO();
			es = new EmpService();
			MentionService mention = new MentionService();
			PrintWriter out = response.getWriter();
			Integer post = Integer.valueOf(request.getParameter("post"));
			Integer borough = Integer.valueOf(request.getParameter("borough"));
			List<EmpVO> postEmpList = es.getByPostJSON(post);
			List toJsonList = new LinkedList();
			for(int i =0;i < postEmpList.size();i++){
				empVO = postEmpList.get(i);
				Map m1 = new LinkedHashMap();
				ArrayList<Integer> boroughNOs = mention.getBoroughNOByEmpNO(empVO.getEmpNO());
				for(Integer boro : boroughNOs){
					if((boro == borough) && (!empVO.getEmpStatus()) ){
						m1.put("empName",empVO.getEmpName());
						m1.put("empNO",empVO.getEmpNO());
						toJsonList.add(m1);
						break;
					}
				}
			}
			Map m2 = new LinkedHashMap();
			m2.put("fixEmp", toJsonList);
			String fixEmps = JSONValue.toJSONString(m2);
			out.print(fixEmps);
			out.flush();
			return;
			}
		if("queryEmp".equals(action)){
			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			EmpService empdao=new EmpService();
			empNO=Integer.valueOf(request.getParameter("empNO"));
			EmpVO vo=new EmpVO();
			vo=empdao.getOneEmp(empNO);
			empName=vo.getEmpName();
			PrintWriter out = response.getWriter();
			out.print(empName);
			out.flush();
			return;
		}
		
		}
	
	
	
	private Map<String, String> checkData(HttpServletRequest req){
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
//			if(empPhone == null || empPhone.trim().length() == 0){
			if(StringUtils.isBlank(empPhone)){
				errorMsgMap.put("empPhoneError", "請勿空白");
			}
			
			String telReg = "^[(0-9)]{10}";//員工電話驗證{10位}
			if(!empPhone.trim().matches(telReg)){
				errorMsgMap.put("TelFormatError", "請輸入正確格式");
			}
			
			String empStatus = req.getParameter("empStatus");
			empStatus = "0".equals(empStatus) ? "可上班" : "已離職";
			
			String postNO = req.getParameter("empPostVO");
			if("310".equals(postNO))
				postNO = "系統管理員";
			else if("320".equals(postNO))
				postNO = "業務人員";
			else if("330".equals(postNO))
				postNO = "客服人員";
			else
				postNO = "修繕人員";
			
			return errorMsgMap;
			
	}

}
