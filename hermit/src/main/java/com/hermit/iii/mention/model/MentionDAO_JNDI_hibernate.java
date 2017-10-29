package com.hermit.iii.mention.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.hibernate.Query;
import org.hibernate.Session;

import com.hermit.iii.teamArea.model.TeamAreaVO;
import com.hermit.iii.teammemberlist.model.TeamMemberListVO;
import com.hermit.iii.util.HibernateUtil;

public class MentionDAO_JNDI_hibernate implements MentionDAO_Interface{
	
	//員工登入頁面後，取得員工所負責區域編號
	String getBoroughNOByEmpNO = "select boroughNO from TeamMemberList L "
			+ "join TeamArea T on L.businNO = T.businNO where L.empNO = ?";
	private static String GET_BOROUGHNO_BY_EMPNO = "from TeamMemberListVO where empNO = ?";
	@Override
	public ArrayList<Integer> getBoroughNOByEmpNO(Integer EmpNO){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		ArrayList<Integer> result = new ArrayList<Integer>();
		Set<Integer> temp = new HashSet<Integer>();
		try{
			session.getTransaction().begin();
			Query query = session.createQuery(GET_BOROUGHNO_BY_EMPNO);
			query.setParameter(0, EmpNO);
			List<TeamMemberListVO> list = query.list();
			for(TeamMemberListVO t :list){
				Set<TeamAreaVO> set= t.getBusinTeamVO().getTeamAreaVOs();
				for(TeamAreaVO v :set){
					temp.add(v.getBoroughsVO().getBoroughNO());
				}
			}
			result.addAll(temp);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}
	
}
