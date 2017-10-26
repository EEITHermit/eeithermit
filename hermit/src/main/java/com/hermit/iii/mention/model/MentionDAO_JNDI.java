package com.hermit.iii.mention.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MentionDAO_JNDI implements MentionDAO_Interface{
	DataSource ds = null;
	public MentionDAO_JNDI(){
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/JNDI");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//員工登入頁面後，取得員工所負責區域編號
	String getBoroughNOByEmpNO = "select boroughNO from TeamMemberList L "
			+ "join TeamArea T on L.businNO = T.businNO where L.empNO = ?";
	@Override
	public ArrayList<Integer> getBoroughNOByEmpNO(Integer EmpNO){
		ArrayList<Integer> result = new ArrayList<Integer>();
		try (Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getBoroughNOByEmpNO);){
			ps.setInt(1, EmpNO);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				result.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
