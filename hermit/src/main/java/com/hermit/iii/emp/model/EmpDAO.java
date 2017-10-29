package com.hermit.iii.emp.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmpDAO implements EmpDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Emp(empAccount,empPwd,empPhone,empName,postNO,empStatus) VALUES (?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT empNO,empAccount,empPwd,empPhone,empName,postNO,empStatus FROM Emp order by empNO";
	private static final String GET_ONE_STMT = "SELECT empNO,empAccount,empPwd,empPhone,empName,postNO,empStatus FROM Emp where empNO=?";
	private static final String SELECT_BY_ACCOUNT = "SELECT empNO,empAccount,empPwd,empPhone,empName,postNO,empStatus FROM Emp where empAccount=?";
	private static final String UPDATE = "UPDATE Emp set empAccount=?,empPwd=?,empPhone=?,empName=?,postNO=?,empStatus=? where empNO=?";
	private static final String DELETE = "DELETE FROM Emp where empNO=?";

	@Override
	public void insert(EmpVO_original empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmpAccount());
			pstmt.setString(2, empVO.getEmpPwd());
			pstmt.setString(3, empVO.getEmpPhone());
			pstmt.setString(4, empVO.getEmpName());
			pstmt.setInt(5, empVO.getPostNO());
			pstmt.setBoolean(6, empVO.getEmpStatus());

			pstmt.executeUpdate();

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
	public void update(EmpVO_original empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, empVO.getEmpAccount());
			pstmt.setString(2, empVO.getEmpPwd());
			pstmt.setString(3, empVO.getEmpPhone());
			pstmt.setString(4, empVO.getEmpName());
			pstmt.setInt(5, empVO.getPostNO());
			pstmt.setBoolean(6, empVO.getEmpStatus());
			pstmt.setInt(7, empVO.getEmpNO());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, empNO);
			pstmt.executeUpdate();

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
	public EmpVO_original findByAccount(String empAccount) {

		EmpVO_original empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_ACCOUNT);

			pstmt.setString(1, empAccount);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				empVO = new EmpVO_original();
				empVO.setEmpNO(rs.getInt("empNO"));
				empVO.setEmpAccount(rs.getString("empAccount"));
				empVO.setEmpPwd(rs.getString("empPwd"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setPostNO(rs.getInt("postNO"));
				empVO.setEmpStatus(rs.getBoolean("empStatus"));
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
		return empVO;
	}

	@Override
	public EmpVO_original findByPrimaryKey(Integer empNO) {

		EmpVO_original empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, empNO);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				empVO = new EmpVO_original();
				empVO.setEmpNO(rs.getInt("empNO"));
				empVO.setEmpAccount(rs.getString("empAccount"));
				empVO.setEmpPwd(rs.getString("empPwd"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setPostNO(rs.getInt("postNO"));
				empVO.setEmpStatus(rs.getBoolean("empStatus"));
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
		return empVO;
	}

	@Override
	public List<EmpVO_original> getAll() {

		List<EmpVO_original> list = new ArrayList<EmpVO_original>();
		EmpVO_original empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				empVO = new EmpVO_original();
				empVO.setEmpNO(rs.getInt("empNO"));
				empVO.setEmpAccount(rs.getString("empAccount"));
				empVO.setEmpPwd(rs.getString("empPwd"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setPostNO(rs.getInt("postNO"));
				empVO.setEmpStatus(rs.getBoolean("empStatus"));
				list.add(empVO);
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
		return list;
	}

}
