package com.hermit.iii.house.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.hermit.iii.house.model.HouseService;
import com.hermit.iii.house.model.HouseVO;

@WebServlet("/House.do")
public class HouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action =request.getParameter("action");
		HouseService svc = new HouseService(); 
		HouseVO vo ;
		Integer houseNO = null;
		String houseTitle = null;
		Integer cityNO = null;
		Integer boroughNO = null;
		Integer highestFloor = null;
		Integer nowFloor = null;
		String houseStatus = null;
		Integer houseRent = null;
		Integer houseCharge = null;
		String waterRate = null;
		String powerRate = null;
		String houseVideo = null;
		Integer typeNO = null;
		Integer formNO = null;
		String houseAddr = null;
		Double houseSize = null;
		
		if("insertHouse".equals(action)){
			houseTitle = request.getParameter("houstTitle");
			cityNO = Integer.valueOf(request.getParameter("cityNO"));
			boroughNO = Integer.valueOf(request.getParameter("boroughNO"));
			highestFloor = Integer.valueOf(request.getParameter("highestFloor"));
			nowFloor = Integer.valueOf(request.getParameter("nowFloor"));
			houseStatus = request.getParameter("houseStatus");
			houseRent = Integer.valueOf(request.getParameter("houseRent"));
			houseCharge = Integer.valueOf(request.getParameter("housecharge"));
			waterRate = request.getParameter("waterRate");
			powerRate = request.getParameter("powerRate");
			houseVideo = request.getParameter("houseVideo");
			typeNO = Integer.valueOf(request.getParameter("typeNO"));
			formNO = Integer.valueOf(request.getParameter("formNO"));
			houseAddr = request.getParameter("houseAddr");
			houseSize = Double.valueOf(request.getParameter("houseSize"));
			svc.insertHouse(houseTitle, cityNO, boroughNO, highestFloor, nowFloor, houseStatus, houseRent, houseCharge, waterRate, powerRate, houseVideo, typeNO, formNO, houseAddr, houseSize);
			System.out.println("Insert Success");
		}
		if("updateHouse".equals(action)){
			houseNO = Integer.valueOf("houseNO");
			houseTitle = request.getParameter("houstTitle");
			cityNO = Integer.valueOf(request.getParameter("cityNO"));
			boroughNO = Integer.valueOf(request.getParameter("boroughNO"));
			highestFloor = Integer.valueOf(request.getParameter("highestFloor"));
			nowFloor = Integer.valueOf(request.getParameter("nowFloor"));
			houseStatus = request.getParameter("houseStatus");
			houseRent = Integer.valueOf(request.getParameter("houseRent"));
			houseCharge = Integer.valueOf(request.getParameter("housecharge"));
			waterRate = request.getParameter("waterRate");
			powerRate = request.getParameter("powerRate");
			houseVideo = request.getParameter("houseVideo");
			typeNO = Integer.valueOf(request.getParameter("typeNO"));
			formNO = Integer.valueOf(request.getParameter("formNO"));
			houseAddr = request.getParameter("houseAddr");
			houseSize = Double.valueOf(request.getParameter("houseSize"));
			svc.updateHouse(houseNO, houseTitle, cityNO, boroughNO, highestFloor, nowFloor, houseStatus, houseRent, houseCharge, waterRate, powerRate, houseVideo, typeNO, formNO, houseAddr, houseSize);
			System.out.println("Update Success");
		}
		if("deleteHouse".equals(action)){
			houseNO = Integer.valueOf("houseNO");
			svc.dateHouse(houseNO);
			System.out.println("Delete success");
		}
		if("getOneHouse".equals(action)){
			houseNO = Integer.valueOf("houseNO");
			vo = svc.getOneHouse(houseNO);
			System.out.println("Search One Success");
		}
		if("getAllHouse".equals(action)){
			List<HouseVO> list = svc.getAllHouse();
			System.out.println("Get All House Success");
			RequestDispatcher rd = request.getRequestDispatcher("SignatureUpdate.jsp");
			rd.forward(request,response);
		}
		if("getAllHouseForJson".equals(action)){
			System.out.println("asd");
			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			List<HouseVO> list = svc.getAllHouse();
			List list2 = new LinkedList();
			PrintWriter out = response.getWriter();
			for(int i = 0; i<list.size();i++){
				Map m1 = new LinkedHashMap();
				vo = list.get(i);
				m1.put("houseNO",vo.getHouseNO());
				m1.put("houseTitle", vo.getHouseTitle());
				m1.put("cityNO", vo.getCityNO());
				m1.put("boroughNO", vo.getBoroughNO());
				m1.put("highestFloor", vo.getHighestFloor());
				m1.put("nowFloor", vo.getNowFloor());
				m1.put("houseStatus", vo.getHouseStatus());
				m1.put("houseRent", vo.getHouseRent());
				m1.put("houseCharge", vo.getHouseCharge());
				m1.put("waterRate", vo.getWaterRate());
				m1.put("powerRate", vo.getPowerRate());
				m1.put("houseVideo", vo.getHouseVideo());
				m1.put("typeNO", vo.getTypeNO());
				m1.put("formNO", vo.getFormNO());
				m1.put("houseAddr", vo.getHouseAddr());
				m1.put("houseSize", vo.getHouseSize());
				list2.add(m1);
			}
			Map m2 = new LinkedHashMap();
			m2.put("list",list2);
			String strJson = JSONValue.toJSONString(m2);
			out.println(strJson);
			out.flush();
			out.close();
			System.out.println("Get All For JSON success");
		}
		
	}

}
