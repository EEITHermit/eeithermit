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

public class HousePictureDAO_JDBC implements HousePictureDAO_interface{
	
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
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, housePictureVO.gethPicture());
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
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, housePictureVO.gethPicture());
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
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
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
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, housePictureNO);
			rs = pstmt.executeQuery();
			while(rs.next()){
				housePictureVO.setHousePictureNO(rs.getInt("housePictureNO"));
				housePictureVO.sethPicture(rs.getString("hPicture"));
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
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				housePictureVO = new HousePictureVO();
				housePictureVO.setHousePictureNO(rs.getInt("housePictureNO"));
				housePictureVO.sethPicture(rs.getString("hPicture"));
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
	public static void main(String args[]){
		HousePictureVO vo = new HousePictureVO();
		HousePictureDAO_JDBC dao = new HousePictureDAO_JDBC();
		
//		File f = new File("D://temp//1.jpg");
//		FileInputStream fis;
//		try {
//			fis = new FileInputStream(f);
//			byte[] b = new byte[(int) f.length()];
//			fis.read(b,0,b.length);
//			vo.setHouseNO(20001);
//			vo.sethPicture(b);
//			dao.insert(vo);
//			fis.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}


//		File f = new File("D://temp//141611309244151602_210x158.crop.jpg");
//		FileInputStream fis;
//		try {
//			fis = new FileInputStream(f);
//			byte[] b = new byte[(int) f.length()];
//			fis.read(b,0,b.length);
//			vo.setHouseNO(20001);
//			vo.sethPicture(b);
//			vo.setHousePictureNO(1);
//			dao.update(vo);
//			fis.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		dao.delete(1);
		
//		try {
//			FileOutputStream fos = new FileOutputStream("D://temp//out//2.jpg");
//			vo = dao.findByPrimaryKey(1);
//			System.out.println(vo.getHousePictureNO());
//			System.out.println(vo.gethPicture());
//			System.out.println(vo.getHouseNO());
//			fos.write(vo.gethPicture());
//			fos.flush();
//			fos.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		List<HousePictureVO> list = dao.getAll();
		
//		for(int i =0 ; i<list.size();i++){
//			vo = list.get(i);
//			System.out.println(vo.getHousePictureNO());
//			System.out.println(vo.gethPicture());
//			System.out.println(vo.getHouseNO());
//		}
		
		
	}
	
}
