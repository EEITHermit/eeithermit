package com.hermit.iii.teammemberlist.model;

import java.util.Set;

public class TeamMemberListService {

	private TeamMemberListDAO_interface dao;

	public TeamMemberListService() {
		dao = new TeamMemberListJNDIDAO();
	}

	// 新增service
	public TeamMemberListVO_original addTeamMemberList(Integer empNO, Integer businNO, java.sql.Date tmlStartTime,
			java.sql.Date tmlEndTime, Byte tmlStatus) {

		TeamMemberListVO_original teamMemberListVO = new TeamMemberListVO_original();

		teamMemberListVO.setEmpNO(empNO);
		teamMemberListVO.setBusinNO(businNO);
		teamMemberListVO.setTmlStartTime(tmlStartTime);
		teamMemberListVO.setTmlEndTime(tmlEndTime);
		teamMemberListVO.setTmlStatus(tmlStatus);

		dao.insert(teamMemberListVO);

		return teamMemberListVO;
	}

	// 修改service
	public TeamMemberListVO_original updateTeamMemberList(Integer memberListNO, Integer empNO, Integer businNO,
			java.sql.Date tmlStartTime, java.sql.Date tmlEndTime, Byte tmlStatus) {

		TeamMemberListVO_original teamMemberListVO = new TeamMemberListVO_original();

		teamMemberListVO.setMemberListNO(memberListNO);
		teamMemberListVO.setEmpNO(empNO);
		teamMemberListVO.setBusinNO(businNO);
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
	public TeamMemberListVO_original getOneTeamMemberList(Integer memberListNO) {
		return dao.findByPrimaryKey(memberListNO);
	}

	// 查詢全部service
	public Set<TeamMemberListVO_original> getAll() {
		return dao.getAll();
	}
}