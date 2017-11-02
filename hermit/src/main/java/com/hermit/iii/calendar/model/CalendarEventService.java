package com.hermit.iii.calendar.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CalendarEventService {
	CalendarEventDAO_interface_hibernate resDAO = new CalendarEventJNDIDAO_hibernate();
	public ArrayList<CalendarEventVO> selectByEmpAndTime(Integer empID,Timestamp start,Timestamp end){
		return resDAO.selectByEmpAndTime(empID, start, end);
	};
	public boolean checkExist(Integer empID,Timestamp start,Timestamp end,Integer eventNo){
		return resDAO.checkExist(empID, start, end,eventNo);
	};
	
	public Integer update(CalendarEventVO resVO){
		return resDAO.update(resVO);
	};
	public Integer insert(CalendarEventVO resVO){
		return resDAO.insert(resVO);
	};
	public Integer delete(int ID){
		return resDAO.delete(ID);
	};
	public ArrayList<CalendarEventVO> selectByMember(Integer memberNo){
		return resDAO.selectByMember(memberNo);
	};
	public Integer deleteNotice(Integer eventNO,String ps){
		return resDAO.deleteNotice(eventNO,ps);
	};
	public ArrayList<CalendarEventVO> selectDeleteNotice(Integer empNO){
		return resDAO.selectDeleteNotice(empNO);
	}
	
}
