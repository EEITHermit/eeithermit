package com.hermit.iii.house.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;

public class HouseDAO_JDBC implements HouseDAO_interface{
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
	//子傑加
	private static final String GET_ALL_JOIN_FK = " SELECT A.houseNO,A.houseTitle,A.cityNO, D.cityName ,A.boroughNO ,E.boroughName,A.highestFloor,A.nowFloor,A.houseStatus,A.houseRent,A.houseCharge,A.waterRate,A.powerRate,A.houseVideo,A.TypeNO,B.hType,A.formNO,C.hForm,A.houseAddr,A.houseSize from House as A JOIN  HouseType as B on A.typeNO =B.typeNO JOIN HouseForm as C on A.formNO=C.formNO JOIN city as D on A.cityNO=D.cityNO JOIN Boroughs as E on A.boroughNO=E.boroughNO";
	private static final String GET_ONE_HOUSE_FK = "SELECT A.houseNO,A.houseTitle,A.cityNO,D.cityName ,A.boroughNO,E.boroughName,A.highestFloor,A.nowFloor,A.houseStatus,A.houseRent,A.houseCharge,A.waterRate,A.powerRate,A.houseVideo,A.TypeNO,B.hType,A.formNO,C.hForm,A.houseAddr,A.houseSize from House as A JOIN  HouseType as B on A.typeNO =B.typeNO JOIN HouseForm as C on A.formNO=C.formNO  JOIN city as D on A.cityNO=D.cityNO JOIN Boroughs as E on A.boroughNO=E.boroughNO where A.houseNO=?";
	
