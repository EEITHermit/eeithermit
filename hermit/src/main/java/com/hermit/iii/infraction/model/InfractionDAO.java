package com.hermit.iii.infraction.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InfractionDAO implements InfractionDAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=hermit";
	public InfractionDAO(){
		
	}
	String insert = "insert into Infraction values(?,?,getDate(),?)";
	//新增，已測試
	@Override
	public Integer insert(InfractionVO inVO) {
		Integer result = 0;
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd")){
			PreparedStatement ps = conn.prepareStatement(insert);
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
