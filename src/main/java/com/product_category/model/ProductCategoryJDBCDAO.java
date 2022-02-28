package com.product_category.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductVO;

public class ProductCategoryJDBCDAO implements ProductCategoryDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
//	String userid = "John";
//	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO PRODUCT_CATEGORY (p_category_name) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT p_category_no,p_category_name FROM product_category order by p_category_no";
	private static final String GET_ONE_STMT = "SELECT p_category_no,p_category_name FROM product_category where p_category_no = ?";
	private static final String GET_Products_ByPC_no_STMT = "SELECT product_no,p_category_no,p_price,p_status,p_name,p_detail,p_upload_time FROM product where p_category_no = ? order by product_no";

	private static final String DELETE_PRODUCT = "DELETE FROM product where p_category_no = ?";
	private static final String DELETE_PC = "DELETE FROM product_category where p_category_no = ?";
	private static final String UPDATE = "UPDATE product_category set p_category_name=? where p_category_no = ?";

	@Override
	public void insert(ProductCategoryVO productcategoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productcategoryVO.getP_category_name());
//			pstmt.executeUpdate("set auto_increment_offset=10;");
//			pstmt.executeUpdate("set auto_increment_increment=10;");
			pstmt.executeUpdate();

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
	public void update(ProductCategoryVO productcategoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productcategoryVO.getP_category_name());
			pstmt.setInt(2, productcategoryVO.getP_category_no());
			pstmt.executeUpdate();

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
	public void delete(Integer p_category_no) {
		int updateCount_PRODUCT = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

		
			con = ds.getConnection();

			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE_PRODUCT);
			pstmt.setInt(1, p_category_no);
			updateCount_PRODUCT = pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(DELETE_PC);
			pstmt.setInt(1, p_category_no);
			
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除商品類別編號" + p_category_no + "時,共有商品" + updateCount_PRODUCT
					+ "項同時被刪除");

			
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
	public ProductCategoryVO findByPrimaryKey(Integer p_category_no) {

		ProductCategoryVO productcategoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, p_category_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				productcategoryVO = new ProductCategoryVO();
				productcategoryVO.setP_category_no(rs.getInt("p_category_no"));
				productcategoryVO.setP_category_name(rs.getString("p_category_name"));

			}

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
					return productcategoryVO;
				}

	@Override
	public List<ProductCategoryVO> getAll() {
		List<ProductCategoryVO> list = new ArrayList<ProductCategoryVO>();
		ProductCategoryVO productcategoryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				productcategoryVO = new ProductCategoryVO();
				productcategoryVO.setP_category_no(rs.getInt("p_category_no"));
				productcategoryVO.setP_category_name(rs.getString("p_category_name"));

				list.add(productcategoryVO); // Store the row in the list
			}

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
	public Set<ProductVO> getProductsByPC(Integer p_category_no) {
		Set<ProductVO> set = new LinkedHashSet<ProductVO>();
		ProductVO productVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Products_ByPC_no_STMT);
			pstmt.setInt(1, p_category_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_no(rs.getInt("product_no"));
				productVO.setP_category_no(rs.getInt("p_category_no"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_price(rs.getInt("p_price"));
				productVO.setP_detail(rs.getString("p_detail"));
				productVO.setP_status(rs.getInt("p_status"));
				productVO.setP_upload_time(rs.getDate("p_upload_time"));
				set.add(productVO); // Store the row in the vector
			}
	
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
			

	public static void main(String[] args) {

		ProductCategoryJDBCDAO dao = new ProductCategoryJDBCDAO();

//		 新增
//		ProductCategoryVO productcategoryVO1 = new ProductCategoryVO();
//		productcategoryVO1.setP_category_name("製造部");
//		
//		dao.insert( productcategoryVO1);

		// 修改
//		ProductCategoryVO  productcategoryVO2 = new ProductCategoryVO();
//		 productcategoryVO2.setP_category_no(10);
//		 productcategoryVO2.setP_category_name("財務部2");
//		
//		dao.update(productcategoryVO2);

		// 刪除
//		dao.delete(20);

		// 查詢
//		ProductCategoryVO productcategoryVO3 = dao.findByPrimaryKey(2);
//		System.out.print( productcategoryVO3.getP_category_no() + ",");
//		System.out.print( productcategoryVO3.getP_category_name() );
//	
//		System.out.println("---------------------");

		// 查詢部門
		List<ProductCategoryVO> list = dao.getAll();
		for (ProductCategoryVO aPC : list) {
			System.out.print(aPC.getP_category_no() + ",");
			System.out.print(aPC.getP_category_name());
			
			System.out.println();
		}
//		
		// 查詢某部門的員工
//		Set<ProductVO> set = dao.getProductsByPC(2);
//		for (ProductVO aP : set) {
//			System.out.print(aP.getProduct_no() + ",");
//			System.out.print(aP.getP_category_no() + ",");
//			System.out.print(aP.getP_name() + ",");
//			System.out.print(aP.getP_price() + ",");
//			System.out.print(aP.getP_detail() + ",");
//			System.out.print(aP.getP_status() + ",");
//			System.out.print(aP.getP_upload_time());
//			System.out.println();
//		}
	}
}
