package com.hermit.iii.teamArea.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hermit.iii.boroughs.model.BoroughsVO;
import com.hermit.iii.teammemberlist.model.TeamMemberListVO;
import com.hermit.iii.util.HibernateUtil;

public class TeamAreaDAO_hibernate implements TeamAreaDAO_interface {

	private static final String GET_ALL_STMT="from TeamArea";
	
	@Override
	public void insert(TeamAreaVO taVO) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(taVO);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer businNO) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			TeamAreaVO vo=session.get(TeamAreaVO.class, businNO);
			session.delete(vo);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public TeamAreaVO findByPrimaryKey(Integer businNO) {
		TeamAreaVO vo=null;
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			vo=session.get(TeamAreaVO.class, businNO);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
		return null;
	}

	@Override
	public void update(TeamAreaVO taVO) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(taVO);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
	}
	@Override
	public List<TeamAreaVO> getAll() {
		List<TeamAreaVO>list=null;
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query=session.createQuery(GET_ALL_STMT);
			list=query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	public static void main(String[] args) {
		TeamAreaDAO_hibernate dao=new TeamAreaDAO_hibernate();
		TeamAreaVO vo=new TeamAreaVO();
		
		//insert
		TeamMemberListVO vo1=new TeamMemberListVO();
		vo1.setBusinNO(30010);
		vo.setTeamMemberListVO(vo1);
		vo.setCityNO(2);
		BoroughsVO vo2=new BoroughsVO();
		vo2.setBoroughNO(1);
		vo.setBoroughVO(vo2);
		
		dao.insert(vo);
		
		
//		TeamAreaVO vo=new TeamAreaVO();
//		vo.setBusinNO(30020);
//		vo.setCityNO(1);
//		vo.setBoroughNO(2);
//		dao.insert(vo);
		
		//update
//		TeamAreaVO vo=new TeamAreaVO();
//		vo.setBusinNO(30010);
//		vo.setCityNO(1);
//		vo.setBoroughNO(2);
//		dao.update(vo);

	}

	

}
