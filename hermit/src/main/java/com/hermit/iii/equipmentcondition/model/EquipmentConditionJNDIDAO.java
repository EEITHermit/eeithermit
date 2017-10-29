package com.hermit.iii.equipmentcondition.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

public class EquipmentConditionJNDIDAO implements EquipmentConditionDAO_interface {
	DataSource ds = null;

	public EquipmentConditionJNDIDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/JNDI");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 順序同資料庫表格(與VO/Bean)設定
	// (houseNO,TV,aircondition,refrigerator,waterHeater,gas,theFourthStation,net,washing,bed,wardrobe,sofa,parking,elevator,balcony,permitCook,pet,closeMRT)
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

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);) {

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

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(EquipmentConditionVO_orignal EquipmentConditionVO_orignal) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_STMT);) {

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

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer houseNO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_STMT);) {

			pstmt.setInt(1, houseNO);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public EquipmentConditionVO_orignal findByPrimaryKey(Integer houseNO) {
		ResultSet rs = null;
		EquipmentConditionVO_orignal EquipmentConditionVO_orignal = null;

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ONE_STMT);) {

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

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return EquipmentConditionVO_orignal;
	}

	@Override
	public Set<EquipmentConditionVO_orignal> getAll() {
		ResultSet rs = null;
		EquipmentConditionVO_orignal EquipmentConditionVO_orignal = null;
		Set<EquipmentConditionVO_orignal> set = new LinkedHashSet<EquipmentConditionVO_orignal>();

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ALL_STMT);) {

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

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}
}