package com.hermit.iii.lease.model;

import java.util.*;

import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.housepicture.model.HousePictureVO;

public interface LeaseDAO_interface {

	public void insert(LeaseVO leaseVO);

	public void update(LeaseVO leaseVO);

	public void delete(Integer leaseNO);

	public LeaseVO findByPrimaryKey(Integer leaseNO);

	public Set<LeaseVO> getAll();
	
	
}