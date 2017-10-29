package com.hermit.iii.reservation.model;

import java.util.ArrayList;

public class ReservationService {
	ReservationDAO_interface_hibernate rlDAO = new ReservationJNDIDAO_hibernate();
	public Integer insert(ReservationVO rlVO){
		return rlDAO.insert(rlVO);
	}
	public ArrayList<ReservationVO> selectByArea(Integer areaNo){
		return rlDAO.selectByArea(areaNo);
	}
	public Integer updateStatus(Integer reservationNo,Integer empNo){
		return rlDAO.updateStatus(reservationNo, empNo);
	}
	
	public boolean checkExist(Integer houseNo,Integer memberNo){
		return rlDAO.checkExist(houseNo, memberNo);
	};
}
