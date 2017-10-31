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
	
//	begin transataion
//	savaorupdate
//	getTransaction commit
//	rollback

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
	public List<EmpVO> getAll() {
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
		return list;
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
		EmpVO empVO = new EmpVO();
		EmpDAO_hibernate dao = new EmpDAO_hibernate();

		// insert
//		 EmpVO empVO1 = new EmpVO();
//		 empVO1.setEmpAccount("eeit9704");
//		 empVO1.setEmpPwd("sa123456");
//		 empVO1.setEmpPhone("0928265804");
//		 empVO1.setEmpName("藍浩天");
//		 empVO1.getPostVO().setPostNO(330);
//		 empVO1.setEmpStatus(true);
//		 dao.insert(empVO1);

		// update
//		 EmpVO empVO2 = new EmpVO();
//		 empVO2.setEmpNO(30002);
//		 empVO2.setEmpAccount("eeit97087");
//		 empVO2.setEmpPwd("12312311");
//		 empVO2.setEmpPhone("0957057006");
//		 empVO2.setEmpName("徐漢勳");
//		 empVO2.getPostVO().setPostNO(330);
//		 empVO2.setEmpStatus(true);
//		 dao.update(empVO2);

//		 delete
		 System.out.println("delete start");
		 dao.delete(30006);
		 System.out.println("delete success");

		// select one
//		EmpVO empVO3 = dao.findByPrimaryKey(30001);
//		System.out.print(empVO3.getEmpNO() + ",");
//		System.out.print(empVO3.getEmpAccount() + ",");
//		System.out.print(empVO3.getEmpPwd() + ",");
//		System.out.print(empVO3.getEmpPhone() + ",");
//		System.out.print(empVO3.getEmpName() + ",");
//		System.out.print(empVO3.getPostVO().getPostNO() + ",");
//		System.out.println(empVO3.getEmpStatus());
//		System.out.println("----------------------");

		// select all
//		Set<EmpVO> set = dao.getAll();
//		for (EmpVO empVO1 : set) {
//			System.out.print(empVO1.getEmpNO() + ",");
//			System.out.print(empVO1.getEmpAccount() + ",");
//			System.out.print(empVO1.getEmpPwd() + ",");
//			System.out.print(empVO1.getEmpPhone() + ",");
//			System.out.print(empVO1.getEmpName() + ",");
//			System.out.print(empVO1.getPostVO().getPostNO() + ",");
//			System.out.print(empVO1.getEmpStatus());
//			System.out.println("");
//		}
//		System.out.println("----------------------");

		// select account
//		EmpVO empVO4 = dao.findByAccount("Vir3");
//		System.out.print(empVO4.getEmpNO() + ",");
//		System.out.print(empVO4.getEmpAccount() + ",");
//		System.out.print(empVO4.getEmpPwd() + ",");
//		System.out.print(empVO4.getEmpPhone() + ",");
//		System.out.print(empVO4.getEmpName() + ",");
		// 改寫成以下三行寫法
//		System.out.print(empVO.getPostVO().getPostNO() + ",");
//		System.out.println(empVO.getEmpStatus());
//		System.out.println("----------------------");
//
//		System.out.println("Finish.");
	}
}
