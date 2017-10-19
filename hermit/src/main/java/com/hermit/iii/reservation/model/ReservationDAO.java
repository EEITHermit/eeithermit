package com.hermit.iii.reservation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAO implements ReservationDAO_interface{
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=hermit";
	public ReservationDAO(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	// 會員確認預約後的新增
	String insert = "insert into reservation("
			+ "memNO,houseNO,areaNO,exceptTime,applyTime,takedOver) values(?,?,?,?,?,?)";
	@Override
	public Integer insert(ReservationVO rlVO) {
		int result = 0;
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd")){
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, rlVO.getMemberVO().getMemNO());
			ps.setInt(2, rlVO.getHouseVO().getHouseNO());
			ps.setInt(3, rlVO.getAreaNO());
			ps.setString(4,rlVO.getExceptTime());
			ps.setTimestamp(5, rlVO.getApplyTime());
			ps.setBoolean(6, false);
			result = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	//推播功能用
	String select = "select * from reservation where (areaNO = ?) AND (takedOver = false)";
	@Override
	public ArrayList<ReservationVO> selectByArea(Integer areaNo) {
		ArrayList<ReservationVO> array = new ArrayList<ReservationVO>();
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd")){
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, areaNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ReservationVO rlVO = new ReservationVO();
				rlVO.setReservationNo(rs.getInt("reservationNO"));
				rlVO.getMemberVO().setMemNO(rs.getInt("memNO"));
				rlVO.getHouseVO().setHouseNO(rs.getInt("houseNO"));
				rlVO.setExceptTime(rs.getString("exceptTime"));
				rlVO.setAreaNO(rs.getInt("areaNO"));
				rlVO.setApplyTime(rs.getTimestamp("applyTime"));
				array.add(rlVO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return array;
	}
	//員工接案後確認是否已被接案，再更新狀態
	String selectStatus = "select takedOver from reservation where reservationNO = ?";
	String updateEmpNo = "update reservation set empNO = ?,takedOver = true where reservationNO = ?";
	@Override
	public Integer updateStatus(Integer reservationNo , Integer empNo) {
		int result = 0;
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd")){
			PreparedStatement ps = conn.prepareStatement(selectStatus);
			ps.setInt(1, reservationNo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Boolean exist = rs.getBoolean(1);
			if(exist == false){
				ps = conn.prepareStatement(updateEmpNo);
				ps.setInt(1, empNo);
				ps.setInt(2, reservationNo);
				result = ps.executeUpdate();
			}else{
				return result;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	String checkExist = "select * from reservation where houseNO= ? AND memNO = ?";
	@Override
	public boolean checkExist(Integer houseNo,Integer memberNo){
		boolean result = true;
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd")){
			PreparedStatement ps = conn.prepareStatement(checkExist);
			ps.setInt(1, houseNo);
			ps.setInt(2, memberNo);
			ResultSet rs = ps.executeQuery();
			result = rs.next();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
}
