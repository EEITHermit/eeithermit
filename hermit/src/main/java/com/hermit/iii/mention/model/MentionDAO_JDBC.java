package com.hermit.iii.mention.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MentionDAO_JDBC implements MentionDAO_Interface{
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=hermit";
	public MentionDAO_JDBC(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//員工登入頁面後，取得員工所負責區域編號
	String getBoroughNOByEmpNO = "select boroughNO from TeamMemberList L "
			+ "join TeamArea T on L.businNO = T.businNO where L.empNO = ?";
	@Override
	public Integer getBoroughNOByEmpNO(Integer EmpNO){
		Integer result = null;
		try (Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(getBoroughNOByEmpNO);){
			ps.setInt(1, EmpNO);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
