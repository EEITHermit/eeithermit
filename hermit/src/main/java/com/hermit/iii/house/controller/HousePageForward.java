package com.hermit.iii.house.controller;

import java.io.IOException;
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

import org.json.simple.JSONValue;

import com.hermit.iii.equipmentcondition.model.EquipmentConditionService;
import com.hermit.iii.equipmentcondition.model.EquipmentConditionVO;
import com.hermit.iii.house.model.HouseService;
import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.housepicture.model.HousePictureService;
import com.hermit.iii.housepicture.model.HousePictureVO;

/**
 * Servlet implementation class HousePageForward
 */
@WebServlet("/HousePage")
public class HousePageForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HouseService hsv = new HouseService();
		HousePictureService hpsv = new HousePictureService(); 
		Integer houseNO = Integer.valueOf(request.getParameter("NO"));
		HouseVO hvo = hsv.getOneHouse(houseNO);
		List<HousePictureVO> hPicsList = hpsv.getHousePictures(houseNO);
		List houseImgSrc = new LinkedList();
		for(int i = 0 ; i<hPicsList.size() ;i++){
			Map srcMap = new HashMap();
			srcMap.put("src", hPicsList.get(i).gethPicture());
			houseImgSrc.add(srcMap);
		}
		
		Map houseMap = new LinkedHashMap();
		houseMap.put("houseTitle",hvo.getHouseTitle());
		houseMap.put("cityName",hvo.getCityVO().getCityName());
		houseMap.put("boroughName",hvo.getBoroughsVO().getBoroughName());
		houseMap.put("houseAddr",hvo.getHouseAddr());
		houseMap.put("highestFloor",hvo.getHighestFloor());
		houseMap.put("nowFloor",hvo.getNowFloor());
		houseMap.put("houseRent",hvo.getHouseRent());
		houseMap.put("houseSize",hvo.getHouseSize());
		houseMap.put("houseInfo",hvo.getHouseInfo());
		houseMap.put("houseCharge",hvo.getHouseCharge());
		houseMap.put("houseVideo",hvo.getHouseVideo());
		houseMap.put("waterRate",hvo.getWaterRate());
		houseMap.put("powerRate",hvo.getPowerRate());
		houseMap.put("hForm",hvo.getHouseFormVO().gethForm());
		houseMap.put("hType",hvo.getHouseTypeVO().gethType());
		
		EquipmentConditionService eqSvc = new EquipmentConditionService();
		EquipmentConditionVO eqVO = eqSvc.getOneEquipmentCondition(houseNO);
		Map equidMap = new LinkedHashMap();
		equidMap.put("TV", eqVO.getTV());
		equidMap.put("aircondition", eqVO.getAircondition());
		equidMap.put("refrigerator", eqVO);
		equidMap.put("waterHeater", eqVO);
		equidMap.put("gas", eqVO);
		equidMap.put("theFourthStation", eqVO);
		equidMap.put("net", eqVO);
		equidMap.put("washing", eqVO);
		equidMap.put("bed", eqVO);
		equidMap.put("wardrobe", eqVO);
		equidMap.put("sofa", eqVO);
		equidMap.put("parking", eqVO);
		equidMap.put("elevator", eqVO);
		equidMap.put("balcony", eqVO);
		equidMap.put("permitCook", eqVO);
		equidMap.put("pet", eqVO);
		equidMap.put("closeMRT", eqVO);
		
		
		String houseVOJSON = JSONValue.toJSONString(houseMap);
		String hPicsJSONStr = JSONValue.toJSONString(houseImgSrc);
		request.setAttribute("House", houseVOJSON);
		request.setAttribute("hPics", hPicsJSONStr);
		request.setAttribute("eqVO", eqVO);
		RequestDispatcher rd = request.getRequestDispatcher("/HousePage.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}