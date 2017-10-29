package com.hermit.iii.member.model;

import java.util.*;

import org.hibernate.*;

import com.hermit.iii.util.*;

public class MemberDAO_hibernate implements MemberDAO_interface_hibernate {

	private static final String GET_ALL_STMT = "from MemberVO order by memNO";
	private static final String SELECT_BY_ACCOUNT = "FROM MemberVO where memAccount=?";
	private static final String SELECT_BY_TEL = "FROM MemberVO where memTel=?";

	@Override
	public void insert(MemberVO memberVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			memberVO.setMemPwd(new SecurityCipher().encryptString(memberVO.getMemPwd()));
			session.saveOrUpdate(memberVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(MemberVO memberVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			memberVO.setMemPwd(new SecurityCipher().encryptString(memberVO.getMemPwd()));
			session.saveOrUpdate(memberVO);
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
			MemberVO memberVO = (MemberVO) session.get(MemberVO.class, memNO);
			session.delete(memberVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public MemberVO findByPrimaryKey(Integer memNO) {
		MemberVO memberVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			memberVO = (MemberVO) session.get(MemberVO.class, memNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return memberVO;
	}

	@Override
	public Set<MemberVO> getAll() {
		List<MemberVO> list = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			list = session.createQuery(GET_ALL_STMT).getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return new LinkedHashSet<MemberVO>(list);
	}

	/**** 自訂指令 ****/

	@Override
	public ArrayList<MemberVO> autoCompleteM(String name) {
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
	public void update_MemStatusByMemTel(MemberVO memberVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			int updateCount = session.createQuery("UPDATE MemberVO SET memStatus='" + memberVO.getMemStatus()
					+ "' WHERE memTel = '" + memberVO.getMemTel() + "'").executeUpdate();
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
			str = (String) session.createQuery("SELECT memImage FROM MemberVO WHERE memNO =" + memNO).getResultList()
					.get(0);
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
					.createQuery("SELECT count(*) FROM MemberVO WHERE memAccount ='" + memAccount + "'").getResultList()
					.get(0);

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
	public MemberVO findByAccount(String memAccount) {
		List<MemberVO> list = null;
		MemberVO account = new MemberVO();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			Query query = session.createQuery(SELECT_BY_ACCOUNT);
			query.setParameter(0, memAccount);
			list = query.list();
			for (MemberVO memberVO : list) {
				account = memberVO;
			}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return account;
	}

	// 忘記帳號查詢
	@Override
	public MemberVO findByTel(String memTel) {
		List<MemberVO> list = null;
		MemberVO tel = new MemberVO();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			Query query = session.createQuery(SELECT_BY_TEL);
			query.setParameter(0, memTel);
			list = query.list();
			for (MemberVO memberVO : list) {
				tel = memberVO;
			}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return tel;
	}

	public static void main(String[] args) {
		MemberDAO_hibernate dao = new MemberDAO_hibernate();

		// 新增 (register由資料庫系統給)
		MemberVO memberVO1 = new MemberVO();
		memberVO1.setMemTel("0905123456");
		memberVO1.setMemAccount("account123");
		memberVO1.setMemPwd("123Pwd@@");
		memberVO1.setMemName("小明Lin");
		memberVO1.setMemGender("男");
		memberVO1.setMemEmail("mail123@gmail.com");
		memberVO1.setMemStatus("未驗證會員");
		memberVO1.setMemInfract(0);
		memberVO1.setMemImage(null);
		dao.insert(memberVO1);

		// 修改初始資料第一筆 (register由資料庫系統給)
		MemberVO memberVO2 = new MemberVO();
		memberVO2.setMemNO(40001);
		memberVO2.setMemTel("0905123456");
		memberVO2.setMemAccount("account123");
		memberVO2.setMemPwd("123Pwd@@");
		memberVO2.setMemName("小花Lin");
		memberVO2.setMemGender("女");
		memberVO2.setMemEmail("mail123@gmail.com");
		memberVO2.setMemStatus("一般會員驗證");
		memberVO2.setMemInfract(2);
		// memberVO2.setMimage(null); // not use
		dao.update(memberVO2);

		// 查詢初始資料第一筆
		MemberVO memberVO3 = dao.findByPrimaryKey(40003);
		System.out.print(memberVO3.getMemNO() + ",");
		System.out.print(memberVO3.getMemTel() + ",");
		System.out.print(memberVO3.getMemAccount() + ",");
		System.out.print(memberVO3.getMemPwd() + ",");
		System.out.print(memberVO3.getMemName() + ",");
		System.out.print(memberVO3.getMemGender() + ",");
		System.out.print(memberVO3.getMemEmail() + ",");
		System.out.print(memberVO3.getMemRegister() + ",");
		System.out.print(memberVO3.getMemStatus() + ",");
		System.out.print(memberVO3.getMemInfract() + ",");
		// System.out.println(memberHibernateVO3.getMemImage());
		System.out.println();
		System.out.println("---------------------");

		// 查詢全部
		Set<MemberVO> set = dao.getAll();
		for (MemberVO memberVO : set) {
			System.out.print(memberVO.getMemNO() + ",");
			System.out.print(memberVO.getMemTel() + ",");
			System.out.print(memberVO.getMemAccount() + ",");
			System.out.print(memberVO.getMemPwd() + ",");
			System.out.print(memberVO.getMemName() + ",");
			System.out.print(memberVO.getMemGender() + ",");
			System.out.print(memberVO.getMemEmail() + ",");
			System.out.print(memberVO.getMemRegister() + ",");
			System.out.print(memberVO.getMemStatus() + ",");
			System.out.print(memberVO.getMemInfract() + ",");
			// System.out.println(memberHibernateVO.getMemImage());
			System.out.println();
		}

		// 刪除初始資料一筆
		dao.delete(40002);

		/**** 自訂指令 ****/

		// // SMS更新會員狀態
		// MemberVO memberVO4 = new MemberVO();
		// memberVO4.setMemStatus("FB驗證");
		// memberVO4.setMemTel("0905123456");
		// dao.update_MemStatusByMemTel(memberVO4);
		//
		// // Image查詢(讀取)
		// System.out.println(dao.find_MemImageByMemNO(40003));
		//
		// // AJAX 帳號檢查
		// System.out.println(dao.count_MemAccount_AJAX("account123"));

		// 驗證帳號是否存在
		MemberVO memberVO4 = dao.findByAccount("Vir333");
		System.out.print(memberVO4.getMemNO() + ",");
		System.out.print(memberVO4.getMemTel() + ",");
		System.out.print(memberVO4.getMemAccount() + ",");
		System.out.print(memberVO4.getMemPwd() + ",");
		System.out.print(memberVO4.getMemName() + ",");
		System.out.print(memberVO4.getMemGender() + ",");
		System.out.print(memberVO4.getMemEmail() + ",");
		System.out.print(memberVO4.getMemRegister() + ",");
		System.out.print(memberVO4.getMemStatus() + ",");
		System.out.print(memberVO4.getMemInfract() + ",");
		// System.out.println(memberVO4.getMemImage());
		System.out.println();

		// 忘記帳號查詢
		MemberVO memberVO5 = dao.findByTel("0912345689");
		System.out.print(memberVO5.getMemNO() + ",");
		System.out.print(memberVO5.getMemTel() + ",");
		System.out.print(memberVO5.getMemAccount() + ",");
		System.out.print(memberVO5.getMemPwd() + ",");
		System.out.print(memberVO5.getMemName() + ",");
		System.out.print(memberVO5.getMemGender() + ",");
		System.out.print(memberVO5.getMemEmail() + ",");
		System.out.print(memberVO5.getMemRegister() + ",");
		System.out.print(memberVO5.getMemStatus() + ",");
		System.out.print(memberVO5.getMemInfract() + ",");
		// System.out.println(memberVO4.getMemImage());
		System.out.println();

		System.out.println("Done");
	}
}
