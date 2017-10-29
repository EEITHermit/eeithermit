package com.hermit.iii.emp.model;

import java.util.*;

public interface EmpDAO_interface {
	//insert
	public void insert(EmpVO_original empVO);
	//update
	public void update(EmpVO_original empVO);
	//delete
	public void delete(Integer empNO);
	//驗證帳號是否存在
	public EmpVO_original findByAccount(String empAccount);
	//select one
	public EmpVO_original findByPrimaryKey(Integer empNO);
	//select all
	public List<EmpVO_original> getAll();
	
}
