package com.hermit.iii.house.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
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
import com.hermit.iii.equipmentcondition.model.EquipmentConditionService;
import com.hermit.iii.equipmentcondition.model.EquipmentConditionVO;
import com.hermit.iii.house.model.HouseService;
import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.houseform.model.HouseFormVO;
import com.hermit.iii.housepicture.model.HousePictureService;
import com.hermit.iii.housepicture.model.HousePictureVO;
import com.hermit.iii.housetype.model.HouseTypeVO;
import com.hermit.iii.util.ConvertToBase64;
import com.mchange.v2.cfg.PropertiesConfigSource.Parse;

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
		String houseVedio = null;
		if ("insertHouse".equals(action)) {
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			request.setAttribute("ErrorMsgKey", errorMsgMap);
			ConvertToBase64 ctb = new ConvertToBase64();
			HouseVO houseVO = new HouseVO();
			String strBase64 = null;
			Set<HousePictureVO> set = new HashSet<HousePictureVO>();
			houseTitle = request.getParameter("houseTitle");
			houseVO.setHouseTitle(houseTitle);
			try{
				cityNO = Integer.valueOf(request.getParameter("cityNO"));
				CityVO cityVO = new CityVO();
				cityVO.setCityNO(cityNO);
				houseVO.setCityVO(cityVO);
			}catch(Exception e){
					errorMsgMap.put("city", "請選擇縣市");
			}
			try{
				boroughNO = Integer.valueOf(request.getParameter("boroughNO"));
				BoroughsVO boroughsVO = new BoroughsVO();
				boroughsVO.setBoroughNO(boroughNO);
				houseVO.setBoroughsVO(boroughsVO);
			}catch(Exception e){
				errorMsgMap.put("borough", "請選擇地區");
			}
			
			previewPic = request.getParameter("previewPic");
			houseVO.setPreviewPic(previewPic);
			try{
			highestFloor = Integer.valueOf(request.getParameter("highestFloor"));
			houseVO.setHighestFloor(highestFloor);
			}catch(Exception e){
				errorMsgMap.put("highestFloor", "請勿空白");
			}
			try{
			nowFloor = Integer.valueOf(request.getParameter("nowFloor"));
			houseVO.setNowFloor(nowFloor);
			}catch(Exception e){
				errorMsgMap.put("nowFloor", "請勿空白");
			}
			houseStatus = request.getParameter("houseStatus");
			houseVO.setHouseStatus(houseStatus);
			try{
			houseRent = Integer.valueOf(request.getParameter("houseRent"));
			houseVO.setHouseRent(houseRent);
			}catch(Exception e){
				errorMsgMap.put("houseRent", "請勿空白");
			}
			try{
			houseCharge = Integer.valueOf(request.getParameter("houseCharge"));
			houseVO.setHouseCharge(houseCharge);
			}catch(Exception e){
				errorMsgMap.put("houseCharge", "請勿空白");
			}
			waterRate = request.getParameter("waterRate");
			houseVO.setWaterRate(waterRate);
			powerRate = request.getParameter("powerRate");
			houseVO.setPowerRate(powerRate);
			houseVideo = request.getParameter("houseVideo");
			houseVO.setHouseVideo(houseVideo);
			try{
			typeNO = Integer.valueOf(request.getParameter("typeNO"));
			HouseTypeVO typeVO = new HouseTypeVO();
			typeVO.setTypeNO(typeNO);
			houseVO.setHouseTypeVO(typeVO);
			}catch(Exception e){
				errorMsgMap.put("typeNO", "請勿空白");
			}
			try{
			formNO = Integer.valueOf(request.getParameter("formNO"));
			HouseFormVO formVO = new HouseFormVO();
			formVO.setFormNO(formNO);
			houseVO.setHouseFormVO(formVO);
			}catch(Exception e){
				errorMsgMap.put("formNO", "請勿空白");
			}
			houseAddr = request.getParameter("houseAddr");
			houseVO.setHouseAddr(houseAddr);
			try{
			houseSize = Double.valueOf(request.getParameter("houseSize"));
			houseVO.setHouseSize(houseSize);
			houseInfo=request.getParameter("houseInfo");
			houseVO.setHouseInfo(houseInfo);
			}catch(Exception e){
				errorMsgMap.put("houseSize","請勿空白");
			}
			houseVideo = request.getParameter("houseVideo");
			houseVO.setHouseVideo(houseVideo);
			Collection<Part> parts = request.getParts();
			for (Part item : request.getParts()) {
				strBase64 = ctb.encode(item);
				if (strBase64 != null) {
					if(strBase64.length() >= 40){
						// System.out.println(strBase64);
						HousePictureVO picVO = new HousePictureVO();
						picVO.sethPicture(strBase64);
						set.add(picVO);
					}
				}

			}
			
			EquipmentConditionService eqsvc=new EquipmentConditionService();
			Byte TV = 0;
			if("on".equals(request.getParameter("TV"))){
				TV = 1;
			}
			Byte aircondition=0;
			if("on".equals(request.getParameter("aircondition"))){
				aircondition=1;
			}
			Byte refrigerator=0;
			
			if("on".equals(request.getParameter("refrigerator"))){
				refrigerator=1;
			}
			Byte waterHeater=0;
			if("on".equals(request.getParameter("waterHeater"))){
				waterHeater=1;
			}
			Byte gas=0;
			if("on".equals(request.getParameter("gas"))){
				gas=1;
			}
			Byte theFourthStation=0;
			if("on".equals(request.getParameter("theFourthStation"))){
				theFourthStation=1;
			}
			Byte net=0;
			if("on".equals(request.getParameter("net"))){
						net=1;
			}
			Byte washing=0;
			if("on".equals(request.getParameter("washing"))){
				washing=1;
			}
			Byte bed=0;
			if("on".equals(request.getParameter("bed"))){
				bed=1;
			}
			Byte wardrobe=0;
			if("on".equals(request.getParameter("wardrobe"))){
				wardrobe=1;
			}
			Byte sofa=0;
			if("on".equals(request.getParameter("sofa"))){
				sofa=1;
			}
			Byte parking=0;
			if("on".equals(request.getParameter("parking"))){
				parking=1;
			}
			Byte elevator=0;
			if("on".equals(request.getParameter("elevator"))){
				elevator=1;
			}
			Byte balcony=0;
			if("on".equals(request.getParameter("balcony"))){
				balcony=1;
			}
			Byte permitCook=0;
			if("on".equals(request.getParameter("permitCook"))){
						permitCook=1;
			}
			Byte pet=0;
			if("on".equals(request.getParameter("pet"))){
				pet=1;
			}
			Byte closeMRT=0;
			if("on".equals(request.getParameter("closeMRT"))){
				closeMRT=1;
			}
			
			//放錯誤訊息
			if (houseTitle.trim().length() == 0||houseTitle==null) {
				errorMsgMap.put("title", "請勿空白");
			}
			
			if(waterRate.trim().length()==0||waterRate==null){
				errorMsgMap.put("waterRate", "請勿空白");
			}
			if(powerRate.trim().length()==0||powerRate==null){
				errorMsgMap.put("powerRate", "請勿空白");
			}
			if(houseAddr.trim().length()==0||houseAddr==null){
				errorMsgMap.put("houseAddr", "請勿空白");
			}
			
			if(!errorMsgMap.isEmpty()){
				RequestDispatcher failureView = request.getRequestDispatcher("/House/House_management.jsp");
				failureView.forward(request, response);
				return;
			}
			
			houseNO= svc.insertHouseAndHousePicture(houseVO, set);
			eqsvc.addEquipmentCondition(houseNO, TV, aircondition, refrigerator, waterHeater, gas, theFourthStation, net, washing, bed, wardrobe, sofa, parking, elevator, balcony, permitCook, pet, closeMRT);
			response.sendRedirect("/hermit/House/House_management.jsp");
			return;
			
		}
		if("updateHouseStatus".equals(action)){
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			request.setAttribute("ErrorMsgKey", errorMsgMap);
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
			
			EquipmentConditionService eqsvc=new EquipmentConditionService();
			Byte TV=0;
			if("on".equals(request.getParameter("TV"))){
				TV=1;
			}
			Byte aircondition=0;
			if("on".equals(request.getParameter("aircondition"))){
				aircondition=1;
			}
			Byte refrigerator=0;
			if("on".equals(request.getParameter("refrigerator"))){
				refrigerator=1;
			}
			Byte waterHeater=0;
			if("on".equals(request.getParameter("waterHeater"))){
				waterHeater=1;
			}
			Byte gas=0;
			if("on".equals(request.getParameter("gas"))){
				gas=1;
			}
			Byte theFourthStation=0;
			if("on".equals(request.getParameter("theFourthStation"))){
				theFourthStation=1;
			}
			Byte net=0;
			if("on".equals(request.getParameter("net"))){
				net=1;
			}
			Byte washing=0;
			if("on".equals(request.getParameter("washing"))){
				washing=1;
			}
			Byte bed=0;
			if("on".equals(request.getParameter("bed"))){
				bed=1;
			}
			Byte wardrobe=0;
			if("on".equals(request.getParameter("wardrobe"))){
				wardrobe=1;
			}
			Byte sofa=0;
			if("on".equals(request.getParameter("sofa"))){
				sofa=1;
			}
			Byte parking=0;
			if("on".equals(request.getParameter("parking"))){
				parking=1;
			}
			Byte elevator=0;
			if("on".equals(request.getParameter("elevator"))){
				elevator=1;
			}
			Byte balcony=0;
			if("on".equals(request.getParameter("balcony"))){
				balcony=1;
			}
			Byte permitCook=0;
			if("on".equals(request.getParameter("permitCook"))){
				permitCook=1;
			}
			Byte pet=0;
			if("on".equals(request.getParameter("pet"))){
				pet=1;
			}
			Byte closeMRT=0;
			if("on".equals(request.getParameter("closeMRT"))){
				closeMRT=1;
			}
			
			svc.updateHouse(houseNO, houseTitle, cityNO, boroughNO, previewPic, highestFloor, nowFloor, houseStatus,
					houseRent, houseCharge, waterRate, powerRate, houseVideo, typeNO, formNO, houseAddr, houseSize,houseInfo);
			eqsvc.updateEquipmentCondition(houseNO, TV, aircondition, refrigerator, waterHeater, gas, theFourthStation, net, washing, bed, wardrobe, sofa, parking, elevator, balcony, permitCook, pet, closeMRT);
			
			response.sendRedirect("/hermit/House/HouseStatusChange.jsp");
			return;
		}
		if ("updateHouse".equals(action)) {
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			request.setAttribute("ErrorMsgKey", errorMsgMap);
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
			
			
			
			EquipmentConditionService eqsvc=new EquipmentConditionService();
			Byte TV=0;
			if("on".equals(request.getParameter("TV"))){
				TV=1;
			}
			Byte aircondition=0;
			if("on".equals(request.getParameter("aircondition"))){
				aircondition=1;
			}
			Byte refrigerator=0;
			if("on".equals(request.getParameter("refrigerator"))){
				refrigerator=1;
			}
			Byte waterHeater=0;
			if("on".equals(request.getParameter("waterHeater"))){
				waterHeater=1;
			}
			Byte gas=0;
			if("on".equals(request.getParameter("gas"))){
				gas=1;
			}
			Byte theFourthStation=0;
			if("on".equals(request.getParameter("theFourthStation"))){
				theFourthStation=1;
			}
			Byte net=0;
			if("on".equals(request.getParameter("net"))){
				net=1;
			}
			Byte washing=0;
			if("on".equals(request.getParameter("washing"))){
				washing=1;
			}
			Byte bed=0;
			if("on".equals(request.getParameter("bed"))){
				bed=1;
			}
			Byte wardrobe=0;
			if("on".equals(request.getParameter("wardrobe"))){
				wardrobe=1;
			}
			Byte sofa=0;
			if("on".equals(request.getParameter("sofa"))){
				sofa=1;
			}
			Byte parking=0;
			if("on".equals(request.getParameter("parking"))){
				parking=1;
			}
			Byte elevator=0;
			if("on".equals(request.getParameter("elevator"))){
				elevator=1;
			}
			Byte balcony=0;
			if("on".equals(request.getParameter("balcony"))){
				balcony=1;
			}
			Byte permitCook=0;
			if("on".equals(request.getParameter("permitCook"))){
				permitCook=1;
			}
			Byte pet=0;
			if("on".equals(request.getParameter("pet"))){
				pet=1;
			}
			Byte closeMRT=0;
			if("on".equals(request.getParameter("closeMRT"))){
				closeMRT=1;
			}
			
			svc.updateHouse(houseNO, houseTitle, cityNO, boroughNO, previewPic, highestFloor, nowFloor, houseStatus,
					houseRent, houseCharge, waterRate, powerRate, houseVideo, typeNO, formNO, houseAddr, houseSize,houseInfo);
			eqsvc.updateEquipmentCondition(houseNO, TV, aircondition, refrigerator, waterHeater, gas, theFourthStation, net, washing, bed, wardrobe, sofa, parking, elevator, balcony, permitCook, pet, closeMRT);
			
			response.sendRedirect("/hermit/House/House_management.jsp");
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
		if("getOneHouseStatus".equals(action)){
			houseNO=Integer.valueOf(request.getParameter("houseNO"));
			svc=new HouseService();
			vo=svc.GET_ONE_HOUSE_FK(houseNO);
			request.setAttribute("vo", vo);
			RequestDispatcher rd = request.getRequestDispatcher("/House/SingleHouseStatusUpdate.jsp");
			rd.forward(request, response);
		}
		if ("getOneHouse_FK".equals(action)) {
			houseNO = Integer.valueOf(request.getParameter("houseNO"));
			svc = new HouseService();
			vo = svc.GET_ONE_HOUSE_FK(houseNO);
			request.setAttribute("vo", vo);
			//測試↓
			
			HousePictureService Picsvc =new HousePictureService();
			List<HousePictureVO>list=Picsvc.getHousePictures(houseNO);
			List housePic=new LinkedList();
			for(int i=0;i<list.size();i++){
				Map m1=new HashMap();
				m1.put("housepic", list.get(i).gethPicture());
				m1.put("housePictureNO", list.get(i).getHousePictureNO());
				housePic.add(m1);
			}
			String hPicJSON=JSONValue.toJSONString(housePic);
			request.setAttribute("hPics",hPicJSON);
			
//			EquipmentConditionService eqsvc=new EquipmentConditionService();
//			EquipmentConditionVO eqvo=null;
//			eqvo=eqsvc.getOneEquipmentCondition(houseNO);
//			request.setAttribute("eqhouse", eqvo);
//			System.out.println(eqvo.getTV());
			//↑測試
			
//			for(HousePictureVO hVO:vo.getHousePictureVO()){
//				System.out.println(hVO.gethPicture());
//			}
			
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
		if ("getOneHouseToJSON".equals(action)) {
			svc = new HouseService();
			vo = svc.getOneHouse(Integer.valueOf(request.getParameter("houseNO")));
			Map m1 = new HashMap();
			String address = vo.getCityVO().getCityName()+vo.getBoroughsVO().getBoroughName()+vo.getHouseAddr();
			m1.put("houseAddress", address);
			m1.put("borough", vo.getBoroughsVO().getBoroughNO());
			PrintWriter out = response.getWriter();
			String addrJSON = JSONValue.toJSONString(m1);
			out.print(addrJSON);
			out.flush();
			return;
		}
		
		if("getThree".equals(action)){
			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			List<HouseVO> list = svc.getNewThree();
			List getThree = new LinkedList();
			for(int i=0;i<3;i++){
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
				m1.put("previewPic", vo.getPreviewPic());
				m1.put("hType", vo.getHouseTypeVO().gethType());
				m1.put("hForm", vo.getHouseFormVO().gethForm());
				m1.put("houseAddr", vo.getHouseAddr());
				m1.put("houseSize", vo.getHouseSize());
				m1.put("hType", vo.getHouseTypeVO().gethType());
				m1.put("hForm", vo.getHouseFormVO().gethForm());
				m1.put("houseInfo", vo.getHouseInfo());
				getThree.add(m1);
			}
			Map m2 = new HashMap();
			m2.put("newHouse", getThree);
			String newThree = JSONValue.toJSONString(m2);
			out.print(newThree);
			out.flush();
			return;
		}
		
		if("getHotHouse".equals(action)){
			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			List<HouseVO> list = svc.getHotHouse();
			List getThree = new LinkedList();
			for(int i=0;i<3;i++){
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
				m1.put("previewPic", vo.getPreviewPic());
				m1.put("hType", vo.getHouseTypeVO().gethType());
				m1.put("hForm", vo.getHouseFormVO().gethForm());
				m1.put("houseAddr", vo.getHouseAddr());
				m1.put("houseSize", vo.getHouseSize());
				m1.put("hType", vo.getHouseTypeVO().gethType());
				m1.put("hForm", vo.getHouseFormVO().gethForm());
				m1.put("houseInfo", vo.getHouseInfo());
				getThree.add(m1);
			}
			Map m2 = new HashMap();
			m2.put("newHouse", getThree);
			String newThree = JSONValue.toJSONString(m2);
			out.print(newThree);
			out.flush();
			return;
		}
		if("queryHouse".equals(action)){
			HouseService housedao=new HouseService();
			houseNO=Integer.valueOf(request.getParameter("houseNO"));
			vo=housedao.getOneHouse(houseNO);
			String cityName=vo.getCityVO().getCityName();
			String boroughName=vo.getBoroughsVO().getBoroughName();
			houseAddr=vo.getHouseAddr();
			String Addr=cityName+boroughName+houseAddr;
			PrintWriter out = response.getWriter();
			out.print(Addr);
			out.flush();
			return;
		}
		if("queryRent".equals(action)){
			HouseService housedao=new HouseService();
			houseNO=Integer.valueOf(request.getParameter("houseNO"));
			vo=housedao.getOneHouse(houseNO);
			houseRent=vo.getHouseRent();
			PrintWriter out = response.getWriter();
			out.print(houseRent);
			out.flush();
			return;
		}
		if("queryStatus".equals(action)){
			HouseService housedao=new HouseService();
			houseNO=Integer.valueOf(request.getParameter("houseNO"));
			vo=housedao.getOneHouse(houseNO);
			houseStatus=vo.getHouseStatus();
			PrintWriter out = response.getWriter();
			out.print(houseStatus);
			out.flush();
			return;
		}

	}

}
