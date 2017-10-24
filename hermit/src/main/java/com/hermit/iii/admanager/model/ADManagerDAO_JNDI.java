package com.hermit.iii.admanager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ADManagerDAO_JNDI implements ADManagerDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO ADManager (adImage, adLink, adMessage, adTimeStart, adTimeEnd, adStatus, adBrowse, adModify) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE ADManager set adImage=?, adLink=?, adMessage=?, adTimeStart=?, adTimeEnd=?, adStatus=?, adBrowse=?, adModify=? WHERE adNo=?";
	private static final String DELETE = "DELETE FROM ADManager WHERE adNo=?";
	private static final String GET_ONE = "SELECT adNo, adImage, adLink, adMessage, adTimeStart, adTimeEnd, adStatus, adBrowse, adModify FROM ADManager WHERE adNo=?";
	private static final String GET_ALL = "SELECT adNo, adImage, adLink, adMessage, adTimeStart, adTimeEnd, adStatus, adBrowse, adModify FROM ADManager ORDER BY adNo";

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

		ADManagerVO adVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE);

			pstmt.setInt(1, adNo);

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
}