package com.hermit.iii.house.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.simple.JSONValue;

import com.hermit.iii.boroughs.model.BoroughsVO;
import com.hermit.iii.city.model.CityVO;
import com.hermit.iii.house.model.HouseService;
import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.houseform.model.HouseFormVO;
import com.hermit.iii.housepicture.model.HousePictureVO;
import com.hermit.iii.housetype.model.HouseTypeVO;
import com.hermit.iii.util.ConvertToBase64;

@WebServlet("/House.do")
public class HouseServlet extends HttpServlet {
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
		HouseService svc = new HouseService();
		HouseVO vo;
		Integer houseNO = null;
		String houseTitle = null;
		Integer cityNO = null;
		Integer boroughNO = null;
		String previewPic = null;
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
		String houseInfo=null;
		if ("insertHouse".equals(action)) {
			ConvertToBase64 ctb = new ConvertToBase64();
			HouseVO houseVO = new HouseVO();
			String strBase64 = null;
			Set<HousePictureVO> set = new HashSet<HousePictureVO>();
			houseTitle = request.getParameter("houseTitle");
			houseVO.setHouseTitle(houseTitle);
			cityNO = Integer.valueOf(request.getParameter("cityNO"));
			CityVO cityVO = new CityVO();
			cityVO.setCityNO(cityNO);
			houseVO.setCityVO(cityVO);
			boroughNO = Integer.valueOf(request.getParameter("boroughNO"));
			BoroughsVO boroughsVO = new BoroughsVO();
			boroughsVO.setBoroughNO(boroughNO);
			houseVO.setBoroughsVO(boroughsVO);
			previewPic = request.getParameter("previewPic");
			houseVO.setPreviewPic(previewPic);
			highestFloor = Integer.valueOf(request.getParameter("highestFloor"));
			houseVO.setHighestFloor(highestFloor);
			nowFloor = Integer.valueOf(request.getParameter("nowFloor"));
			houseVO.setNowFloor(nowFloor);
			houseStatus = request.getParameter("houseStatus");
			houseVO.setHouseStatus(houseStatus);
			houseRent = Integer.valueOf(request.getParameter("houseRent"));
			houseVO.setHouseRent(houseRent);
			houseCharge = Integer.valueOf(request.getParameter("houseCharge"));
			houseVO.setHouseCharge(houseCharge);
			waterRate = request.getParameter("waterRate");
			houseVO.setWaterRate(waterRate);
			powerRate = request.getParameter("powerRate");
			houseVO.setPowerRate(powerRate);
			houseVideo = request.getParameter("houseVideo");
			houseVO.setHouseVideo(houseVideo);
			typeNO = Integer.valueOf(request.getParameter("typeNO"));
			HouseTypeVO typeVO = new HouseTypeVO();
			typeVO.setTypeNO(typeNO);
			houseVO.setHouseTypeVO(typeVO);
			formNO = Integer.valueOf(request.getParameter("formNO"));
			HouseFormVO formVO = new HouseFormVO();
			formVO.setFormNO(formNO);
			houseVO.setHouseFormVO(formVO);
			houseAddr = request.getParameter("houseAddr");
			houseVO.setHouseAddr(houseAddr);
			houseSize = Double.valueOf(request.getParameter("houseSize"));
			houseVO.setHouseSize(houseSize);
			houseInfo=request.getParameter("houseInfo");
			houseVO.setHouseInfo(houseInfo);
			Collection<Part> parts = request.getParts();
			for (Part item : request.getParts()) {
				strBase64 = ctb.encode(item);
				if (strBase64 != null) {
					// System.out.println(strBase64);
					HousePictureVO picVO = new HousePictureVO();
					picVO.sethPicture(strBase64);
					set.add(picVO);
				}

				
				// svc.insertHouse(houseTitle, cityNO, boroughNO,
				// previewPic,highestFloor, nowFloor, houseStatus, houseRent,
				// houseCharge, waterRate, powerRate, houseVideo, typeNO,
				// formNO, houseAddr, houseSize);
				
			}
			svc.insertHouseAndHousePicture(houseVO, set);
			response.sendRedirect("/hermit/House/House_management.jsp");
			return;
		}
		if ("updateHouse".equals(action)) {

			svc = new HouseService();

			houseNO = Integer.valueOf(request.getParameter("houseNO"));
			houseTitle = request.getParameter("houseTitle");
			cityNO = Integer.valueOf(request.getParameter("cityNO"));
			boroughNO = Integer.valueOf(request.getParameter("boroughNO"));
			previewPic = request.getParameter("previewPic");
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
			houseInfo=request.getParameter("houseInfo");
			
			svc.updateHouse(houseNO, houseTitle, cityNO, boroughNO, previewPic, highestFloor, nowFloor, houseStatus,
					houseRent, houseCharge, waterRate, powerRate, houseVideo, typeNO, formNO, houseAddr, houseSize,houseInfo);
			// RequestDispatcher
			// rd=request.getRequestDispatcher("/House/House_management.jsp");
			// rd.forward(request, response);
			response.sendRedirect("/hermit/House/House_management.jsp");
			System.out.println("Update Success");
			return;
		}
		if ("deleteHouse".equals(action)) {
			houseNO = Integer.valueOf(request.getParameter("houseNO"));
			svc.dateHouse(houseNO);
			System.out.println("Delete success");
		}
		if ("getOneHouse".equals(action)) {
			svc = new HouseService();
			vo = svc.getOneHouse(Integer.valueOf(request.getParameter("houseNO")));
			request.setAttribute("vo", vo);
			//
			RequestDispatcher rd = request.getRequestDispatcher("");
			//
			rd.forward(request, response);
			System.out.println("Search One Success");
		}
		if ("getOneHouse_FK".equals(action)) {
			svc = new HouseService();
			vo = svc.GET_ONE_HOUSE_FK(Integer.valueOf(request.getParameter("houseNO")));
			request.setAttribute("vo", vo);
			
			for(HousePictureVO hVO:vo.getHousePictureVO()){
				System.out.println(hVO.gethPicture());
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/House/SingleHouseUpdate.jsp");
			rd.forward(request, response);
			System.out.println("Search One Success");
		}
		if ("getAllHouse".equals(action)) {
			List<HouseVO> list = svc.getAllHouse();
			System.out.println("Get All House Success");
			RequestDispatcher rd = request.getRequestDispatcher("SignatureUpdate.jsp");
			rd.forward(request, response);
		}
		if ("getAllHouseForJson".equals(action)) {
			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			List<HouseVO> list = svc.getAllHouse_FK();
			List list2 = new LinkedList();
			PrintWriter out = response.getWriter();
			for (int i = 0; i < list.size(); i++) {
				Map m1 = new LinkedHashMap();
				vo = list.get(i);
				m1.put("houseNO", vo.getHouseNO());
				m1.put("houseTitle", vo.getHouseTitle());
				m1.put("cityNO", vo.getCityVO().getCityNO());
				m1.put("cityName", vo.getCityVO().getCityName());
				m1.put("boroughNO", vo.getBoroughsVO().getBoroughNO());
				m1.put("boroughName", vo.getBoroughsVO().getBoroughName());
				m1.put("highestFloor", vo.getHighestFloor());
				m1.put("nowFloor", vo.getNowFloor());
				m1.put("houseStatus", vo.getHouseStatus());
				m1.put("houseRent", vo.getHouseRent());
				m1.put("houseCharge", vo.getHouseCharge());
				m1.put("waterRate", vo.getWaterRate());
				m1.put("powerRate", vo.getPowerRate());
				m1.put("houseVideo", vo.getHouseVideo());
				m1.put("typeNO", vo.getHouseTypeVO().getTypeNO());
				m1.put("hType", vo.getHouseTypeVO().gethType());
				m1.put("formNO", vo.getHouseFormVO().getFormNO());
				m1.put("hForm", vo.getHouseFormVO().gethForm());
				m1.put("houseAddr", vo.getHouseAddr());
				m1.put("houseSize", vo.getHouseSize());
				m1.put("hType", vo.getHouseTypeVO().gethType());
				m1.put("hForm", vo.getHouseFormVO().gethForm());
				m1.put("houseInfo", vo.getHouseInfo());
				
				Set<HousePictureVO>set=vo.getHousePictureVO();
				for(HousePictureVO housePictureVO:set){
				m1.put("housePicture", housePictureVO.gethPicture());
				}
				list2.add(m1);
			}
			Map m2 = new LinkedHashMap();
			m2.put("list", list2);
			String strJson = JSONValue.toJSONString(m2);
			out.println(strJson);
			out.flush();
			out.close();
			System.out.println("Get All For JSON success");
		}

	}

}
