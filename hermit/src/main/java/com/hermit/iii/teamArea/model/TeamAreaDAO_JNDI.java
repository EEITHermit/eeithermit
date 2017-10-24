package com.hermit.iii.teamArea.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hermit.iii.businTeam.model.BusinTeamVO;

public class TeamAreaDAO_JNDI implements TeamAreaDAO_interface{
	DataSource ds = null;
	public TeamAreaDAO_JNDI(){
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/JNDI");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	String insert = "insert into TeamArea values(?,?,?)";
	@Override
	public Integer insert(TeamAreaVO taVO) {
		Integer result = 0;
		try(Connection conn = ds.getConnection();
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
		try(Connection conn = ds.getConnection();
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
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(select)){
				ps.setInt(1, taVO.getBusinNO());
				
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
		try(Connection conn = ds.getConnection();
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
