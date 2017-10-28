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
	public void insert(EquipmentConditionVO_orignal EquipmentConditionVO_orignal) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, EquipmentConditionVO_orignal.getHouseNO());
			pstmt.setByte(2, EquipmentConditionVO_orignal.getTV());
			pstmt.setByte(3, EquipmentConditionVO_orignal.getAircondition());
			pstmt.setByte(4, EquipmentConditionVO_orignal.getRefrigerator());
			pstmt.setByte(5, EquipmentConditionVO_orignal.getWaterHeater());
			pstmt.setByte(6, EquipmentConditionVO_orignal.getGas());
			pstmt.setByte(7, EquipmentConditionVO_orignal.getTheFourthStation());
			pstmt.setByte(8, EquipmentConditionVO_orignal.getNet());
			pstmt.setByte(9, EquipmentConditionVO_orignal.getWashing());
			pstmt.setByte(10, EquipmentConditionVO_orignal.getBed());
			pstmt.setByte(11, EquipmentConditionVO_orignal.getWardrobe());
			pstmt.setByte(12, EquipmentConditionVO_orignal.getSofa());
			pstmt.setByte(13, EquipmentConditionVO_orignal.getParking());
			pstmt.setByte(14, EquipmentConditionVO_orignal.getElevator());
			pstmt.setByte(15, EquipmentConditionVO_orignal.getBalcony());
			pstmt.setByte(16, EquipmentConditionVO_orignal.getPermitCook());
			pstmt.setByte(17, EquipmentConditionVO_orignal.getPet());
			pstmt.setByte(18, EquipmentConditionVO_orignal.getCloseMRT());

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
	public void update(EquipmentConditionVO_orignal EquipmentConditionVO_orignal) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setByte(1, EquipmentConditionVO_orignal.getTV());
			pstmt.setByte(2, EquipmentConditionVO_orignal.getAircondition());
			pstmt.setByte(3, EquipmentConditionVO_orignal.getRefrigerator());
			pstmt.setByte(4, EquipmentConditionVO_orignal.getWaterHeater());
			pstmt.setByte(5, EquipmentConditionVO_orignal.getGas());
			pstmt.setByte(6, EquipmentConditionVO_orignal.getTheFourthStation());
			pstmt.setByte(7, EquipmentConditionVO_orignal.getNet());
			pstmt.setByte(8, EquipmentConditionVO_orignal.getWashing());
			pstmt.setByte(9, EquipmentConditionVO_orignal.getBed());
			pstmt.setByte(10, EquipmentConditionVO_orignal.getWardrobe());
			pstmt.setByte(11, EquipmentConditionVO_orignal.getSofa());
			pstmt.setByte(12, EquipmentConditionVO_orignal.getParking());
			pstmt.setByte(13, EquipmentConditionVO_orignal.getElevator());
			pstmt.setByte(14, EquipmentConditionVO_orignal.getBalcony());
			pstmt.setByte(15, EquipmentConditionVO_orignal.getPermitCook());
			pstmt.setByte(16, EquipmentConditionVO_orignal.getPet());
			pstmt.setByte(17, EquipmentConditionVO_orignal.getCloseMRT());
			pstmt.setInt(18, EquipmentConditionVO_orignal.getHouseNO());

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
	public EquipmentConditionVO_orignal findByPrimaryKey(Integer houseNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EquipmentConditionVO_orignal EquipmentConditionVO_orignal = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, houseNO);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 確定有資料才開始new EquipmentConditionVO_orignal物件
				// EquipmentConditionVO_orignal = Domain objects
				EquipmentConditionVO_orignal = new EquipmentConditionVO_orignal();
				EquipmentConditionVO_orignal.setHouseNO(rs.getInt("houseNO"));
				EquipmentConditionVO_orignal.setTV(rs.getByte("TV"));
				EquipmentConditionVO_orignal.setAircondition(rs.getByte("aircondition"));
				EquipmentConditionVO_orignal.setRefrigerator(rs.getByte("refrigerator"));
				EquipmentConditionVO_orignal.setWaterHeater(rs.getByte("waterHeater"));
				EquipmentConditionVO_orignal.setGas(rs.getByte("gas"));
				EquipmentConditionVO_orignal.setTheFourthStation(rs.getByte("theFourthStation"));
				EquipmentConditionVO_orignal.setNet(rs.getByte("net"));
				EquipmentConditionVO_orignal.setWashing(rs.getByte("washing"));
				EquipmentConditionVO_orignal.setBed(rs.getByte("bed"));
				EquipmentConditionVO_orignal.setWardrobe(rs.getByte("wardrobe"));
				EquipmentConditionVO_orignal.setSofa(rs.getByte("sofa"));
				EquipmentConditionVO_orignal.setParking(rs.getByte("parking"));
				EquipmentConditionVO_orignal.setElevator(rs.getByte("elevator"));
				EquipmentConditionVO_orignal.setBalcony(rs.getByte("balcony"));
				EquipmentConditionVO_orignal.setPermitCook(rs.getByte("permitCook"));
				EquipmentConditionVO_orignal.setPet(rs.getByte("pet"));
				EquipmentConditionVO_orignal.setCloseMRT(rs.getByte("closeMRT"));
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
		return EquipmentConditionVO_orignal;
	}

	@Override
	public Set<EquipmentConditionVO_orignal> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EquipmentConditionVO_orignal EquipmentConditionVO_orignal = null;
		Set<EquipmentConditionVO_orignal> set = new LinkedHashSet<EquipmentConditionVO_orignal>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 確定有資料才開始new EquipmentConditionVO_orignal物件
				// EquipmentConditionVO_orignal = Domain objects
				EquipmentConditionVO_orignal = new EquipmentConditionVO_orignal();
				EquipmentConditionVO_orignal.setHouseNO(rs.getInt("houseNO"));
				EquipmentConditionVO_orignal.setTV(rs.getByte("TV"));
				EquipmentConditionVO_orignal.setAircondition(rs.getByte("aircondition"));
				EquipmentConditionVO_orignal.setRefrigerator(rs.getByte("refrigerator"));
				EquipmentConditionVO_orignal.setWaterHeater(rs.getByte("waterHeater"));
				EquipmentConditionVO_orignal.setGas(rs.getByte("gas"));
				EquipmentConditionVO_orignal.setTheFourthStation(rs.getByte("theFourthStation"));
				EquipmentConditionVO_orignal.setNet(rs.getByte("net"));
				EquipmentConditionVO_orignal.setWashing(rs.getByte("washing"));
				EquipmentConditionVO_orignal.setBed(rs.getByte("bed"));
				EquipmentConditionVO_orignal.setWardrobe(rs.getByte("wardrobe"));
				EquipmentConditionVO_orignal.setSofa(rs.getByte("sofa"));
				EquipmentConditionVO_orignal.setParking(rs.getByte("parking"));
				EquipmentConditionVO_orignal.setElevator(rs.getByte("elevator"));
				EquipmentConditionVO_orignal.setBalcony(rs.getByte("balcony"));
				EquipmentConditionVO_orignal.setPermitCook(rs.getByte("permitCook"));
				EquipmentConditionVO_orignal.setPet(rs.getByte("pet"));
				EquipmentConditionVO_orignal.setCloseMRT(rs.getByte("closeMRT"));
				set.add(EquipmentConditionVO_orignal); // Store the row in the list
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
		EquipmentConditionVO_orignal EquipmentConditionVO_orignal1 = new EquipmentConditionVO_orignal();
		EquipmentConditionVO_orignal1.setHouseNO(20002);
		EquipmentConditionVO_orignal1.setTV((byte) 1);
		EquipmentConditionVO_orignal1.setAircondition((byte) 1);
		EquipmentConditionVO_orignal1.setRefrigerator((byte) 1);
		EquipmentConditionVO_orignal1.setWaterHeater((byte) 1);
		EquipmentConditionVO_orignal1.setGas((byte) 1);
		EquipmentConditionVO_orignal1.setTheFourthStation((byte) 1);
		EquipmentConditionVO_orignal1.setNet((byte) 1);
		EquipmentConditionVO_orignal1.setWashing((byte) 1);
		EquipmentConditionVO_orignal1.setBed((byte) 1);
		EquipmentConditionVO_orignal1.setWardrobe((byte) 1);
		EquipmentConditionVO_orignal1.setSofa((byte) 1);
		EquipmentConditionVO_orignal1.setParking((byte) 1);
		EquipmentConditionVO_orignal1.setElevator((byte) 1);
		EquipmentConditionVO_orignal1.setBalcony((byte) 1);
		EquipmentConditionVO_orignal1.setPermitCook((byte) 1);
		EquipmentConditionVO_orignal1.setPet((byte) 1);
		EquipmentConditionVO_orignal1.setCloseMRT((byte) 1);
		dao.insert(EquipmentConditionVO_orignal1);

		// 修改初始資料第一筆
		EquipmentConditionVO_orignal EquipmentConditionVO_orignal2 = new EquipmentConditionVO_orignal();
		EquipmentConditionVO_orignal2.setHouseNO(20002);
		EquipmentConditionVO_orignal2.setTV((byte) 1);
		EquipmentConditionVO_orignal2.setAircondition((byte) 0);
		EquipmentConditionVO_orignal2.setRefrigerator((byte) 1);
		EquipmentConditionVO_orignal2.setWaterHeater((byte) 0);
		EquipmentConditionVO_orignal2.setGas((byte) 1);
		EquipmentConditionVO_orignal2.setTheFourthStation((byte) 0);
		EquipmentConditionVO_orignal2.setNet((byte) 1);
		EquipmentConditionVO_orignal2.setWashing((byte) 0);
		EquipmentConditionVO_orignal2.setBed((byte) 1);
		EquipmentConditionVO_orignal2.setWardrobe((byte) 0);
		EquipmentConditionVO_orignal2.setSofa((byte) 1);
		EquipmentConditionVO_orignal2.setParking((byte) 0);
		EquipmentConditionVO_orignal2.setElevator((byte) 1);
		EquipmentConditionVO_orignal2.setBalcony((byte) 0);
		EquipmentConditionVO_orignal2.setPermitCook((byte) 1);
		EquipmentConditionVO_orignal2.setPet((byte) 0);
		EquipmentConditionVO_orignal2.setCloseMRT((byte) 1);
		dao.update(EquipmentConditionVO_orignal2);

		// 查詢初始資料第一筆
		EquipmentConditionVO_orignal EquipmentConditionVO_orignal3 = dao.findByPrimaryKey(20001);
		System.out.print(EquipmentConditionVO_orignal3.getHouseNO() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getTV() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getAircondition() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getRefrigerator() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getWaterHeater() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getGas() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getTheFourthStation() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getNet() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getWashing() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getBed() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getWardrobe() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getSofa() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getParking() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getElevator() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getBalcony() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getPermitCook() + ",");
		System.out.print(EquipmentConditionVO_orignal3.getPet() + ",");
		System.out.println(EquipmentConditionVO_orignal3.getCloseMRT());
		System.out.println("---------------------");

		// 查詢全部
		Set<EquipmentConditionVO_orignal> set = dao.getAll();
		for (EquipmentConditionVO_orignal EquipmentConditionVO_orignal : set) {
			System.out.print(EquipmentConditionVO_orignal.getHouseNO() + ",");
			System.out.print(EquipmentConditionVO_orignal.getTV() + ",");
			System.out.print(EquipmentConditionVO_orignal.getAircondition() + ",");
			System.out.print(EquipmentConditionVO_orignal.getRefrigerator() + ",");
			System.out.print(EquipmentConditionVO_orignal.getWaterHeater() + ",");
			System.out.print(EquipmentConditionVO_orignal.getGas() + ",");
			System.out.print(EquipmentConditionVO_orignal.getTheFourthStation() + ",");
			System.out.print(EquipmentConditionVO_orignal.getNet() + ",");
			System.out.print(EquipmentConditionVO_orignal.getWashing() + ",");
			System.out.print(EquipmentConditionVO_orignal.getBed() + ",");
			System.out.print(EquipmentConditionVO_orignal.getWardrobe() + ",");
			System.out.print(EquipmentConditionVO_orignal.getSofa() + ",");
			System.out.print(EquipmentConditionVO_orignal.getParking() + ",");
			System.out.print(EquipmentConditionVO_orignal.getElevator() + ",");
			System.out.print(EquipmentConditionVO_orignal.getBalcony() + ",");
			System.out.print(EquipmentConditionVO_orignal.getPermitCook() + ",");
			System.out.print(EquipmentConditionVO_orignal.getPet() + ",");
			System.out.println(EquipmentConditionVO_orignal.getCloseMRT());
			System.out.println();
		}

		// 刪除初始資料一筆
		dao.delete(20002);

		System.out.println("Done");
	}
}