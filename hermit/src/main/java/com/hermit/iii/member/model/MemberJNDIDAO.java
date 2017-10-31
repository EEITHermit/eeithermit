package com.hermit.iii.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import com.hermit.iii.util.SecurityCipher;

public class MemberJNDIDAO implements MemberDAO_interface {
	DataSource ds = null;

	public MemberJNDIDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/JNDI");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// memNO在資料庫為自動流水號免新增，順序同資料庫表格(與VO/Bean)設定
	// (memTel,memAccount,memPwd,memName,memGender,memEmail,memRegister改預設,memStatus,memInfract,memImage)
	private static final String INSERT_STMT = "INSERT INTO Member (memTel,memAccount,memPwd,memName,memGender,memEmail,memStatus,memInfract,memImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	// 同上順序，全改通吃法(PK流水號當條件)
	private static final String UPDATE_STMT = "UPDATE Member SET memTel=?, memAccount=?, memPwd=?, memName=?, memGender=?, memEmail=?, memStatus=?, memInfract=?, memImage=? WHERE memNO = ?";
	private static final String DELETE_STMT = "DELETE FROM Member WHERE memNO = ?";
	// (含PK流水號)全選，避免用＊(PK流水號當條件)
	private static final String GET_ONE_STMT = "SELECT memNO,memTel,memAccount,memPwd,memName,memGender,memEmail,memRegister,memStatus,memInfract,memImage FROM Member WHERE memNO = ?";
	// (含PK流水號)全選，避免用＊(免PK流水號當條件全打包，PK流水號當排序使得每次結果顯示方式統一)
	private static final String GET_ALL_STMT = "SELECT memNO,memTel,memAccount,memPwd,memName,memGender,memEmail,memRegister,memStatus,memInfract,memImage FROM Member ORDER BY memNO";
	/**** 自訂指令 ****/
	// 更新會員狀態
	private static final String UPDATE_MEMSTATUS_STMT = "UPDATE Member SET memStatus=? WHERE memTel = ?";
	// Image查詢(讀取)
	private static final String GET_MEMIMAGE_STMT = "SELECT memImage FROM Member WHERE memNO = ?";
	// AJAX 帳號檢查 ，求總數
	private static final String COUNT_MEMACCOUNT_STMT = "SELECT count(*) FROM Member WHERE memAccount = ?";

	private static final String SELECT_BY_ACCOUNT = "SELECT memNO,memTel,memAccount,memPwd,memName,memGender,memEmail,memRegister,memStatus,memInfract FROM Member where memAccount = ?";
	private static final String SELECT_BY_TEL = "SELECT memNO,memTel,memAccount,memPwd,memName,memGender,memEmail,memRegister,memStatus,memInfract FROM Member where memTel = ?";
	private static final String SELECT_BY_EMAIL = "SELECT memNO,memTel,memAccount,memPwd,memName,memGender,memEmail,memRegister,memStatus,memInfract FROM Member where memEmail = ?";

