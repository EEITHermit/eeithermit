package com.hermit.iii.lease.model;

import java.util.*;

import com.hermit.iii.house.model.HouseVO;

public interface LeaseDAO_interface_hibernate {

	public void insert(LeaseVO leaseVO);

	public void update(LeaseVO leaseVO);

	public void delete(Integer leaseNO);

	public LeaseVO findByPrimaryKey(Integer leaseNO);

	public Set<LeaseVO> getAll();
	
	public ArrayList<HouseVO> findHouseBymemNO(Integer memNO);
	//漢勳加
	public ArrayList<LeaseVO> getAllByBoroughNO(Integer boroughNO);
}
