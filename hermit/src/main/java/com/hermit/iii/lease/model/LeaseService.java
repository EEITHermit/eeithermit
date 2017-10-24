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
			Integer empNO, Integer Rent, Integer Deposit, Integer Relief, java.sql.Date leaseDate, InputStream is,
			String houseNote, Byte Refund) {

		LeaseVO leaseVO = new LeaseVO();

		leaseVO.setHouseNO(houseNO);
		leaseVO.setLeaseBeginDate(leaseBeginDate);
		leaseVO.setLeaseEndDate(leaseEndDate);
		leaseVO.setMemNO(memNO);
		leaseVO.setEmpNO(empNO);
		leaseVO.setRent(Rent);
		leaseVO.setDeposit(Deposit);
		leaseVO.setRelief(Relief);
		leaseVO.setLeaseDate(leaseDate);
		leaseVO.setHouseNote(houseNote);
		leaseVO.setRefund(Refund);

		int size;
		try {
			size = is.available();
		} catch (IOException e) {
			size = 0;
		}

		dao.insert(leaseVO, is, size);

		return leaseVO;
	}

	// 修改service
	public LeaseVO updateLease(Integer leaseNO, Integer houseNO, java.sql.Date leaseBeginDate,
			java.sql.Date leaseEndDate, Integer memNO, Integer empNO, Integer Rent, Integer Deposit, Integer Relief,
			java.sql.Date leaseDate, InputStream is, String houseNote, Byte Refund) {

		LeaseVO leaseVO = new LeaseVO();

		leaseVO.setLeaseNO(leaseNO);
		leaseVO.setHouseNO(houseNO);
		leaseVO.setLeaseBeginDate(leaseBeginDate);
		leaseVO.setLeaseEndDate(leaseEndDate);
		leaseVO.setMemNO(memNO);
		leaseVO.setEmpNO(empNO);
		leaseVO.setRent(Rent);
		leaseVO.setDeposit(Deposit);
		leaseVO.setRelief(Relief);
		leaseVO.setLeaseDate(leaseDate);
		leaseVO.setHouseNote(houseNote);
		leaseVO.setRefund(Refund);

		int size;
		try {
			size = is.available();
		} catch (IOException e) {
			size = 0;
		}

		dao.update(leaseVO, is, size);

		return leaseVO;
	}

	// 刪除service
	public void deleteMember(Integer leaseNO) {
		dao.delete(leaseNO);
	}

	// 查詢一筆service
	public LeaseVO getOneMember(Integer leaseNO) {
		return dao.findByPrimaryKey(leaseNO);
	}

	// 查詢全部service
	public Set<LeaseVO> getAll() {
		return dao.getAll();
	}
}
