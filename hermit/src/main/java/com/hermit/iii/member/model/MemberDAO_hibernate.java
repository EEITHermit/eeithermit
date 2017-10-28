package com.hermit.iii.member.model;

import java.util.*;

import org.hibernate.*;

import com.hermit.iii.util.*;

public class MemberDAO_hibernate implements MemberDAO_interface_hibernate {

	private static final String GET_ALL_STMT = "from MemberVO_hibernate order by memNO";

	@Override
	public void insert(MemberVO_hibernate memberVO_hibernate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(memberVO_hibernate);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(MemberVO_hibernate memberVO_hibernate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(memberVO_hibernate);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer memNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			// 【注意多方不可(不宜)採用cascade聯級刪除】
			session.beginTransaction();
			MemberVO_hibernate memberVO_hibernate = (MemberVO_hibernate) session.get(MemberVO_hibernate.class, memNO);
			session.delete(memberVO_hibernate);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public MemberVO_hibernate findByPrimaryKey(Integer memNO) {
		MemberVO_hibernate memberVO_hibernate = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			memberVO_hibernate = (MemberVO_hibernate) session.get(MemberVO_hibernate.class, memNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return memberVO_hibernate;
	}

	@Override
	public Set<MemberVO_hibernate> getAll() {
		List<MemberVO_hibernate> list = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			list = session.createQuery(GET_ALL_STMT).getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return new LinkedHashSet<MemberVO_hibernate>(list);
	}

	/**** 自訂指令 ****/

	@Override
	public ArrayList<MemberVO_hibernate> autoCompleteM(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkInfraction(Integer memNO) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer infractPlus1(Integer memNO) {
		// TODO Auto-generated method stub
		return null;
	}

	// SMS更新會員狀態
	@Override
	public void update_MemStatusByMemTel(MemberVO_hibernate memberVO_hibernate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			int updateCount = session.createQuery("UPDATE MemberVO_hibernate SET memStatus='"
					+ memberVO_hibernate.getMemStatus() + "' WHERE memTel = '" + memberVO_hibernate.getMemTel() + "'")
					.executeUpdate();
			System.out.println("Hibernate createQuery 執行更新筆數： " + updateCount);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	// Image查詢(讀取)
	@Override
	public String find_MemImageByMemNO(Integer memNO) {
		String str = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			str = (String) session.createQuery("SELECT memImage FROM MemberVO_hibernate WHERE memNO =" + memNO)
					.getResultList().get(0);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return str;
	}

	// AJAX 帳號檢查
	@Override
	public String count_MemAccount_AJAX(String memAccount) {
		String str = "帳號不存在";

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			long cnt = (Long) session
					.createQuery("SELECT count(*) FROM MemberVO_hibernate WHERE memAccount ='" + memAccount + "'")
					.getResultList().get(0);

			if (cnt >= 1)
				str = "帳號已存在";

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return str;
	}

	// 驗證帳號是否存在
	@Override
	public MemberVO_hibernate findByAccount(String memAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	// 忘記帳號查詢
	@Override
	public MemberVO_hibernate findByTel(String memTel) {
		// TODO Auto-generated method stub
		return null;
	}

	// 忘記密碼查詢
	@Override
	public MemberVO_hibernate findByEmail(String memEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		MemberDAO_hibernate dao = new MemberDAO_hibernate();

		// 新增 (register由資料庫系統給)
		MemberVO_hibernate memberVO_hibernate1 = new MemberVO_hibernate();
		memberVO_hibernate1.setMemTel("0905123456");
		memberVO_hibernate1.setMemAccount("account123");
		memberVO_hibernate1.setMemPwd("N7GB7OYPuLFGMxFF2hylXA==");
		memberVO_hibernate1.setMemName("小明Lin");
		memberVO_hibernate1.setMemGender("男");
		memberVO_hibernate1.setMemEmail("mail123@gmail.com");
		// memberVO_hibernate1.setMemRegister(java.sql.Date.valueOf("2017-10-10"));
		memberVO_hibernate1.setMemStatus("未驗證會員");
		memberVO_hibernate1.setMemInfract(0);
		memberVO_hibernate1.setMemImage(null);
		dao.insert(memberVO_hibernate1);

		// // 修改初始資料第一筆 (register由資料庫系統給)
		// MemberVO_hibernate memberVO_hibernate2 = new MemberVO_hibernate();
		// memberVO_hibernate2.setMemNO(40001);
		// memberVO_hibernate2.setMemTel("0905123456");
		// memberVO_hibernate2.setMemAccount("account123");
		// memberVO_hibernate2.setMemPwd("1N7GB7OYPuLFGMxFF2hylXA==");
		// memberVO_hibernate2.setMemName("小花Lin");
		// memberVO_hibernate2.setMemGender("女");
		// memberVO_hibernate2.setMemEmail("mail123@gmail.com");
		// //
		// memberVO_hibernate2.setMemRegister(java.sql.Date.valueOf("2017-10-10"));
		// memberVO_hibernate2.setMemStatus("一般會員驗證");
		// memberVO_hibernate2.setMemInfract(2);
		// // memberVO_hibernate2.setMimage(null); // not use
		// dao.update(memberVO_hibernate2);
		//
		// // 查詢初始資料第一筆
		// MemberVO_hibernate memberVO_hibernate3 = dao.findByPrimaryKey(40003);
		// System.out.print(memberVO_hibernate3.getMemNO() + ",");
		// System.out.print(memberVO_hibernate3.getMemTel() + ",");
		// System.out.print(memberVO_hibernate3.getMemAccount() + ",");
		// System.out.print(memberVO_hibernate3.getMemPwd() + ",");
		// System.out.print(memberVO_hibernate3.getMemName() + ",");
		// System.out.print(memberVO_hibernate3.getMemGender() + ",");
		// System.out.print(memberVO_hibernate3.getMemEmail() + ",");
		// System.out.print(memberVO_hibernate3.getMemRegister() + ",");
		// System.out.print(memberVO_hibernate3.getMemStatus() + ",");
		// System.out.print(memberVO_hibernate3.getMemInfract() + ",");
		// // System.out.println(memberHibernateVO3.getMemImage());
		// System.out.println();
		// System.out.println("---------------------");
		//
		// // 查詢全部
		// Set<MemberVO_hibernate> set = dao.getAll();
		// for (MemberVO_hibernate memberVO_hibernate : set) {
		// System.out.print(memberVO_hibernate.getMemNO() + ",");
		// System.out.print(memberVO_hibernate.getMemTel() + ",");
		// System.out.print(memberVO_hibernate.getMemAccount() + ",");
		// System.out.print(memberVO_hibernate.getMemPwd() + ",");
		// System.out.print(memberVO_hibernate.getMemName() + ",");
		// System.out.print(memberVO_hibernate.getMemGender() + ",");
		// System.out.print(memberVO_hibernate.getMemEmail() + ",");
		// System.out.print(memberVO_hibernate.getMemRegister() + ",");
		// System.out.print(memberVO_hibernate.getMemStatus() + ",");
		// System.out.print(memberVO_hibernate.getMemInfract() + ",");
		// // System.out.println(memberHibernateVO.getMemImage());
		// System.out.println();
		// }
		//
		// // 刪除初始資料一筆
		// dao.delete(40002);

		/**** 自訂指令 ****/

		// SMS更新會員狀態
		// MemberVO_hibernate memberVO_hibernate4 = new MemberVO_hibernate();
		// memberVO_hibernate4.setMemStatus("FB驗證");
		// memberVO_hibernate4.setMemTel("0905123456");
		// dao.update_MemStatusByMemTel(memberVO_hibernate4);

		// Image查詢(讀取)
		// System.out.println(dao.find_MemImageByMemNO(40001));

		// AJAX 帳號檢查
		// System.out.println(dao.count_MemAccount_AJAX("account123"));

		System.out.println("Done");
	}
}
