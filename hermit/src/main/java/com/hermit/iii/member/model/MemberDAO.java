package com.hermit.iii.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=hermit";
	//member會員輸入的autoComplete(黑名單及行事曆使用)
	public ArrayList<MemberVO> autoCompleteM(String name){
		ArrayList<MemberVO> array = new ArrayList<MemberVO>();
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement("select * from member where memName like ?");
			ps.setString(1, "%"+name+"%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				MemberVO memberVO = new MemberVO();
				memberVO.setMemNO(rs.getInt("memNO"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemTel(rs.getString("memTel"));
				array.add(memberVO);
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
	//員工申請黑名單時，黑名單次數+1(黑名單用)
	String infractplus1 = "update member set memInfract = memInfract+1 where memNO = ? AND memStatus != '黑名單會員'";
	public Integer infractPlus1(Integer memNO){
		Connection conn = null;
		Integer result=0;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(infractplus1);
			ps.setInt(1, memNO);
			result = ps.executeUpdate();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//黑名單檢查，若次數超過三次，則更改會員狀態為"黑名單會員"(黑名單用)
	String checkInfraction = "update member set memStatus = '黑名單會員' where memNO = ? AND memInfract >= 3";
	public void checkInfraction(Integer memNO){
		Connection conn = null;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(checkInfraction);
			ps.setInt(1, memNO);
			ps.executeUpdate();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
