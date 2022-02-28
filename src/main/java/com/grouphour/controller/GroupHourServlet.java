package com.grouphour.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.groupclass.model.*;
import com.grouphour.model.*;
import com.grouporder.model.*;

public class GroupHourServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("groupTimeNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入時間編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/grouphour/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer groupTimeNo = null;
				try {
					groupTimeNo = new Integer(str);
					System.out.println(str);
				} catch (Exception e) {
					errorMsgs.add("時間編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/grouphour/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				GroupHourService groupHourSvc = new GroupHourService();
				GroupHourVO groupHourVO = groupHourSvc.getOneGroupHour(groupTimeNo);
				if (groupHourVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/grouphour/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("groupHourVO", groupHourVO); 
				String url = "/back-end/grouphour/listOneGroupHour.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/grouphour/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer groupTimeNo = new Integer(req.getParameter("groupTimeNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				GroupHourService groupHourSvc = new GroupHourService();
				GroupHourVO groupHourVO = groupHourSvc.getOneGroupHour(groupTimeNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("groupHourVO", groupHourVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/grouphour/update_groupHour_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer groupTimeNo = new Integer(req.getParameter("groupTimeNo").trim());

				Integer groupClassNo = null;
				try {
					groupClassNo = new Integer(req.getParameter("groupClassNo").trim());
				} catch (NumberFormatException e) {
					groupClassNo = 0;
					errorMsgs.add("團課編號請填數字.");
				}

				java.sql.Date groupDate = null;
				try {
					groupDate = java.sql.Date.valueOf(req.getParameter("groupDate").trim());
				} catch (IllegalArgumentException e) {
					groupDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String groupStartingTime = req.getParameter("groupStartingTime");
				if (groupStartingTime == null || (groupStartingTime.trim()).length() == 0) {
					errorMsgs.add("請輸入課程時間");
				}else {
					int index = Integer.parseInt(groupStartingTime);
					String allDateTime = "000000000000000000000000";
					String allTime[] = allDateTime.split("");
					allTime[index] = "1";
					String groupTimeAtData = "";
					for(int i = 0; i < allTime.length; i++) {
						groupTimeAtData += allTime[i];
						groupStartingTime = groupTimeAtData;
					}
				}

				java.sql.Timestamp registStartTime = null;
				try {
					String time = req.getParameter("registStartTime") + " 00:00:00";
					registStartTime = java.sql.Timestamp.valueOf((time).trim());
					
				} catch (IllegalArgumentException e) {
					registStartTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Timestamp registEndTime = null;
				try {
					String time = req.getParameter("registEndTime") + " 00:00:00";
					registEndTime = java.sql.Timestamp.valueOf(time.trim());
				} catch (IllegalArgumentException e) {
					registEndTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer courseStatus = null;
				try {
					courseStatus = new Integer(req.getParameter("courseStatus").trim());
				} catch (NumberFormatException e) {
					courseStatus = 0;
					errorMsgs.add("課程狀態請填數字.");
				}

				GroupHourVO groupHourVO = new GroupHourVO();
				groupHourVO.setGroupTimeNo(groupTimeNo);
				groupHourVO.setGroupClassNo(groupClassNo);
				groupHourVO.setGroupDate(groupDate);
				groupHourVO.setGroupStartingTime(groupStartingTime);
				groupHourVO.setRegistStartTime(registStartTime);
				groupHourVO.setRegistEndTime(registEndTime);
				groupHourVO.setCourseStatus(courseStatus);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groupHourVO", groupHourVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/grouphour/update_groupHour_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				GroupHourService groupHourSvc = new GroupHourService();
				groupHourSvc.updateGroupHour(groupTimeNo, groupClassNo, groupDate, groupStartingTime, registStartTime,
						registEndTime, courseStatus);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				GroupClassService groupClassSvc = new GroupClassService();
				if (requestURL.equals("/back-end/groupclass/listGroupHours_ByGroupClassNo.jsp")
						|| requestURL.equals("/back-end/groupclass/listAllGroupClass.jsp"))
					req.setAttribute("listGroupHours_ByGroupClassNo", groupClassSvc.getGroupHoursByGroupClassNo(groupClassNo)); // 資料庫取出的list物件,存入request

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/grouphour/update_groupHour_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
//				System.out.print("1");
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer groupClassNo = null;
				try {
					groupClassNo = new Integer(req.getParameter("groupClassNo").trim());
//					System.out.print(groupClassNo);
				} catch (NumberFormatException e) {
					groupClassNo = 0;
					errorMsgs.add("團課編號請填數字.");
				}

				java.sql.Date groupDate = null;
				try {
					groupDate = java.sql.Date.valueOf(req.getParameter("groupDate").trim());
					System.out.print(groupDate);
				} catch (IllegalArgumentException e) {
					groupDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				/////////////////////////////////////////
				String groupStartingTime = req.getParameter("groupStartingTime");
//				System.out.print(groupStartingTime);
				if (groupStartingTime == null || (groupStartingTime.trim()).length() == 0)  {
					errorMsgs.add("請輸入課程時間");
				}else {
					int index = Integer.parseInt(groupStartingTime);
					String allDateTime = "000000000000000000000000";
					String allTime[] = allDateTime.split("");
					allTime[index] = "1";
					String groupTimeAtData = "";
					for(int i = 0; i < allTime.length; i++) {
						groupTimeAtData += allTime[i];
						groupStartingTime = groupTimeAtData;
					}
				}
				java.sql.Timestamp registStartTime = null;
				try {
					String time = req.getParameter("registStartTime") + " 00:00:00";
//					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					String timeStr = df.format(time); 
//					System.out.print(timeStr);
					registStartTime = java.sql.Timestamp.valueOf(time.trim());
//					System.out.print(registStartTime);
				} catch (IllegalArgumentException e) {
					registStartTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Timestamp registEndTime = null;
				try {
					String time = req.getParameter("registEndTime") + " 00:00:00";
					registEndTime = java.sql.Timestamp.valueOf(time.trim());
//					System.out.print(registEndTime);
				} catch (IllegalArgumentException e) {
					registEndTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				GroupHourVO groupHourVO = new GroupHourVO();
				groupHourVO.setGroupClassNo(groupClassNo);
				groupHourVO.setGroupDate(groupDate);
				groupHourVO.setGroupStartingTime(groupStartingTime);
				groupHourVO.setRegistStartTime(registStartTime);
				groupHourVO.setRegistEndTime(registEndTime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groupHourVO", groupHourVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupHour/addGroupHour.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				GroupHourService groupHourSvc = new GroupHourService();
				groupHourVO = groupHourSvc.addGroupHour(groupClassNo, groupDate, groupStartingTime, registStartTime, registEndTime);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/grouphour/listAllGroupHour.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/grouphour/addGroupHour.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete_GroupHour".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			try {
				/***************************1.接收請求參數***************************************/
				Integer groupTimeNo = new Integer(req.getParameter("groupTimeNo"));
				
				/***************************2.開始刪除資料***************************************/
				GroupHourService groupHourSvc = new GroupHourService();
				GroupHourVO groupHourVO = groupHourSvc.getOneGroupHour(groupTimeNo);
				groupHourSvc.deleteGroupHour(groupTimeNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				GroupClassService groupClassSvc = new GroupClassService();
				if(requestURL.equals("/back-end/groupclass/listGroupHours_ByGroupClassNo.jsp") || requestURL.equals("/back-end/groupclass/listAllGroupClass.jsp"))
					req.setAttribute("listGroupHours_ByGroupClassNo",groupClassSvc.getGroupHoursByGroupClassNo(groupHourVO.getGroupClassNo())); // 資料庫取出的list物件,存入request
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("listGroupOrders_ByGroupTimeNo_A".equals(action) || "listGroupOrders_ByGroupTimeNo_B".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer groupTimeNo = new Integer(req.getParameter("groupTimeNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				GroupHourService groupHourSvc = new GroupHourService();
				Set<GroupOrderVO> set = groupHourSvc.getGroupOrdersByGroupTimeNo(groupTimeNo);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listGroupOrders_ByGroupTimeNo", set);    // 資料庫取出的set物件,存入request

				String url = null;
				if ("listGroupOrders_ByGroupTimeNo_A".equals(action))
					url = "/back-end/grouphour/listGroupOrders_ByGroupTimeNo.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listGroupOrders_ByGroupTimeNo_B".equals(action))
					url = "/back-end/grouphour/listAllGroupHour.jsp";              // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("listGroupHours_ByCourseStatus_A".equals(action) || "listGroupHours_ByCourseStatus_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer courseStatus = new Integer(req.getParameter("courseStatus"));

				/*************************** 2.開始查詢資料 ****************************************/
				GroupHourService groupHourSvc = new GroupHourService();
				Set<GroupHourVO> set = groupHourSvc.getGroupHoursByCourseStatus(courseStatus);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listGroupHours_ByCourseStatus", set);    // 資料庫取出的set物件,存入request

				String url = null;
				if ("listGroupHours_ByCourseStatus_A".equals(action))
					url = "/back-end/grouphour/listGroupHours_ByCourseStatus.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listGroupHours_ByCourseStatus_B".equals(action))
					url = "/back-end/grouphour/listGroupHours_ByCourseStatus.jsp";              // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			}catch (Exception e) {
				throw new ServletException(e);
			}
		}
	
	}

}
