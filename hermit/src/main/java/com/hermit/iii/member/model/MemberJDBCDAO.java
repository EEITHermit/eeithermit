package com.hermit.iii.member.model;

import java.sql.*;
import java.util.*;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Hermit";
	String userid = "sa";
	String passwd = "P@ssw0rd";

	// memNO在資料庫為自動流水號免新增，順序同資料庫表格(與VO/Bean)設定
	// (memTel,memAccount,memPwd,memName,memGender,memEmail,memRegister,memStatus,memInfract,memImage)
	private static final String INSERT_STMT = "INSERT INTO Member VALUES (?, ?, ?, ?, ?, ?, getdate(), ?, ?, ?)";
	private static final String INSERT_WITHDATE_STMT = "INSERT INTO Member VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
	public void insert(MemberVO memberVO) { // register由資料庫系統給
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			if (memberVO.getMemRegister() != null) {
				pstmt = con.prepareStatement(INSERT_WITHDATE_STMT);
				pstmt.setString(1, memberVO.getMemTel());
				pstmt.setString(2, memberVO.getMemAccount());
				pstmt.setString(3, memberVO.getMemPwd());
				pstmt.setString(4, memberVO.getMemName());
				pstmt.setString(5, memberVO.getMemGender());
				pstmt.setString(6, memberVO.getMemEmail());
				pstmt.setDate(7, memberVO.getMemRegister());
				pstmt.setString(8, memberVO.getMemStatus());
				pstmt.setInt(9, memberVO.getMemInfract());
				pstmt.setString(10, memberVO.getMemImage());
			} else {
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1, memberVO.getMemTel());
				pstmt.setString(2, memberVO.getMemAccount());
				pstmt.setString(3, memberVO.getMemPwd());
				pstmt.setString(4, memberVO.getMemName());
				pstmt.setString(5, memberVO.getMemGender());
				pstmt.setString(6, memberVO.getMemEmail());
				pstmt.setString(7, memberVO.getMemStatus());
				pstmt.setInt(8, memberVO.getMemInfract());
				pstmt.setString(9, memberVO.getMemImage());
			}

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
	public void update(MemberVO memberVO) { // register由資料庫系統給
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, memberVO.getMemTel());
			pstmt.setString(2, memberVO.getMemAccount());
			pstmt.setString(3, memberVO.getMemPwd());
			pstmt.setString(4, memberVO.getMemName());
			pstmt.setString(5, memberVO.getMemGender());
			pstmt.setString(6, memberVO.getMemEmail());
			pstmt.setString(7, memberVO.getMemStatus());
			pstmt.setInt(8, memberVO.getMemInfract());
			pstmt.setString(9, memberVO.getMemImage());
			pstmt.setInt(10, memberVO.getMemNO());

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
	public void delete(Integer memNO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, memNO);

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
	public MemberVO findByPrimaryKey(Integer memNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

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

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
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
		return memberVO;
	}

	@Override
	public Set<MemberVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;
		Set<MemberVO> set = new LinkedHashSet<MemberVO>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

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

		} catch (ClassNotFoundException e) { // Handle any driver errors
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) { // Handle any SQL errors
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally { // Clean up JDBC resources
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
		return set;
	}

	/**** 自訂指令 ****/
	// SMS更新會員狀態
	@Override
	public void update_MemStatusByMemTel(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_MEMSTATUS_STMT);

			pstmt.setString(1, memberVO.getMemStatus());
			pstmt.setString(2, memberVO.getMemTel());

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

	// Image查詢(讀取)
	@Override
	public String find_MemImageByMemNO(Integer memNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String str = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEMIMAGE_STMT);

			pstmt.setInt(1, memNO);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 確定有資料才開始
				str = rs.getString(1);
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
		return str;
	}

	// AJAX 帳號檢查
	@Override
	public String count_MemAccount_AJAX(String memAccount) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String str = "帳號不存在";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(COUNT_MEMACCOUNT_STMT);

			pstmt.setString(1, memAccount);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 確定有資料才開始
				if (rs.getInt(1) >= 1)
					str = "帳號已存在";
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
		return str;
	}

	// member會員輸入的autoComplete(黑名單及行事曆使用)
	@Override
	public ArrayList<MemberVO> autoCompleteM(String name) {
		ArrayList<MemberVO> array = new ArrayList<MemberVO>();
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "sa", "P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement("select * from member where memName like ?");
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setMemNO(rs.getInt("memNO"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemTel(rs.getString("memTel"));
				array.add(memberVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return array;
	}

	// 黑名單檢查，若次數超過三次，則更改會員狀態為"黑名單會員"(黑名單用)
	String checkInfraction = "update member set memStatus = '黑名單會員' where memNO = ? AND memInfract >= 3";

	@Override
	public void checkInfraction(Integer memNO) {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "sa", "P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(checkInfraction);
			ps.setInt(1, memNO);
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 員工申請黑名單時，黑名單次數+1(黑名單用)
	String infractplus1 = "update member set memInfract = memInfract+1 where memNO = ? AND memStatus != '黑名單會員'";

	@Override
	public Integer infractPlus1(Integer memNO) {
		Connection conn = null;
		Integer result = 0;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "sa", "P@ssw0rd");
			PreparedStatement ps = conn.prepareStatement(infractplus1);
			ps.setInt(1, memNO);
			result = ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public MemberVO findByAccount(String memAccount) {
		MemberVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				memVO.setMemImage(rs.getString("memImage"));
				// memVO.setMemImage(rs.getBinaryStream("memImage"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				memVO.setMemImage(rs.getString("memImage"));
				// memVO.setMemImage(rs.getBinaryStream("memImage"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				memVO.setMemImage(rs.getString("memImage"));
				// memVO.setMemImage(rs.getBinaryStream("memImage"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args) {
		MemberJDBCDAO dao = new MemberJDBCDAO();

		// 新增 (register由資料庫系統給)
		MemberVO memberVO1 = new MemberVO();
		memberVO1.setMemTel("0905123456");
		memberVO1.setMemAccount("account123");
		memberVO1.setMemPwd("123Pwd@@");
		memberVO1.setMemName("小明Lin");
		memberVO1.setMemGender("男");
		memberVO1.setMemEmail("mail123@gmail.com");
		memberVO1.setMemStatus("未驗證會員");
		memberVO1.setMemInfract(0);
		memberVO1.setMemImage(null);
		dao.insert(memberVO1);

		// // 修改初始資料第一筆 (register由資料庫系統給)
		// MemberVO memberVO2 = new MemberVO();
		// memberVO2.setMemNO(40001);
		// memberVO2.setMemTel("0905123456");
		// memberVO2.setMemAccount("account123");
		// memberVO2.setMemPwd("123Pwd@@");
		// memberVO2.setMemName("小花Lin");
		// memberVO2.setMemGender("女");
		// memberVO2.setMemEmail("mail123@gmail.com");
		// memberVO2.setMemStatus("一般會員驗證");
		// memberVO2.setMemInfract(2);
		// // memberVO2.setMimage(null); // not use
		// dao.update(memberVO2, null, 0);
		//
		// // 查詢初始資料第一筆
		// MemberVO memberVO3 = dao.findByPrimaryKey(40005);
		// System.out.print(memberVO3.getMemNO() + ",");
		// System.out.print(memberVO3.getMemTel() + ",");
		// System.out.print(memberVO3.getMemAccount() + ",");
		// System.out.print(memberVO3.getMemPwd() + ",");
		// System.out.print(memberVO3.getMemName() + ",");
		// System.out.print(memberVO3.getMemGender() + ",");
		// System.out.print(memberVO3.getMemEmail() + ",");
		// System.out.print(memberVO3.getMemRegister() + ",");
		// System.out.print(memberVO3.getMemStatus() + ",");
		// System.out.print(memberVO3.getMemInfract() + ",");
		// System.out.println(memberVO3.getMemImage());
		// System.out.println("---------------------");
		//
		// // 查詢全部
		// Set<MemberVO> set = dao.getAll();
		// for (MemberVO member : set) {
		// System.out.print(member.getMemNO() + ",");
		// System.out.print(member.getMemTel() + ",");
		// System.out.print(member.getMemAccount() + ",");
		// System.out.print(member.getMemPwd() + ",");
		// System.out.print(member.getMemName() + ",");
		// System.out.print(member.getMemGender() + ",");
		// System.out.print(member.getMemEmail() + ",");
		// System.out.print(member.getMemRegister() + ",");
		// System.out.print(member.getMemStatus() + ",");
		// System.out.print(member.getMemInfract() + ",");
		// System.out.println(member.getMemImage());
		// System.out.println();
		// }
		//
		// // 刪除初始資料一筆
		// dao.delete(40002);

		// select by account
		// MemberVO memVO4 = dao.findByAccount("eeit9704");
		// System.out.print(memVO4.getMemNO() + ",");
		// System.out.print(memVO4.getMemTel() + ",");
		// System.out.print(memVO4.getMemAccount() + ",");
		// System.out.print(memVO4.getMemPwd() + ",");
		// System.out.print(memVO4.getMemName() + ",");
		// System.out.print(memVO4.getMemGender() + ",");
		// System.out.print(memVO4.getMemEmail() + ",");
		// System.out.print(memVO4.getMemRegister() + ",");
		// System.out.print(memVO4.getMemMstatus() + ",");
		// System.out.print(memVO4.getInfract()+",");
		// System.out.println(memVO4.getMemImage());
		// System.out.println("--------------------");

		// select by tel
		MemberVO memVO5 = dao.findByTel("0928265804");
		System.out.print(memVO5.getMemNO() + ",");
		System.out.print(memVO5.getMemTel() + ",");
		System.out.print(memVO5.getMemAccount() + ",");
		System.out.print(memVO5.getMemPwd() + ",");
		System.out.print(memVO5.getMemName() + ",");
		System.out.print(memVO5.getMemGender() + ",");
		System.out.print(memVO5.getMemEmail() + ",");
		System.out.print(memVO5.getMemRegister() + ",");
		System.out.print(memVO5.getMemStatus() + ",");
		System.out.print(memVO5.getMemInfract() + ",");
		System.out.println(memVO5.getMemImage());
		System.out.println("--------------------");

		// select by email
		// MemberVO memVO6 = dao.findByEmail("eeit9704@gmail.com");
		// System.out.print(memVO6.getMemNO() + ",");
		// System.out.print(memVO6.getMemTel() + ",");
		// System.out.print(memVO6.getMemAccount() + ",");
		// System.out.print(memVO6.getMemPwd() + ",");
		// System.out.print(memVO6.getMemName() + ",");
		// System.out.print(memVO6.getMemGender() + ",");
		// System.out.print(memVO6.getMemEmail() + ",");
		// System.out.print(memVO6.getMemRegister() + ",");
		// System.out.print(memVO6.getMemStatus() + ",");
		// System.out.print(memVO6.getMemInfract()+",");
		// System.out.println(memVO6.getMemImage());
		// System.out.println("--------------------");

		System.out.println("Done");
	}
}