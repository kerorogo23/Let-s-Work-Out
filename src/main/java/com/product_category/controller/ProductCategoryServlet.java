package com.product_category.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.product_category.model.*;
import com.product.model.*;

public class ProductCategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		    // 來自select_page.jsp的請求                                  // 來自 dept/listAllDept.jsp的請求
		if ("listProducts_ByPC_A".equals(action) || "listProducts_ByPC_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer p_category_no = new Integer(req.getParameter("p_category_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ProductCategoryService productcategorySvc = new ProductCategoryService();
				Set<ProductVO> set = productcategorySvc.getProductsByPC(p_category_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listProducts_ByPC", set);    // 資料庫取出的set物件,存入request

				String url = null;
				if ("listProducts_ByPC_A".equals(action))
					url = "/back-end/product_category/listProducts_ByPC.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listProducts_ByPC_B".equals(action))
					url = "/back-end/product_category/listAllPC.jsp";              // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		if ("delete_PC".equals(action)) { // 來自/dept/listAllDept.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer p_category_no = new Integer(req.getParameter("p_category_no"));
				
				/***************************2.開始刪除資料***************************************/
				ProductCategoryService productcategorySvc = new ProductCategoryService();
				productcategorySvc.deletePC(p_category_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/product_category/listAllPC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_category/listAllPC.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update_PC".equals(action)) { // 來自/dept/listAllDept.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer p_category_no = new Integer(req.getParameter("p_category_no"));
				
				/***************************2.開始刪除資料***************************************/
				ProductCategoryService productcategorySvc = new ProductCategoryService();
				ProductCategoryVO productcategoryVO = productcategorySvc.getOnePC(p_category_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				req.setAttribute("productcategoryVO",productcategoryVO);
				String url = "/back-end/product_category/update_productcategory_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_category/listAllPC.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
