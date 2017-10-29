package com.hermit.iii.businTeam.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import com.hermit.iii.util.HibernateUtil;

public class BusinTeamDAO_hibernate implements BusinTeamDAO_interface_hibernate {

	private static final String GET_ALL_STMT = "FROM BusinTeamVO order by businNO";

	@Override
	public void insert(BusinTeamVO businTeamVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(businTeamVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(BusinTeamVO businTeamVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(businTeamVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer businNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			BusinTeamVO businTeamVO = (BusinTeamVO) session.get(BusinTeamVO.class, businNO);
			session.delete(businTeamVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public BusinTeamVO findByPrimaryKey(Integer businNO) {
		BusinTeamVO businTeamVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			businTeamVO = (BusinTeamVO) session.get(BusinTeamVO.class, businNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return businTeamVO;
	}

	@Override
	public Set<BusinTeamVO> getAll() {
		List<BusinTeamVO> list = null;
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
		return new LinkedHashSet<BusinTeamVO>(list);
	}

	public static void main(String[] args) {
		BusinTeamDAO_hibernate dao = new BusinTeamDAO_hibernate();

		// insert
		BusinTeamVO businTeamVO1 = new BusinTeamVO();
		businTeamVO1.setBusinName("第二組");
		businTeamVO1.setManager(30002);
		dao.insert(businTeamVO1);

		// update
		BusinTeamVO businTeamVO2 = new BusinTeamVO();
		businTeamVO2.setBusinNO(30010);
		businTeamVO2.setBusinName("第七組");
		businTeamVO2.setManager(30003);
		dao.update(businTeamVO2);

		// delete
		dao.delete(30020);

		// select one
		BusinTeamVO businTeamVO3 = dao.findByPrimaryKey(30010);
		System.out.print(businTeamVO3.getBusinNO() + ",");
		System.out.print(businTeamVO3.getBusinName() + ",");
		System.out.println(businTeamVO3.getManager());
		System.out.println("----------------------");

		// select all
		Set<BusinTeamVO> set = dao.getAll();
		for (BusinTeamVO businTeamVO : set) {
			System.out.print(businTeamVO.getBusinNO() + ",");
			System.out.print(businTeamVO.getBusinName() + ",");
			System.out.print(businTeamVO.getManager());
			System.out.println("");
		}
		System.out.println("----------------------");
	}

}
