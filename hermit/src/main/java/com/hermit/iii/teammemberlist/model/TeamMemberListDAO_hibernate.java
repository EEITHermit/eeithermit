package com.hermit.iii.teammemberlist.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.util.HibernateUtil;

public class TeamMemberListDAO_hibernate implements TeamMemberListDAO_interface_hibernate {

	private static final String GET_ALL_STMT = "FROM TeamMemberListVO order by memberListNO";

	@Override
	public void insert(TeamMemberListVO teamMemberListVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(teamMemberListVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(TeamMemberListVO teamMemberListVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(teamMemberListVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer memberListNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			TeamMemberListVO teamMemberListVO = (TeamMemberListVO) session.get(TeamMemberListVO.class, memberListNO);
			session.delete(teamMemberListVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public TeamMemberListVO findByPrimaryKey(Integer memberListNO) {
		TeamMemberListVO teamMemberListVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			teamMemberListVO = (TeamMemberListVO) session.get(TeamMemberListVO.class, memberListNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return teamMemberListVO;
	}

	@Override
	public Set<TeamMemberListVO> getAll() {
		List<TeamMemberListVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list(); // getResultList()
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return new LinkedHashSet<TeamMemberListVO>(list);
	}

	public static void main(String[] args) {
		TeamMemberListDAO_hibernate dao = new TeamMemberListDAO_hibernate();
		// Emp POJO
		com.hermit.iii.emp.model.EmpVO empVO = new com.hermit.iii.emp.model.EmpVO();
		empVO.setEmpNO(30002);
		// BusinTeam POJO
		com.hermit.iii.businTeam.model.BusinTeamVO businTeamVO = new com.hermit.iii.businTeam.model.BusinTeamVO();
		businTeamVO.setBusinNO(30020);

		// insert
		TeamMemberListVO teamMemberListVO1 = new TeamMemberListVO();
		teamMemberListVO1.setEmpVO(empVO);
		teamMemberListVO1.setBusinTeamVO(businTeamVO);
		teamMemberListVO1.setTmlStartTime(java.sql.Date.valueOf("2015-10-10"));
		teamMemberListVO1.setTmlEndTime(java.sql.Date.valueOf("2015-10-11"));
		teamMemberListVO1.setTmlStatus((byte) 1);
		dao.insert(teamMemberListVO1);

		// update
		TeamMemberListVO teamMemberListVO2 = new TeamMemberListVO();
		teamMemberListVO2.setMemberListNO(30001);
		teamMemberListVO2.setEmpVO(empVO);
		teamMemberListVO2.setBusinTeamVO(businTeamVO);
		teamMemberListVO2.setTmlStartTime(java.sql.Date.valueOf("2017-10-15"));
		teamMemberListVO2.setTmlEndTime(java.sql.Date.valueOf("2017-10-14"));
		teamMemberListVO2.setTmlStatus((byte) 0);
		dao.update(teamMemberListVO2);

		// delete
//		dao.delete(30002);

		// select one
		TeamMemberListVO teamMemberListVO3 = dao.findByPrimaryKey(30001);
		System.out.print(teamMemberListVO3.getMemberListNO() + ",");
		System.out.print(teamMemberListVO3.getEmpVO().getEmpNO() + ",");
		System.out.print(teamMemberListVO3.getBusinTeamVO().getBusinNO() + ",");
		System.out.print(teamMemberListVO3.getTmlStartTime() + ",");
		System.out.print(teamMemberListVO3.getTmlEndTime() + ",");
		System.out.println(teamMemberListVO3.getTmlStatus());
		System.out.println("---------------------");

		// select all
		Set<TeamMemberListVO> set = dao.getAll();
		for (TeamMemberListVO teamMemberListVO : set) {
			System.out.print(teamMemberListVO.getMemberListNO() + ",");
			System.out.print(teamMemberListVO.getEmpVO().getEmpNO() + ",");
			System.out.print(teamMemberListVO.getBusinTeamVO().getBusinNO() + ",");
			System.out.print(teamMemberListVO.getTmlStartTime() + ",");
			System.out.print(teamMemberListVO.getTmlEndTime() + ",");
			System.out.println(teamMemberListVO.getTmlStatus());
		}

		System.out.println("Finish.");
	}

}
