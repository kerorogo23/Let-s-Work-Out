package com.classtype.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.classtype.model.*;
import com.groupclass.model.*;

public class ClassTypeServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("classTypeNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入類別編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classtype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer classTypeNo = null;
				try {
					classTypeNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("類別編號格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classtype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				ClassTypeVO classTypeVO = classTypeSvc.getOneClassType(classTypeNo);
				if (classTypeVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classtype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("classTypeVO", classTypeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/classtype/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/classtype/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer classTypeNo = new Integer(req.getParameter("classTypeNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				ClassTypeVO classTypeVO = classTypeSvc.getOneClassType(classTypeNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("classTypeVO", classTypeVO);
				String url = "/back-end/classtype/update_classType_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/classtype/listAllClassType.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.print(requestURL);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer classTypeNo = new Integer(req.getParameter("classTypeNo"));

				String classTypeName = req.getParameter("classTypeName").trim();
				if (classTypeName == null || classTypeName.trim().length() == 0) {
					errorMsgs.add("類別名稱請勿空白");
				}

				ClassTypeVO classTypeVO = new ClassTypeVO();
				classTypeVO.setClassTypeNo(classTypeNo);
				classTypeVO.setClassTypeName(classTypeName);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("classTypeVO", classTypeVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/classtype/update_classType_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				classTypeVO = classTypeSvc.updateClassType(classTypeNo, classTypeName);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("classTypeVO", classTypeVO);
//				String url = "/back-end/classtype/.jsp";
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/classtype/update_classType_input.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String classTypeName = req.getParameter("classTypeName");
				if (classTypeName == null || classTypeName.trim().length() == 0) {
					errorMsgs.add("類別請勿空白");
				}

				ClassTypeVO classTypeVO = new ClassTypeVO();
				classTypeVO.setClassTypeName(classTypeName);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("classTypeVO", classTypeVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/classtype/addClassType.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				classTypeVO = classTypeSvc.addClassType(classTypeName);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/classtype/listAllClassType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/classtype/addClassType.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer classTypeNo = new Integer(req.getParameter("classTypeNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				classTypeSvc.deleteClassType(classTypeNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/classtype/listAllClassType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/classtype/listAllClassType.jsp");
				failureView.forward(req, res);
			}
		}

		if ("listGroupClasses_ByClassTypeNo_A".equals(action) || "listGroupClasses_ByClassTypeNo_B".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer classTypeNo = new Integer(req.getParameter("classTypeNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				ClassTypeService classTypetSvc = new ClassTypeService();
				Set<GroupClassVO> set = classTypetSvc.getGroupClassesByClassTypeNo(classTypeNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listGroupClasses_ByClassTypeNo", set); // 資料庫取出的set物件,存入request

				String url = null;
				if ("listGroupClasses_ByClassTypeNo_A".equals(action))
					url = "/back-end/classtype/listGroupClasses_ByClassTypeNo.jsp"; // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listGroupClasses_ByClassTypeNo_B".equals(action))
					url = "/back-end/classtype/listAllClassType.jsp"; // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		if ("delete_ClassType".equals(action)) { // 來自/dept/listAllDept.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer classTypeNo = new Integer(req.getParameter("classTypeNo"));
				
				/***************************2.開始刪除資料***************************************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				classTypeSvc.deleteClassType(classTypeNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/classtype/listAllClassType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/classtype/listAllClassType.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}

}
