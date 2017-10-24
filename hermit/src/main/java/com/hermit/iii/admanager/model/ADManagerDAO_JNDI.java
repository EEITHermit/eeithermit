package com.hermit.iii.admanager.model;

import java.sql.Connection;
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

import com.hermit.iii.dispatchlist.model.DispatchListVO;

public class ADManagerDAO_JNDI implements ADManagerDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			//進DB
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = 
			"INSERT INTO ADManager (adImage, adLink, adMessage, adTimeStart, adTimeEnd, adStatus, adBrowse, adModify) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE ADManager set adImage=?, adLink=?, adMessage=?, adTimeStart=?, adTimeEnd=?, adStatus=?, adBrowse=?, adModify=? WHERE adNo=?";
	private static final String DELETE = 
			"DELETE FROM ADManager WHERE adNo=?";
	private static final String GET_ONE = 
			"SELECT adNo, adImage, adLink, adMessage, adTimeStart, adTimeEnd, adStatus, adBrowse, adModify FROM ADManager WHERE adNo=?";
	private static final String GET_ALL = 
			"SELECT adNo, adImage, adLink, adMessage, adTimeStart, adTimeEnd, adStatus, adBrowse, adModify FROM ADManager ORDER BY adNo";

	@Override
	public void insert(ADManagerVO ad) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, ad.getAdImage());
			pstmt.setString(2, ad.getAdLink());
			pstmt.setString(3, ad.getAdMessage());
			pstmt.setDate(4, ad.getAdTimeStart());
			pstmt.setDate(5, ad.getAdTimeEnd());
			pstmt.setBoolean(6, ad.getAdStatus());
			pstmt.setInt(7, ad.getAdBrowse());
			pstmt.setInt(8, ad.getAdModify());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ADManagerVO ad) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setString(1, ad.getAdImage());
			pstmt.setString(2, ad.getAdLink());
			pstmt.setString(3, ad.getAdMessage());
			pstmt.setDate(4, ad.getAdTimeStart());
			pstmt.setDate(5, ad.getAdTimeEnd());
			pstmt.setBoolean(6, ad.getAdStatus());
			pstmt.setInt(7, ad.getAdBrowse());
			pstmt.setInt(8, ad.getAdModify());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(int adNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, adNo);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ADManagerVO findByPrimaryKey(int adNo) {

		ADManagerVO adVO = new ADManagerVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE);

			pstmt.setInt(1, adNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				adVO.setAdNo(rs.getInt("adNo"));
				adVO.setAdImage(rs.getString("adImage"));
				adVO.setAdLink(rs.getString("adLink"));
				adVO.setAdMessage(rs.getString("adMessage"));
				adVO.setAdTimeStart(rs.getDate("adTimeStart"));
				adVO.setAdTimeEnd(rs.getDate("adTimeEnd"));
				adVO.setAdStatus(rs.getBoolean("adStatus"));
				adVO.setAdBrowse(rs.getInt("adBrowse"));
				adVO.setAdModify(rs.getInt("adModify"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return adVO;
	}

	@Override
	public List<ADManagerVO> getAll() {
		List<ADManagerVO> list = new ArrayList<ADManagerVO>();
		ADManagerVO adVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adVO = new ADManagerVO();
				adVO.setAdNo(rs.getInt("adNo"));
				adVO.setAdImage(rs.getString("adImage"));
				adVO.setAdLink(rs.getString("adLink"));
				adVO.setAdMessage(rs.getString("adMessage"));
				adVO.setAdTimeStart(rs.getDate("adTimeStart"));
				adVO.setAdTimeEnd(rs.getDate("adTimeEnd"));
				adVO.setAdStatus(rs.getBoolean("adStatus"));
				adVO.setAdBrowse(rs.getInt("adBrowse"));
				adVO.setAdModify(rs.getInt("adModify"));
				list.add(adVO);
			}
						
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
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
		
	try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL);
		rs = pstmt.executeQuery();
	
	while (rs.next()) {					
		Map m1 = new LinkedHashMap();
		m1.put("adNo",rs.getInt("adNo"));
		m1.put("adImage",rs.getString("adImage"));
		m1.put("adLink",rs.getString("adLink"));
		m1.put("adMessage",rs.getString("adMessage"));
		m1.put("adTimeStart",rs.getDate("adTimeStart"));
		m1.put("adTimeEnd",rs.getDate("adTimeEnd"));
		m1.put("adStatus",rs.getString("adStatus"));
		m1.put("adBrowse",rs.getString("adBrowse"));
		m1.put("adModify",rs.getString("adModify"));
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
		return jsonString;
	}
}
}
