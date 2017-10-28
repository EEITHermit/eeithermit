package com.hermit.iii.reservation.model;

import java.util.ArrayList;

public interface ReservationDAO_interface_hibernate {
	public Integer insert(ReservationVO rlVO);
	public ArrayList<ReservationVO> selectByArea(Integer areaNo);
	public Integer updateStatus(Integer reservationNo,Integer empNo);
	public boolean checkExist(Integer houseNo,Integer memberNo);
}
