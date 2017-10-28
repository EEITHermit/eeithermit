package com.hermit.iii.teamArea.model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.simple.JSONValue;

import com.hermit.iii.util.HibernateUtil;

public class TeamAreaDAO_hibernate implements TeamAreaDAO_interface {

	private static final String GET_ALL_STMT="from TeamArea";
	
	@Override
	public void insert(TeamAreaVO taVO) {
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
	public void delete(Integer businNO) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public TeamAreaVO select(Integer businNO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(TeamAreaVO taVO) {
		// TODO Auto-generated method stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
