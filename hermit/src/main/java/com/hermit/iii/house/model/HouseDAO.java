package com.hermit.iii.house.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HouseDAO {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=hermit";
	public ArrayList<HouseVO> autoCompleteH(String address){
		HouseVO houseVO = new HouseVO();
		ArrayList<HouseVO> array = new ArrayList<HouseVO>();
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement("select * from house where houseAddr like ?");
			ps.setString(1, "%"+address+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				houseVO.setHouseNO(rs.getInt("houseNO"));
				houseVO.setHouseAddr(rs.getString("houseAddr"));
				array.add(houseVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return array;
	}
	
	public Integer findAreaNoByHouseNo(Integer houseNo){
		Integer areaNo = null;
		Connection conn = null;
		String find = "select areaNo from house where houseNO = ?";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(find);
			ps.setInt(1, houseNo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			areaNo = rs.getInt(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return areaNo;
	}
}
