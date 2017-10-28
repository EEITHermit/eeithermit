package com.hermit.iii.lease.model;

import java.util.*;

public interface LeaseDAO_interface_hibernate {

	public void insert(LeaseVO_hibernate leaseVO_hibernate);

	public void update(LeaseVO_hibernate leaseVO_hibernate);

	public void delete(Integer leaseNO);

	public LeaseVO_hibernate findByPrimaryKey(Integer leaseNO);

	public Set<LeaseVO_hibernate> getAll();
}
