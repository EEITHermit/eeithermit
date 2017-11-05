package com.hermit.iii.emp.model;

import java.util.*;

public interface EmpDAO_interface_hibernate {
	public void insert(EmpVO empVO);
	public void update(EmpVO empVO);
	public void delete(Integer empNO);
	public EmpVO findByPrimaryKey(Integer empNO);
	public List<EmpVO> getAll();
	public String getAllForJson();
	public List<EmpVO> getByPost(Integer post);
	/*** 自訂指令 ***/
	// 驗證帳號是否存在
	public EmpVO findByAccount(String empAccount);
}
