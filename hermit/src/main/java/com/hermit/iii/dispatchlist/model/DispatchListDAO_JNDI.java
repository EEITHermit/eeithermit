package com.hermit.iii.dispatchlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.simple.JSONValue;



public class DispatchListDAO_JNDI implements DispatchListDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
	public void insert(DispatchListVO_orignal DispatchListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,DispatchListVO.getDempNO());
			pstmt.setInt(2,DispatchListVO.getAempNO());
			pstmt.setInt(3,DispatchListVO.getQaNO());
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
	public void update(DispatchListVO_orignal DispatchListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1,DispatchListVO.getDempNO());
			pstmt.setInt(2,DispatchListVO.getAempNO());
			pstmt.setInt(3,DispatchListVO.getQaNO());
			pstmt.setDate(4,DispatchListVO.getDlStime());
			pstmt.setDate(5,DispatchListVO.getDlEtime());
			pstmt.setString(6,DispatchListVO.getElesign());
			pstmt.setString(7,DispatchListVO.getDlNote());
			pstmt.setInt(8,DispatchListVO.getDlNO());
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
			con = ds.getConnection();
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
	public DispatchListVO_orignal findByPrimaryKey(Integer dlno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		DispatchListVO_orignal dlVO = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, dlno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dlVO = new DispatchListVO_orignal();
				dlVO.setDlNO(rs.getInt("dlNO"));
				dlVO.setDempNO(rs.getInt("dempNO"));
				dlVO.setAempNO(rs.getInt("aempNO"));
				dlVO.setQaNO(rs.getInt("qaNO"));
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
	public List<DispatchListVO_orignal> getAll() {
		List<DispatchListVO_orignal> list = new ArrayList<DispatchListVO_orignal>();
		DispatchListVO_orignal dlVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
				
			while (rs.next()) {
				dlVO = new DispatchListVO_orignal();
				dlVO.setDlNO(rs.getInt("dlNO"));
				dlVO.setDempNO(rs.getInt("dempNO"));
				dlVO.setAempNO(rs.getInt("aempNO"));
				dlVO.setQaNO(rs.getInt("qaNO"));
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
			DispatchListVO_orignal dlVO = null;
			String jsonString = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
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
}

