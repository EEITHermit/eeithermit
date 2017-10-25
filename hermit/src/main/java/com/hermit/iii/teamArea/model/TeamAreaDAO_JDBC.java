package com.hermit.iii.teamArea.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hermit.iii.businTeam.model.BusinTeamVO;

public class TeamAreaDAO_JDBC implements TeamAreaDAO_interface{
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=hermit";
	public TeamAreaDAO_JDBC(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	String insert = "insert into TeamArea values(?,?,?)";
	@Override
	public Integer insert(TeamAreaVO taVO) {
		Integer result = 0;
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(insert)){
			ps.setInt(1, taVO.getBusinNO());
			ps.setInt(2, taVO.getCityNO());
			ps.setInt(3, taVO.getBoroughNO());
			result = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	String delete = "delete from TeamArea where businNO = ?";
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

	String select = "select * from TeamArea where businNO = ?";
	@Override
	public TeamAreaVO select(Integer businNO) {
		TeamAreaVO taVO = new TeamAreaVO();
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
				PreparedStatement ps = conn.prepareStatement(select)){
				ps.setInt(1, businNO);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					taVO.setBusinNO(rs.getInt("businNO"));
					taVO.setCityNO(rs.getInt("cityNO"));
					taVO.setBoroughNO(rs.getInt("boroughNO"));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		return taVO;
	}

	String update = "update TeamArea set cityNO = ? , boroughNO = ? where businNO = ?";
	@Override
	public Integer update(TeamAreaVO taVO) {
		Integer result = 0;
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(update)){
			ps.setInt(1, taVO.getCityNO());
			ps.setInt(2, taVO.getBoroughNO());
			ps.setInt(3, taVO.getBusinNO());
			result = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
}
