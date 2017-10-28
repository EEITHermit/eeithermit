package com.hermit.iii.calendar.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CalendarEventService {
	CalendarEventDAO_interface resDAO = new CalendarEventJNDIDAO();
	public ArrayList<CalendarEventVO_original> selectByEmpAndTime(Integer empID,Timestamp start,Timestamp end){
		return resDAO.selectByEmpAndTime(empID, start, end);
	};
	public boolean checkExist(Integer empID,Timestamp start,Timestamp end,Integer eventNo){
		return resDAO.checkExist(empID, start, end,eventNo);
	};
	
	public Integer update(CalendarEventVO_original resVO){
		return resDAO.update(resVO);
	};
	public Integer insert(CalendarEventVO_original resVO){
		return resDAO.insert(resVO);
	};
	public Integer delete(int ID){
		return resDAO.delete(ID);
	};
	public ArrayList<CalendarEventVO_original> selectByMember(Integer memberNo){
		return resDAO.selectByMember(memberNo);
	};
	
}
