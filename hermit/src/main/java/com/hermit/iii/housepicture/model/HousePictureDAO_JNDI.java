package com.hermit.iii.housepicture.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class HousePictureDAO_JNDI implements HousePictureDAO_interface{
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
			"INSERT INTO HousePicture (hPicture,houseNO) VALUES (?,?)";
	private static final String UPDATE_STMT =
			"UPDATE HousePicture set hPicture=?,houseNO=? where housePictureNO = ?";
	private static final String DELETE_STMT =
			"DELETE FROM HousePicture where housePictureNO = ?";
	private static final String GET_ONE_STMT =
			"SELECT housePictureNO,hPicture,houseNO FROM HousePicture where housePictureNO = ?";
	private static final String GET_ALL_STMT =
			"SELECT housePictureNO,hPicture,houseNO FROM HousePicture order by housePictureNO";
	
	
	@Override
	public void insert(HousePictureVO housePictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setBytes(1, housePictureVO.gethPicture());
			pstmt.setInt(2, housePictureVO.getHouseNO());
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
	public void update(HousePictureVO housePictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setBytes(1, housePictureVO.gethPicture());
			pstmt.setInt(2, housePictureVO.getHouseNO());
			pstmt.setInt(3, housePictureVO.getHousePictureNO());
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
	public void delete(Integer housePictureNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, housePictureNO);
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
	public HousePictureVO findByPrimaryKey(Integer housePictureNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		HousePictureVO housePictureVO = new HousePictureVO();
		ResultSet rs ;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, housePictureNO);
			rs = pstmt.executeQuery();
			while(rs.next()){
				housePictureVO.setHousePictureNO(rs.getInt("housePictureNO"));
				housePictureVO.sethPicture(rs.getBytes("hPicture"));
				housePictureVO.setHouseNO(rs.getInt("HouseNO"));
			}
		return housePictureVO;	
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
	public List<HousePictureVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		HousePictureVO housePictureVO;
		ResultSet rs ;
		List<HousePictureVO> list = new LinkedList<HousePictureVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				housePictureVO = new HousePictureVO();
				housePictureVO.setHousePictureNO(rs.getInt("housePictureNO"));
				housePictureVO.sethPicture(rs.getBytes("hPicture"));
				housePictureVO.setHouseNO(rs.getInt("HouseNO"));
				list.add(housePictureVO);
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
