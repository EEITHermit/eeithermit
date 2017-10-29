package com.hermit.iii.emp.model;

import java.util.*;
import org.hibernate.*;
import com.hermit.iii.util.*;

public class EmpDAO_hibernate implements EmpDAO_interface_hibernate {

	private static final String GET_ALL_STMT = "FROM EmpVO order by empNO";
	private static final String SELECT_BY_ACCOUNT = "FROM EmpVO where empAccount=?";

	@Override
	public void insert(EmpVO empVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(empVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(EmpVO empVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(empVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer empNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			EmpVO empVO = (EmpVO) session.get(EmpVO.class, empNO);
			session.delete(empVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public EmpVO findByPrimaryKey(Integer empNO) {
		EmpVO empVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			empVO = (EmpVO) session.get(EmpVO.class, empNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return empVO;
	}

	@Override
	public Set<EmpVO> getAll() {
		List<EmpVO> list = null;
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
		return new LinkedHashSet<EmpVO>(list);
	}

	/*** 自訂指令findByAccount ***/
	@Override
	public EmpVO findByAccount(String empAccount) {
		List<EmpVO> list = null;
		EmpVO account = new EmpVO();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(SELECT_BY_ACCOUNT);
			query.setParameter(0, empAccount);
			list = query.list();

			for (EmpVO empVO : list) {
				account = empVO;
			}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return account;
	}

	public static void main(String[] args) {
		EmpDAO_hibernate dao = new EmpDAO_hibernate();

		// insert
		// EmpVO empVO1 = new EmpVO();
		// empVO1.setEmpAccount("eeit9704");
		// empVO1.setEmpPwd("sa123456");
		// empVO1.setEmpPhone("0928265804");
		// empVO1.setEmpName("藍浩天");
		// empVO1.setPostNO(310);
		// empVO1.setEmpStatus(false);
		// dao.insert(empVO1);

		// update
		// EmpVO empVO2 = new EmpVO();
		// empVO2.setEmpNO(30002);
		// empVO2.setEmpAccount("eeit97087");
		// empVO2.setEmpPwd("12312311");
		// empVO2.setEmpPhone("0957057006");
		// empVO2.setEmpName("徐漢勳");
		// empVO2.setPostNO(330);
		// empVO2.setEmpStatus(true);
		// dao.update(empVO2);

		// delete
		// dao.delete(30003);

		// select one
		// EmpVO empVO3 = dao.findByPrimaryKey(30001);
		// System.out.print(empVO3.getEmpNO() + ",");
		// System.out.print(empVO3.getEmpAccount() + ",");
		// System.out.print(empVO3.getEmpPwd() + ",");
		// System.out.print(empVO3.getEmpPhone() + ",");
		// System.out.print(empVO3.getEmpName() + ",");
		// System.out.print(empVO3.getPostNO() + ",");
		// System.out.println(empVO3.getEmpStatus());
		// System.out.println("----------------------");

		// select all
		// Set<EmpVO> set = dao.getAll();
		// for (EmpVO empVO : set) {
		// System.out.print(empVO.getEmpNO() + ",");
		// System.out.print(empVO.getEmpAccount() + ",");
		// System.out.print(empVO.getEmpPwd() + ",");
		// System.out.print(empVO.getEmpPhone() + ",");
		// System.out.print(empVO.getEmpName() + ",");
		// System.out.print(empVO.getPostNO() + ",");
		// System.out.print(empVO.getEmpStatus());
		// System.out.println("");
		// }
		// System.out.println("----------------------");

		// select account
		EmpVO empVO4 = dao.findByAccount("Vir3");
		System.out.print(empVO4.getEmpNO() + ",");
		System.out.print(empVO4.getEmpAccount() + ",");
		System.out.print(empVO4.getEmpPwd() + ",");
		System.out.print(empVO4.getEmpPhone() + ",");
		System.out.print(empVO4.getEmpName() + ",");
		System.out.print(empVO4.getPostNO() + ",");
		System.out.println(empVO4.getEmpStatus());
		System.out.println("----------------------");

		System.out.println("Finish.");
	}
}
