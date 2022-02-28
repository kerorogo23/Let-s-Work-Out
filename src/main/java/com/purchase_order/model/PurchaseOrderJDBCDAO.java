package com.purchase_order.model;

import java.util.*;
import java.sql.*;


import com.purchase_order_details.model.PurchaseOrderDetailsJDBCDAO;
import com.purchase_order_details.model.PurchaseOrderDetailsVO;




public class PurchaseOrderJDBCDAO implements PurchaseOrderDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	String userid = "John";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO PURCHASE_ORDER  ( mem_id,po_time, po_payment, po_delivery, po_total,purchase_detail,po_status) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT po_no,mem_id,po_time,po_payment,po_delivery,po_total,purchase_detail,po_status FROM purchase_order ";
	private static final String GET_ONE_STMT = "SELECT po_no,mem_id,po_time,po_payment,po_delivery,po_total,purchase_detail,po_status FROM purchase_order where po_no = ?";
	private static final String GET_Pods_ByPo_no_STMT = "SELECT po_no,product_no,quantity,p_price FROM purchase_order_details where po_no = ? order by po_no";

	private static final String DELETE_PODs= "DELETE FROM purchase_order_details where po_no = ?";
	private static final String DELETE_PO = "DELETE FROM purchase_order where po_no = ?";
	private static final String UPDATE = "UPDATE purchase_order set mem_id = ?, po_time =? ,po_payment =? ,po_delivery =?,po_total =?,purchase_detail =?,po_status =? where po_no = ?";

	@Override
	public void insert(PurchaseOrderVO purchaseorderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,purchaseorderVO.getMem_id());
			pstmt.setTimestamp(2, purchaseorderVO.getPo_time());
			pstmt.setString(3, purchaseorderVO.getPo_payment());
			pstmt.setString(4, purchaseorderVO.getPo_delivery());
			pstmt.setInt(5, purchaseorderVO.getPo_total());
			pstmt.setString(6, purchaseorderVO.getPurchase_detail());
			pstmt.setInt(7, purchaseorderVO.getPo_status());

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
	public void update(PurchaseOrderVO purchaseorderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, purchaseorderVO.getMem_id());
			pstmt.setTimestamp(2, purchaseorderVO.getPo_time());
			pstmt.setString(3, purchaseorderVO.getPo_payment());
			pstmt.setString(4, purchaseorderVO.getPo_delivery());
			pstmt.setInt(5, purchaseorderVO.getPo_total());
			pstmt.setString(6, purchaseorderVO.getPurchase_detail());
			pstmt.setInt(7, purchaseorderVO.getPo_status());
			pstmt.setInt(8, purchaseorderVO.getPo_no());

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
		int updateCount_PODs = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			

			// 先刪除訂單明細
						pstmt = con.prepareStatement(DELETE_PODs);
						pstmt.setInt(1, po_no);
						updateCount_PODs = pstmt.executeUpdate();
						// 再刪除訂單
						pstmt = con.prepareStatement(DELETE_PO);
						pstmt.setInt(1, po_no);
						pstmt.executeUpdate();

						// 2●設定於 pstm.executeUpdate()之後
						con.commit();
						con.setAutoCommit(true);
						System.out.println("刪除訂單" + po_no + "時,共有訂單明細" + updateCount_PODs
								+ "項同時被刪除");
						
						// Handle any driver errors
					} catch (ClassNotFoundException e) {
						throw new RuntimeException("Couldn't load database driver. "
								+ e.getMessage());
						// Handle any SQL errors
					} catch (SQLException se) {
						if (con != null) {
							try {
								// 3●設定於當有exception發生時之catch區塊內
								con.rollback();
							} catch (SQLException excep) {
								throw new RuntimeException("rollback error occured. "
										+ excep.getMessage());
							}
						}
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
	public PurchaseOrderVO findByPrimaryKey(Integer po_no) {

		PurchaseOrderVO purchaseorderVO = null;
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
				// empVo �]�٬� Domain objects
				purchaseorderVO = new PurchaseOrderVO();
				
				purchaseorderVO.setPo_no(rs.getInt("po_no"));
				purchaseorderVO.setMem_id(rs.getInt("mem_id"));
				purchaseorderVO.setPo_time(rs.getTimestamp("po_time"));
				purchaseorderVO.setPo_payment(rs.getString("po_payment"));
				purchaseorderVO.setPo_delivery(rs.getString("po_delivery"));
				purchaseorderVO.setPo_total(rs.getInt("po_total"));
				purchaseorderVO.setPurchase_detail(rs.getString("purchase_detail"));
				purchaseorderVO.setPo_status(rs.getInt("po_status"));
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
		return purchaseorderVO;
	}

	@Override
	public List<PurchaseOrderVO> getAll() {
		List<PurchaseOrderVO> list = new ArrayList<PurchaseOrderVO>();
		PurchaseOrderVO purchaseorderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				purchaseorderVO = new PurchaseOrderVO();
				
				purchaseorderVO.setPo_no(rs.getInt("po_no"));
				purchaseorderVO.setMem_id(rs.getInt("mem_id"));
				purchaseorderVO.setPo_time(rs.getTimestamp("po_time"));
				purchaseorderVO.setPo_payment(rs.getString("po_payment"));
				purchaseorderVO.setPo_delivery(rs.getString("po_delivery"));
				purchaseorderVO.setPo_total(rs.getInt("po_total"));
				purchaseorderVO.setPurchase_detail(rs.getString("purchase_detail"));
				purchaseorderVO.setPo_status(rs.getInt("po_status"));

				list.add(purchaseorderVO); // Store the row in the list
			}

			// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
	public Set<PurchaseOrderDetailsVO> getPodsByPo_no(Integer po_no) {
		Set<PurchaseOrderDetailsVO> set = new HashSet<PurchaseOrderDetailsVO>();
		PurchaseOrderDetailsVO purchaseorderdetailsVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Pods_ByPo_no_STMT);
			pstmt.setInt(1, po_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				purchaseorderdetailsVO = new PurchaseOrderDetailsVO();
				purchaseorderdetailsVO.setPo_no(rs.getInt("po_no"));
				purchaseorderdetailsVO.setProduct_no(rs.getInt("product_no"));
				purchaseorderdetailsVO.setQuantity(rs.getInt("quantity"));
				purchaseorderdetailsVO.setP_price(rs.getInt("p_price"));
				set.add(purchaseorderdetailsVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
//
	@Override
	public void insertWithPods(PurchaseOrderVO purchaseorderVO, List <PurchaseOrderDetailsVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1���]�w�� pstm.executeUpdate()���e
    		con.setAutoCommit(false);
			
    		// ���s�W����
			String cols[] = {"Po_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			
			pstmt.setInt(1, purchaseorderVO.getMem_id());
			pstmt.setTimestamp(2, purchaseorderVO.getPo_time());
			pstmt.setString(3, purchaseorderVO.getPo_payment());
			pstmt.setString(4, purchaseorderVO.getPo_delivery());
			pstmt.setInt(5, purchaseorderVO.getPo_total());
			pstmt.setString(6, purchaseorderVO.getPurchase_detail());
			pstmt.setInt(7, purchaseorderVO.getPo_status());

			pstmt.executeUpdate();
//Statement stmt=	con.createStatement();
//stmt.executeUpdate("set auto_increment_offset=10;");    //�ۼW�D��-��l��
//stmt.executeUpdate("set auto_increment_increment=1;"); //�ۼW�D��-���W
//			pstmt.executeUpdate();
			//�����������ۼW�D���
			String next_po_no = null;
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_po_no = rs.getString(1);
//				System.out.println("�ۼW�D���= " + next_deptno +"(��s�W���\�������s��)");
			} else {
//				System.out.println("�����o�ۼW�D���");
			}
			rs.close();
			// �A�P�ɷs�W���u
			PurchaseOrderDetailsJDBCDAO dao = new PurchaseOrderDetailsJDBCDAO();
//			System.out.println("list.size()-A="+list.size());
			for (PurchaseOrderDetailsVO aPurchaseOrderDetails : list) {
				System.out.println("==========");
				System.out.println("OrderNo: " + next_po_no);
				System.out.println("ProductNo: " + aPurchaseOrderDetails.getProduct_no());
				System.out.println("==========");
				aPurchaseOrderDetails.setPo_no(new Integer(next_po_no)) ;
				dao.insert2(aPurchaseOrderDetails,con);
			}

			// 2���]�w�� pstm.executeUpdate()����
			con.commit();
			con.setAutoCommit(true);
//			System.out.println("list.size()-B="+list.size());
//			System.out.println("�s�W�����s��" + next_deptno + "��,�@�����u" + list.size()
//					+ "�H�P�ɳQ�s�W");
			
			// Handle any driver errors
			} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3���]�w���exception�o�ͮɤ�catch�϶���
//					System.err.print("Transaction is being ");
//					System.err.println("rolled back-��-dept");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
//
	public static void main(String[] args) {

		PurchaseOrderJDBCDAO dao = new PurchaseOrderJDBCDAO();
		
		PurchaseOrderVO purchaseorderVO = new PurchaseOrderVO();
	

		
		purchaseorderVO.setMem_id(1010);
		purchaseorderVO.setPo_time(new java.sql.Timestamp(System.currentTimeMillis()));
		purchaseorderVO.setPo_payment("貨到付款");
		purchaseorderVO.setPo_delivery("超商取貨");
		purchaseorderVO.setPo_total(6800);
		purchaseorderVO.setPurchase_detail("7-11板中門市3");
		purchaseorderVO.setPo_status(1);
		
		List<PurchaseOrderDetailsVO> testList = new ArrayList<PurchaseOrderDetailsVO>(); // �ǳƸm�J���u�ƤH
		PurchaseOrderDetailsVO purchaseorderdetailsXX = new PurchaseOrderDetailsVO();   // ���uPOJO1
		
//		purchaseorderdetailsXX.setPo_no(0);
		purchaseorderdetailsXX.setProduct_no(4004);
		purchaseorderdetailsXX.setQuantity(2);
		purchaseorderdetailsXX.setP_price(2800);
//
		PurchaseOrderDetailsVO purchaseorderdetailsYY = new PurchaseOrderDetailsVO();   // ���uPOJO1
//		purchaseorderdetailsYY.setPo_no(0);
		purchaseorderdetailsYY.setProduct_no(4005);
		purchaseorderdetailsYY.setQuantity(5);
		purchaseorderdetailsYY.setP_price(4000);
//
		testList.add(purchaseorderdetailsXX);
		testList.add(purchaseorderdetailsYY);
//		
		dao.insertWithPods(purchaseorderVO , testList);
////
//		// 新增
//		PurchaseOrderVO purchaseorderVO1 = new PurchaseOrderVO();
//		
//		purchaseorderVO1.setMem_id(1005);
//		purchaseorderVO1.setPo_time(new java.sql.Timestamp(System.currentTimeMillis()));
//		purchaseorderVO1.setPo_payment("貨到付款");
//		purchaseorderVO1.setPo_delivery("宅配");
//		purchaseorderVO1.setPo_total(6800);
//		purchaseorderVO1.setPurchase_detail("新北市板橋區");
//		purchaseorderVO1.setPo_status(1);
//
//		dao.insert(purchaseorderVO1);
//////
////	// 修改
//		PurchaseOrderVO purchaseorderVO2 = new PurchaseOrderVO();
//		purchaseorderVO2.setMem_id(1009);
//		purchaseorderVO2.setPo_time(java.sql.Date.valueOf("2012-12-04"));
//		purchaseorderVO2.setPo_payment("貨到付款1");
//		purchaseorderVO2.setPo_delivery("宅配1");
//		purchaseorderVO2.setPo_total(60567);
//		purchaseorderVO2.setPurchase_detail("7-11板中門市");
//		purchaseorderVO2.setPo_status(3);
//		purchaseorderVO2.setPo_no(225);
//		dao.update(purchaseorderVO2);
////
//		


////
//		// 刪除
//	dao.delete(12);
////	
//  
////	// 
//
//
//		//查詢		PurchaseOrderVO purchaseorderVO3 = dao.findByPrimaryKey(5);
////		System.out.print(purchaseorderVO3.getPo_no() + ",");
////		System.out.print(purchaseorderVO3.getMem_id() + ",");
////		System.out.print(purchaseorderVO3.getPo_time() + ",");
////		System.out.print(purchaseorderVO3.getPo_payment() + ",");
////		System.out.print(purchaseorderVO3.getPo_delivery() + ",");
////		System.out.print(purchaseorderVO3.getPo_total() + ",");
////		System.out.print(purchaseorderVO3.getPurchase_detail() + ",");
////		System.out.print(purchaseorderVO3.getPo_status());
////
////		System.out.println("---------------------");
		
		//查詢訂單
//		List<PurchaseOrderVO> list = dao.getAll();
//		for (PurchaseOrderVO aPurchaseOrder : list) {
//			
//			System.out.print(aPurchaseOrder.getPo_no() + ",");
//			System.out.print(aPurchaseOrder.getMem_id() + ",");
//			System.out.print(aPurchaseOrder.getPo_time() + ",");
//			System.out.print(aPurchaseOrder.getPo_payment() + ",");
//			System.out.print(aPurchaseOrder.getPo_delivery() + ",");
//			System.out.print(aPurchaseOrder.getPo_total() + ",");
//			System.out.print(aPurchaseOrder.getPurchase_detail() + ",");
//			System.out.print(aPurchaseOrder.getPo_status());
//
//			System.out.println();
//		}
		
//		Set<PurchaseOrderDetailsVO> set = dao.getPodsByPo_no(223);
//				for (PurchaseOrderDetailsVO aPurchaseOrderDetails : set) {
//					System.out.print(aPurchaseOrderDetails.getPo_no() + ",");
//					System.out.print(aPurchaseOrderDetails.getProduct_no() + ",");
//					System.out.print(aPurchaseOrderDetails.getQuantity() + ",");
//					System.out.print(aPurchaseOrderDetails.getP_price());
//					System.out.println();
//				}
				}
	}




