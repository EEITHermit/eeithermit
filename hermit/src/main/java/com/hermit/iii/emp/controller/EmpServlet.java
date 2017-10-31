package com.hermit.iii.emp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hermit.iii.dispatchlist.model.DispatchListService;
import com.hermit.iii.emp.model.EmpService;
import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.post.model.PostVO;

@WebServlet("/EmpServlet")
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
		
		if("InsertEmp".equals(action)){
			es = new EmpService();
			
			empAccount = request.getParameter("empAccount");
			empPwd = request.getParameter("empPwd");
			empPhone = request.getParameter("empPhone");
			empName = request.getParameter("empName");
			postNO = Integer.valueOf(request.getParameter("postNO"));
			empStatus = Boolean.valueOf("empStatus");
			es.insertEmp(empAccount, empPwd, empPhone, empName, postNO, empStatus);
			
			System.out.println("Servlet Insert success");
		}
		
		if("updateEmp".equals(action)){
			es = new EmpService();
			
			empNO = Integer.valueOf(request.getParameter("empNO"));
			empAccount = request.getParameter("empAccount");
			empPwd = request.getParameter("empPwd");
			empPhone = request.getParameter("empPhone");
			empName = request.getParameter("empName");
			postNO = Integer.valueOf(request.getParameter("postNO")); 
			empStatus = Boolean.valueOf("empStatus");
			
			es.updateEmp(empNO, empAccount, empPwd, empPhone, empName, postNO, empStatus);
			System.out.println("Update success");

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
			RequestDispatcher rd =request.getRequestDispatcher("");//尚未輸入
			rd.forward(request, response);
		}
		
		if("getAllEmp".equals(action)){
			es = new EmpService();
			List<EmpVO> list = es.getAllEmp();
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("");//尚未輸入
			rd.forward(request, response);
		}
		
		if("findByAccount".equals(action)){
			EmpVO empVO = new EmpVO();
			es = new EmpService();
			empVO = es.findByAccount(request.getParameter("empAccount"));
			request.setAttribute("empVO", empVO);
			RequestDispatcher rd = request.getRequestDispatcher("");//尚未輸入
			rd.forward(request, response);
			

		}
		
	}

}
