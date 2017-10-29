package com.hermit.iii.reservation.model;

import java.util.ArrayList;

public interface ReservationDAO_interface {
	public Integer insert(ReservationVO_original rlVO);
	public ArrayList<ReservationVO_original> selectByArea(Integer areaNo);
	public Integer updateStatus(Integer reservationNo,Integer empNo);
	public boolean checkExist(Integer houseNo,Integer memberNo);
}
