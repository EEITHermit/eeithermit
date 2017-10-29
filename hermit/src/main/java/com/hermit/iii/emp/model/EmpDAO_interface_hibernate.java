package com.hermit.iii.emp.model;

import java.util.*;

public interface EmpDAO_interface_hibernate {

	public void insert(EmpVO empVO);

	public void update(EmpVO empVO);

	public void delete(Integer empNO);

	public EmpVO findByPrimaryKey(Integer empNO);

	public Set<EmpVO> getAll();

	/*** 自訂指令 ***/
	// 驗證帳號是否存在
	public EmpVO findByAccount(String empAccount);
}
