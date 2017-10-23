package com.hermit.iii.dispatchlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONValue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;




public class DispatchListDAO_JDBC implements DispatchListDAO_interface {
		
	private static final String INSERT_STMT =
		      "INSERT INTO DispatchList (dempNO,aempNO,qaNO,dlStime) VALUES (?, ?, ?, ?)";
	private static final String UPDATE =
			"UPDATE DispatchList set dempNO=?, aempNO=?, qaNO=?, dlStime=?,dlEtime=?, elesign=?,dlNote=? where dlNO = ?";
	private static final String DELETE =
		      "DELETE FROM DispatchList where dlNO = ?";
	private static final String GET_ONE_STMT =
			"SELECT dlNO,dempNO,aempNO,qaNO,dlStime,dlEtime,elesign,dlNote FROM DispatchList where dlNO = ?";
	private static final String GET_ALL_STMT =
			"SELECT dlNO,dempNO,aempNO,qaNO,dlStime,dlEtime,elesign,dlNote FROM DispatchList order by dlNO";
	
	
	
	@Override
	public void insert(DispatchListVO DispatchListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{

			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,DispatchListVO.getDempNo());
			pstmt.setInt(2,DispatchListVO.getAempNo());
			pstmt.setInt(3,DispatchListVO.getQaNo());
			pstmt.setDate(4,DispatchListVO.getDlStime());
			pstmt.execute();

		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(DispatchListVO DispatchListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1,DispatchListVO.getDempNo());
			pstmt.setInt(2,DispatchListVO.getAempNo());
			pstmt.setInt(3,DispatchListVO.getQaNo());
			pstmt.setDate(4,DispatchListVO.getDlStime());
			pstmt.setDate(5,DispatchListVO.getDlEtime());
			pstmt.setString(6,DispatchListVO.getElesign());
			pstmt.setString(7,DispatchListVO.getDlNote());
			pstmt.setInt(8,DispatchListVO.getDlNo());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer dlno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit","sa", "P@ssw0rd");
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1,dlno);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public DispatchListVO findByPrimaryKey(Integer dlno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		DispatchListVO dlVO = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, dlno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dlVO = new DispatchListVO();
				dlVO.setDlNo(rs.getInt("dlNO"));
				dlVO.setDempNo(rs.getInt("dempNO"));
				dlVO.setAempNo(rs.getInt("aempNO"));
				dlVO.setQaNo(rs.getInt("qaNO"));
				dlVO.setDlStime(rs.getDate("dlStime"));
				dlVO.setDlEtime(rs.getDate("dlEtime"));
				dlVO.setElesign(rs.getString("elesign"));
				dlVO.setDlNote(rs.getString("dlNote"));
			}
			return dlVO;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<DispatchListVO> getAll() {
		List<DispatchListVO> list = new ArrayList<DispatchListVO>();
		DispatchListVO dlVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
				
			while (rs.next()) {
				dlVO = new DispatchListVO();
				dlVO.setDlNo(rs.getInt("dlNO"));
				dlVO.setDempNo(rs.getInt("dempNO"));
				dlVO.setAempNo(rs.getInt("aempNO"));
				dlVO.setQaNo(rs.getInt("qaNO"));
				dlVO.setDlStime(rs.getDate("dlStime"));
				dlVO.setDlEtime(rs.getDate("dlEtime"));
				dlVO.setElesign(rs.getString("elesign"));
				dlVO.setDlNote(rs.getString("dlNote"));
				list.add(dlVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
		@Override
		public String getAllForJson() {
			List<Map> list = new ArrayList<Map>();
			DispatchListVO dlVO = null;
			String jsonString = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
					
				while (rs.next()) {
					Map m1 = new LinkedHashMap();
					m1.put("dlNO", rs.getInt("dlNO"));
					m1.put("dempNO", rs.getInt("dempNO"));
					m1.put("aempNO",rs.getInt("aempNO"));
					m1.put("qaNO",rs.getInt("qaNO"));
					m1.put("dlStime","["+rs.getDate("dlStime")+"]");
					m1.put("dlEtime","["+rs.getDate("dlEtime")+"]");
					m1.put("elesign","["+rs.getDate("elesign")+"]");
					m1.put("dlNote",rs.getString("dlNote"));
					list.add(m1);
				}
				Map m2 = new LinkedHashMap();
				m2.put("list",list);
				jsonString = JSONValue.toJSONString(m2);
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return jsonString;
		}
	
	public static void main (String args[]){
		DispatchListVO dVO = new DispatchListVO();
		DispatchListDAO_JDBC dDAO = new DispatchListDAO_JDBC();
		
//Insert Test Start
		
//		System.out.println("insert start");
//		dVO.setDempNo(30001);
//		dVO.setAempNo(30002);
//		dVO.setQaNo(60000001);
//		dVO.setDlStime(java.sql.Date.valueOf("2017-10-10"));
//		dDAO.insert(dVO);
//		System.out.println("insert success");
		
////Insert Test End
////Update Test Start

//		System.out.println("update start");
//		dVO.setDlNo(70000002);
//		dVO.setDempNo(30001);
//		dVO.setAempNo(30002);
//		dVO.setQaNo(60000001);
//		dVO.setDlStime(java.sql.Date.valueOf("2017-10-10"));
//		dVO.setDlEtime(java.sql.Date.valueOf("2017-10-11"));
//		dVO.setElesign("date:image/png;base64,1234");
//		dVO.setDlNote("測試修復完畢+-*/");
//		dDAO.update(dVO);
//		System.out.println("update success");
		
//Update Test End
//Delete Test Start
		
//		System.out.println("delete start");
//		dDAO.delete(70000004);
//		System.out.println("delete success");
		
//Delete Test End		
//Search One Test Start
		
//		System.out.println("Search One Start");
//		dVO = dDAO.findByPrimaryKey(70000001);
//		System.out.println("dlNO \t= " + dVO.getDlNo());
//		System.out.println("dempNO \t= " + dVO.getDempNo());
//		System.out.println("aempNO \t= " + dVO.getAempNo());
//		System.out.println("qaNO \t= " + dVO.getQaNo());
//		System.out.println("dlStime = " + dVO.getDlStime());
//		System.out.println("dlEtime = " + dVO.getDlEtime());
//		System.out.println("elesign = " + dVO.getElesign());
//		System.out.println("dlNote \t= " + dVO.getDlNote());
//		System.out.println("Search One success");
		
//Search One Test End	

//Search All Test Start
		
//		System.out.println("-----------Search All Start------------");	
//		List<DispatchListVO> list = dDAO.getAll();
//		for(int i=0;i<list.size();i++){
//			dVO = list.get(i);
//			System.out.println("dlNO \t= " + dVO.getDlNo());
//			System.out.println("dempNO \t= " + dVO.getDempNo());
//			System.out.println("aempNO \t= " + dVO.getAempNo());
//			System.out.println("qaNO \t= " + dVO.getQaNo());
//			System.out.println("dlStime = " + dVO.getDlStime());
//			System.out.println("dlEtime = " + dVO.getDlEtime());
//			System.out.println("elesign = " + dVO.getElesign());
//			System.out.println("dlNote \t= " + dVO.getDlNote());
//		}
//		System.out.println("-----------Search All success------------");	
		
//Search All Test End		
	}
}

