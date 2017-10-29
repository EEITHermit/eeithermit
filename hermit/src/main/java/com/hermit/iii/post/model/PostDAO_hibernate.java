package com.hermit.iii.post.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import com.hermit.iii.util.HibernateUtil;

public class PostDAO_hibernate implements PostDAO_interface_hibernate {

	private static final String GET_ALL_STMT = "FROM PostVO order by postNO";

	@Override
	public void insert(PostVO postVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(postVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(PostVO postVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(postVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public void delete(Integer postNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PostVO postVO = (PostVO) session.get(PostVO.class, postNO);
			session.delete(postVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public PostVO findByPrimaryKey(Integer postNO) {
		PostVO postVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			postVO = (PostVO) session.get(PostVO.class, postNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return postVO;
	}

	@Override
	public Set<PostVO> getAll() {
		List<PostVO> list = null;
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
		return new LinkedHashSet<PostVO>(list);
	}

	public static void main(String[] args) {
		PostDAO_hibernate dao = new PostDAO_hibernate();

		// insert
		PostVO postVO1 = new PostVO();
		postVO1.setPostName("管理員");
		dao.insert(postVO1);

		// update
		PostVO postVO2 = new PostVO();
		postVO2.setPostNO(320);
		postVO2.setPostName("專案業務人員");
		dao.update(postVO2);

		// delete
		dao.delete(350);

		// select one
		PostVO postVO3 = dao.findByPrimaryKey(330);
		System.out.print(postVO3.getPostNO() + ",");
		System.out.println(postVO3.getPostName());
		System.out.println("---------------------------");

		// select all
		Set<PostVO> set = dao.getAll();
		for (PostVO postVO : set) {
			System.out.print(postVO.getPostNO() + ",");
			System.out.print(postVO.getPostName());
			System.out.println("");
		}
		System.out.println("----------------------");

		System.out.println("Finish.");
	}

}
