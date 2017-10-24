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
	public void insert(EquipmentConditionVO equipmentConditionVO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);) {

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

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(EquipmentConditionVO equipmentConditionVO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_STMT);) {

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
	public EquipmentConditionVO findByPrimaryKey(Integer houseNO) {
		ResultSet rs = null;
		EquipmentConditionVO equipmentConditionVO = null;

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ONE_STMT);) {

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

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipmentConditionVO;
	}

	@Override
	public Set<EquipmentConditionVO> getAll() {
		ResultSet rs = null;
		EquipmentConditionVO equipmentConditionVO = null;
		Set<EquipmentConditionVO> set = new LinkedHashSet<EquipmentConditionVO>();

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ALL_STMT);) {

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

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}
}