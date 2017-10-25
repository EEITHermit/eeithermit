package com.hermit.iii.house.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HouseDAO_JNDI implements HouseDAO_interface {
	
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
		      "INSERT INTO house (houseTitle,cityNO,boroughNO,highestFloor,nowFloor,houseStatus,houseRent,houseCharge,waterRate,powerRate,houseVideo,typeNO,formNO,houseAddr,houseSize) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT =
			"UPDATE house set houseTitle=?,cityNO=?,boroughNO=?,highestFloor=?,nowFloor=?,houseStatus=?,houseRent=?,houseCharge=?,waterRate=?,powerRate=?,houseVideo=?,typeNO=?,formNO=?,houseAddr=?,houseSize=? where houseNO = ?";
	private static final String DELETE_STMT =
		      "DELETE FROM house where houseNO = ?";
	private static final String GET_ONE_STMT =
			"SELECT houseNO,houseTitle,cityNO,boroughNO,highestFloor,nowFloor,houseStatus,houseRent,houseCharge,waterRate,powerRate,houseVideo,typeNO,formNO,houseAddr,houseSize FROM house where houseNO = ?";
	private static final String GET_ALL_STMT =
			"SELECT houseNO,houseTitle,cityNO,boroughNO,highestFloor,nowFloor,houseStatus,houseRent,houseCharge,waterRate,powerRate,houseVideo,typeNO,formNO,houseAddr,houseSize FROM house order by houseNO";
	private static final String AUTO_COMPLETE =
			"SELECT * FROM house WHERE houseAddr LIKE ?";
	private static final String FIND_BOROUGHNO_BY_HOUSENO =
			"select boroughNO from house where houseNO = ?";
	
	@Override
	public void insert(HouseVO houseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,houseVO.getHouseTitle());
			pstmt.setInt(2,houseVO.getCityNO());
			pstmt.setInt(3,houseVO.getBoroughNO());
			pstmt.setInt(4,houseVO.getHighestFloor());
			pstmt.setInt(5,houseVO.getNowFloor());
			pstmt.setString(6,houseVO.getHouseStatus());
			pstmt.setInt(7,houseVO.getHouseRent());
			pstmt.setInt(8,houseVO.getHouseCharge());
			pstmt.setString(9,houseVO.getWaterRate());
			pstmt.setString(10,houseVO.getPowerRate());
			pstmt.setString(11,houseVO.getHouseVideo());
			pstmt.setInt(12,houseVO.getTypeNO());
			pstmt.setInt(13,houseVO.getFormNO());
			pstmt.setString(14,houseVO.getHouseAddr());
			pstmt.setDouble(15,houseVO.getHouseSize());
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
	public void update(HouseVO houseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1,houseVO.getHouseTitle());
			pstmt.setInt(2,houseVO.getCityNO());
			pstmt.setInt(3,houseVO.getBoroughNO());
			pstmt.setInt(4,houseVO.getHighestFloor());
			pstmt.setInt(5,houseVO.getNowFloor());
			pstmt.setString(6,houseVO.getHouseStatus());
			pstmt.setInt(7,houseVO.getHouseRent());
			pstmt.setInt(8,houseVO.getHouseCharge());
			pstmt.setString(9,houseVO.getWaterRate());
			pstmt.setString(10,houseVO.getPowerRate());
			pstmt.setString(11,houseVO.getHouseVideo());
			pstmt.setInt(12,houseVO.getTypeNO());
			pstmt.setInt(13,houseVO.getFormNO());
			pstmt.setString(14,houseVO.getHouseAddr());
			pstmt.setDouble(15,houseVO.getHouseSize());
			pstmt.setInt(16,houseVO.getHouseNO());
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
	public void delete(Integer houseNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();			
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1,houseNO);
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
	public HouseVO findByPrimaryKey(Integer houseNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		HouseVO vo = new HouseVO();
		ResultSet rs =null; 
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1,houseNO);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo.setHouseTitle(rs.getString("houseTitle"));
				vo.setCityNO(rs.getInt("cityNO"));
				vo.setBoroughNO(rs.getInt("boroughNO"));
				vo.setHighestFloor(rs.getInt("highestFloor"));
				vo.setNowFloor(rs.getInt("nowFloor"));
				vo.setHouseStatus(rs.getString("houseStatus"));
				vo.setHouseRent(rs.getInt("houseRent"));
				vo.setHouseCharge(rs.getInt("houseCharge"));
				vo.setWaterRate(rs.getString("waterRate"));
				vo.setPowerRate(rs.getString("powerRate"));
				vo.setHouseVideo(rs.getString("houseVideo"));
				vo.setTypeNO(rs.getInt("typeNO"));
				vo.setFormNO(rs.getInt("formNO"));
				vo.setHouseAddr(rs.getString("houseAddr"));
				vo.setHouseSize(rs.getDouble("houseSize"));
				vo.setHouseNO(rs.getInt("houseNO"));
			}
			return vo;
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
	public List<HouseVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		HouseVO vo;
		ResultSet rs =null; 
		List<HouseVO> list = new LinkedList<HouseVO>();
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new HouseVO();
				vo.setHouseTitle(rs.getString("houseTitle"));
				vo.setCityNO(rs.getInt("cityNO"));
				vo.setBoroughNO(rs.getInt("boroughNO"));
				vo.setHighestFloor(rs.getInt("highestFloor"));
				vo.setNowFloor(rs.getInt("nowFloor"));
				vo.setHouseStatus(rs.getString("houseStatus"));
				vo.setHouseRent(rs.getInt("houseRent"));
				vo.setHouseCharge(rs.getInt("houseCharge"));
				vo.setWaterRate(rs.getString("waterRate"));
				vo.setPowerRate(rs.getString("powerRate"));
				vo.setHouseVideo(rs.getString("houseVideo"));
				vo.setTypeNO(rs.getInt("typeNO"));
				vo.setFormNO(rs.getInt("formNO"));
				vo.setHouseAddr(rs.getString("houseAddr"));
				vo.setHouseSize(rs.getDouble("houseSize"));
				vo.setHouseNO(rs.getInt("houseNO"));
				list.add(vo);
			}
			return list;
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
	public ArrayList<HouseVO> autoCompleteH(String address) {
		HouseVO houseVO = new HouseVO();
		ArrayList<HouseVO> array = new ArrayList<HouseVO>();
		Connection conn = null;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(AUTO_COMPLETE);
			ps.setString(1, "%"+address+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				houseVO.setHouseNO(rs.getInt("houseNO"));
				houseVO.setHouseAddr(rs.getString("houseAddr"));
				array.add(houseVO);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return array;
	}

	@Override
	public Integer findAreaNoByHouseNo(Integer houseNo) {
		Integer areaNo = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(FIND_BOROUGHNO_BY_HOUSENO);
			ps.setInt(1, houseNo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			areaNo = rs.getInt(1);
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return areaNo;
	}
}
