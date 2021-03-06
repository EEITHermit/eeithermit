package com.hermit.iii.admanager.model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;


public class ADManagerDAO_JDBC implements ADManagerDAO_interface{
	private static final String INSERT_STMT =
		"INSERT INTO ADManager (adImage, adLink, adMessage, adTimeStart, adTimeEnd, adStatus, adBrowse, adModify) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
		"UPDATE ADManager set adImage=?, adLink=?, adMessage=?, adTimeStart=?, adTimeEnd=?, adStatus=?, adBrowse=?, adModify=? WHERE adNo=?";
	private static final String DELETE = 
		"DELETE FROM ADManager WHERE adNo=?";
	private static final String GET_ONE_STMT = 
		"SELECT adNo, adImage, adLink, adMessage, adTimeStart, adTimeEnd, adStatus, adBrowse, adModify FROM ADManager WHERE adNo=?";
	private static final String GET_ALL_STMT = 
		"SELECT adNo, adImage, adLink, adMessage, adTimeStart, adTimeEnd, adStatus, adBrowse, adModify FROM ADManager ORDER BY adNo";

		
		@Override
		public void insert(ADManagerVO_original ADManagerVO) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				
			
				conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
				pstmt = conn.prepareStatement(INSERT_STMT);
				pstmt.setString(1, ADManagerVO.getAdImage()); //廣告圖片
				pstmt.setString(2, ADManagerVO.getAdLink());
				pstmt.setString(3, ADManagerVO.getAdMessage());
				pstmt.setDate(4, ADManagerVO.getAdTimeStart());
				pstmt.setDate(5, ADManagerVO.getAdTimeEnd());
				pstmt.setBoolean(6, ADManagerVO.getAdStatus()); //廣告狀態
				pstmt.setInt(7, ADManagerVO.getAdBrowse());  //廣告瀏覽次數
				pstmt.setInt(8, ADManagerVO.getAdModify());  //最後修改人
				pstmt.execute();
				
			} catch(SQLException e){
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace(System.err);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
		}


		@Override
		public void update(ADManagerVO_original ADManagerVO) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				
			
				conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
				pstmt = conn.prepareStatement(UPDATE);
				pstmt.setInt(1, ADManagerVO.getAdNO());
				pstmt.setString(2, ADManagerVO.getAdImage()); //廣告圖片
				pstmt.setString(3, ADManagerVO.getAdLink());
				pstmt.setString(4, ADManagerVO.getAdMessage());
				pstmt.setDate(5, ADManagerVO.getAdTimeStart());
				pstmt.setDate(6, ADManagerVO.getAdTimeEnd());
				pstmt.setBoolean(7, ADManagerVO.getAdStatus()); //廣告狀態
				pstmt.setInt(8, ADManagerVO.getAdBrowse());  //廣告瀏覽次數
				pstmt.setInt(9, ADManagerVO.getAdModify());  //最後修改人
				pstmt.executeUpdate();
				
			} catch(SQLException e){
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace(System.err);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
		}



		@Override
		public void delete(int adNo) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit","sa", "P@ssw0rd");
				pstmt = conn.prepareStatement(DELETE);
				pstmt.setInt(1,adNo);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace(System.err);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
						
		}


		@Override
		public ADManagerVO_original findByPrimaryKey(Integer adNO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ADManagerVO_original adVO = null;
			ResultSet rs = null;
			try {
				con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setInt(1, adNO);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					adVO = new ADManagerVO_original();
					adVO.setAdNO(rs.getInt("adNO"));
					adVO.setAdImage(rs.getString("adImage"));
					adVO.setAdLink(rs.getString("adLink"));
					adVO.setAdMessage(rs.getString("adMessage"));
					adVO.setAdTimeStart(rs.getDate("adTimeStart"));
					adVO.setAdTimeEnd(rs.getDate("adTimeEnd"));
					adVO.setAdStatus(rs.getBoolean("adStatus"));
					adVO.setAdBrowse(rs.getInt("adBrowse"));
					adVO.setAdModify(rs.getInt("adModify"));
				}
				
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
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
					} catch (SQLException e) {
						e.printStackTrace(System.err);
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
			return adVO;
		}

		@Override
		public List<ADManagerVO_original> getAll() {
			List<ADManagerVO_original> list = new ArrayList<ADManagerVO_original>();
			ADManagerVO_original adVO = null;

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			try {
				conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
				pstmt = conn.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				
				
				while (rs.next()) {					
					adVO = new ADManagerVO_original();
					adVO.setAdNO(rs.getInt("adNO"));
					adVO.setAdImage(rs.getString("adImage"));
					adVO.setAdLink(rs.getString("adLink"));
					adVO.setAdMessage(rs.getString("adMessage"));
					adVO.setAdTimeStart(rs.getDate("adTimeStart"));
					adVO.setAdTimeEnd(rs.getDate("adTimeEnd"));
					adVO.setAdStatus(rs.getBoolean("adStatus"));
					adVO.setAdBrowse(rs.getInt("adBrowse"));
					adVO.setAdModify(rs.getInt("adModify"));
					list.add(adVO);
				}
				
								
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace(System.err);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
//			return adVO;
		}
		@Override
		public String getAllForJson() {
			List<Map> list = new ArrayList<Map>();
			ADManagerVO adVO = null;
			String jsonString = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
		try {

			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hermit", "sa", "P@ssw0rd");
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
				
			while (rs.next()) {
				Map m1 = new LinkedHashMap();
				m1.put("adNo", rs.getInt("adNo"));
				m1.put("adLink", rs.getString("adLink"));
				m1.put("adMessage",rs.getString("adMessage"));
				m1.put("adTimeStart",rs.getDate("adTimeStart"));
				m1.put("adTimeEnd",rs.getDate("adTimeEnd"));
				m1.put("adStatus",rs.getInt("adStatus"));
				m1.put("adBrowse",rs.getInt("adBrowse"));
				m1.put("adModify",rs.getInt("adModify"));
				list.add(m1);
			}
			Map m2 = new LinkedHashMap();
			m2.put("list",list);
			jsonString = JSONValue.toJSONString(m2);
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return jsonString;
	}
		

		public static void main (String args[]){
			ADManagerVO_original adVO = new ADManagerVO_original();
			ADManagerDAO_JDBC adDAO = new ADManagerDAO_JDBC();
			
			//Insert Test Start
			
			
			FileInputStream fis;
			
				try {
					fis = new FileInputStream("D://WebSite//imgs//test5.png");
				adVO.setAdImage("date:image/png;base64,1234");
				adVO.setAdLink("www.yahoo.com.tw");
				adVO.setAdMessage("哈囉大家好");
				adVO.setAdTimeStart(java.sql.Date.valueOf("2017-10-23"));
				adVO.setAdTimeEnd(java.sql.Date.valueOf("2017-10-25"));
				adVO.setAdStatus(true);
				adVO.setAdBrowse(1);
				adVO.setAdModify(30001);
				adDAO.insert(adVO);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("insert success");
		
			}
			
	////Insert Test End
	////Update Test Start

			
//			FileInputStream fis;
//			try {
//			
//			fis = new FileInputStream("D://WebSite//imgs//test2.jpg");
//			aVO.setAdNo(5002);
//			aVO.setAdImage(fis);
//			aVO.setAdLink("www.google.com");
//			aVO.setAdMessage("這是測試圖片2");
//			aVO.setAdTimeStart(java.sql.Date.valueOf("2017-10-17"));
//			aVO.setAdTimeEnd(java.sql.Date.valueOf("2017-10-25"));
//			aVO.setAdStatus(true);
//			aVO.setAdBrowse(1);
//			aVO.setAdModify(30001);
//			aDAO.update(aVO);
//			System.out.println("update success");
//			
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
			
	//Update Test End
	//Delete Test Start
			
//			System.out.println("delete start");
//			aDAO.delete(5002);
//			System.out.println("delete success");
			
	//Delete Test End		
	//Search One Test Start
			
//			System.out.println("Search One Start");
//			aVO = aDAO.findByPrimaryKey(5003);
//			System.out.println("adImage \t= " + aVO.getAdImage());
//			System.out.println("adLink \t= " + aVO.getAdLink());
//			System.out.println("adMessage \t= " + aVO.getAdMessage());
//			System.out.println("adTimeStart \t= " + aVO.getAdTimeStart());
//			System.out.println("adTimeEnd \t= " + aVO.getAdTimeEnd());
//			System.out.println("adStatus = " + aVO.getAdStatus());
//			System.out.println("adBrowse \t= " + aVO.getAdBrowse());
//			System.out.println("adModify \t= " + aVO.getAdModify());
//			System.out.println("adNo \t= " + aVO.getAdNo());
//			System.out.println("Search One success");
			
	//Search One Test End	

	//Search All Test Start
			
//			System.out.println("-----------Search All Start------------");	
//			List<ADManagerVO> list = aDAO.getAll();
//			for(int i=0;i<list.size();i++){
//				aVO = list.get(i);
//				aVO = aDAO.findByPrimaryKey(5003);
//				System.out.println("adImage \t= " + aVO.getAdImage());
//				System.out.println("adLink \t= " + aVO.getAdLink());
//				System.out.println("adMessage \t= " + aVO.getAdMessage());
//				System.out.println("adTimeStart \t= " + aVO.getAdTimeStart());
//				System.out.println("adTimeEnd \t= " + aVO.getAdTimeEnd());
//				System.out.println("adStatus = " + aVO.getAdStatus());
//				System.out.println("adBrowse \t= " + aVO.getAdBrowse());
//				System.out.println("adModify \t= " + aVO.getAdModify());
//				System.out.println("adNo \t= " + aVO.getAdNo());
//				System.out.println("Search One success");
//			}
//			System.out.println("-----------Search All success------------");	
//			
//	Search All Test End		
//		}


		
		
}
	
	
	
		
	


