package com.hermit.iii.lease.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class LeaseService {

	private LeaseDAO_interface dao;

	public LeaseService() {
		dao = new LeaseJNDIDAO();
	}

	// 新增service
	public LeaseVO addLease(Integer houseNO, java.sql.Date leaseBeginDate, java.sql.Date leaseEndDate, Integer memNO,
			Integer empNO, Integer leaseRent, Integer leaseDeposit, Integer leaseRelief, java.sql.Date leaseDate, String leasePic,
			String houseNote, Byte leaseRefund) {

		LeaseVO leaseVO = new LeaseVO();

		leaseVO.setHouseNO(houseNO);
		leaseVO.setLeaseBeginDate(leaseBeginDate);
		leaseVO.setLeaseEndDate(leaseEndDate);
		leaseVO.setMemNO(memNO);
		leaseVO.setEmpNO(empNO);
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
		leaseVO.setHouseNO(houseNO);
		leaseVO.setLeaseBeginDate(leaseBeginDate);
		leaseVO.setLeaseEndDate(leaseEndDate);
		leaseVO.setMemNO(memNO);
		leaseVO.setEmpNO(empNO);
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
}
