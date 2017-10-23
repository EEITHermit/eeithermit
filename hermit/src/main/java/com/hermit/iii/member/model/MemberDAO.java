package com.hermit.iii.member.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MemberDAO implements MemberDAO_interface {

	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Hermit";
	String userid = "sa";
	String passwd = "P@ssw0rd";

	private static final String INSERT_STMT = "insert into Member values(?, ?, ?, ?, ?, ?, getdate(), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT memNO,memTel,memAccount,memPwd,memName,memGender,memEmail,memRegister,memStatus,memInfract,memImage FROM Member ORDER BY memNO";
	private static final String GET_ONE_STMT = "SELECT memNO,memTel,memAccount,memPwd,memName,memGender,memEmail,memRegister,memStatus,memInfract,memImage FROM Member WHERE memNO = ?";
	private static final String UPDATE = "UPDATE Member SET memTel=?, memAccount=?, memPwd=?, memName=?, memGender=?, memEmail=?, memStatus=?, memInfract=?, memImage=? WHERE memNO = ?";
	private static final String DELETE = "DELETE FROM Member WHERE memNO = ?";

	@Override
	public void insert(MemberVO memberVO) {

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement stmt = conn.prepareStatement(INSERT_STMT);
			stmt.setString(1, memberVO.getMemTel());
			stmt.setString(2, memberVO.getMemAccount());
			stmt.setString(3, memberVO.getMemPwd());
			stmt.setString(4, memberVO.getMemName());
			stmt.setString(5, memberVO.getMemGender());
			stmt.setString(6, memberVO.getMemEmail());
			stmt.setString(7, memberVO.getMemStatus());
			stmt.setInt(8, memberVO.getMemInfract());
			stmt.setString(9, memberVO.getMemImage());
			stmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(MemberVO memberVO) {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, memberVO.getMemTel());
			stmt.setString(2, memberVO.getMemAccount());
			stmt.setString(3, memberVO.getMemPwd());
			stmt.setString(4, memberVO.getMemName());
			stmt.setString(5, memberVO.getMemGender());
			stmt.setString(6, memberVO.getMemEmail());
			stmt.setString(7, memberVO.getMemStatus());
			stmt.setInt(8, memberVO.getMemInfract());
			stmt.setString(9,memberVO.getMemImage());
			stmt.setInt(10, memberVO.getMemNO());

			stmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer memNO) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			stmt = conn.prepareStatement(DELETE);

			stmt.setInt(1, memNO);

			stmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public MemberVO findByPrimaryKey(Integer memNO) {
		MemberVO memberVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			stmt = conn.prepareStatement(GET_ONE_STMT);

			stmt.setInt(1, memNO);
			rs = stmt.executeQuery();

			while (rs.next()) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}

		return memberVO;

	}
/*
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			stmt = conn.prepareStatement(GET_ALL_STMT);
			rs = stmt.executeQuery();

			while (rs.next()) {
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
				list.add(memberVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}
	*/
	//member會員輸入的autoComplete(黑名單及行事曆使用)
		public ArrayList<MemberVO> autoCompleteM(String name){
			ArrayList<MemberVO> array = new ArrayList<MemberVO>();
			Connection conn = null;
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
				PreparedStatement ps = conn.prepareStatement("select * from member where memName like ?");
				ps.setString(1, "%"+name+"%");
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
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
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return array;
		}
		//員工申請黑名單時，黑名單次數+1(黑名單用)
		String infractplus1 = "update member set memInfract = memInfract+1 where memNO = ? AND memStatus != '黑名單會員'";
		public Integer infractPlus1(Integer memNO){
			Connection conn = null;
			Integer result=0;
			try{
				Class.forName(driver);
				conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
				PreparedStatement ps = conn.prepareStatement(infractplus1);
				ps.setInt(1, memNO);
				result = ps.executeUpdate();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		//黑名單檢查，若次數超過三次，則更改會員狀態為"黑名單會員"(黑名單用)
		String checkInfraction = "update member set memStatus = '黑名單會員' where memNO = ? AND memInfract >= 3";
		public void checkInfraction(Integer memNO){
			Connection conn = null;
			try{
				Class.forName(driver);
				conn = DriverManager.getConnection(url,"sa","P@ssw0rd");
				PreparedStatement ps = conn.prepareStatement(checkInfraction);
				ps.setInt(1, memNO);
				ps.executeUpdate();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	



	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();

		// 新增
		// MemberVO memberVO1=new MemberVO();
		// memberVO1.setMemTel("0912345678");
		// memberVO1.setMemAccount("vir5");
		// memberVO1.setMemPwd("sa123456");
		// memberVO1.setMemName("虛擬會員5");
		// memberVO1.setMemGender("男性");
		// memberVO1.setMemEmail("vir123456@hotmail.com");
		// memberVO1.setMemStatus("已註冊通過");
		// memberVO1.setMemInfract(0);
		// dao.insert(memberVO1, null, 0);

		// 修改
		// MemberVO memberVO2 = new MemberVO();
		// memberVO2.setMemNO(40006);
		// memberVO2.setMemTel("0905123456");
		// memberVO2.setMemAccount("Vir6");
		// memberVO2.setMemPwd("sa123456");
		// memberVO2.setMemName("虛擬會員6");
		// memberVO2.setMemGender("女姓");
		// memberVO2.setMemEmail("vir1234666@gmail.com");
		// memberVO2.setMemStatus("已註冊通過");
		// memberVO2.setMemInfract(1);
		// // memberVO2.setMimage(null); // not use
		// dao.update(memberVO2, null, 0);

		// 查單一
		// MemberVO memberVO3 = dao.findByPrimaryKey(40001);
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

		// 查全部
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO member : list) {
//			System.out.print(member.getMemNO() + ",");
//			System.out.print(member.getMemTel() + ",");
//			System.out.print(member.getMemAccount() + ",");
//			System.out.print(member.getMemPwd() + ",");
//			System.out.print(member.getMemName() + ",");
//			System.out.print(member.getMemGender() + ",");
//			System.out.print(member.getMemEmail() + ",");
//			System.out.print(member.getMemRegister() + ",");
//			System.out.print(member.getMemStatus() + ",");
//			System.out.print(member.getMemInfract() + ",");
//			System.out.println(member.getMemImage());
//			System.out.println("---------------------");
//		}
		
		//查電話
		MemberVO memberVO3 = dao.findByPrimaryKey(40001);
		// System.out.print(memberVO3.getMemNO() + ",");
		 System.out.print(memberVO3.getMemTel() + ",");
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

	}

	@Override
	public Set<MemberVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update_MemStatusByMemTel(MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String find_MemImageByMemNO(Integer memNO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String count_MemAccount_AJAX(String memAccount) {
		// TODO Auto-generated method stub
		return null;
	}
}
