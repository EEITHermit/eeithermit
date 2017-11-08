package com.hermit.iii.lease.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.hermit.iii.house.model.HouseService;
import com.hermit.iii.lease.model.LeaseService;
import com.hermit.iii.lease.model.LeaseVO;

@WebServlet("/LeaseServlet.do")
public class LeaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		LeaseService svc = new LeaseService();
		LeaseVO vo = null;
		
		Integer leaseNO = null;
		Integer houseNO = null;
		Date leaseBeginDate = null;
		Date leaseEndDate = null;
		Integer memNO = null;
		Integer empNO = null;
		Integer leaseRent = null;
		Integer leaseDeposit = null;
		Integer leaseRelief = null;
		Date leaseDate = null;
		String leasePic = null;
		String houseNote = null;
		Byte leaseRefund = null;

		if ("getAllLeaseForJson".equals(action)) {

			Set<LeaseVO> set = svc.getAll();
			List list2 = new ArrayList();
			PrintWriter out = response.getWriter();
			for (LeaseVO vo2 : set) {
				Map m1 = new HashMap();
				m1.put("leaseNO", vo2.getLeaseNO());
				m1.put("houseNO", vo2.getHouseVO().getHouseNO());
				m1.put("leaseBeginDate", vo2.getLeaseBeginDate());
				m1.put("leaseEndDate", vo2.getLeaseEndDate());
				m1.put("memName", vo2.getMemberVO().getMemName());
				m1.put("memNO", vo2.getMemberVO().getMemNO());
				m1.put("empName", vo2.getEmpVO().getEmpName());
				m1.put("empNO", vo2.getEmpVO().getEmpNO());
				m1.put("leaseRent", vo2.getLeaseRent());
				m1.put("leaseDeposit", vo2.getLeaseDeposit());
				m1.put("leaseRelief", vo2.getLeaseRelief());
				m1.put("leaseDate", vo2.getLeaseDate());
				m1.put("leasePic", vo2.getLeasePic());
				m1.put("houseNote", vo2.getHouseNote());
				m1.put("leaseRefund", vo2.getLeaseRefund());
				list2.add(m1);
				
			}
			String jsonString = new JSONArray(list2).toString();
			out.println(jsonString);
			out.flush();
			out.close();
		}

		if ("insertLease".equals(action)) {
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			request.setAttribute("ErrorMsgKey", errorMsgMap); 
			try{
			houseNO = Integer.valueOf(request.getParameter("houseNO"));
			}catch(Exception e){
				if(houseNO==null){
					errorMsgMap.put("houseNO", "請勿空白");
				}
			}
			leaseBeginDate = java.sql.Date.valueOf(request.getParameter("leaseBeginDate"));
			leaseEndDate = java.sql.Date.valueOf(request.getParameter("leaseEndDate"));
			memNO = Integer.valueOf(request.getParameter("memNO"));
			empNO = Integer.valueOf(request.getParameter("empNO"));
			leaseRent = Integer.valueOf(request.getParameter("leaseRent"));
			leaseDeposit = Integer.valueOf(request.getParameter("leaseDeposit"));
			System.out.println(leaseDeposit);
			leaseRelief = Integer.valueOf(request.getParameter("leaseRelief"));
			leaseDate = java.sql.Date.valueOf(request.getParameter("leaseDate"));
			leasePic = request.getParameter("leasePic");
			System.out.println(leasePic);
			houseNote = request.getParameter("houseNote");
			leaseRefund = Byte.valueOf(request.getParameter("leaseRefund"));
			
			HouseService housesvc=new HouseService();
			housesvc.updateHouseStatus(houseNO);
			
			if(!errorMsgMap.isEmpty()){
				RequestDispatcher failureView = request.getRequestDispatcher("/Lease/Lease.jsp");
				failureView.forward(request, response);
				return;
			}
			svc.addLease(houseNO, leaseBeginDate, leaseEndDate, memNO, empNO,leaseRent, leaseDeposit, leaseRelief, leaseDate,
					leasePic, houseNote, leaseRefund);
			response.sendRedirect("/hermit/Lease/Lease.jsp");
			System.out.println("Add Lease success");
		}

		if ("getOneLease".equals(action)) {

			svc = new LeaseService();
			vo = svc.getOneLease(Integer.valueOf(request.getParameter("leaseNO")));
			request.setAttribute("vo", vo);
			RequestDispatcher rd = request.getRequestDispatcher("/Lease/LeaseUpdate.jsp");
			rd.forward(request, response);
//			System.out.println("Search One Success");
		}
		if("getAllLease".equals(action)){
			svc=new LeaseService();
			memNO=Integer.valueOf(request.getParameter("memNO"));
			List<LeaseVO>list=svc.getAllLease(memNO);
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/memberbackstage/mem_back_lease.jsp");
			rd.forward(request, response);
			System.out.println("Search One Success");
		}
		if("update".equals(action)){
			System.out.println("aaa");
			leaseNO=Integer.valueOf(request.getParameter("leaseNO"));
			houseNO = Integer.valueOf(request.getParameter("houseNO"));
			leaseBeginDate = java.sql.Date.valueOf(request.getParameter("leaseBeginDate"));
			leaseEndDate = java.sql.Date.valueOf(request.getParameter("leaseEndDate"));
			memNO = Integer.valueOf(request.getParameter("memNO"));
			empNO = Integer.valueOf(request.getParameter("empNO"));
			leaseRent = Integer.valueOf(request.getParameter("leaseRent"));
			leaseDeposit = Integer.valueOf(request.getParameter("leaseDeposit"));
			System.out.println(leaseDeposit);
			leaseRelief = Integer.valueOf(request.getParameter("leaseRelief"));
			leaseDate = java.sql.Date.valueOf(request.getParameter("leaseDate"));
			leasePic = request.getParameter("leasePic");
			System.out.println(leasePic);
			houseNote = request.getParameter("houseNote");
			leaseRefund = Byte.valueOf(request.getParameter("leaseRefund"));
			svc.updateLease(leaseNO, houseNO, leaseBeginDate, leaseEndDate, memNO, empNO, leaseRent, leaseDeposit, leaseRelief, leaseDate, leasePic, houseNote, leaseRefund);
			response.sendRedirect("/hermit/Lease/Lease.jsp");
		}
		
	
	}

}
