package com.hermit.iii.lease.model;

import java.util.*;

public interface LeaseDAO_interface {

	public void insert(LeaseVO leaseVO);

	public void update(LeaseVO leaseVO);

	public void delete(Integer leaseNO);

	public LeaseVO findByPrimaryKey(Integer leaseNO);

	public Set<LeaseVO> getAll();
}