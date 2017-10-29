package com.hermit.iii.calendar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hermit.iii.calendar.model.CalendarEventService;
import com.hermit.iii.calendar.model.CalendarEventVO;
import com.hermit.iii.house.model.HouseService;
import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.member.model.*;


@WebServlet("/calendarServlet")
public class calendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   public calendarServlet() {
        super();
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mission = request.getParameter("mission");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Transformer transformer = new Transformer();
		CalendarEventService rs = new CalendarEventService();
		if("query".equals(mission)){
			int empNo = Integer.valueOf(request.getParameter("empNo")); //取得員工ID
			String startTime = request.getParameter("STime");
			String endTime = request.getParameter("ETime");
			Timestamp start = Timestamp.valueOf(startTime);
			Timestamp end = Timestamp.valueOf(endTime);
			ArrayList<eventShow> events = new ArrayList<eventShow>();
			ArrayList<CalendarEventVO> array = rs.selectByEmpAndTime(Integer.valueOf(empNo), start, end);
			for(CalendarEventVO resVO:array){
				eventShow event = transformer.reservationToEvent(resVO);
				events.add(event);
			}
			String JSONString = (new JSONArray(events)).toString();
			out.print(JSONString);
			return;
		}else if("queryMember".equals(mission)){
			MemberJNDIDAO mhDAO = new MemberJNDIDAO();
			String name = request.getParameter("member");
			ArrayList<String> arrayR = new ArrayList<String>();
			ArrayList<MemberVO> array = mhDAO.autoCompleteM(name);
			for(MemberVO memberVO : array){
				String result  = memberVO.getMemNO()+"\t"+memberVO.getMemName()+"\t"+memberVO.getMemTel();
				arrayR.add(result);
			}
			out.print(new JSONArray(arrayR));  //中文自必須先行編碼
			return;
		}else if("queryHouse".equals(mission)){
			HouseService mhDAO = new HouseService();
			String address = request.getParameter("house");
			ArrayList<String> arrayR = new ArrayList<String>();
			ArrayList<HouseVO> array = mhDAO.autoCompleteH(address);
			for(HouseVO houseVO : array){
				String result = houseVO.getHouseNO()+"\t"+houseVO.getHouseAddr();
				arrayR.add(result);
			}
			out.print(new JSONArray(arrayR)); //中文自必須先行編碼
			return;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mission = request.getParameter("mission");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		PrintWriter out = response.getWriter();
		CalendarEventService rs = new CalendarEventService();
		if("update".equals(mission)){ // 任務為更新
			int result = 0;
			String events = request.getParameter("events");
			int empNo = Integer.valueOf(request.getParameter("empNo")); //取得員工ID
			JSONArray array = new JSONArray(events);
			Transformer transformer = new Transformer();
			for(int i=0;i<array.length();i++){
				eventShow event = new eventShow();
				event.setEmpNo(empNo); //先存入empNO
				JSONObject object = array.getJSONObject(i);
				Integer id = Integer.valueOf(object.getString("id"));
				event.setId(id);
				event.setStart(Timestamp.valueOf(object.getString("start")));
				event.setEnd(Timestamp.valueOf(object.getString("end")));
				event.setTitle(object.getString("title"));
				//轉換event to reservation 並呼叫update
				CalendarEventVO resVO = transformer.eventToReservation(event);
				//確認時間內無事件，無事件後可繼續更新
				if(rs.checkExist(event.getEmpNo(),event.getStart(),event.getEnd(),id)){
					out.print("此時段已有行程");
					return;
				}else{
					result = rs.update(resVO);
					if(result == 0){	//若出現更新未成功，則停止更新
						out.print("更改失敗");
						return;
					}
				}
			}
			if(result == 1){
				out.print("更改成功");
				return;
			}
			
		}else if("delete".equals(mission)){  //任務為刪除
			int id = Integer.valueOf(request.getParameter("id"));
			if(rs.delete(id) == 1){
				out.print("刪除成功");
			}else{
				out.print("未刪除資料");
			}
			return;
		}else if("insert".equals(mission)){ //任務為新增
			int result = 0;
			int empNo = Integer.valueOf(request.getParameter("empNo")); //取得員工ID
			String member = request.getParameter("member");
			String start = request.getParameter("startTime");
			String end = request.getParameter("endTime");
			String house = request.getParameter("house");
			String ps = request.getParameter("ps");
			Transformer transformer = new Transformer();
			eventShow event = new eventShow();
			event.setEmpNo(empNo); //先存入empNO
			event.setStart(Timestamp.valueOf(start));
			event.setEnd(Timestamp.valueOf(end));
			event.setTitle(member+"\n"+house+"\n"+ps);
			//轉換event to reservation 並呼叫update
			CalendarEventVO resVO = transformer.eventToReservation(event);
			//確認時間內無事件，無事件後可繼續更新
			if(!(rs.checkExist(event.getEmpNo(),event.getStart(),event.getEnd(),0))){
				result = rs.insert(resVO);
				if(result == 1){
					out.print("新增成功");
					return;
				}else{
					out.print("新增失敗");
					return;
				}
			}else{
				out.print("此時段已有行程");
				return;
			}
		}
	}
}
