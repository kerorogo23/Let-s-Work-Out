package com.purchase_order_details.model;

import java.util.*;
import java.sql.*;

public class PurchaseOrderDetailsJDBCDAO implements PurchaseOrderDetailsDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	String userid = "John";
	String passwd = "123456";
		private static final String INSERT_STMT =
				"INSERT INTO PURCHASE_ORDER_DETAILS (po_no,product_no,quantity,p_price) VALUES (?,?,?,?)";
		private static final String GET_ALL_STMT = 
				"SELECT po_no,product_no,quantity,p_price FROM purchase_order_details order by po_no";
		private static final String GET_ONE_STMT =
				"SELECT po_no,product_no,quantity,p_price FROM purchase_order_details where po_no = ?";
		private static final String DELETE =
				"DELETE FROM purchase_order_details where po_no = ? ";
		private static final String UPDATE= 
				"UPDATE purchase_order_details set  quantity =? ,p_price =?  where po_no = ? and product_no = ?";
		private static final String GET_POD_By_PN="select * from purchase_order_details where po_no = ?"
		;
		
		
		
		@Override
		public void insert(PurchaseOrderDetailsVO purchaseorderdetailsVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setInt(1, purchaseorderdetailsVO.getPo_no());
				pstmt.setInt(2, purchaseorderdetailsVO.getProduct_no());
				pstmt.setInt(3, purchaseorderdetailsVO.getQuantity());
				pstmt.setInt(4, purchaseorderdetailsVO.getP_price());
				

	
				pstmt.executeUpdate();


				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public void update(PurchaseOrderDetailsVO purchaseorderdetailsVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);


				
				pstmt.setInt(1, purchaseorderdetailsVO.getQuantity());
				pstmt.setInt(2, purchaseorderdetailsVO.getP_price());
				pstmt.setInt(3, purchaseorderdetailsVO.getPo_no());
				pstmt.setInt(4, purchaseorderdetailsVO.getProduct_no());
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public void delete(Integer po_no) {
			

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, po_no);

				pstmt.executeUpdate();
				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public PurchaseOrderDetailsVO findByPrimaryKey(Integer po_no) {

			PurchaseOrderDetailsVO purchaseorderdetailsVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);


				pstmt.setInt(1, po_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// deptVO �]�٬� Domain objects
					purchaseorderdetailsVO = new PurchaseOrderDetailsVO();
					purchaseorderdetailsVO.setPo_no(rs.getInt("po_no"));
					purchaseorderdetailsVO.setProduct_no(rs.getInt("product_no"));
					purchaseorderdetailsVO.setQuantity(rs.getInt("quantity"));
					purchaseorderdetailsVO.setP_price(rs.getInt("p_price"));
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return purchaseorderdetailsVO;
		}

		@Override
		public List<PurchaseOrderDetailsVO> getAll() {
			List<PurchaseOrderDetailsVO> list = new ArrayList<PurchaseOrderDetailsVO>();
			PurchaseOrderDetailsVO purchaseorderdetailsVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					purchaseorderdetailsVO = new PurchaseOrderDetailsVO();
					purchaseorderdetailsVO.setPo_no(rs.getInt("po_no"));
					purchaseorderdetailsVO.setProduct_no(rs.getInt("product_no"));
					purchaseorderdetailsVO.setQuantity(rs.getInt("quantity"));
					purchaseorderdetailsVO.setP_price(rs.getInt("p_price"));
					
					list.add(purchaseorderdetailsVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		
		@Override
		public List<PurchaseOrderDetailsVO> getPODByPN(Integer po_no) {
			List<PurchaseOrderDetailsVO> list = new ArrayList<PurchaseOrderDetailsVO>();
			PurchaseOrderDetailsVO purchaseorderdetailsVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_POD_By_PN);
				pstmt.setInt(1, po_no);
				
				rs = pstmt.executeQuery();
			

				while (rs.next()) {
					purchaseorderdetailsVO = new PurchaseOrderDetailsVO();
					purchaseorderdetailsVO.setPo_no(rs.getInt("po_no"));
					purchaseorderdetailsVO.setProduct_no(rs.getInt("product_no"));
					purchaseorderdetailsVO.setQuantity(rs.getInt("quantity"));
					purchaseorderdetailsVO.setP_price(rs.getInt("p_price"));
					
					list.add(purchaseorderdetailsVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
	
	
		@Override
		public void insert2(PurchaseOrderDetailsVO purchaseorderdetailsVO, Connection con) {
			PreparedStatement pstmt = null;
		
			try {
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setInt(1, purchaseorderdetailsVO.getPo_no());
				pstmt.setInt(2, purchaseorderdetailsVO.getProduct_no());
				pstmt.setInt(3, purchaseorderdetailsVO.getQuantity());
				pstmt.setInt(4, purchaseorderdetailsVO.getP_price());
				
				
//			Statement stmt=	con.createStatement();
		
			
//			stmt.executeUpdate("set auto_increment_offset=11;"); //�ۼW�D��-��l��
//			stmt.executeUpdate("set auto_increment_increment=1;");   //�ۼW�D��-���W
						pstmt.executeUpdate();

						// Handle any SQL errors
					} catch (SQLException se) {
						if (con != null) {
							try {
								// 3���]�w���exception�o�ͮɤ�catch�϶���
								System.err.print("Transaction is being ");
								System.err.println("rolled back-��-purchaseorderdetails");
								con.rollback();
							} catch (SQLException excep) {
								throw new RuntimeException("rollback error occured. "
										+ excep.getMessage());
							}
						}
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());
						// Clean up JDBC resources
					} finally {
						if (pstmt != null) {
							try {
								pstmt.close();
							} catch (SQLException se) {
								se.printStackTrace(System.err);
							}
						}
					}

		}


		
		public static void main(String[] args) {

			PurchaseOrderDetailsJDBCDAO dao = new PurchaseOrderDetailsJDBCDAO();
//
			// �s�W
//			PurchaseOrderDetailsVO purchaseorderdetailsVO1 = new PurchaseOrderDetailsVO();
//			
//			purchaseorderdetailsVO1.setPo_no(213);
//			purchaseorderdetailsVO1.setProduct_no(4010);
//			purchaseorderdetailsVO1.setQuantity(2);
//			purchaseorderdetailsVO1.setP_price(1600);
//		
//			dao.insert(purchaseorderdetailsVO1);
////	//
////			// �ק�
//			PurchaseOrderDetailsVO purchaseorderdetailsVO2 = new PurchaseOrderDetailsVO();
//			purchaseorderdetailsVO2.setPo_no(9);
//			purchaseorderdetailsVO2.setProduct_no(4007);
//			purchaseorderdetailsVO2.setQuantity(4);
//			purchaseorderdetailsVO2.setP_price(7233);
//		
//			dao.update(purchaseorderdetailsVO2);
////	//
////			// �R��
//			dao.delete(5);
//	//
////			// �d��
////			PurchaseOrderDetailsVO purchaseorderdetailsVO3 = dao.findByPrimaryKey(7001);
////			System.out.print(purchaseorderdetailsVO3.getPo_no() + ",");
////			System.out.print(purchaseorderdetailsVO3.getProduct_no() + ",");
////			System.out.print(purchaseorderdetailsVO3.getQuantity() + ",");
////			System.out.print(purchaseorderdetailsVO3.getP_price());
////			
////			System.out.println("---------------------");
//
//			// �d��
			List<PurchaseOrderDetailsVO> list = dao.getPODByPN(12);
			for (PurchaseOrderDetailsVO aPurchaseOrderDetails : list) {
				System.out.print(aPurchaseOrderDetails.getPo_no() + ",");
				System.out.print(aPurchaseOrderDetails.getProduct_no() + ",");
				System.out.print(aPurchaseOrderDetails.getQuantity() + ",");
				System.out.print(aPurchaseOrderDetails.getP_price());
				
				System.out.println();
			}
		}
	}