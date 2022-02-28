package com.product.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.product.model.*;
import com.product_category.model.ProductCategoryService;

import com.product_photo.model.ProductPhotoService;
import com.product_photo.model.ProductPhotoVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("product_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				Integer product_no = null;
				try {
					product_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(product_no);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;//
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("productVO", productVO); //  資料庫取出的empVO物件,存入req
				String url = "/back-end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer product_no = Integer.valueOf(req.getParameter("product_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(product_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productVO", productVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/product/update_product_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_product_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer product_no = new Integer(req.getParameter("product_no").trim());

				Integer p_category_no = new Integer(req.getParameter("p_category_no").trim());

				String p_name = req.getParameter("p_name");
				String p_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (p_name == null || p_name.trim().length() == 0) {
					errorMsgs.add("商品名稱:請勿空白");
				} else if (!p_name.trim().matches(p_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("商品姓名:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				Integer p_price = null;
				try {
					p_price = new Integer(req.getParameter("p_price").trim());
					if(p_price <= 0) {errorMsgs.add("商品價格: 請勿為負或零");};
				}catch(NumberFormatException e){
					p_price = 0;
					errorMsgs.add("商品價格: 請勿為空白");
				}
				
				
				
				
				String p_detail = req.getParameter("p_detail").trim();
				if (p_detail == null || p_detail.trim().length() == 0) {
					errorMsgs.add("商品詳情:請勿空白");
				}
				Integer p_status = new Integer(req.getParameter("p_status").trim());
//				if(p_status != 0  p_status != 1) {
//					errorMsgs.add("商品狀態請勿空白");
//				}
				

				java.sql.Date p_upload_time = null;
				try {
					p_upload_time = java.sql.Date.valueOf(req.getParameter("p_upload_time").trim());
				} catch (IllegalArgumentException e) {
					p_upload_time = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				ProductVO productVO = new ProductVO();
				productVO.setProduct_no(product_no);
				productVO.setP_category_no(p_category_no);
				productVO.setP_name(p_name);
				productVO.setP_price(p_price);
				productVO.setP_detail(p_detail);
				productVO.setP_status(p_status);
				productVO.setP_upload_time(p_upload_time);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/update_product_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.開始修改資料 *****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProduct(product_no, p_category_no, p_name, p_price, p_detail, p_status,
						p_upload_time);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("productVO", productVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.
						getRequestDispatcher("/back-end/product/update_product_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				Integer p_category_no = new Integer(req.getParameter("p_category_no").trim());

				String p_name = req.getParameter("p_name");
				String p_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (p_name == null || p_name.trim().length() == 0) {
					errorMsgs.add("商品名稱:請勿空白");
				} else if (!p_name.trim().matches(p_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("商品姓名:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				Integer p_price = null;
				try {
					p_price = new Integer(req.getParameter("p_price").trim());
					if(p_price <= 0) {errorMsgs.add("商品價格: 請勿為負或零");};
				}catch(NumberFormatException e){
					p_price = 0;
					errorMsgs.add("商品價格: 請勿為空白");
				}
				
				
				
				
				String p_detail = req.getParameter("p_detail").trim();
				if (p_detail == null || p_detail.trim().length() == 0) {
					errorMsgs.add("商品詳情:請勿空白");
				}
				Integer p_status = new Integer(req.getParameter("p_status").trim());
//				if(p_status != 0  p_status != 1) {
//					errorMsgs.add("商品狀態請勿空白");
//				}
				

				java.sql.Date p_upload_time = null;
				try {
					p_upload_time = java.sql.Date.valueOf(req.getParameter("p_upload_time").trim());
				} catch (IllegalArgumentException e) {
					p_upload_time = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				ProductVO productVO = new ProductVO();
				productVO.setP_category_no(p_category_no);
				productVO.setP_name(p_name);
				productVO.setP_price(p_price);
				productVO.setP_detail(p_detail);
				productVO.setP_status(p_status);
				productVO.setP_upload_time(p_upload_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				ProductService productSvc = new ProductService();
				Integer newProduct_no = productSvc.addProduct(productVO);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllProduct.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer product_no = new Integer(req.getParameter("product_no"));

				/*************************** 2.�}�l�R����� ***************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(product_no);
				productSvc.deleteProduct(product_no);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				ProductCategoryService productcategorySvc = new ProductCategoryService();

				if(requestURL.equals("/back-end/product_category/listProducts_ByPC.jsp") || requestURL.equals("/back-end/product_category/listAllPC.jsp"))
					req.setAttribute("listProducts_ByPC", productcategorySvc.getProductsByPC(productVO.getP_category_no()));
				
				String url =requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.
						getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
			
			if ("searchProduct_For_Display".equals(action)) { // From itemManage.jsp Request
				String errorMsgs = null;
				req.setAttribute("errorMsgs", errorMsgs);
				List <ProductVO> list =new ArrayList<ProductVO>();
					try {
						/***************************1.Accept request parameters - input format error handle**********************/
						String productKeyWord = req.getParameter("productKeyword");
						
						if (productKeyWord == null || (productKeyWord.trim()).length() == 0) {
							String url = "/back-end/product/listAllProduct.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // Successful forward searchResult.jsp
							successView.forward(req, res);
							return;
						}
					
						
						/***************************2.Beginning inquire**********************/
						ProductService productService = new ProductService();
						list=productService.searchProduct(productKeyWord);

						/***************************3.Inquire finish ready forward(Send the Success view)*************/
						HttpSession session=req.getSession();
						session.setAttribute("list", list); 
						String url = "/back-end/product/listOneProduct.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // Successful forward searchResult.jsp
						successView.forward(req, res);

						/***************************Other error handle*************************************/
					} catch (Exception e) {
						errorMsgs="無法取得資料:" + e.getMessage();
						RequestDispatcher failureView = req
								.getRequestDispatcher("/select_page.jsp");
						failureView.forward(req, res);
					}
				
			}
			
			if ("front-end-productSearch".equals(action)) { // From shop.jsp Request
				String errorMsgs = null;
				req.setAttribute("errorMsgs", errorMsgs);
				List <ProductVO> frontEndProductSearchList =new ArrayList<ProductVO>();
				try {
					/***************************1.Accept request parameters - input format error handle**********************/
//					String productKeyword = req.getParameter("productKeyword");
					ProductService ps = new ProductService();
					frontEndProductSearchList = ps.getAll();
					System.out.print(frontEndProductSearchList);

					/***************************2.Beginning inquire**********************/
//					ProductService productService = new ProductService();
//					frontEndProductSearchList=productService.frontEndSearchProduct(productKeyword);

					/***************************3.Inquire finish ready forward(Send the Success view)*************/
					HttpSession session=req.getSession();
					session.setAttribute("frontEndProductSearchList", frontEndProductSearchList); 
					String url = "/front-end/product/shop.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // Successful forward searchResult.jsp
					successView.forward(req, res);

					/***************************Other error handle*************************************/
				} catch (Exception e) {
					errorMsgs="無法取得資料:" + e.getMessage();
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/product/shop.jsp");
					failureView.forward(req, res);
				}
				
			}
			
			
			if ("shoppingCarUpdate".equals(action)) { // From shoppingCar.jsp Request
				String actionForProduct = req.getParameter("actionForProduct");
				Integer product_no =Integer.valueOf(req.getParameter("product_no"));
				
				HttpSession session = req.getSession();
				Map<Integer,Integer> shoppingList = (Map<Integer,Integer>)session.getAttribute("shoppingList");
				Integer count= (Integer)session.getAttribute("count");
				if("plus".equals(actionForProduct)) {
					shoppingList.put(product_no, shoppingList.get(product_no)+1);
					session.setAttribute("count", count+1);
				}
				
				if("mius".equals(actionForProduct)) {
					if(shoppingList.get(product_no)-1<0) {
						shoppingList.put(product_no, 0);
					}
					else {
						shoppingList.put(product_no, shoppingList.get(product_no)-1);
						session.setAttribute("count", count-1);
					}
				}
				
				if("delete".equals(actionForProduct)) {
					session.setAttribute("count", count-shoppingList.get(product_no));
					shoppingList.put(product_no, 0);
				}
				System.out.println(shoppingList);
				String url = "/front-end/product/shoppingCar.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // Successful forward listOneEmp.jsp
				successView.forward(req, res);
				
				
			}
		}
	}