	@Override
	public void insert(MemberVO memberVO) {
		PreparedStatement pstmt = null;

		try (Connection conn = ds.getConnection();) {

			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setString(1, memberVO.getMemTel());
			pstmt.setString(2, memberVO.getMemAccount());
			pstmt.setString(3, new SecurityCipher().encryptString(memberVO.getMemPwd()));
			pstmt.setString(4, memberVO.getMemName());
			pstmt.setString(5, memberVO.getMemGender());
			pstmt.setString(6, memberVO.getMemEmail());
			pstmt.setString(7, memberVO.getMemStatus());
			pstmt.setInt(8, memberVO.getMemInfract());
			pstmt.setString(9, memberVO.getMemImage());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Clean up resources
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(MemberVO memberVO) {
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_STMT);) {

			pstmt.setString(1, memberVO.getMemTel());
			pstmt.setString(2, memberVO.getMemAccount());
			pstmt.setString(3, new SecurityCipher().encryptString(memberVO.getMemPwd()));
			pstmt.setString(4, memberVO.getMemName());
			pstmt.setString(5, memberVO.getMemGender());
			pstmt.setString(6, memberVO.getMemEmail());
			pstmt.setString(7, memberVO.getMemStatus());
			pstmt.setInt(8, memberVO.getMemInfract());
			pstmt.setString(9, memberVO.getMemImage());
			pstmt.setInt(10, memberVO.getMemNO());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer memNO) {
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_STMT);) {

			pstmt.setInt(1, memNO);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MemberVO findByPrimaryKey(Integer memNO) {
		ResultSet rs = null;
		MemberVO memberVO = null;

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ONE_STMT);) {

			pstmt.setInt(1, memNO);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 確定有資料才開始new MemberVO物件
				// memberVO = Domain objects
				memberVO = new MemberVO();
				memberVO.setMemNO(rs.getInt("memNO"));
				memberVO.setMemTel(rs.getString("memTel"));
				memberVO.setMemAccount(rs.getString("memAccount"));
				memberVO.setMemPwd(rs.getString("memPwd"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemGender(rs.getString("memGender"));
				memberVO.setMemEmail(rs.getString("memEmail"));
				memberVO.setMemRegister(rs.getDate("memRegister"));
				memberVO.setMemStatus(rs.getString("memStatus"));
				memberVO.setMemInfract(rs.getInt("memInfract"));
				memberVO.setMemImage(rs.getString("memImage"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Clean up resources
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return memberVO;
	}

	@Override
	public Set<MemberVO> getAll() {
		ResultSet rs = null;
		MemberVO memberVO = null;
		Set<MemberVO> set = new LinkedHashSet<MemberVO>();

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ALL_STMT);) {

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 確定有資料才開始new MemberVO物件
				// memberVO = Domain objects
				memberVO = new MemberVO();
				memberVO.setMemNO(rs.getInt("memNO"));
				memberVO.setMemTel(rs.getString("memTel"));
				memberVO.setMemAccount(rs.getString("memAccount"));
				memberVO.setMemPwd(rs.getString("memPwd"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemGender(rs.getString("memGender"));
				memberVO.setMemEmail(rs.getString("memEmail"));
				memberVO.setMemRegister(rs.getDate("memRegister"));
				memberVO.setMemStatus(rs.getString("memStatus"));
				memberVO.setMemInfract(rs.getInt("memInfract"));
				memberVO.setMemImage(rs.getString("memImage"));
				set.add(memberVO); // Store the row in the list
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Clean up resources
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return set;
	}

	/**** 自訂指令 ****/
	// member會員輸入的autoComplete(黑名單及行事曆使用)
	@Override
	public ArrayList<MemberVO> autoCompleteM(String name) {
		ArrayList<MemberVO> array = new ArrayList<MemberVO>();
		ResultSet rs = null;

		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement("select * from member where memName like ?");) {

			ps.setString(1, "%" + name + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setMemNO(rs.getInt("memNO"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemTel(rs.getString("memTel"));
				array.add(memberVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // Clean up resources
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return array;
	}

	// 黑名單檢查，若次數超過三次，則更改會員狀態為"黑名單會員"(黑名單用)
	String checkInfraction = "update member set memStatus = '黑名單會員' where memNO = ? AND memInfract >= 3";

	@Override
	public void checkInfraction(Integer memNO) {
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(checkInfraction);) {

			ps.setInt(1, memNO);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 員工申請黑名單時，黑名單次數+1(黑名單用)
	String infractplus1 = "update member set memInfract = memInfract+1 where memNO = ? AND memStatus != '黑名單會員'";

	@Override
	public Integer infractPlus1(Integer memNO) {
		Integer result = 0;

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(infractplus1);) {

			PreparedStatement ps = conn.prepareStatement(infractplus1);
			ps.setInt(1, memNO);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// SMS更新會員狀態
	@Override
	public void update_MemStatusByMemTel(MemberVO memberVO) {
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(UPDATE_MEMSTATUS_STMT);) {

			pstmt.setString(1, memberVO.getMemStatus());
			pstmt.setString(2, memberVO.getMemTel());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Image查詢(讀取)
	@Override
	public String find_MemImageByMemNO(Integer memNO) {
		ResultSet rs = null;
		String str = null;

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(GET_MEMIMAGE_STMT);) {

			pstmt.setInt(1, memNO);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 確定有資料才開始
				str = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}

	// AJAX 帳號檢查
	@Override
	public String count_MemAccount_AJAX(String memAccount) {
		ResultSet rs = null;
		String str = "帳號不存在";

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(COUNT_MEMACCOUNT_STMT);) {

			pstmt.setString(1, memAccount);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 確定有資料才開始
				if (rs.getInt(1) >= 1)
					str = "帳號已存在";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public MemberVO findByAccount(String memAccount) {
		MemberVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_ACCOUNT);

			pstmt.setString(1, memAccount);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemberVO();
				memVO.setMemNO(rs.getInt("memNO"));
				memVO.setMemTel(rs.getString("memTel"));
				memVO.setMemAccount(rs.getString("memAccount"));
				memVO.setMemPwd(rs.getString("memPwd"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemGender(rs.getString("memGender"));
				memVO.setMemEmail(rs.getString("memEmail"));
				memVO.setMemRegister(rs.getDate("memRegister"));
				memVO.setMemStatus(rs.getString("memStatus"));
				memVO.setMemInfract(rs.getInt("memInfract"));
				memVO.setMemImage(rs.getString("memberImage"));
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return memVO;
	}

	@Override
	public MemberVO findByTel(String memTel) {
		MemberVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_TEL);

			pstmt.setString(1, memTel);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemberVO();
				memVO.setMemNO(rs.getInt("memNO"));
				memVO.setMemTel(rs.getString("memTel"));
				memVO.setMemAccount(rs.getString("memAccount"));
				memVO.setMemPwd(rs.getString("memPwd"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemGender(rs.getString("memGender"));
				memVO.setMemEmail(rs.getString("memEmail"));
				memVO.setMemRegister(rs.getDate("memRegister"));
				memVO.setMemStatus(rs.getString("memStatus"));
				memVO.setMemInfract(rs.getInt("memInfract"));
				memVO.setMemImage(rs.getString("memberImage"));
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return memVO;
	}

	@Override
	public MemberVO findByEmail(String memEmail) {
		MemberVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_EMAIL);

			pstmt.setString(1, memEmail);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemberVO();
				memVO.setMemNO(rs.getInt("memNO"));
				memVO.setMemTel(rs.getString("memTel"));
				memVO.setMemAccount(rs.getString("memAccount"));
				memVO.setMemPwd(rs.getString("memPwd"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemGender(rs.getString("memGender"));
				memVO.setMemEmail(rs.getString("memEmail"));
				memVO.setMemRegister(rs.getDate("memRegister"));
				memVO.setMemStatus(rs.getString("memStatus"));
				memVO.setMemInfract(rs.getInt("memInfract"));
				memVO.setMemImage(rs.getString("memberImage"));
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return memVO;
	}
}