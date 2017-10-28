package com.hermit.iii.favorite.model;

import java.util.*;

import org.hibernate.*;

import com.hermit.iii.util.*;

public class FavoriteDAO_hibernate implements FavoriteDAO_interface_hibernate {

	private static final String GET_ALL_STMT = "from FavoriteVO_hibernate order by favNO";

	@Override
	public void insert(FavoriteVO_hibernate favoriteVO_hibernate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(favoriteVO_hibernate);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(FavoriteVO_hibernate favoriteVO_hibernate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(favoriteVO_hibernate);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer favNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			// 【注意多方不可(不宜)採用cascade聯級刪除】
			session.beginTransaction();
			FavoriteVO_hibernate favoriteVO_hibernate = (FavoriteVO_hibernate) session.get(FavoriteVO_hibernate.class,
					favNO);
			session.delete(favoriteVO_hibernate);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public FavoriteVO_hibernate findByPrimaryKey(Integer favNO) {
		FavoriteVO_hibernate favoriteVO_hibernate = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			favoriteVO_hibernate = (FavoriteVO_hibernate) session.get(FavoriteVO_hibernate.class, favNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return favoriteVO_hibernate;
	}

	@Override
	public Set<FavoriteVO_hibernate> getAll() {
		List<FavoriteVO_hibernate> list = null;

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
		return new LinkedHashSet<FavoriteVO_hibernate>(list);
	}

	public static void main(String[] args) {
		FavoriteDAO_hibernate dao = new FavoriteDAO_hibernate();

		// 新增
		FavoriteVO_hibernate favoriteVO_hibernate1 = new FavoriteVO_hibernate();
		favoriteVO_hibernate1.setMemNO(40001);
		favoriteVO_hibernate1.setHouseNO(20001);
		favoriteVO_hibernate1.setFavDate(java.sql.Date.valueOf("2017-10-08"));
		dao.insert(favoriteVO_hibernate1);

		// 修改初始資料第一筆
		FavoriteVO_hibernate favoriteVO_hibernate2 = new FavoriteVO_hibernate();
		favoriteVO_hibernate2.setFavNO(40001);
		favoriteVO_hibernate2.setMemNO(40001);
		favoriteVO_hibernate2.setHouseNO(20001);
		favoriteVO_hibernate2.setFavDate(java.sql.Date.valueOf("2015-02-02"));
		dao.update(favoriteVO_hibernate2);

		// 查詢初始資料第一筆
		FavoriteVO_hibernate favoriteVO_hibernate3 = dao.findByPrimaryKey(40001);
		System.out.print(favoriteVO_hibernate3.getMemNO() + ",");
		System.out.println(favoriteVO_hibernate3.getHouseNO());
		System.out.println("---------------------");

		// 查詢全部
		Set<FavoriteVO_hibernate> set = dao.getAll();
		for (FavoriteVO_hibernate favoriteVO_hibernate : set) {
			System.out.print(favoriteVO_hibernate.getMemNO() + ",");
			System.out.println(favoriteVO_hibernate.getHouseNO());
		}

		// 刪除初始資料一筆
		dao.delete(40001);

		System.out.println("Done");
	}
}