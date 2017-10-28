package com.hermit.iii.calendar.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface CalendarEventDAO_interface_hibernate {
	public ArrayList<CalendarEventVO_hibernate> selectByEmpAndTime(Integer empID,Timestamp start,Timestamp end);
	public boolean checkExist(Integer empID,Timestamp start,Timestamp end,Integer eventNo);
//	public Integer checkMaxID(String ID,Integer empID);
	public Integer update(CalendarEventVO_hibernate resVO);
	public Integer insert(CalendarEventVO_hibernate resVO);
	public Integer delete(int ID);
	public ArrayList<CalendarEventVO_hibernate> selectByMember(Integer memberNo);
}
