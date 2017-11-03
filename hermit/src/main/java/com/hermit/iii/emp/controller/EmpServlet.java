package com.hermit.iii.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import org.json.simple.JSONValue;

import com.hermit.iii.emp.model.EmpService;
import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.mention.model.MentionService;
import com.hermit.iii.teammemberlist.model.TeamMemberListVO;

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
			RequestDispatcher rd =request.getRequestDispatcher("empIndex_include.jsp");//尚未輸入
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
		if("getAllEmpForJson".equals(action)){
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
	}
}
