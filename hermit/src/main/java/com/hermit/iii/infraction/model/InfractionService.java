package com.hermit.iii.infraction.model;

import java.util.*;

public class InfractionService {
	InfractionDAO_interface inDAO = new InfractionJNDIDAO();

	public Integer insert(InfractionVO_original inVO) {
		return inDAO.insert(inVO);
	}

	// 修改service
	public InfractionVO_original updateInfraction(Integer inNO, Integer memNO, String reason, java.sql.Date inDate,
			Integer empNO) {
		InfractionVO_original infractionVO = new InfractionVO_original();

		infractionVO.setInNO(inNO);
		infractionVO.setMemNO(memNO);
		infractionVO.setReason(reason);
		infractionVO.setInDate(inDate);
		infractionVO.setEmpNO(empNO);

		inDAO.update(infractionVO);

		return infractionVO;
	}

	// 修改service Struts 2可 用(預留)
	public void updateInfraction(InfractionVO_original infractionVO) {
		inDAO.update(infractionVO);
	}

	// 刪除service
	public void deleteInfraction(Integer inNO) {
		inDAO.delete(inNO);
	}

	// 查詢一筆service
	public InfractionVO_original getOneInfraction(Integer inNO) {
		return inDAO.findByPrimaryKey(inNO);
	}

	// 查詢全部service
	public Set<InfractionVO_original> getAll() {
		return inDAO.getAll();
	}
}