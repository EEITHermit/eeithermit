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
		LeaseVO vo;

		Integer leaseNO = null;
		Integer houseNO = null;
		Date leaseBeginDate = null;
		Date leaseEndDate = null;
		Integer memNO = null;
		Integer empNO = null;
		Integer Rent = null;
		Integer Deposit = null;
		Integer Relief = null;
		Date leaseDate = null;
		String leasePic = null;
		String houseNote = null;
		Byte Refund = null;

		if ("getAllLeaseForJson".equals(action)) {

			Set<LeaseVO> set = svc.getAll();
			List list2 = new ArrayList();
			PrintWriter out = response.getWriter();
			for (LeaseVO vo2 : set) {
				Map m1 = new HashMap();
				m1.put("leaseNO", vo2.getLeaseNO());
				m1.put("houseNO", vo2.getHouseNO());
				m1.put("leaseBeginDate", vo2.getLeaseBeginDate());
				m1.put("leaseEndDate", vo2.getLeaseEndDate());
				m1.put("memNO", vo2.getMemNO());
				m1.put("empNO", vo2.getEmpNO());
				m1.put("Rent", vo2.getRent());
				m1.put("Deposit", vo2.getDeposit());
				m1.put("Relief", vo2.getRelief());
				m1.put("leaseDate", vo2.getLeaseDate());
				m1.put("leasePic", vo2.getLeasePic());
				m1.put("houseNote", vo2.getHouseNote());
				m1.put("Refund", vo2.getRefund());
				list2.add(m1);
				System.out.println("for");
			}
			String jsonString = new JSONArray(list2).toString();
			out.println(jsonString);
			out.flush();
			out.close();
		}

		if ("insertLease".equals(action)) {
			houseNO = Integer.valueOf(request.getParameter("houseNO"));
			leaseBeginDate = java.sql.Date.valueOf(request.getParameter("leaseBeginDate"));
			leaseEndDate = java.sql.Date.valueOf(request.getParameter("leaseEndDate"));
			memNO = Integer.valueOf(request.getParameter("memNO"));
			empNO = Integer.valueOf(request.getParameter("empNO"));
			Rent = Integer.valueOf(request.getParameter("Rent"));
			Deposit = Integer.valueOf(request.getParameter("Deposit"));
			Relief = Integer.valueOf(request.getParameter("Relief"));
			leaseDate = java.sql.Date.valueOf(request.getParameter("leaseDate"));
			leasePic = request.getParameter("leasePic");
			houseNote = request.getParameter("houseNote");
			Refund = Byte.valueOf(request.getParameter("Refund"));
			svc.addLease(houseNO, leaseBeginDate, leaseEndDate, memNO, empNO, Rent, Deposit, Relief, leaseDate,
					leasePic, houseNote, Refund);
			response.sendRedirect("/hermit/Lease/Lease.jsp");
			System.out.println("Add Lease success");
		}

		if ("getOneLease".equals(action)) {

			svc = new LeaseService();
			vo = svc.getOneLease(Integer.valueOf(request.getParameter("leaseNO")));
			request.setAttribute("vo", vo);
			RequestDispatcher rd = request.getRequestDispatcher("/Lease/LeaseUpdate.jsp");
			rd.forward(request, response);
			System.out.println("Search One Success");
		}
	}

}
