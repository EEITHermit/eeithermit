package com.hermit.iii.lease.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.member.model.MemberVO;

public class LeaseService {

	private LeaseDAO_interface_hibernate dao;

	public LeaseService() {
		dao = new LeaseDAO_hibernate();
	}

	// 新增service
	public LeaseVO addLease(Integer houseNO, java.sql.Date leaseBeginDate, java.sql.Date leaseEndDate, Integer memNO,
			Integer empNO, Integer leaseRent, Integer leaseDeposit, Integer leaseRelief, java.sql.Date leaseDate, String leasePic,
			String houseNote, Byte leaseRefund) {

		LeaseVO leaseVO = new LeaseVO();
		
		HouseVO houseVO = new HouseVO();
		houseVO.setHouseNO(houseNO);
		leaseVO.setHouseVO(houseVO);
		leaseVO.setLeaseBeginDate(leaseBeginDate);
		leaseVO.setLeaseEndDate(leaseEndDate);
		MemberVO memberVO=new MemberVO();
		memberVO.setMemNO(memNO);
		leaseVO.setMemberVO(memberVO);
		EmpVO empVO=new EmpVO();
		empVO.setEmpNO(empNO);
		leaseVO.setEmpVO(empVO);
		leaseVO.setLeaseRent(leaseRent);
		leaseVO.setLeaseDeposit(leaseDeposit);
		leaseVO.setLeaseRelief(leaseRelief);
		leaseVO.setLeaseDate(leaseDate);
		leaseVO.setLeasePic(leasePic);
		leaseVO.setHouseNote(houseNote);
		leaseVO.setLeaseRefund(leaseRefund);



		dao.insert(leaseVO);

		return leaseVO;
	}

	// 修改service
	public LeaseVO updateLease(Integer leaseNO, Integer houseNO, java.sql.Date leaseBeginDate,
			java.sql.Date leaseEndDate, Integer memNO, Integer empNO, Integer leaseRent, Integer leaseDeposit, Integer leaseRelief,
			java.sql.Date leaseDate, String leasePic, String houseNote, Byte leaseRefund) {

		LeaseVO leaseVO = new LeaseVO();

		leaseVO.setLeaseNO(leaseNO);
		HouseVO houseVO = new HouseVO();
		houseVO.setHouseNO(houseNO);
		leaseVO.setHouseVO(houseVO);
		leaseVO.setLeaseBeginDate(leaseBeginDate);
		leaseVO.setLeaseEndDate(leaseEndDate);
		MemberVO memberVO=new MemberVO();
		memberVO.setMemNO(memNO);
		leaseVO.setMemberVO(memberVO);
		EmpVO empVO=new EmpVO();
		empVO.setEmpNO(empNO);
		leaseVO.setEmpVO(empVO);
		leaseVO.setLeaseRent(leaseRent);
		leaseVO.setLeaseDeposit(leaseDeposit);
		leaseVO.setLeaseRelief(leaseRelief);
		leaseVO.setLeaseDate(leaseDate);
		leaseVO.setLeasePic(leasePic);
		leaseVO.setHouseNote(houseNote);
		leaseVO.setLeaseRefund(leaseRefund);

		

		dao.update(leaseVO);

		return leaseVO;
	}

	// 刪除service
	public void deleteLease(Integer leaseNO) {
		dao.delete(leaseNO);
	}

	// 查詢一筆service
	public LeaseVO getOneLease(Integer leaseNO) {
		return dao.findByPrimaryKey(leaseNO);
	}

	// 查詢全部service
	public Set<LeaseVO> getAll() {
		return dao.getAll();
	}
	//查詢房屋物件by memberNO
	public ArrayList<HouseVO> findHouseBymemNO(Integer memNO){
		return dao.findHouseBymemNO(memNO);
	}
	//查詢快過期之租賃契約
	public ArrayList<LeaseVO> getAllByBoroughNO(Integer boroughNO){
		return dao.getAllByBoroughNO(boroughNO);
	}

	public List<LeaseVO> getAllLease(Integer memNO) {
		return dao.getAllLease(memNO);
	}
	
}
