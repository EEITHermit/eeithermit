package com.hermit.iii.infraction.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class InfractionJNDIDAO implements InfractionDAO_interface {
	DataSource ds = null;
	public InfractionJNDIDAO(){
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/JNDI");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	String insert = "insert into Infraction values(?,?,getDate(),?)";
	//新增，已測試
	@Override
	public Integer insert(InfractionVO inVO) {
		Integer result = 0;
		try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(insert);){
			ps.setInt(1, inVO.getMemNO());
			ps.setString(2, inVO.getReason());
			ps.setInt(3, inVO.getEmpNO());
			result = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

}