	@Override
	public void insert(HouseVO_orignal HouseVO_orignal) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,HouseVO_orignal.getHouseTitle());
			pstmt.setInt(2,HouseVO_orignal.getCityNO());
			pstmt.setInt(3,HouseVO_orignal.getBoroughNO());
			pstmt.setInt(4,HouseVO_orignal.getHighestFloor());
			pstmt.setInt(5,HouseVO_orignal.getNowFloor());
			pstmt.setString(6,HouseVO_orignal.getHouseStatus());
			pstmt.setInt(7,HouseVO_orignal.getHouseRent());
			pstmt.setInt(8,HouseVO_orignal.getHouseCharge());
			pstmt.setString(9,HouseVO_orignal.getWaterRate());
			pstmt.setString(10,HouseVO_orignal.getPowerRate());
			pstmt.setString(11,HouseVO_orignal.getHouseVideo());
			pstmt.setInt(12,HouseVO_orignal.getTypeNO());
			pstmt.setInt(13,HouseVO_orignal.getFormNO());
			pstmt.setString(14,HouseVO_orignal.getHouseAddr());
			pstmt.setDouble(15,HouseVO_orignal.getHouseSize());
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
	public void update(HouseVO_orignal HouseVO_orignal) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1,HouseVO_orignal.getHouseTitle());
			pstmt.setInt(2,HouseVO_orignal.getCityNO());
			pstmt.setInt(3,HouseVO_orignal.getBoroughNO());
			pstmt.setInt(4,HouseVO_orignal.getHighestFloor());
			pstmt.setInt(5,HouseVO_orignal.getNowFloor());
			pstmt.setString(6,HouseVO_orignal.getHouseStatus());
			pstmt.setInt(7,HouseVO_orignal.getHouseRent());
			pstmt.setInt(8,HouseVO_orignal.getHouseCharge());
			pstmt.setString(9,HouseVO_orignal.getWaterRate());
			pstmt.setString(10,HouseVO_orignal.getPowerRate());
			pstmt.setString(11,HouseVO_orignal.getHouseVideo());
			pstmt.setInt(12,HouseVO_orignal.getTypeNO());
			pstmt.setInt(13,HouseVO_orignal.getFormNO());
			pstmt.setString(14,HouseVO_orignal.getHouseAddr());
			pstmt.setDouble(15,HouseVO_orignal.getHouseSize());
			pstmt.setInt(16,HouseVO_orignal.getHouseNO());
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
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
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
	public HouseVO_orignal findByPrimaryKey(Integer houseNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		HouseVO_orignal vo = new HouseVO_orignal();
		ResultSet rs =null; 
		try{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
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
	public List<HouseVO_orignal> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		HouseVO_orignal vo;
		ResultSet rs =null; 
		List<HouseVO_orignal> list = new LinkedList<HouseVO_orignal>();
		try{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new HouseVO_orignal();
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
	
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=hermit";
	@Override
	public ArrayList<HouseVO_orignal> autoCompleteH(String address) {
		HouseVO_orignal HouseVO_orignal = new HouseVO_orignal();
		ArrayList<HouseVO_orignal> array = new ArrayList<HouseVO_orignal>();
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(AUTO_COMPLETE);
			ps.setString(1, "%"+address+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				HouseVO_orignal.setHouseNO(rs.getInt("houseNO"));
				HouseVO_orignal.setHouseAddr(rs.getString("houseAddr"));
				array.add(HouseVO_orignal);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(FIND_BOROUGHNO_BY_HOUSENO);
			ps.setInt(1, houseNo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			areaNo = rs.getInt(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return areaNo;
	}
	

	@Override
	public List<HouseVO_orignal> GET_ALL_JOIN_FK() {
		Connection con = null;
		PreparedStatement pstmt = null;
		HouseVO_orignal vo;
		ResultSet rs =null; 
		List<HouseVO_orignal> list = new LinkedList<HouseVO_orignal>();
		try{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ALL_JOIN_FK);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new HouseVO_orignal();
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
				vo.sethType(rs.getString("hType"));
				vo.setFormNO(rs.getInt("formNO"));
				vo.sethForm(rs.getString("hForm"));
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
	public HouseVO_orignal GET_ONE_HOUSE_FK(Integer houseNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		HouseVO_orignal vo = new HouseVO_orignal();
		ResultSet rs =null; 
		try{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ONE_HOUSE_FK);
			pstmt.setInt(1,houseNO);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo.setHouseTitle(rs.getString("houseTitle"));
				vo.setCityNO(rs.getInt("cityNO"));
				vo.setCityName(rs.getString("cityName"));
				vo.setBoroughNO(rs.getInt("boroughNO"));
				vo.setBoroughName(rs.getString("boroughName"));
				vo.setHighestFloor(rs.getInt("highestFloor"));
				vo.setNowFloor(rs.getInt("nowFloor"));
				vo.setHouseStatus(rs.getString("houseStatus"));
				vo.setHouseRent(rs.getInt("houseRent"));
				vo.setHouseCharge(rs.getInt("houseCharge"));
				vo.setWaterRate(rs.getString("waterRate"));
				vo.setPowerRate(rs.getString("powerRate"));
				vo.setHouseVideo(rs.getString("houseVideo"));
				vo.setTypeNO(rs.getInt("typeNO"));
				vo.sethType(rs.getString("hType"));
				vo.setFormNO(rs.getInt("formNO"));
				vo.sethForm(rs.getString("hForm"));
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
	public String advencedSearch(String searchStr) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(searchStr);
			rs = pstmt.executeQuery();
			List<Map> searchList = new LinkedList<Map>();
			while (rs.next()) {
				Map m1 = new LinkedHashMap();
				m1.put("houseNO", rs.getString("houseNO"));
				m1.put("houseTitle", rs.getString("houseTitle"));
				m1.put("cityName", rs.getString("cityName"));
				m1.put("boroughtName", rs.getString("boroughtName"));
				m1.put("highestFloor", rs.getString("highestFloor"));
				m1.put("nowFloor", rs.getString("nowFloor"));
				m1.put("houseRent", rs.getString("houseRent"));
				m1.put("hType", rs.getString("hType"));
				m1.put("hForm", rs.getString("hForm"));
				m1.put("houseAddr", rs.getString("houseAddr"));
				m1.put("houseSize", rs.getString("houseSize"));
				searchList.add(m1);
			}
			Map m2 = new LinkedHashMap();
			m2.put("searchList", searchList);
			String jsonStr = JSONValue.toJSONString(m2);
			return jsonStr;
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
		HouseVO_orignal vo = new HouseVO_orignal();
		HouseDAO_JDBC dao = new HouseDAO_JDBC();
		List<HouseVO_orignal> list;
		
//Insert Test Start		
//		vo.setHouseTitle("東區忠孝復興站,極簡設計師裝潢");
//		vo.setCityNO(1);
//		vo.setBoroughNO(1);
//		vo.setHighestFloor(13);
//		vo.setNowFloor(5);
//		vo.setHouseStatus("未出租");
//		vo.setHouseRent(20000);
//		vo.setHouseCharge(40000);     //押金
//		vo.setWaterRate("依帳單繳費");
//		vo.setPowerRate("依帳單繳費");
//		vo.setHouseVideo("http://www.youtube.com");
//		vo.setTypeNO(2010);
//		vo.setFormNO(2010);
//		vo.setHouseAddr("新北市板橋區大馬路3號");
//		vo.setHouseSize(10.32);
//		dao.insert(vo);
//		System.out.println("Insert Success");
//Insert Test End	
		
		
//Update Test Start
//		vo.setHouseTitle("不甜蜜小套房");
//		vo.setCityNO(2);
//		vo.setBoroughNO(2);
//		vo.setHighestFloor(18);
//		vo.setNowFloor(18);
//		vo.setHouseStatus("出租中");
//		vo.setHouseRent(18000);
//		vo.setHouseCharge(36000);
//		vo.setWaterRate("依帳單繳費");
//		vo.setPowerRate("依帳單繳費");
//		vo.setHouseVideo("http://www.youtube.com/notsweethouse");
//		vo.setTypeNO(2020);
//		vo.setFormNO(2020);
//		vo.setHouseAddr("新北市板橋區大馬路2號");
//		vo.setHouseSize(18.87);
//		vo.setHouseNO(20002);
//		dao.update(vo);
//		System.out.println("Update Success");
//Update Test End	
		
		
//Delete Test Start
//		dao.delete(20003);
//		System.out.println("Delete Success");
//Delete Test End
		
//Get One Test Start
//		vo = dao.findByPrimaryKey(20001);	
//		System.out.println("getHouseTitle = \t" + vo.getHouseTitle());
//		System.out.println("getCityNO = \t\t" + vo.getCityNO());
//		System.out.println("getBoroughNO = \t\t" + vo.getBoroughNO());
//		System.out.println("getHighestFloor = \t" + vo.getHighestFloor());
//		System.out.println("getNowFloor = \t\t" + vo.getNowFloor());
//		System.out.println("getHouseStatus = \t" + vo.getHouseStatus());
//		System.out.println("getHouseRent = \t\t" + vo.getHouseRent());
//		System.out.println("getHouseCharge = \t" + vo.getHouseCharge());
//		System.out.println("getWaterRate = \t\t" + vo.getWaterRate());
//		System.out.println("getPowerRate = \t\t" + vo.getPowerRate());
//		System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//		System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//		System.out.println("getTypeNO = \t\t" + vo.getTypeNO());
//		System.out.println("getFormNO = \t\t" + vo.getFormNO());
//		System.out.println("getHouseAddr = \t\t" + vo.getHouseAddr());
//		System.out.println("getHouseSize = \t\t" + vo.getHouseSize());
//		System.out.println("getHouseNO = \t\t" + vo.getHouseNO());
//		System.out.println("Search Success");
//Get One Test End	
		
//Get All Test Start
//		list = dao.getAll();
//		for(int i=0;i<list.size();i++){
//			vo = list.get(i);
//			System.out.println("getHouseNO = \t\t" + vo.getHouseNO());
//			System.out.println("getHouseTitle = \t" + vo.getHouseTitle());
//			System.out.println("getCityNO = \t\t" + vo.getCityNO());
//			System.out.println("getBoroughNO = \t\t" + vo.getBoroughNO());
//			System.out.println("getHighestFloor = \t" + vo.getHighestFloor());
//			System.out.println("getNowFloor = \t\t" + vo.getNowFloor());
//			System.out.println("getHouseStatus = \t" + vo.getHouseStatus());
//			System.out.println("getHouseRent = \t\t" + vo.getHouseRent());
//			System.out.println("getHouseCharge = \t" + vo.getHouseCharge());
//			System.out.println("getWaterRate = \t\t" + vo.getWaterRate());
//			System.out.println("getPowerRate = \t\t" + vo.getPowerRate());
//			System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//			System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//			System.out.println("getTypeNO = \t\t" + vo.getTypeNO());
//			System.out.println("getFormNO = \t\t" + vo.getFormNO());
//			System.out.println("getHouseAddr = \t\t" + vo.getHouseAddr());
//			System.out.println("getHouseSize = \t\t" + vo.getHouseSize());
//			System.out.println();
//			System.out.println("------------------------------next---------------------------------------------");
//			System.out.println();
//		}
//		System.out.println("Search All Success");
//Get All Test End		
		
		//GET ALL_FK TEST BEGIN
//		list = dao.GET_ALL_JOIN_FK();
//		for(int i=0;i<list.size();i++){
//			vo = list.get(i);
//			System.out.println("getHouseNO = \t\t" + vo.getHouseNO());
//			System.out.println("getHouseTitle = \t" + vo.getHouseTitle());
//			System.out.println("getCityNO = \t\t" + vo.getCityNO());
//			System.out.println("getBoroughNO = \t\t" + vo.getBoroughNO());
//			System.out.println("getHighestFloor = \t" + vo.getHighestFloor());
//			System.out.println("getNowFloor = \t\t" + vo.getNowFloor());
//			System.out.println("getHouseStatus = \t" + vo.getHouseStatus());
//			System.out.println("getHouseRent = \t\t" + vo.getHouseRent());
//			System.out.println("getHouseCharge = \t" + vo.getHouseCharge());
//			System.out.println("getWaterRate = \t\t" + vo.getWaterRate());
//			System.out.println("getPowerRate = \t\t" + vo.getPowerRate());
//			System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//			System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//			System.out.println("getTypeNO = \t\t" + vo.getTypeNO());
//			System.out.println("gethType = \t\t" + vo.gethType());
//			System.out.println("getFormNO = \t\t" + vo.getFormNO());
//			System.out.println("gethForm = \t\t" + vo.gethForm());
//			System.out.println("getHouseAddr = \t\t" + vo.getHouseAddr());
//			System.out.println("getHouseSize = \t\t" + vo.getHouseSize());
//			System.out.println();
//			System.out.println("------------------------------next---------------------------------------------");
//			System.out.println();
//		}
//		System.out.println("Search All Success");
		//GET ALL_FK TEST END
	
		//GET ONE_FK TEST BEGIN
//		vo = dao.GET_ONE_HOUSE_FK(20001);	
//		System.out.println("getHouseTitle = \t" + vo.getHouseTitle());
//		System.out.println("getCityNO = \t\t" + vo.getCityNO());
//		System.out.println("getBoroughNO = \t\t" + vo.getBoroughNO());
//		System.out.println("getHighestFloor = \t" + vo.getHighestFloor());
//		System.out.println("getNowFloor = \t\t" + vo.getNowFloor());
//		System.out.println("getHouseStatus = \t" + vo.getHouseStatus());
//		System.out.println("getHouseRent = \t\t" + vo.getHouseRent());
//		System.out.println("getHouseCharge = \t" + vo.getHouseCharge());
//		System.out.println("getWaterRate = \t\t" + vo.getWaterRate());
//		System.out.println("getPowerRate = \t\t" + vo.getPowerRate());
//		System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//		System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//		System.out.println("getTypeNO = \t\t" + vo.getTypeNO());
//		System.out.println("gethType = \t\t" + vo.gethType());
//		System.out.println("getFormNO = \t\t" + vo.getFormNO());
//		System.out.println("gethForm = \t\t" +vo.gethForm());
//		System.out.println("getHouseAddr = \t\t" + vo.getHouseAddr());
//		System.out.println("getHouseSize = \t\t" + vo.getHouseSize());
//		System.out.println("getHouseNO = \t\t" + vo.getHouseNO());
//		System.out.println("Search Success");
		//GET ONE_FK TEST END
	
	
	}
	

}
