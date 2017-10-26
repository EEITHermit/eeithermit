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

import org.json.simple.JSONValue;

public class BoroughsDAO_JDBC implements BoroughsDAO_interface {

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
			"SELECT boroughNO,boroughName,cityNO FROM boroughs WHERE cityNO = ?  order by boroughNO";
	private static final String GET_ALL_STMT_cityNO =
			"SELECT boroughNO,boroughName,cityNO FROM boroughs where cityNO=? order by boroughNO";
	
	@Override
	public void insert(BoroughsVO BoroughsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,BoroughsVO.getBoroughName());
			pstmt.setInt(2,BoroughsVO.getCityNO());
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
	public void update(BoroughsVO BoroughsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1,BoroughsVO.getBoroughName());
			pstmt.setInt(2,BoroughsVO.getCityNO());
			pstmt.setInt(3,BoroughsVO.getBoroughNO());
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
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
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
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
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
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
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
				m1.put("bcityNO", rs.getInt("cityNO"));
				list.add(m1);
			}
			Map m2 = new LinkedHashMap();
			m2.put("boroughList", list);
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
	public List<BoroughsVO> getAll_cityNO(Integer cityNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		BoroughsVO vo ;
		ResultSet rs;
		List<BoroughsVO> list = new LinkedList<BoroughsVO>();
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ALL_STMT_cityNO);
			pstmt.setInt(1, cityNO);
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
	public static void main(String[] args){
		BoroughsVO vo = new BoroughsVO();
		BoroughsDAO_JDBC dao = new BoroughsDAO_JDBC();

		// insert test
		
//		vo.setBoroughName("小鎮區");
//		vo.setCityNO(1);
//		dao.insert(vo);
		// insert test
		
		// update test
//			vo.setBoroughNO(40);
//			vo.setBoroughName("友克鑫市");
//			vo.setCityNO(3);
//			dao.update(vo);
//			System.out.println("update success");
		// update test
		//delete test
//			dao.delete(41);
//			System.out.println("delete success");
		//delete test
		
		//search one test
//			vo = dao.findByPrimaryKey(40);
//			System.out.println(vo.getBoroughNO());
//			System.out.println(vo.getBoroughName());
//			System.out.println(vo.getCityNO());
		//search one test
		//search all
//		List<BoroughsVO> list = dao.getAll();
//		for(int i =0 ; i< list.size();i++){
//			vo = list.get(i);
//			System.out.println(vo.getBoroughNO());
//			System.out.println(vo.getBoroughName());
//			System.out.println(vo.getCityNO());
//		}
		
		
		System.out.println(dao.getAllWhereCity(1));
		
	}

	

}
