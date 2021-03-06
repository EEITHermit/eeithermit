package com.hermit.iii.calendar.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface CalendarEventDAO_interface {
	public ArrayList<CalendarEventVO_original> selectByEmpAndTime(Integer empID,Timestamp start,Timestamp end);
	public boolean checkExist(Integer empID,Timestamp start,Timestamp end,Integer eventNo);
//	public Integer checkMaxID(String ID,Integer empID);
	public Integer update(CalendarEventVO_original resVO);
	public Integer insert(CalendarEventVO_original resVO);
	public Integer delete(int ID);
	public ArrayList<CalendarEventVO_original> selectByMember(Integer memberNo);
}
