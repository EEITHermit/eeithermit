package com.hermit.iii.equipmentcondition.contorller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONString;
import org.json.simple.JSONValue;

import com.hermit.iii.equipmentcondition.model.EquipmentConditionService;
import com.hermit.iii.equipmentcondition.model.EquipmentConditionVO;
import com.hermit.iii.member.model.MemberVO;
import com.hermit.iii.util.DefaultImage;

/**
 * Servlet implementation class EquipmentConditionServlet
 */
@WebServlet("/EquipmentConditionServlet")
public class EquipmentConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		
		if("getAllEquip".equals(action)){
			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			EquipmentConditionService eqSvc = new EquipmentConditionService();
			Set<EquipmentConditionVO> set = eqSvc.getAll();
			List jsonList = new LinkedList();
			PrintWriter out = response.getWriter();
			for (EquipmentConditionVO eq : set) {
				Map m1 = new LinkedHashMap();
				m1.put("TV", eq.getTV());	//電視
				m1.put("aircondition", eq.getAircondition()); //冷氣
				m1.put("refrigerator",eq.getRefrigerator());//冰箱
				m1.put("waterHeater",eq.getWaterHeater());//熱水器
				m1.put("gas",eq.getGas());//瓦斯
				m1.put("theFourthStation",eq.getTheFourthStation());// 第四台
				m1.put("net",eq.getNet());//網路
				m1.put("washing",eq.getWashing());//洗衣機
				m1.put("bed",eq.getBed());//床
				m1.put("wardrobe",eq.getWardrobe());//衣櫃
				m1.put("sofa",eq.getSofa());//沙發
				m1.put("parking",eq.getParking());//停車位
				m1.put("elevator",eq.getElevator());//電梯
				m1.put("balcony",eq.getBalcony());//陽台
				m1.put("permitCook",eq.getPermitCook());//可開伙
				m1.put("pet",eq.getPet());//可養寵物
				m1.put("closeMRT",eq.getCloseMRT());//近捷運
				jsonList.add(m1);
			}
			Map m2 = new LinkedHashMap();
			m2.put("eqList", jsonList);
			String eqStr = JSONValue.toJSONString(m2);
			out.println(eqStr);
			out.flush();
			out.close();
		}
	}
}
