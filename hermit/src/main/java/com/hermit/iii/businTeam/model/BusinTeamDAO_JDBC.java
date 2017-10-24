package com.hermit.iii.businTeam.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinTeamDAO_JDBC implements BusinTeamDAO_interface{
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=hermit";
	public BusinTeamDAO_JDBC(){
		try {
			Class.forName("driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	String delete = "delete from BusinTeam where businNO = ?";
	@Override
	public Integer delete(Integer businNO) {
		Integer result = 0;
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(delete)){
			ps.setInt(1, businNO);
			result = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	String insert = "insert into BusinTeam values(?,?)";
	@Override
	public Integer insert(BusinTeamVO btVO) {
		Integer result = 0;
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(insert)){
			ps.setString(1, btVO.getBusinName());
			ps.setInt(2, btVO.getManager());
			result = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	String update = "update BusinTeam set businName = ? , manager = ? where businNO = ?";
	@Override
	public Integer update(BusinTeamVO btVO) {
		Integer result = 0;
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(update)){
			ps.setString(1, btVO.getBusinName());
			ps.setInt(2, btVO.getManager());
			ps.setInt(3, btVO.getBusinNO());
			result = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	String select = "select * from BusinTeam where businNO = ?";
	@Override
	public BusinTeamVO select(Integer businNO) {
		BusinTeamVO btVO = new BusinTeamVO();
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
				PreparedStatement ps = conn.prepareStatement(select)){
				ps.setInt(1, btVO.getBusinNO());
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					btVO.setBusinNO(rs.getInt("businNO"));
					btVO.setBusinName(rs.getString("businName"));
					btVO.setManager(rs.getInt("manager"));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		return btVO;
	}
	
}
