package com.hermit.iii.advancedsearch.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class AdvancedSearch
 */
@WebServlet("/AdvancedSearch")
public class AdvancedSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cityNO = request.getParameter("cityNO");
		String boroughNO = request.getParameter("boroughNO");
		String typeNO = request.getParameter("typeNO");
		String formNO = request.getParameter("formNO");
		String houseSize = request.getParameter("houseSize");
		String houseRent = request.getParameter("houseRent");
		String equid = request.getParameter("equid");
		JSONObject equidObj = new JSONObject(equid);
		LinkedHashMap<String, Object> equidMap = new Gson().fromJson(equid, new TypeToken<LinkedHashMap<String, Object>>() {}.getType());
		System.out.println(equidMap.get("TV"));
		
	}

}
