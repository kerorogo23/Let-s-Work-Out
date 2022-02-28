package com.grouporder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.classtype.model.ClassTypeService;
import com.grouphour.model.*;
import com.grouporder.model.*;

public class GroupOrderServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("groupOrderNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				
				Integer groupOrderNo = null;
				try {
					groupOrderNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/grouporder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				GroupOrderService groupOrderSvc = new GroupOrderService();
				GroupOrderVO groupOrderVO = groupOrderSvc.getOneGroupOrder(groupOrderNo);
				if (groupOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/grouporder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("groupOrderVO", groupOrderVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/grouporder/listOneGroupOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/grouporder/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.print("我是縣");
//			try {
				/***************************1.接收請求參數****************************************/
				Integer groupOrderNo = new Integer(req.getParameter("groupOrderNo"));
//				System.out.print(groupOrderNo);	
				/***************************2.開始查詢資料****************************************/
				GroupOrderService groupOrderSvc = new GroupOrderService();
				GroupOrderVO groupOrderVO = groupOrderSvc.getOneGroupOrder(groupOrderNo);
//				System.out.print(groupOrderVO);	
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("groupOrderVO", groupOrderVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/grouporder/update_groupOrder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
				
//			}catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/grouporder/listAllGroupOrder.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if ("update".equals(action)) { 
			
			 List<String> errorMsgs = new LinkedList<String>();
			 req.setAttribute("errorMsgs", errorMsgs);
			 String requestURL = req.getParameter("requestURL");
			 System.out.print("111");
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer groupOrderNo = new Integer(req.getParameter("groupOrderNo").trim());
				Integer memId = new Integer(req.getParameter("memId").trim());
				Integer groupTimeNo = new Integer(req.getParameter("groupTimeNo").trim());
				Timestamp groupOrderTime = java.sql.Timestamp.valueOf(req.getParameter("groupOrderTime").trim());
				Integer groupOrderStatus = new Integer(req.getParameter("groupOrderStatus"));
				
				GroupOrderVO groupOrderVO = new GroupOrderVO();
				groupOrderVO.setGroupOrderNo(groupOrderNo);
				groupOrderVO.setMemId(memId);;
				groupOrderVO.setGroupTimeNo(groupTimeNo);
				groupOrderVO.setGroupOrderTime(groupOrderTime);
				groupOrderVO.setGroupOrderStatus(groupOrderStatus);
	
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groupOrderVO", groupOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/grouporder/update_groupOrder_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				GroupOrderService groupOrderSvc = new GroupOrderService();
				groupOrderVO = groupOrderSvc.updateGroupOrder(groupOrderNo, memId, groupTimeNo, groupOrderTime, groupOrderStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				GroupHourService groupHourSvc = new GroupHourService();
				req.setAttribute("groupOrderVO", groupOrderVO);
				if(requestURL.equals("/back-end/grouphour/listGroupOrders_ByGroupTimeNo.jsp") || requestURL.equals("/back-end/classtype/listAllClassType.jsp"))
					req.setAttribute("listGroupOrders_ByGroupTimeNo",groupHourSvc.getGroupOrdersByGroupTimeNo(groupTimeNo));
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/grouporder/update_groupOrder_input.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer memId = null;
				try {
					memId = new Integer(req.getParameter("memId").trim());
				} catch (NumberFormatException e) {
					memId = 0;
					errorMsgs.add("會員編號請填數字.");
				}

				Integer groupTimeNo = null;
				try {
					groupTimeNo = new Integer(req.getParameter("groupTimeNo").trim());
				} catch (NumberFormatException e) {
					groupTimeNo = 0;
					errorMsgs.add("時間編號請填數字.");
				}
				Long datetime = System.currentTimeMillis();
				Timestamp groupOrderTime = new Timestamp(datetime);
				GroupOrderVO groupOrderVO = new GroupOrderVO();
				groupOrderVO.setMemId(memId);
				groupOrderVO.setGroupTimeNo(groupTimeNo);
				groupOrderVO.setGroupOrderTime(groupOrderTime);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groupOrderVO", groupOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/grouporder/addGroupOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				GroupOrderService groupOrderSvc = new GroupOrderService();
				groupOrderVO = groupOrderSvc.addGroupOrder(memId, groupTimeNo, groupOrderTime);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/grouporder/listAllGroupOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/grouporder/addGroupOrder.jsp");
				failureView.forward(req, res);
			}
			
		}
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數***************************************/
				Integer groupOrderNo = new Integer(req.getParameter("groupOrderNo"));
				
				/***************************2.開始刪除資料***************************************/
				GroupOrderService groupOrderSvc = new GroupOrderService();
				GroupOrderVO groupOrderVO = groupOrderSvc.getOneGroupOrder(groupOrderNo);
				groupOrderSvc.deleteGroupOrder(groupOrderNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				GroupHourService groupHourSvc = new GroupHourService();
				if(requestURL.equals("/back-end/grouphour/listGroupOrders_ByGroupTimeNo.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
					req.setAttribute("listGroupOrders_ByGroupTimeNo",groupHourSvc.getGroupOrdersByGroupTimeNo(groupOrderNo)); // 資料庫取出的list物件,存入request
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("listGroupOrders_ByMemId_A".equals(action) || "listGroupOrders_ByMemId_B".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer memId = new Integer(req.getParameter("memId"));

				/*************************** 2.開始查詢資料 ****************************************/
				GroupOrderService groupOrderSvc = new GroupOrderService();
				Set<GroupOrderVO> set = groupOrderSvc.getGroupOrdersByMemId(memId);
		System.out.print(set);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listGroupOrders_ByMemId", set);    // 資料庫取出的set物件,存入request

				String url = null;
				if ("listGroupOrders_ByMemId_A".equals(action))
					url = "/back-end/grouporder/listGroupOrders_ByMemId.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listGroupOrders_ByMemId_B".equals(action))
					url = "/back-end/grouporder/listGroupOrders_ByMemId.jsp";              // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("listGroupOrders_ByGroupOrderStatus_A".equals(action) || "listGroupOrders_ByGroupOrderStatus_B".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer groupOrderStatus = new Integer(req.getParameter("groupOrderStatus"));

				/*************************** 2.開始查詢資料 ****************************************/
				GroupOrderService groupOrderSvc = new GroupOrderService();
				Set<GroupOrderVO> set = groupOrderSvc.getGroupOrdersByGroupOrderStatus(groupOrderStatus);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listGroupOrders_ByGroupOrderStatus", set);    // 資料庫取出的set物件,存入request

				String url = null;
				if ("listGroupOrders_ByGroupOrderStatus_A".equals(action))
					url = "/back-end/grouporder/listGroupOrders_ByGroupOrderStatus.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listGroupOrders_ByGroupOrderStatus_B".equals(action))
					url = "/back-end/grouporder/listGroupOrders_ByGroupOrderStatus.jsp";              // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
