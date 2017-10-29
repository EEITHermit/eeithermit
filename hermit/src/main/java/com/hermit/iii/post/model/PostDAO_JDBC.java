package com.hermit.iii.post.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PostDAO_JDBC implements PostDAO_interface {

	
	private static final String INSERT_STMT =
		      "INSERT INTO Post (postName) VALUES (?)";
	private static final String UPDATE_STMT =
			  "UPDATE Post set postName=? where postNO = ?";
	private static final String DELETE_STMT =
		      "DELETE FROM Post where postNO = ?";
	private static final String GET_ONE_STMT =
			  "SELECT postNO,postName FROM Post where postNO = ?";
	private static final String GET_ALL_STMT =
			  "SELECT postNO,postName FROM Post order by postNO";
	
	@Override
	public void insert(PostVO_original postVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,postVO.getPostName());
			pstmt.execute();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(PostVO_original postVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1,postVO.getPostName());
			pstmt.setInt(2,postVO.getPostNO());
			pstmt.execute();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(Integer postNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1,postNO);
			pstmt.execute();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public PostVO_original findByPrimaryKey(Integer postNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PostVO_original vo = new PostVO_original();
		ResultSet rs ;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1,postNO);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				vo.setPostName(rs.getString("postName"));
				vo.setPostNO(rs.getInt("postNO"));
			}
			return vo;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<PostVO_original> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		PostVO_original vo;
		ResultSet rs ;
		List<PostVO_original> list = new LinkedList<PostVO_original>();
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				vo = new PostVO_original();
				vo.setPostName(rs.getString("postName"));
				vo.setPostNO(rs.getInt("postNO"));
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	
	public static void main(String args[]){
		PostVO_original vo = new PostVO_original();
		PostDAO_JDBC dao = new PostDAO_JDBC();
		
//		vo.setPostName("管理員");
//		dao.insert(vo);
		
//		vo.setPostNO(350);
//		vo.setPostName("行政人員");
//		dao.update(vo);
		
//		dao.delete(350);
		
//		vo = dao.findByPrimaryKey(350);
//		System.out.println(vo.getPostName());
//		System.out.println(vo.getPostNO());
		
//		List<PostVO> list = dao.getAll();
//		for(int i = 0; i<list.size();i++){
//			vo = list.get(i);
//			System.out.println(vo.getPostName());
//			System.out.println(vo.getPostNO());
//		}
		
	}
}
