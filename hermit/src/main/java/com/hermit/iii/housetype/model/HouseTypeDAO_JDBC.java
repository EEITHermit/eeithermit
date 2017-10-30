package com.hermit.iii.housetype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class HouseTypeDAO_JDBC implements HouseTypeDAO_interface {

	private static final String INSERT_STMT =
		      "INSERT INTO houseType (hType) VALUES (?)";
	private static final String UPDATE_STMT =
			"UPDATE houseType set hType=? where typeNO = ?";
	private static final String DELETE_STMT =
		      "DELETE FROM houseType where typeNO = ?";
	private static final String GET_ONE_STMT =
			"SELECT typeNO,hType FROM houseType where typeNO = ?";
	private static final String GET_ALL_STMT =
			"SELECT typeNO,hType FROM houseType order by typeNO";
	
	@Override
	public void insert(HouseTypeVO_original houseTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,houseTypeVO.gethType());
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
	public void update(HouseTypeVO_original houseTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1,houseTypeVO.gethType());
			pstmt.setInt(2,houseTypeVO.getTypeNO());
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
	public void delete(Integer typeNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setInt(1,typeNO);
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
	public HouseTypeVO_original findByPrimaryKey(Integer typeNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		HouseTypeVO_original vo = new HouseTypeVO_original();
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1,typeNO);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo.setTypeNO(rs.getInt("typeNO"));
				vo.sethType(rs.getString("hType"));
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
	public List<HouseTypeVO_original> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<HouseTypeVO_original> list = new LinkedList<HouseTypeVO_original>();
		ResultSet rs;
		HouseTypeVO_original vo;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new HouseTypeVO_original();
				vo.setTypeNO(rs.getInt("typeNO"));
				vo.sethType(rs.getString("hType"));
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
	
	public static void main(String args[]){
		HouseTypeVO_original vo = new HouseTypeVO_original();
		HouseTypeDAO_JDBC dao = new HouseTypeDAO_JDBC();
		
		List<HouseTypeVO_original> list;
		
		
		//test insert 
//		vo.sethType("柴房");
//		dao.insert(vo);
		//test update
		
//		vo.setTypeNO(2060);
//		vo.sethType("茅坑");
//		dao.update(vo);
		
		
//		dao.delete(2060);
		
//		vo = dao.findByPrimaryKey(2010);
//		System.out.println(vo.gethType());
//		System.out.println(vo.getTypeNO());
		
		list = dao.getAll();
		for(int i = 0;i<list.size();i++){
			vo = list.get(i);
			System.out.println(vo.gethType());
			System.out.println(vo.getTypeNO());
		}
		
	}

}
