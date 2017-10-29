package com.hermit.iii.houseform.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HouseFormDAO_JNDI implements HouseFormDAO_interface {
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
		      "INSERT INTO houseForm (hForm) VALUES (?)";
	private static final String UPDATE_STMT =
			"UPDATE houseForm set hForm=? where formNO = ?";
	private static final String DELETE_STMT =
		      "DELETE FROM houseForm where formNO = ?";
	private static final String GET_ONE_STMT =
			"SELECT formNO,hForm FROM houseForm where formNO = ?";
	private static final String GET_ALL_STMT =
			"SELECT formNO,hForm FROM houseForm order by formNO";
	
	
	@Override
	public void insert(HouseFormVO_original houseFormVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,houseFormVO.gethForm());
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
	public void update(HouseFormVO_original houseFormVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1,houseFormVO.gethForm());
			pstmt.setInt(2,houseFormVO.getFormNO());
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
	public void delete(Integer formNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1,formNO);;
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
	public HouseFormVO_original findByPrimaryKey(Integer formNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		HouseFormVO_original houseFormVO = new HouseFormVO_original();
		ResultSet rs ;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1,formNO);;
			rs = pstmt.executeQuery();
			while(rs.next()){
				houseFormVO.setFormNO(rs.getInt("formNO"));
				houseFormVO.sethForm(rs.getString("hForm"));
			}
			return houseFormVO;
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
	public List<HouseFormVO_original> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		HouseFormVO_original houseFormVO ;
		List<HouseFormVO_original> list = new LinkedList<HouseFormVO_original>();
		ResultSet rs ;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				houseFormVO = new HouseFormVO_original();
				houseFormVO.setFormNO(rs.getInt("formNO"));
				houseFormVO.sethForm(rs.getString("hForm"));
				list.add(houseFormVO);
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
}
