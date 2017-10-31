package com.hermit.iii.lease.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.housepicture.model.HousePictureVO;

public class LeaseJNDIDAO implements LeaseDAO_interface {
	DataSource ds = null;

	public LeaseJNDIDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/JNDI");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// leaseNO在資料庫為自動流水號免新增，順序同資料庫表格(與VO/Bean)設定
	// (houseNO,leaseBeginDate,leaseEndDate,memNO,empNO,Rent,Deposit,Relief,leaseDate,leasePic,houseNote,Refund)
	private static final String INSERT_STMT = "INSERT INTO Lease VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	// 同上順序，全改通吃法(PK流水號當條件)
	private static final String UPDATE_STMT = "UPDATE Lease SET houseNO=?, leaseBeginDate=?, leaseEndDate=?, memNO=?, empNO=?, leaseRent=?, leaseDeposit=?, leaseRelief=?, leaseDate=?, leasePic=?, houseNote=?, leaseRefund=? WHERE leaseNO = ?";
	private static final String DELETE_STMT = "DELETE FROM Lease WHERE leaseNO = ?";
	// (含PK流水號)全選，避免用＊(PK流水號當條件)
	private static final String GET_ONE_STMT = "SELECT leaseNO,houseNO,leaseBeginDate,leaseEndDate,memNO,empNO,leaseRent,leaseDeposit,leaseRelief,leaseDate,leasePic,houseNote,leaseRefund FROM Lease WHERE leaseNO = ?";
	// (含PK流水號)全選，避免用＊(免PK流水號當條件全打包，PK流水號當排序使得每次結果顯示方式統一)
	private static final String GET_ALL_STMT = "SELECT leaseNO,houseNO,leaseBeginDate,leaseEndDate,memNO,empNO,leaseRent,leaseDeposit,leaseRelief,leaseDate,leasePic,houseNote,leaseRefund FROM Lease ORDER BY leaseNO";
	
	@Override
	public void insert(LeaseVO leaseVO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);) {
			
			pstmt.setInt(1, leaseVO.getHouseVO().getHouseNO());
			pstmt.setDate(2, leaseVO.getLeaseBeginDate());
			pstmt.setDate(3, leaseVO.getLeaseEndDate());
			pstmt.setInt(4, leaseVO.getMemNO());
			pstmt.setInt(5, leaseVO.getEmpNO());
			pstmt.setInt(6, leaseVO.getLeaseRent());
			pstmt.setInt(7, leaseVO.getLeaseDeposit());
			pstmt.setInt(8, leaseVO.getLeaseRelief());
			pstmt.setDate(9, leaseVO.getLeaseDate());
			pstmt.setString(10,leaseVO.getLeasePic());
			pstmt.setString(11, leaseVO.getHouseNote());
			pstmt.setByte(12, leaseVO.getLeaseRefund());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(LeaseVO leaseVO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_STMT);) {

			pstmt.setInt(1, leaseVO.getHouseVO().getHouseNO());
			pstmt.setDate(2, leaseVO.getLeaseBeginDate());
			pstmt.setDate(3, leaseVO.getLeaseEndDate());
			pstmt.setInt(4, leaseVO.getMemNO());
			pstmt.setInt(5, leaseVO.getEmpNO());
			pstmt.setInt(6, leaseVO.getLeaseRent());
			pstmt.setInt(7, leaseVO.getLeaseDeposit());
			pstmt.setInt(8, leaseVO.getLeaseRelief());
			pstmt.setDate(9, leaseVO.getLeaseDate());
			pstmt.setString(10,leaseVO.getLeasePic());
			pstmt.setString(11, leaseVO.getHouseNote());
			pstmt.setByte(12, leaseVO.getLeaseRefund());
			pstmt.setInt(13, leaseVO.getLeaseNO());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer leaseNO) {

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_STMT);) {

			pstmt.setInt(1, leaseNO);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public LeaseVO findByPrimaryKey(Integer leaseNO) {
		ResultSet rs = null;
		LeaseVO leaseVO = null;

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ONE_STMT);) {

			pstmt.setInt(1, leaseNO);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 確定有資料才開始new LeaseVO物件
				// leaseVO = Domain objects
				leaseVO = new LeaseVO();
				leaseVO.setLeaseNO(rs.getInt("leaseNO"));
				HouseVO houseVO = new HouseVO();
				houseVO.setHouseNO(rs.getInt("houseNO"));
				leaseVO.setHouseVO(houseVO);
				leaseVO.setLeaseBeginDate(rs.getDate("leaseBeginDate"));
				leaseVO.setLeaseEndDate(rs.getDate("leaseEndDate"));
				leaseVO.setMemNO(rs.getInt("memNO"));
				leaseVO.setEmpNO(rs.getInt("empNO"));
				leaseVO.setLeaseRent(rs.getInt("leaseRent"));
				leaseVO.setLeaseDeposit(rs.getInt("leaseDeposit"));
				leaseVO.setLeaseRelief(rs.getInt("leaseRelief"));
				leaseVO.setLeaseDate(rs.getDate("leaseDate"));
				leaseVO.setLeasePic(rs.getString("leasePic"));
				leaseVO.setHouseNote(rs.getString("houseNote"));
				leaseVO.setLeaseRefund(rs.getByte("leaseRefund"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return leaseVO;
	}

	@Override
	public Set<LeaseVO> getAll() {
		ResultSet rs = null;
		LeaseVO leaseVO = null;
		Set<LeaseVO> set = new LinkedHashSet<LeaseVO>();

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ALL_STMT);) {

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 確定有資料才開始new LeaseVO物件
				// leaseVO = Domain objects
				leaseVO = new LeaseVO();
				leaseVO.setLeaseNO(rs.getInt("leaseNO"));
				HouseVO houseVO = new HouseVO();
				houseVO.setHouseNO(rs.getInt("houseNO"));
				leaseVO.setHouseVO(houseVO);
				leaseVO.setLeaseBeginDate(rs.getDate("leaseBeginDate"));
				leaseVO.setLeaseEndDate(rs.getDate("leaseEndDate"));
				leaseVO.setMemNO(rs.getInt("memNO"));
				leaseVO.setEmpNO(rs.getInt("empNO"));
				leaseVO.setLeaseRent(rs.getInt("leaseRent"));
				leaseVO.setLeaseDeposit(rs.getInt("leaseDeposit"));
				leaseVO.setLeaseRelief(rs.getInt("leaseRelief"));
				leaseVO.setLeaseDate(rs.getDate("leaseDate"));
				leaseVO.setLeasePic(rs.getString("leasePic"));
				leaseVO.setHouseNote(rs.getString("houseNote"));
				leaseVO.setLeaseRefund(rs.getByte("leaseRefund"));
				set.add(leaseVO); // Store the row in the list
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}


}