package com.hermit.iii.city.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;

import com.hermit.iii.boroughs.model.BoroughsVO;

public class CityDAO_JDBC implements CityDAO_interface {

	private static final String INSERT_STMT =
		      "INSERT INTO City (cityName) VALUES (?)";
	private static final String UPDATE_STMT =
			"UPDATE City set cityName=? where cityNO = ?";
	private static final String DELETE_STMT =
		      "DELETE FROM City where cityNO = ?";
	private static final String GET_ONE_STMT =
			"SELECT cityNO,cityName FROM City where cityNO = ?";
	private static final String GET_ALL_STMT =
			"SELECT cityNO,cityName FROM City order by cityNO";
	

	
	
	
	

	@Override
	public void insert(CityVO cityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,cityVO.getCityName());
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
	public void update(CityVO cityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1,cityVO.getCityName());
			pstmt.setInt(2,cityVO.getCityNO());
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
	public void delete(Integer cityNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, cityNO);
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
	public CityVO findByPrimaryKey(Integer cityNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		CityVO cityVO = new CityVO();
		ResultSet rs ;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, cityNO);
			rs = pstmt.executeQuery();
			while(rs.next()){
				cityVO.setCityNO(rs.getInt("cityNO"));
				cityVO.setCityName(rs.getString("cityName"));
			}
			return cityVO;
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
	public List<CityVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		CityVO cityVO ;
		ResultSet rs ;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			List<CityVO> list = new LinkedList<CityVO>();
			while(rs.next()){
				cityVO = new CityVO();
				cityVO.setCityNO(rs.getInt("cityNO"));
				cityVO.setCityName(rs.getString("cityName"));
				list.add(cityVO);
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
	public String getAllForJson() {
		Connection con = null;
		PreparedStatement pstmt = null;
		BoroughsVO vo ;
		ResultSet rs;
		List list = new LinkedList();
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Map m1 = new LinkedHashMap();
				m1.put("cityNO", rs.getInt("cityNO"));
				m1.put("cityName", rs.getString("cityName"));
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
	
	
	public static void main (String[] args){
		CityVO vo = new CityVO();
		CityDAO_JDBC dao = new CityDAO_JDBC();
		
//		vo.setCityName("瑪莎多拉");
//		dao.insert(vo);
//		System.out.println("insert success");
//		
//		vo.setCityName("奧格瑪");
//		vo.setCityNO(23);
//		dao.update(vo);
//		System.out.println("update success");
		
//		dao.delete(23);
//		System.out.println("delete success");
		
//		vo = dao.findByPrimaryKey(1);
//		System.out.println("CityNO = " + vo.getCityNO());
//		System.out.println("CityName = " + vo.getCityName());
		
//		List<CityVO> list = dao.getAll();
//		for(int i = 0 ; i < list.size();i++){
//			vo = list.get(i);
//			System.out.println("CityNO = " + vo.getCityNO());
//			System.out.println("CityName = " + vo.getCityName());
//		}
//		
		System.out.println(dao.getAllForJson());
		
		
	}

}
