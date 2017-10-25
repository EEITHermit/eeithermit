package com.hermit.iii.reservation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReservationJNDIDAO implements ReservationDAO_interface{
	DataSource ds = null;
	public ReservationJNDIDAO(){
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/JNDI");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	// 會員確認預約後的新增
	String insert = "insert into reservation("
			+ "memNO,houseNO,boroughNO,exceptTime,applyTime,takedOver) values(?,?,?,?,?,?)";
	@Override
	public Integer insert(ReservationVO rlVO) {
		int result = 0;
		try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(insert);){
			ps.setInt(1, rlVO.getMemberVO().getMemNO());
			ps.setInt(2, rlVO.getHouseVO().getHouseNO());
			ps.setInt(3, rlVO.getBoroughNO());
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
	String select = "select * from reservation r join house h on h.houseNO = r.houseNO "
			+ "join member m on m.memNO = r.memNO "
			+ "where (r.boroughNO = ?) AND (takedOver = 'false')";
	@Override
	public ArrayList<ReservationVO> selectByArea(Integer areaNo) {
		ArrayList<ReservationVO> array = new ArrayList<ReservationVO>();
		try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(select);){
			ps.setInt(1, areaNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ReservationVO rlVO = new ReservationVO();
				rlVO.setReservationNo(rs.getInt("reservationNO"));
				rlVO.getMemberVO().setMemNO(rs.getInt("memNO"));
				rlVO.getMemberVO().setMemName(rs.getString("memName"));
				rlVO.getMemberVO().setMemGender(rs.getString("memGender"));
				rlVO.getMemberVO().setMemTel(rs.getString("memTel"));
				rlVO.getHouseVO().setHouseNO(rs.getInt("houseNO"));
				rlVO.getHouseVO().setHouseAddr(rs.getString("houseAddr"));
				rlVO.setExceptTime(rs.getString("exceptTime"));
				rlVO.setBoroughNO(rs.getInt("boroughNO"));
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
	String updateEmpNo = "update reservation set empNO = ?,takedOver = 'true' where reservationNO = ?";
	@Override
	public Integer updateStatus(Integer reservationNo , Integer empNo) {
		int result = 0;
		try(Connection conn = ds.getConnection();){
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
	//確認是否有預約過此房屋
	String checkExist = "select * from reservation where houseNO= ? AND memNO = ? AND takedOver = 'false'";
	@Override
	public boolean checkExist(Integer houseNo,Integer memberNo){
		boolean result = true;
		try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(checkExist);){
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
