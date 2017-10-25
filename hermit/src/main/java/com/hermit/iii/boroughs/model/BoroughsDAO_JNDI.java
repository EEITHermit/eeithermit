package com.hermit.iii.boroughs.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.simple.JSONValue;

public class BoroughsDAO_JNDI implements BoroughsDAO_interface {


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
		      "INSERT INTO boroughs (boroughName,cityNO) VALUES (?, ?)";
	private static final String UPDATE_STMT =
			"UPDATE boroughs set boroughName=?, cityNO=? where boroughNO = ?";
	private static final String DELETE_STMT =
		      "DELETE FROM boroughs where boroughNO = ?";
	private static final String GET_ONE_STMT =
			"SELECT boroughNO,boroughName,cityNO FROM boroughs where boroughNO = ?";
	private static final String GET_ALL_STMT =
			"SELECT boroughNO,boroughName,cityNO FROM boroughs order by boroughNO";
	private static final String FIND_BORO_WHERE_CITY = 
			"SELECT boroughNO,boroughName,cityNO FROM boroughs order by boroughNO WHERE cityNO = ?";

	
	@Override
	public void insert(BoroughsVO boroughsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,boroughsVO.getBoroughName());
			pstmt.setInt(2,boroughsVO.getCityNO());
			pstmt.execute();
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
	public void update(BoroughsVO boroughsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1,boroughsVO.getBoroughName());
			pstmt.setInt(2,boroughsVO.getCityNO());
			pstmt.setInt(2,boroughsVO.getBoroughNO());
			pstmt.execute();
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
	public void delete(Integer boroughNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1,boroughNO);
			pstmt.execute();
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
	public BoroughsVO findByPrimaryKey(Integer boroughNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		BoroughsVO vo = new BoroughsVO();
		ResultSet rs; 
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1,boroughNO);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo.setBoroughNO(rs.getInt("boroughNO"));
				vo.setBoroughName(rs.getString("boroughName"));
				vo.setCityNO(rs.getInt("cityNO"));
			}
			return vo;
			
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
	public List<BoroughsVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		BoroughsVO vo ;
		ResultSet rs;
		List<BoroughsVO> list = new LinkedList<BoroughsVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new BoroughsVO();
				vo.setBoroughNO(rs.getInt("boroughNO"));
				vo.setBoroughName(rs.getString("boroughName"));
				vo.setCityNO(rs.getInt("cityNO"));
				list.add(vo);
			}
			return list;
			
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
	public String getAllWhereCity(Integer cityNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		BoroughsVO vo ;
		ResultSet rs;
		List list = new LinkedList();
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(FIND_BORO_WHERE_CITY);
			pstmt.setInt(1, cityNO);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Map m1 = new LinkedHashMap();
				m1.put("boroughNO", rs.getInt("boroughNO"));
				m1.put("boroughName", rs.getInt("boroughNO"));
				m1.put("bcityNO", rs.getInt("bcityNO"));
				list.add(m1);
			}
			Map m2 = new LinkedHashMap();
			m2.put("list", list);
		String boroughJSON = JSONValue.toJSONString(m2);	
			return boroughJSON;
			
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

}
