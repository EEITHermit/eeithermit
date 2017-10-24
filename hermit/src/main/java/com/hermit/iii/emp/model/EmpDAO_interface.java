package com.hermit.iii.emp.model;

import java.util.*;

public interface EmpDAO_interface {
	//insert
	public void insert(EmpVO empVO);
	//update
	public void update(EmpVO empVO);
	//delete
	public void delete(Integer empNO);
	//驗證帳號是否存在
	public EmpVO findByAccount(String empAccount);
	//select one
	public EmpVO findByPrimaryKey(Integer empNO);
	//select all
	public List<EmpVO> getAll();
	
}
