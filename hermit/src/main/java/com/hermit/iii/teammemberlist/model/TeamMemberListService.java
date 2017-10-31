package com.hermit.iii.teammemberlist.model;

import java.util.Set;

import com.hermit.iii.businTeam.model.BusinTeamVO;
import com.hermit.iii.emp.model.EmpVO;

public class TeamMemberListService {

	private TeamMemberListDAO_interface_hibernate dao;

	public TeamMemberListService() {
		dao = new TeamMemberListDAO_hibernate();
	}

	// 新增service
	public TeamMemberListVO addTeamMemberList(Integer empNO, Integer businNO, java.sql.Date tmlStartTime,
			java.sql.Date tmlEndTime, Byte tmlStatus) {

		TeamMemberListVO teamMemberListVO = new TeamMemberListVO();
		
		EmpVO empVO = new EmpVO();
		empVO.setEmpNO(empNO);
		teamMemberListVO.setEmpVO(empVO);
		
		BusinTeamVO businTeamVO = new BusinTeamVO();
		businTeamVO.setBusinNO(businNO);
		teamMemberListVO.setBusinTeamVO(businTeamVO);
		
		teamMemberListVO.setTmlStartTime(tmlStartTime);
		teamMemberListVO.setTmlEndTime(tmlEndTime);
		teamMemberListVO.setTmlStatus(tmlStatus);

		dao.insert(teamMemberListVO);

		return teamMemberListVO;
	}

	// 修改service
	public TeamMemberListVO updateTeamMemberList(Integer memberListNO, Integer empNO, Integer businNO,
			java.sql.Date tmlStartTime, java.sql.Date tmlEndTime, Byte tmlStatus) {

		TeamMemberListVO teamMemberListVO = new TeamMemberListVO();
		
		
		teamMemberListVO.setMemberListNO(memberListNO);
		
		EmpVO empVO = new EmpVO();
		empVO.setEmpNO(empNO);
		teamMemberListVO.setEmpVO(empVO);
		
		BusinTeamVO businTeamVO = new BusinTeamVO();
		businTeamVO.setBusinNO(businNO);
		teamMemberListVO.setBusinTeamVO(businTeamVO);
		
		teamMemberListVO.setTmlStartTime(tmlStartTime);
		teamMemberListVO.setTmlEndTime(tmlEndTime);
		teamMemberListVO.setTmlStatus(tmlStatus);

		dao.update(teamMemberListVO);

		return teamMemberListVO;
	}

	// 刪除service
	public void deleteTeamMemberList(Integer memberListNO) {
		dao.delete(memberListNO);
	}

	// 查詢一筆service
	public TeamMemberListVO getOneTeamMemberList(Integer memberListNO) {
		return dao.findByPrimaryKey(memberListNO);
	}

	// 查詢全部service
	public Set<TeamMemberListVO> getAll() {
		return dao.getAll();
	}
}