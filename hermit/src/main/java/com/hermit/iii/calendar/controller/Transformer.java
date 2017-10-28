package com.hermit.iii.calendar.controller;

import com.hermit.iii.calendar.model.CalendarEventVO_original;

public class Transformer {
	//更改event物件變為Calender物件
	public CalendarEventVO_original eventToReservation(eventShow event){
		CalendarEventVO_original resVO = new CalendarEventVO_original();
		resVO.setEventNO(event.getId());
		resVO.setEventStartTime(event.getStart());
		resVO.setEventEndTime(event.getEnd());
		resVO.getEmpVO().setEmpNO(event.getEmpNo());
		String items[] = (event.getTitle()).split("\n");
		String member[] = items[0].split("\t");
		resVO.getMemberVO().setMemNO(Integer.valueOf(member[0]));
		String house[] = items[1].split("\t");
		resVO.getHouseVO().setHouseNO(Integer.valueOf(house[0]));
		resVO.setPs(items[2]);
		return resVO;
	}
	//更改reservation物件變為event物件
	public eventShow reservationToEvent(CalendarEventVO_original resVO){
		eventShow event = new eventShow();
		event.setId(resVO.getEventNO());
		event.setStart(resVO.getEventStartTime());
		event.setEnd(resVO.getEventEndTime());
		event.setEmpNo(resVO.getEmpVO().getEmpNO());
		String title = resVO.getMemberVO().getMemNO()+"\t"+resVO.getMemberVO().getMemName()+"\t"
					 + resVO.getMemberVO().getMemTel()+"\n"
					 +resVO.getHouseVO().getHouseNO()+"\t"+resVO.getHouseVO().getHouseAddr()+"\n"
					 +resVO.getPs();
		event.setTitle(title);
		return event;
	}
}
