package com.hermit.iii.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpJDBCDAO implements EmpDAO_interface {

	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Hermit";
	String userid = "sa";
	String passwd = "P@ssw0rd";

	private static final String INSERT_STMT = "INSERT INTO Emp(empAccount,empPwd,empPhone,empName,postNO,empStatus) VALUES (?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT empNO,empAccount,empPwd,empPhone,empName,postNO,empStatus FROM Emp order by empNO";
	private static final String GET_ONE_STMT = "SELECT empNO,empAccount,empPwd,empPhone,empName,postNO,empStatus FROM Emp where empNO=?";
	private static final String SELECT_BY_ACCOUNT = "SELECT empNO,empAccount,empPwd,empPhone,empName,postNO,empStatus FROM Emp where empAccount=?";
	private static final String UPDATE = "UPDATE Emp set empAccount=?,empPwd=?,empPhone=?,empName=?,postNO=?,empStatus=? where empNO=?";
	private static final String DELETE = "DELETE FROM Emp where empNO=?";

	@Override
	public void insert(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmpAccount());
			pstmt.setString(2, empVO.getEmpPwd());
			pstmt.setString(3, empVO.getEmpPhone());
			pstmt.setString(4, empVO.getEmpName());
			pstmt.setInt(5, empVO.getPostNO());
			pstmt.setBoolean(6, empVO.getEmpStatus());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmpAccount());
			pstmt.setString(2, empVO.getEmpPwd());
			pstmt.setString(3, empVO.getEmpPhone());
			pstmt.setString(4, empVO.getEmpName());
			pstmt.setInt(5, empVO.getPostNO());
			pstmt.setBoolean(6, empVO.getEmpStatus());
			pstmt.setInt(7, empVO.getEmpNO());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void delete(Integer empNO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empNO);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public EmpVO findByAccount(String empAccount) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_BY_ACCOUNT);

			pstmt.setString(1, empAccount);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				empVO = new EmpVO();
				empVO.setEmpNO(rs.getInt("empNO"));
				empVO.setEmpAccount(rs.getString("empAccount"));
				empVO.setEmpPwd(rs.getString("empPwd"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setPostNO(rs.getInt("postNO"));
				empVO.setEmpStatus(rs.getBoolean("empStatus"));
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
		return empVO;
	}

	@Override
	public EmpVO findByPrimaryKey(Integer empNO) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empNO);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				empVO = new EmpVO();
				empVO.setEmpNO(rs.getInt("empNO"));
				empVO.setEmpAccount(rs.getString("empAccount"));
				empVO.setEmpPwd(rs.getString("empPwd"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setPostNO(rs.getInt("postNO"));
				empVO.setEmpStatus(rs.getBoolean("empStatus"));
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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {

		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				empVO = new EmpVO();
				empVO.setEmpNO(rs.getInt("empNO"));
				empVO.setEmpAccount(rs.getString("empAccount"));
				empVO.setEmpPwd(rs.getString("empPwd"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setPostNO(rs.getInt("postNO"));
				empVO.setEmpStatus(rs.getBoolean("empStatus"));
				list.add(empVO);
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
		return list;
	}

	public static void main(String[] args) {

		EmpJDBCDAO dao = new EmpJDBCDAO();

		// insert
		EmpVO empVO1 = new EmpVO();
		empVO1.setEmpAccount("eeit9704");
		empVO1.setEmpPwd("sa123456");
		empVO1.setEmpPhone("0928265804");
		empVO1.setEmpName("藍浩天");
		empVO1.setPostNO(310);
		empVO1.setEmpStatus(false);
		dao.insert(empVO1);

		// update
		// EmpVO empVO2 = new EmpVO();
		// empVO2.setEmpNO(30002);
		// empVO2.setEmpAccount("eeit97087");
		// empVO2.setEmpPwd("12312311");
		// empVO2.setEmpPhone("0957057006");
		// empVO2.setEmpName("徐漢勳");
		// empVO2.setPostNO(330);
		// empVO2.setEmpStatus(true);
		// dao.update(empVO2);

		// delete
		// dao.delete(30003);

		// select by account
		EmpVO empVO3 = dao.findByAccount("Vir3");
		System.out.print(empVO3.getEmpNO() + ",");
		System.out.print(empVO3.getEmpAccount() + ",");
		System.out.print(empVO3.getEmpPwd() + ",");
		System.out.print(empVO3.getEmpPhone() + ",");
		System.out.print(empVO3.getEmpName() + ",");
		System.out.print(empVO3.getPostNO() + ",");
		System.out.println(empVO3.getEmpStatus());
		System.out.println("----------------------");

		// select one
		EmpVO empVO4 = dao.findByPrimaryKey(30001);
		System.out.print(empVO4.getEmpNO() + ",");
		System.out.print(empVO4.getEmpAccount() + ",");
		System.out.print(empVO4.getEmpPwd() + ",");
		System.out.print(empVO4.getEmpPhone() + ",");
		System.out.print(empVO4.getEmpName() + ",");
		System.out.print(empVO4.getPostNO() + ",");
		System.out.println(empVO4.getEmpStatus());
		System.out.println("----------------------");

		// select all
		List<EmpVO> list = dao.getAll();
		for (EmpVO aEmp : list) {
			System.out.print(aEmp.getEmpNO() + ",");
			System.out.print(aEmp.getEmpAccount() + ",");
			System.out.print(aEmp.getEmpPwd() + ",");
			System.out.print(aEmp.getEmpPhone() + ",");
			System.out.print(aEmp.getEmpName() + ",");
			System.out.print(aEmp.getPostNO() + ",");
			System.out.print(aEmp.getEmpStatus());
			System.out.println();
		}

	}

}
