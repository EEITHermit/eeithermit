package com.hermit.iii.infraction.model;

import java.util.*;

import com.hermit.iii.member.model.MemberVO;

public class InfractionService {
	InfractionDAO_interface_hibernate inDAO = new InfractionJNDIDAO_hibernate();

	public Integer insert(InfractionVO inVO) {
		return inDAO.insert(inVO);
	}

	// 修改service
	public InfractionVO updateInfraction(Integer inNO, Integer memNO, String reason, java.sql.Date inDate,
			Integer empNO) {
		InfractionVO infractionVO = new InfractionVO();

		infractionVO.setInNO(inNO);
		MemberVO memberVO = new MemberVO();
		memberVO.setMemNO(memNO);
		infractionVO.setMemberVO(memberVO);
		infractionVO.setReason(reason);
		infractionVO.setInDate(inDate);
		infractionVO.setEmpNO(empNO);

		inDAO.update(infractionVO);

		return infractionVO;
	}

	// 修改service Struts 2可 用(預留)
	public void updateInfraction(InfractionVO infractionVO) {
		inDAO.update(infractionVO);
	}

	// 刪除service
	public void deleteInfraction(Integer inNO) {
		inDAO.delete(inNO);
	}

	// 查詢一筆service
	public InfractionVO getOneInfraction(Integer inNO) {
		return inDAO.findByPrimaryKey(inNO);
	}

	// 查詢全部service
	public Set<InfractionVO> getAll() {
		return inDAO.getAll();
	}
}