package com.hermit.iii.equipmentcondition.model;

import java.sql.*;
import java.util.*;

public class EquipmentConditionJDBCDAO implements EquipmentConditionDAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Hermit";
	String userid = "sa";
	String passwd = "P@ssw0rd";

	// houseNO在資料庫為自動流水號免新增，順序同資料庫表格(與VO/Bean)設定
	// (TV,aircondition,refrigerator,waterHeater,gas,theFourthStation,net,washing,bed,wardrobe,sofa,parking,elevator,balcony,permitCook,pet,closeMRT)
	private static final String INSERT_STMT = "INSERT INTO equipmentCondition VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	// 同上順序，全改通吃法(PK流水號當條件)
	private static final String UPDATE_STMT = "UPDATE equipmentCondition SET TV=?, aircondition=?, refrigerator=?, waterHeater=?, gas=?, theFourthStation=?, net=?, washing=?, bed=?, wardrobe=?, sofa=?, parking=?, elevator=?, balcony=?, permitCook=?, pet=?, closeMRT=? WHERE houseNO = ?";
	private static final String DELETE_STMT = "DELETE FROM equipmentCondition WHERE houseNO = ?";
	// (含PK流水號)全選，避免用＊(PK流水號當條件)
	private static final String GET_ONE_STMT = "SELECT houseNO,TV,aircondition,refrigerator,waterHeater,gas,theFourthStation,net,washing,bed,wardrobe,sofa,parking,elevator,balcony,permitCook,pet,closeMRT FROM equipmentCondition WHERE houseNO = ?";
	// (含PK流水號)全選，避免用＊(免PK流水號當條件全打包，PK流水號當排序使得每次結果顯示方式統一)
	private static final String GET_ALL_STMT = "SELECT houseNO,TV,aircondition,refrigerator,waterHeater,gas,theFourthStation,net,washing,bed,wardrobe,sofa,parking,elevator,balcony,permitCook,pet,closeMRT FROM equipmentCondition ORDER BY houseNO";

	@Override
	public void insert(EquipmentConditionVO equipmentConditionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, equipmentConditionVO.getHouseNO());
			pstmt.setByte(2, equipmentConditionVO.getTV());
			pstmt.setByte(3, equipmentConditionVO.getAircondition());
			pstmt.setByte(4, equipmentConditionVO.getRefrigerator());
			pstmt.setByte(5, equipmentConditionVO.getWaterHeater());
			pstmt.setByte(6, equipmentConditionVO.getGas());
			pstmt.setByte(7, equipmentConditionVO.getTheFourthStation());
			pstmt.setByte(8, equipmentConditionVO.getNet());
			pstmt.setByte(9, equipmentConditionVO.getWashing());
			pstmt.setByte(10, equipmentConditionVO.getBed());
			pstmt.setByte(11, equipmentConditionVO.getWardrobe());
			pstmt.setByte(12, equipmentConditionVO.getSofa());
			pstmt.setByte(13, equipmentConditionVO.getParking());
			pstmt.setByte(14, equipmentConditionVO.getElevator());
			pstmt.setByte(15, equipmentConditionVO.getBalcony());
			pstmt.setByte(16, equipmentConditionVO.getPermitCook());
			pstmt.setByte(17, equipmentConditionVO.getPet());
			pstmt.setByte(18, equipmentConditionVO.getCloseMRT());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
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
	public void update(EquipmentConditionVO equipmentConditionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setByte(1, equipmentConditionVO.getTV());
			pstmt.setByte(2, equipmentConditionVO.getAircondition());
			pstmt.setByte(3, equipmentConditionVO.getRefrigerator());
			pstmt.setByte(4, equipmentConditionVO.getWaterHeater());
			pstmt.setByte(5, equipmentConditionVO.getGas());
			pstmt.setByte(6, equipmentConditionVO.getTheFourthStation());
			pstmt.setByte(7, equipmentConditionVO.getNet());
			pstmt.setByte(8, equipmentConditionVO.getWashing());
			pstmt.setByte(9, equipmentConditionVO.getBed());
			pstmt.setByte(10, equipmentConditionVO.getWardrobe());
			pstmt.setByte(11, equipmentConditionVO.getSofa());
			pstmt.setByte(12, equipmentConditionVO.getParking());
			pstmt.setByte(13, equipmentConditionVO.getElevator());
			pstmt.setByte(14, equipmentConditionVO.getBalcony());
			pstmt.setByte(15, equipmentConditionVO.getPermitCook());
			pstmt.setByte(16, equipmentConditionVO.getPet());
			pstmt.setByte(17, equipmentConditionVO.getCloseMRT());
			pstmt.setInt(18, equipmentConditionVO.getHouseNO());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
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

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, houseNO);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
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
	public EquipmentConditionVO findByPrimaryKey(Integer houseNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EquipmentConditionVO equipmentConditionVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, houseNO);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 確定有資料才開始new EquipmentConditionVO物件
				// equipmentConditionVO = Domain objects
				equipmentConditionVO = new EquipmentConditionVO();
				equipmentConditionVO.setHouseNO(rs.getInt("houseNO"));
				equipmentConditionVO.setTV(rs.getByte("TV"));
				equipmentConditionVO.setAircondition(rs.getByte("aircondition"));
				equipmentConditionVO.setRefrigerator(rs.getByte("refrigerator"));
				equipmentConditionVO.setWaterHeater(rs.getByte("waterHeater"));
				equipmentConditionVO.setGas(rs.getByte("gas"));
				equipmentConditionVO.setTheFourthStation(rs.getByte("theFourthStation"));
				equipmentConditionVO.setNet(rs.getByte("net"));
				equipmentConditionVO.setWashing(rs.getByte("washing"));
				equipmentConditionVO.setBed(rs.getByte("bed"));
				equipmentConditionVO.setWardrobe(rs.getByte("wardrobe"));
				equipmentConditionVO.setSofa(rs.getByte("sofa"));
				equipmentConditionVO.setParking(rs.getByte("parking"));
				equipmentConditionVO.setElevator(rs.getByte("elevator"));
				equipmentConditionVO.setBalcony(rs.getByte("balcony"));
				equipmentConditionVO.setPermitCook(rs.getByte("permitCook"));
				equipmentConditionVO.setPet(rs.getByte("pet"));
				equipmentConditionVO.setCloseMRT(rs.getByte("closeMRT"));
			}

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
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
		return equipmentConditionVO;
	}

	@Override
	public Set<EquipmentConditionVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EquipmentConditionVO equipmentConditionVO = null;
		Set<EquipmentConditionVO> set = new LinkedHashSet<EquipmentConditionVO>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 確定有資料才開始new EquipmentConditionVO物件
				// equipmentConditionVO = Domain objects
				equipmentConditionVO = new EquipmentConditionVO();
				equipmentConditionVO.setHouseNO(rs.getInt("houseNO"));
				equipmentConditionVO.setTV(rs.getByte("TV"));
				equipmentConditionVO.setAircondition(rs.getByte("aircondition"));
				equipmentConditionVO.setRefrigerator(rs.getByte("refrigerator"));
				equipmentConditionVO.setWaterHeater(rs.getByte("waterHeater"));
				equipmentConditionVO.setGas(rs.getByte("gas"));
				equipmentConditionVO.setTheFourthStation(rs.getByte("theFourthStation"));
				equipmentConditionVO.setNet(rs.getByte("net"));
				equipmentConditionVO.setWashing(rs.getByte("washing"));
				equipmentConditionVO.setBed(rs.getByte("bed"));
				equipmentConditionVO.setWardrobe(rs.getByte("wardrobe"));
				equipmentConditionVO.setSofa(rs.getByte("sofa"));
				equipmentConditionVO.setParking(rs.getByte("parking"));
				equipmentConditionVO.setElevator(rs.getByte("elevator"));
				equipmentConditionVO.setBalcony(rs.getByte("balcony"));
				equipmentConditionVO.setPermitCook(rs.getByte("permitCook"));
				equipmentConditionVO.setPet(rs.getByte("pet"));
				equipmentConditionVO.setCloseMRT(rs.getByte("closeMRT"));
				set.add(equipmentConditionVO); // Store the row in the list
			}

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
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
		return set;
	}

	public static void main(String[] args) {
		EquipmentConditionJDBCDAO dao = new EquipmentConditionJDBCDAO();

		// 新增
		EquipmentConditionVO equipmentConditionVO1 = new EquipmentConditionVO();
		equipmentConditionVO1.setHouseNO(20002);
		equipmentConditionVO1.setTV((byte) 1);
		equipmentConditionVO1.setAircondition((byte) 1);
		equipmentConditionVO1.setRefrigerator((byte) 1);
		equipmentConditionVO1.setWaterHeater((byte) 1);
		equipmentConditionVO1.setGas((byte) 1);
		equipmentConditionVO1.setTheFourthStation((byte) 1);
		equipmentConditionVO1.setNet((byte) 1);
		equipmentConditionVO1.setWashing((byte) 1);
		equipmentConditionVO1.setBed((byte) 1);
		equipmentConditionVO1.setWardrobe((byte) 1);
		equipmentConditionVO1.setSofa((byte) 1);
		equipmentConditionVO1.setParking((byte) 1);
		equipmentConditionVO1.setElevator((byte) 1);
		equipmentConditionVO1.setBalcony((byte) 1);
		equipmentConditionVO1.setPermitCook((byte) 1);
		equipmentConditionVO1.setPet((byte) 1);
		equipmentConditionVO1.setCloseMRT((byte) 1);
		dao.insert(equipmentConditionVO1);

		// 修改初始資料第一筆
		EquipmentConditionVO equipmentConditionVO2 = new EquipmentConditionVO();
		equipmentConditionVO2.setHouseNO(20002);
		equipmentConditionVO2.setTV((byte) 1);
		equipmentConditionVO2.setAircondition((byte) 0);
		equipmentConditionVO2.setRefrigerator((byte) 1);
		equipmentConditionVO2.setWaterHeater((byte) 0);
		equipmentConditionVO2.setGas((byte) 1);
		equipmentConditionVO2.setTheFourthStation((byte) 0);
		equipmentConditionVO2.setNet((byte) 1);
		equipmentConditionVO2.setWashing((byte) 0);
		equipmentConditionVO2.setBed((byte) 1);
		equipmentConditionVO2.setWardrobe((byte) 0);
		equipmentConditionVO2.setSofa((byte) 1);
		equipmentConditionVO2.setParking((byte) 0);
		equipmentConditionVO2.setElevator((byte) 1);
		equipmentConditionVO2.setBalcony((byte) 0);
		equipmentConditionVO2.setPermitCook((byte) 1);
		equipmentConditionVO2.setPet((byte) 0);
		equipmentConditionVO2.setCloseMRT((byte) 1);
		dao.update(equipmentConditionVO2);

		// 查詢初始資料第一筆
		EquipmentConditionVO equipmentConditionVO3 = dao.findByPrimaryKey(20001);
		System.out.print(equipmentConditionVO3.getHouseNO() + ",");
		System.out.print(equipmentConditionVO3.getTV() + ",");
		System.out.print(equipmentConditionVO3.getAircondition() + ",");
		System.out.print(equipmentConditionVO3.getRefrigerator() + ",");
		System.out.print(equipmentConditionVO3.getWaterHeater() + ",");
		System.out.print(equipmentConditionVO3.getGas() + ",");
		System.out.print(equipmentConditionVO3.getTheFourthStation() + ",");
		System.out.print(equipmentConditionVO3.getNet() + ",");
		System.out.print(equipmentConditionVO3.getWashing() + ",");
		System.out.print(equipmentConditionVO3.getBed() + ",");
		System.out.print(equipmentConditionVO3.getWardrobe() + ",");
		System.out.print(equipmentConditionVO3.getSofa() + ",");
		System.out.print(equipmentConditionVO3.getParking() + ",");
		System.out.print(equipmentConditionVO3.getElevator() + ",");
		System.out.print(equipmentConditionVO3.getBalcony() + ",");
		System.out.print(equipmentConditionVO3.getPermitCook() + ",");
		System.out.print(equipmentConditionVO3.getPet() + ",");
		System.out.println(equipmentConditionVO3.getCloseMRT());
		System.out.println("---------------------");

		// 查詢全部
		Set<EquipmentConditionVO> set = dao.getAll();
		for (EquipmentConditionVO equipmentConditionVO : set) {
			System.out.print(equipmentConditionVO.getHouseNO() + ",");
			System.out.print(equipmentConditionVO.getTV() + ",");
			System.out.print(equipmentConditionVO.getAircondition() + ",");
			System.out.print(equipmentConditionVO.getRefrigerator() + ",");
			System.out.print(equipmentConditionVO.getWaterHeater() + ",");
			System.out.print(equipmentConditionVO.getGas() + ",");
			System.out.print(equipmentConditionVO.getTheFourthStation() + ",");
			System.out.print(equipmentConditionVO.getNet() + ",");
			System.out.print(equipmentConditionVO.getWashing() + ",");
			System.out.print(equipmentConditionVO.getBed() + ",");
			System.out.print(equipmentConditionVO.getWardrobe() + ",");
			System.out.print(equipmentConditionVO.getSofa() + ",");
			System.out.print(equipmentConditionVO.getParking() + ",");
			System.out.print(equipmentConditionVO.getElevator() + ",");
			System.out.print(equipmentConditionVO.getBalcony() + ",");
			System.out.print(equipmentConditionVO.getPermitCook() + ",");
			System.out.print(equipmentConditionVO.getPet() + ",");
			System.out.println(equipmentConditionVO.getCloseMRT());
			System.out.println();
		}

		// 刪除初始資料一筆
		dao.delete(20002);

		System.out.println("Done");
	}
}