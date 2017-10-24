package com.hermit.iii.lease.model;

import java.io.InputStream;
import java.util.*;

public interface LeaseDAO_interface {

	public void insert(LeaseVO leaseVO, InputStream is, long size);

	public void update(LeaseVO leaseVO, InputStream is, long size);

	public void delete(Integer leaseNO);

	public LeaseVO findByPrimaryKey(Integer leaseNO);

	public Set<LeaseVO> getAll();
}