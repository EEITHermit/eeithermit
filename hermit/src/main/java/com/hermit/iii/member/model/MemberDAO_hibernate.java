package com.hermit.iii.member.model;

import java.util.*;

import org.hibernate.*;
import org.hibernate.*;
import org.springframework.context.*;
import org.springframework.context.support.*;
import org.springframework.orm.hibernate5.*;
import org.springframework.transaction.annotation.*;

import com.hermit.iii.util.*;

//使用Spring(Aspect)的Transactional取代hibernate交易session.beginTransaction(Annotation版本)
@Transactional(readOnly = true)
public class MemberDAO_hibernate implements MemberDAO_interface_hibernate {

	private static final String GET_ALL_STMT = "from MemberVO order by memNO";
	private static final String SELECT_BY_ACCOUNT = "FROM MemberVO where memAccount=?";
	private static final String SELECT_BY_TEL = "FROM MemberVO where memTel=?";

	// 使用Spring的hibernateTemplate接管hibernate A~D車(不含交易)
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	// @Override
	// public void insert(MemberVO memberVO) {
	// Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	// try {
	// session.beginTransaction();
	// memberVO.setMemPwd(new
	// SecurityCipher().encryptString(memberVO.getMemPwd()));
	// session.saveOrUpdate(memberVO);
	// session.getTransaction().commit();
	// } catch (RuntimeException ex) {
	// session.getTransaction().rollback();
	// throw ex;
	// }
	// }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insert(MemberVO memberVO) {
		memberVO.setMemPwd(new SecurityCipher().encryptString(memberVO.getMemPwd()));
		hibernateTemplate.saveOrUpdate(memberVO);
	}

	// @Override
	// public void update(MemberVO memberVO) {
	// Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	// try {
	// session.beginTransaction();
	// memberVO.setMemPwd(new
	// SecurityCipher().encryptString(memberVO.getMemPwd()));
	// session.saveOrUpdate(memberVO);
	// session.getTransaction().commit();
	// } catch (RuntimeException ex) {
	// session.getTransaction().rollback();
	// throw ex;
	// }
	// }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(MemberVO memberVO) {
		memberVO.setMemPwd(new SecurityCipher().encryptString(memberVO.getMemPwd()));
		hibernateTemplate.update(memberVO);
	}

	// @Override
	// public void delete(Integer memNO) {
	// Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	// try {
	// // 【注意多方不可(不宜)採用cascade聯級刪除】
	// session.beginTransaction();
	// MemberVO memberVO = (MemberVO) session.get(MemberVO.class, memNO);
	// session.delete(memberVO);
	// session.getTransaction().commit();
	// } catch (RuntimeException ex) {
	// session.getTransaction().rollback();
	// throw ex;
	// }
	// }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Integer memNO) {
		// EmpVO empVO = (EmpVO) hibernateTemplate.get(EmpVO.class, empno);
		MemberVO memberVO = new MemberVO(); // ●●●去除關聯關係後，再刪除
		memberVO.setMemNO(memNO);
		hibernateTemplate.delete(memberVO);
	}

	// @Override
	// public MemberVO findByPrimaryKey(Integer memNO) {
	// MemberVO memberVO = null;
	// Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	// try {
	// session.beginTransaction();
	// memberVO = (MemberVO) session.get(MemberVO.class, memNO);
	// session.getTransaction().commit();
	// } catch (RuntimeException ex) {
	// session.getTransaction().rollback();
	// throw ex;
	// }
	// return memberVO;
	// }

	@Override
	public MemberVO findByPrimaryKey(Integer memNO) {
		MemberVO memberVO = (MemberVO) hibernateTemplate.get(MemberVO.class, memNO);
		return memberVO;
	}

	// @Override
	// public Set<MemberVO> getAll() {
	// List<MemberVO> list = null;
	//
	// Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	// try {
	// session.beginTransaction();
	// list = session.createQuery(GET_ALL_STMT).getResultList();
	// session.getTransaction().commit();
	// } catch (RuntimeException ex) {
	// session.getTransaction().rollback();
	// throw ex;
	// }
	// return new LinkedHashSet<MemberVO>(list);
	// }

	@Override
	public Set<MemberVO> getAll() {
		Object obj = hibernateTemplate.find(GET_ALL_STMT);// 可封裝查詢條件,必須使用find方法.
		List<MemberVO> list = (List<MemberVO>) obj;
		return new LinkedHashSet<MemberVO>(list);
	}

	/**** 自訂指令 ****/
	// OK
	// member會員輸入的autoComplete(黑名單及行事曆使用)
	private static String AUTO_COMPLETE = "from MemberVO where memName like ?";

	@Override
	public ArrayList<MemberVO> autoCompleteM(String name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		ArrayList<MemberVO> array = new ArrayList<MemberVO>();
		try {
			session.getTransaction().begin();
			Query query = session.createQuery(AUTO_COMPLETE);
			query.setParameter(0, "%" + name + "%");
			List<MemberVO> list = query.list();
			array.addAll(list);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
		return array;
	}

	// OK
	// 黑名單檢查，若次數超過三次，則更改會員狀態為"黑名單會員"(黑名單用)
	private static String CHECK_INFRACTION = "from MemberVO where memNO = ? AND memInfract>=3";

	@Override
	public void checkInfraction(Integer memNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			Query query = session.createQuery(CHECK_INFRACTION);
			query.setParameter(0, memNO);
			List<MemberVO> list = query.list();
			for (MemberVO vo : list) {
				vo.setMemStatus("黑名單會員");
				session.saveOrUpdate(vo);
			}
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	// OK
	// 員工申請黑名單時，黑名單次數+1(黑名單用)
	private static String INFRACTPLUS1 = "from MemberVO where memNO = ? AND memStatus != '黑名單會員'";

	@Override
	public Integer infractPlus1(Integer memNO) {
		Integer result = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			Query query = session.createQuery(INFRACTPLUS1);
			query.setParameter(0, memNO);
			List<MemberVO> list = query.list();
			for (MemberVO vo : list) {
				vo.setMemInfract(vo.getMemInfract() + 1);
				session.saveOrUpdate(vo);
				result = 1;
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return result;
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
		// List<MemberVO> list = null;
		// MemberVO account = new MemberVO();
		// Session session =
		// HibernateUtil.getSessionFactory().getCurrentSession();
		//
		// try {
		// session.beginTransaction();
		// Query query = session.createQuery(SELECT_BY_ACCOUNT);
		// query.setParameter(0, memAccount);
		// list = query.list();
		// for (MemberVO memberVO : list) {
		// account = memberVO;
		// }
		// session.getTransaction().commit();
		// } catch (RuntimeException ex) {
		// session.getTransaction().rollback();
		// throw ex;
		// }

		MemberVO account = new MemberVO();
		Object obj = hibernateTemplate.find(SELECT_BY_ACCOUNT, memAccount);// 可封裝查詢條件,必須使用find方法.
		List<MemberVO> list = (List<MemberVO>) obj;
		for (MemberVO memberVO : list) {
			account = memberVO;
		}
		return account;
	}

	// 忘記帳號查詢
	@Override
	public MemberVO findByTel(String memTel) {
		// List<MemberVO> list = null;
		// MemberVO tel = new MemberVO();
		// Session session =
		// HibernateUtil.getSessionFactory().getCurrentSession();
		//
		// try {
		// session.beginTransaction();
		// Query query = session.createQuery(SELECT_BY_TEL);
		// query.setParameter(0, memTel);
		// list = query.list();
		// for (MemberVO memberVO : list) {
		// tel = memberVO;
		// }
		// session.getTransaction().commit();
		// } catch (RuntimeException ex) {
		// session.getTransaction().rollback();
		// throw ex;
		// }
		// return tel;

		MemberVO tel = new MemberVO();
		Object obj = hibernateTemplate.find(SELECT_BY_TEL, memTel);// 可封裝查詢條件,必須使用find方法.
		List<MemberVO> list = (List<MemberVO>) obj;
		for (MemberVO memberVO : list) {
			tel = memberVO;
		}
		return tel;
	}

	/**** 執行前注意事項：目前自訂指令暫不使用Spring hibernateTemplate ****/
	/**** 此測試時請注意暫先利用產生不同的Spring和hibernate之daSP和dao區分 ****/
	public static void main(String[] args) {
		MemberDAO_hibernate dao = new MemberDAO_hibernate();

		// 為方便一般應用程式main方的測試,所以底下的model-config1內部dataSource設定是採用org.springframework.jdbc.datasource.DriverManagerDataSource
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-model-JDBCcfg.xml");
		// 建立DAO物件
		MemberDAO_interface_hibernate daSP = (MemberDAO_interface_hibernate) context.getBean("memDAO");

		// 新增 (register由資料庫系統給)
		// MemberVO memberVO1 = new MemberVO();
		// memberVO1.setMemTel("0905123456");
		// memberVO1.setMemAccount("account123");
		// memberVO1.setMemPwd("123Pwd@@");
		// memberVO1.setMemName("小明Lin");
		// memberVO1.setMemGender("男");
		// memberVO1.setMemEmail("mail123@gmail.com");
		// memberVO1.setMemStatus("未驗證會員");
		// memberVO1.setMemInfract(0);
		// memberVO1.setMemImage(null);
		// daSP.insert(memberVO1);
		//
		// // 修改初始資料第一筆 (register由資料庫系統給)
		// MemberVO memberVO2 = new MemberVO();
		// memberVO2.setMemNO(40003);
		// memberVO2.setMemTel("0905123456");
		// memberVO2.setMemAccount("account123");
		// memberVO2.setMemPwd("123Pwd@@");
		// memberVO2.setMemName("小花Lin");
		// memberVO2.setMemGender("女");
		// memberVO2.setMemEmail("mail123@gmail.com");
		// memberVO2.setMemStatus("一般會員驗證");
		// memberVO2.setMemInfract(2);
		// // memberVO2.setMimage(null); // not use
		// daSP.update(memberVO2);
		//
		// // 查詢初始資料第一筆
		// MemberVO memberVO3 = daSP.findByPrimaryKey(40003);
		// System.out.print(memberVO3.getMemNO() + ",");
		// System.out.print(memberVO3.getMemTel() + ",");
		// System.out.print(memberVO3.getMemAccount() + ",");
		// System.out.print(memberVO3.getMemPwd() + ",");
		// System.out.print(memberVO3.getMemName() + ",");
		// System.out.print(memberVO3.getMemGender() + ",");
		// System.out.print(memberVO3.getMemEmail() + ",");
		// System.out.print(memberVO3.getMemRegister() + ",");
		// System.out.print(memberVO3.getMemStatus() + ",");
		// System.out.print(memberVO3.getMemInfract() + ",");
		// // System.out.println(memberHibernateVO3.getMemImage());
		// System.out.println();
		// System.out.println("---------------------");
		//
		// // 查詢全部
		// Set<MemberVO> set = daSP.getAll();
		// for (MemberVO memberVO : set) {
		// System.out.print(memberVO.getMemNO() + ",");
		// System.out.print(memberVO.getMemTel() + ",");
		// System.out.print(memberVO.getMemAccount() + ",");
		// System.out.print(memberVO.getMemPwd() + ",");
		// System.out.print(memberVO.getMemName() + ",");
		// System.out.print(memberVO.getMemGender() + ",");
		// System.out.print(memberVO.getMemEmail() + ",");
		// System.out.print(memberVO.getMemRegister() + ",");
		// System.out.print(memberVO.getMemStatus() + ",");
		// System.out.print(memberVO.getMemInfract() + ",");
		// // System.out.println(memberHibernateVO.getMemImage());
		// System.out.println();
		// }

		// 刪除初始資料一筆
		// dao.delete(40002);

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
		MemberVO memberVO4 = daSP.findByAccount("Vir111");
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
		System.out.println(memberVO4.getMemImage());

		// 忘記帳號查詢
		MemberVO memberVO5 = daSP.findByTel("0911841573");
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
		System.out.println(memberVO4.getMemImage());

		System.out.println("Done");
	}
}
