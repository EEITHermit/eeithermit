package com.hermit.iii.advancedsearch.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hermit.iii.house.model.HouseService;

@WebServlet("/AdvancedSearch")
public class AdvancedSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String searchStr =  
				"SELECT DISTINCT h.houseNO,h.houseTitle,c.cityName,b.boroughName,h.previewPic,h.highestFloor,h.nowFloor,h.houseRent,t.hType,f.hForm,h.houseAddr,h.houseSize "
				+ "FROM house h JOIN equipmentCondition eq ON h.houseNO = eq.houseNO JOIN City c ON h.cityNO = c.cityNO JOIN Boroughs b ON h.boroughNO = b.boroughNO JOIN HouseType t ON h.typeNO = t.typeNO JOIN HouseForm f ON h.formNO = f.formNO "
				+ "WHERE h.houseStatus = '未出租'";
		
		String houseTitle = request.getParameter("houseTitle");
		Integer cityNO = Integer.valueOf(request.getParameter("cityNO"));
		Integer boroughNO = Integer.valueOf(request.getParameter("boroughNO"));
		Integer typeNO = Integer.valueOf(request.getParameter("typeNO"));
		Integer formNO = Integer.valueOf(request.getParameter("formNO"));
		Integer houseSize = Integer.valueOf(request.getParameter("houseSize"));
		Integer houseRent = Integer.valueOf(request.getParameter("houseRent"));
		String equid = request.getParameter("equid");

		if((houseTitle.length()!= 0 ) && (houseTitle != null)){
			searchStr = searchStr + " and (h.houseTitle like '%" + houseTitle +"%')";
			session.setAttribute("houseTitle",houseTitle);
		}
		
		if((!cityNO.equals(-1)) && (cityNO != null)){
			searchStr = searchStr + " and (h.cityNO = " + cityNO.toString() +")";
			session.setAttribute("cityNO",cityNO);
		}else{
			session.setAttribute("cityNO",cityNO);
		}
		
		if((!boroughNO.equals(-1)) && (boroughNO != null)){
			searchStr = searchStr + " and (h.boroughNO = " + boroughNO.toString() +")";
			session.setAttribute("boroughNO",boroughNO);
		}else{
			session.setAttribute("boroughNO",boroughNO);
		}
		
		if((!typeNO.equals(-1)) && typeNO != null){
			searchStr = searchStr + " and (h.typeNO = " + typeNO.toString() +")";
			session.setAttribute("typeNO",typeNO);
		}else{
			session.setAttribute("typeNO",typeNO);
		}
		
		if((!formNO.equals(-1)) && formNO != null){
			searchStr = searchStr + " and (h.formNO = " + formNO.toString() +")";
			session.setAttribute("formNO",formNO);
		}else{
			session.setAttribute("formNO",formNO);
		}
		
		if((!houseSize.equals(-1)) && (houseSize != null) ){
			session.setAttribute("houseSize",houseSize);
			switch(houseSize){
				case 0:
					searchStr = searchStr +" and (h.houseSize BETWEEN 0 AND 10)";
					break;
				case 1:
					searchStr = searchStr +" and (h.houseSize BETWEEN 10 AND 15)";
					break;
				case 2:
					searchStr = searchStr +" and (h.houseSize BETWEEN 15 AND 20)";
					break;
				case 3:
					searchStr = searchStr +" and (h.houseSize BETWEEN 20 AND 30)";
					break;
				case 4:
					searchStr = searchStr +" and (h.houseSize > 30)";
					break;
				default:
					searchStr = searchStr +" and (houseSize > 0)";
					break;
			}
		}else{
			session.setAttribute("houseSize",houseSize);
		}
		
		if(houseRent != null){
			session.setAttribute("houseRent",houseRent);
			switch(houseRent){
			case 0:
				searchStr = searchStr +" and (h.houseRent > 0)";
				break;
			case 1:
				searchStr = searchStr +" and (h.houseRent BETWEEN 0 AND 5000)";
				break;
			case 2:
				searchStr = searchStr +" and (h.houseRent BETWEEN 5000 AND 10000)";
				break;
			case 3:
				searchStr = searchStr +" and (h.houseRent BETWEEN 10000 AND 20000)";
				break;
			case 4:
				searchStr = searchStr +" and (h.houseRent BETWEEN 20000 AND 30000)";
				break;
			case 5:
				searchStr = searchStr +" and (h.houseRent > 30000)";
				break;
			default:
				searchStr = searchStr +" and (h.houseRent > 0)";
				break;
			}
		}
		
		if(equid != null){
			session.setAttribute("equid",equid);
			JSONObject equidObj = new JSONObject(equid);
			JSONArray nameAry = equidObj.names();
			JSONArray valAry = equidObj.toJSONArray(nameAry);
			for(int i =0;i<valAry.length();i++){
				if(valAry.getBoolean(i)){
					searchStr = searchStr + " and (eq." + nameAry.getString(i) +" = 1)";
				}
			}
		}
		HouseService hsv = new HouseService();
		session.setAttribute("houseItems",hsv.advencedSearch(searchStr));
	}
	
}
