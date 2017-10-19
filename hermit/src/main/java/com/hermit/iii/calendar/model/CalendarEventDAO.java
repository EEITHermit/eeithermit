package com.hermit.iii.calendar.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class CalendarEventDAO implements CalendarEventDAO_interface{
	Connection conn = null;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=hermit";
	//查詢員工時段內的預約，測試完畢
	String selectByEandT="select * from CalendarEvent C "
			+ "join Emp E ON C.empNO = E.empNO "
			+ "join member M ON C.memNO = M.memNO "
			+ "join house H ON C.houseNO = H.houseNO "
			+ "where E.empNO=? and (C.eventStartTime between ? and ?)";
	@Override
	public ArrayList<CalendarEventVO> selectByEmpAndTime(Integer empID, Timestamp start, Timestamp end) {
		ArrayList<CalendarEventVO> array = new ArrayList<CalendarEventVO>();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(selectByEandT);
			ps.setInt(1, empID);
			ps.setTimestamp(2, start);
			ps.setTimestamp(3, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				CalendarEventVO resVO = new CalendarEventVO();
				resVO.setEventNO(rs.getInt("eventNO"));
				resVO.getEmpVO().setEmpNO(rs.getInt("empNO"));
				resVO.getMemberVO().setMemNO(rs.getInt("memNO"));
				resVO.getMemberVO().setMemName(rs.getString("memName"));
				resVO.getMemberVO().setMemTel(rs.getString("memTel"));
				resVO.getHouseVO().setHouseNO(rs.getInt("houseNO"));
				resVO.getHouseVO().setHouseAddr(rs.getString("houseAddr"));
				resVO.setEventStartTime(rs.getTimestamp("eventStartTime"));
				resVO.setEventEndTime(rs.getTimestamp("eventEndTime"));
				resVO.setPs(rs.getString("ps"));
				array.add(resVO);
			}
		} catch (SQLException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return array;
	}
	//查詢時間是否衝突，測試完畢
	String checkE = "select * from CalendarEvent where (empNO = ?) AND (eventNO != ?)"
					+ "AND(eventStartTime between ? and ?) "
					+ "AND((?>=eventStartTime AND ?<eventEndTime)"
					+ "or(?>eventStartTime AND ?<=eventEndTime)"
					+ "or(?<=eventStartTime AND ?>=eventEndTime))";
	@Override
	public boolean checkExist(Integer empID, Timestamp start, Timestamp end,Integer eventNo) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		boolean result = true;
		String day = start.toString().substring(0,10);
		Date date1 = Date.valueOf(day);
		Date date2 = new Date(Timestamp.valueOf(day+" 00:00:00").getTime()+60*60*24*1000);
		try (Connection conn=DriverManager.getConnection(url,"sa","P@ssw0rd")){
			PreparedStatement ps = conn.prepareStatement(checkE);
			ps.setInt(1, empID);
			ps.setInt(2, eventNo);
			ps.setDate(3, date1);
			ps.setDate(4, date2);
			ps.setTimestamp(5, start);
			ps.setTimestamp(6, start);
			ps.setTimestamp(7, end);
			ps.setTimestamp(8, end);
			ps.setTimestamp(9, start);
			ps.setTimestamp(10, end);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				result = true;
			}else{
				result = false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//查詢當日最大ID，測試完畢
//	String checkMax = "select MAX(預約ID) from reservation where 員工ID=? AND 預約ID like ?";
//	@Override
//	public Integer checkMaxID(String ID,Integer empID) {
//		Integer max = null;
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd")){
//			PreparedStatement ps = conn.prepareStatement(checkMax);
//			ps.setInt(1, empID);
//			ps.setString(2, ID.substring(0,8)+"%");
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()){
//				String maxId = rs.getString(1).substring(9);
//				max = new Integer(maxId);
//			}else{
//				max = 0;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return max;
//	}
	//更新資料，測試完畢
	String update = "update CalendarEvent set "
			+ "empNO = ? , memNO = ? , houseNO = ? ,"
			+ "eventStartTime= ?, eventEndTime=?,ps=? "
			+ "where eventNO = ?";
	@Override
	public Integer update(CalendarEventVO resVO) {
		Integer result = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd")){
			PreparedStatement ps = conn.prepareStatement(update);
			ps.setInt(7, resVO.getEventNO());
			ps.setInt(1, resVO.getEmpVO().getEmpNO());
			ps.setInt(2, resVO.getMemberVO().getMemNO());
			ps.setInt(3, resVO.getHouseVO().getHouseNO());
			ps.setTimestamp(4, resVO.getEventStartTime());
			ps.setTimestamp(5, resVO.getEventEndTime());
			ps.setString(6, resVO.getPs());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//新增資料，測試OK
	String insert = "insert into CalendarEvent(empNO,memNO,houseNO,eventStartTime,"
			+ "eventEndTime,ps) values(?,?,?,?,?,?)";
	@Override
	public Integer insert(CalendarEventVO resVO) {
		Integer result = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd")){
			PreparedStatement ps = conn.prepareStatement(insert);
			
			ps.setInt(1, resVO.getEmpVO().getEmpNO());
			ps.setInt(2, resVO.getMemberVO().getMemNO());
			ps.setInt(3, resVO.getHouseVO().getHouseNO());
			ps.setTimestamp(4, resVO.getEventStartTime());
			ps.setTimestamp(5, resVO.getEventEndTime());
			ps.setString(6, resVO.getPs());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//刪除資料
	String delete = "delete from CalendarEvent where eventNO = ?";
	@Override
	public Integer delete(int ID) {
		Integer result = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd")){
			PreparedStatement ps = conn.prepareStatement(delete);
			ps.setInt(1,ID);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//查詢員工時段內的預約，測試完畢
		String selectByMember="select * from CalendarEvent C "
				+ "join Emp E ON C.empNO = E.empNO "
				+ "join member M ON C.memNO = M.memNO "
				+ "join house H ON C.houseNO = H.houseNO "
				+ "where M.memNO=? and (C.eventStartTime between getDate() and (getDate()+365))"; //+1為加一天
		@Override
		public ArrayList<CalendarEventVO> selectByMember(Integer memberNo){
			ArrayList<CalendarEventVO> array = new ArrayList<CalendarEventVO>();
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
				PreparedStatement ps = conn.prepareStatement(selectByMember);
				ps.setInt(1, memberNo);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					CalendarEventVO resVO = new CalendarEventVO();
					resVO.setEventNO(rs.getInt("eventNO"));
					resVO.getEmpVO().setEmpName(rs.getString("empName"));
					resVO.getEmpVO().setEmpPhone(rs.getString("empPhone"));
					resVO.getHouseVO().setHouseTitle(rs.getString("houseTitle"));
					resVO.getHouseVO().setHouseAddr(rs.getString("houseAddr"));
					resVO.setEventStartTime(rs.getTimestamp("eventStartTime"));
					array.add(resVO);
				}
			} catch (SQLException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			return array;
		}
	
}
