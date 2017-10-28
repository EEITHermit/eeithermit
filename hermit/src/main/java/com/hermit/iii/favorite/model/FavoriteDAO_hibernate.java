package com.hermit.iii.favorite.model;

import java.util.*;

import org.hibernate.*;

import com.hermit.iii.util.*;

public class FavoriteDAO_hibernate implements FavoriteDAO_interface_hibernate {

	private static final String GET_ALL_STMT = "from FavoriteVO order by favNO";

	@Override
	public void insert(FavoriteVO favoriteVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(favoriteVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(FavoriteVO favoriteVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(favoriteVO);
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
			FavoriteVO favoriteVO = (FavoriteVO) session.get(FavoriteVO.class, favNO);
			session.delete(favoriteVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public FavoriteVO findByPrimaryKey(Integer favNO) {
		FavoriteVO favoriteVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			favoriteVO = (FavoriteVO) session.get(FavoriteVO.class, favNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return favoriteVO;
	}

	@Override
	public Set<FavoriteVO> getAll() {
		List<FavoriteVO> list = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			list = session.createQuery(GET_ALL_STMT).getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return new LinkedHashSet<FavoriteVO>(list);
	}

	public static void main(String[] args) {
		FavoriteDAO_hibernate dao = new FavoriteDAO_hibernate();

		// 新增
		FavoriteVO favoriteVO1 = new FavoriteVO();
		favoriteVO1.setMemNO(40001);
		favoriteVO1.setHouseNO(20001);
		favoriteVO1.setFavDate(java.sql.Date.valueOf("2017-10-08"));
		dao.insert(favoriteVO1);

		// 修改初始資料第一筆
		FavoriteVO favoriteVO2 = new FavoriteVO();
		favoriteVO2.setFavNO(40001);
		favoriteVO2.setMemNO(40001);
		favoriteVO2.setHouseNO(20001);
		favoriteVO2.setFavDate(java.sql.Date.valueOf("2015-02-02"));
		dao.update(favoriteVO2);

		// 查詢初始資料第一筆
		FavoriteVO favoriteVO3 = dao.findByPrimaryKey(40001);
		System.out.print(favoriteVO3.getMemNO() + ",");
		System.out.println(favoriteVO3.getHouseNO());
		System.out.println("---------------------");

		// 查詢全部
		Set<FavoriteVO> set = dao.getAll();
		for (FavoriteVO favoriteVO : set) {
			System.out.print(favoriteVO.getMemNO() + ",");
			System.out.println(favoriteVO.getHouseNO());
		}

		// 刪除初始資料一筆
		dao.delete(40001);

		System.out.println("Done");
	}
}