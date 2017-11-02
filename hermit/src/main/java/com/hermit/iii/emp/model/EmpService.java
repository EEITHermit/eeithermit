package com.hermit.iii.emp.model;

import java.util.List;

import com.hermit.iii.post.model.PostVO;

public class EmpService {
	EmpDAO_interface_hibernate dao;

	public EmpService() {
		dao = new EmpDAO_hibernate();
	}

	public void insertEmp(String empAccount, String empPwd, String empPhone, String empName, Integer postNO, Boolean empStatus) {
		EmpVO empVO = new EmpVO();
		empVO.setEmpAccount(empAccount);
		empVO.setEmpPwd(empPwd);
		empVO.setEmpPhone(empPhone);
		empVO.setEmpName(empName);
		PostVO postVO = new PostVO();
		postVO.setPostNO(postNO);
		empVO.setPostVO(postVO);
		empVO.setEmpStatus(empStatus);
		dao.insert(empVO);
	}

	public void updateEmp(Integer empNO, String empAccount, String empPwd, String empPhone, String empName,	Integer postNO, Boolean empStatus) {
		EmpVO empVO = new EmpVO();
		empVO.setEmpNO(empNO);
		empVO.setEmpAccount(empAccount);
		empVO.setEmpPwd(empPwd);
		empVO.setEmpPhone(empPhone);
		empVO.setEmpName(empName);
		PostVO postVO = new PostVO();
		postVO.setPostNO(postNO);
		empVO.setPostVO(postVO);
		empVO.setEmpStatus(empStatus);
	}

	public void deleteEmp(Integer empNO) {
		dao.delete(empNO);
	}

	public EmpVO getOneEmp(Integer empNO) {
		return dao.findByPrimaryKey(empNO);
	}

	public List<EmpVO> getAllEmp() {
		return dao.getAll();
	}
	
	public EmpVO findByAccount(String empAccount){
		return dao.findByAccount(empAccount);
	}

	public String getAllForJson() {
		return dao.getAllForJson();
	}
}
