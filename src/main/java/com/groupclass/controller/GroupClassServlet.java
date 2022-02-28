package com.groupclass.controller;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.classtype.model.*;
import com.groupclass.model.*;
import com.grouphour.model.*;

@MultipartConfig
public class GroupClassServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		try {
			Integer id = new Integer(req.getParameter("id"));
			
			GroupClassService groupClassSvc = new GroupClassService();
			GroupClassVO groupClassVO = groupClassSvc.getOneGroupClass(id);
			
			out.write(groupClassVO.getGroupClassPic());
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try { 
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("groupClassNo");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入團體課程編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupclass/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer groupClassNo = null;
				try {
					groupClassNo = new Integer(str);
				}catch(Exception e) {
					errorMsgs.add("團體課程編號格式不正確");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupclass/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				GroupClassService groupClassSvc = new GroupClassService();
				GroupClassVO groupClassVO = groupClassSvc.getOneGroupClass(groupClassNo);
				if(groupClassVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/groupclass/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/***************************3.查詢完成，準備轉交(Send the Success view)*************/
				req.setAttribute("groupClassVO", groupClassVO);
				String url = "/back-end/groupclass/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" +e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupclass/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數**********************/
				Integer groupClassNo = new Integer(req.getParameter("groupClassNo"));
				
				/***************************2.開始查詢資料**********************/
				GroupClassService groupClassSvc = new GroupClassService();
				GroupClassVO groupClassVO = groupClassSvc.getOneGroupClass(groupClassNo);
				
				/***************************3.查詢完成，準備轉交**********************/
				req.setAttribute("groupClassVO", groupClassVO);
				String url = "/back-end/groupclass/update_groupClass_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer groupClassNo = null;
				try {
					groupClassNo = new Integer(req.getParameter("groupClassNo").trim());
				}catch (NumberFormatException e) {
					groupClassNo = 0;
					errorMsgs.add("團體課程編號請填數字.");
				}
				Integer proId = null;
				try {
					proId = new Integer(req.getParameter("proId").trim());
				}catch (NumberFormatException e) {
					proId = 0;
					errorMsgs.add("教練編號請填數字.");
				}
				Integer classTypeNo = null;
				try {
					classTypeNo = new Integer(req.getParameter("classTypeNo").trim());
				}catch (NumberFormatException e) {
					classTypeNo = 0;
					errorMsgs.add("課程類別編號請填數字.");
				}
				String className = req.getParameter("className");
				if (className == null || className.trim().length() == 0) {
					errorMsgs.add("團體課程名稱: 請勿空白");
				}
				String loc = req.getParameter("loc").trim();
				if (loc == null || loc.trim().length() == 0) {
					errorMsgs.add("課程地點: 請勿空白");
				}
				
				Integer groupMax = null;
				try {
					groupMax = new Integer(req.getParameter("groupMax").trim());
				}catch (NumberFormatException e) {
					groupMax = 0;
					errorMsgs.add("人數上限請填數字.");
				}
				
				Integer groupMin = null;
				try {
					groupMin = new Integer(req.getParameter("groupMin").trim());
				}catch (NumberFormatException e) {
					groupMin = 0;
					errorMsgs.add("人數下限請填數字.");
				}
				
				Integer groupClassPrice = null;
				try {
					groupClassPrice = new Integer(req.getParameter("groupClassPrice").trim());
				}catch (NumberFormatException e) {
					groupMin = 0;
					errorMsgs.add("價格請填數字.");
				}
				
				String groupClassDetail = req.getParameter("groupClassDetail");
				if (groupClassDetail == null || groupClassDetail.trim().length() == 0) {
					errorMsgs.add("課程詳情請勿空白");
				}
				
				Part part = req.getPart("groupClassPic");
				InputStream in = part.getInputStream();
				byte[] groupClassPic = null;
if(in.available()!=0) {				
				groupClassPic = new byte[in.available()];
				in.read(groupClassPic);
				in.close();
}else {
	            GroupClassService groupClassSvc = new GroupClassService();
	            groupClassPic = groupClassSvc.getOneGroupClass(groupClassNo).getGroupClassPic();
}			
				GroupClassVO groupClassVO = new GroupClassVO();
				groupClassVO.setGroupClassNo(groupClassNo);
				groupClassVO.setProId(proId);
				groupClassVO.setClassTypeNo(classTypeNo);
				groupClassVO.setClassName(className);
				groupClassVO.setLoc(loc);
				groupClassVO.setGroupMax(groupMax);
				groupClassVO.setGroupMin(groupMin);
				groupClassVO.setGroupClassPrice(groupClassPrice);
				groupClassVO.setGroupClassDetail(groupClassDetail);
				groupClassVO.setGroupClassPic(groupClassPic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groupClassVO", groupClassVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/groupclass/update_groupClass_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}	
				/***************************2.開始修改資料*****************************************/
				GroupClassService groupClassSvc = new GroupClassService();
				groupClassVO = groupClassSvc.updateGroupClass(groupClassNo, proId, classTypeNo, className, loc, groupMax, groupMin, groupClassPrice, groupClassDetail, groupClassPic);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				ClassTypeService classTypeSvc = new ClassTypeService();
				req.setAttribute("groupClassVO", groupClassVO);
				if(requestURL.equals("/back-end/classtype/listGroupClasses_ByClassTypeNo.jsp") || requestURL.equals("/back-end/classtype/listAllClassType.jsp"))
					req.setAttribute("listGroupClasses_ByClassTypeNo",classTypeSvc.getGroupClassesByClassTypeNo(classTypeNo));
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/groupclass/update_groupClass_input.jsp");
				failureView.forward(req, res);
			}
		}
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer proId = null;
				try {
					proId = new Integer(req.getParameter("proId").trim());
				}catch (NumberFormatException e) {
					proId = 0;
					errorMsgs.add("教練編號請填數字.");
				}
				Integer classTypeNo = null;
				try {
					classTypeNo = new Integer(req.getParameter("classTypeNo").trim());
				}catch (NumberFormatException e) {
					classTypeNo = 0;
					errorMsgs.add("課程類別編號請填數字.");
				}
				String className = req.getParameter("className");
				if (className == null || className.trim().length() == 0) {
					errorMsgs.add("團體課程名稱請勿空白");
				}
				String loc = req.getParameter("loc");
				if (loc == null || loc.trim().length() == 0) {
					errorMsgs.add("團體課程地點請勿空白");
				}
				Integer groupMax = null;
				try {
					groupMax = new Integer(req.getParameter("groupMax").trim());
				} catch (NumberFormatException e) {
					groupMax = 0;
					errorMsgs.add("人數上限請填數字.");
				}
 
				Integer groupMin = null;
				try {
					groupMin = new Integer(req.getParameter("groupMin").trim());
				} catch (NumberFormatException e) {
					groupMin = 0;
					errorMsgs.add("人數下限請填數字.");
				}

				Integer groupClassPrice = null;
				try {
					groupClassPrice = new Integer(req.getParameter("groupClassPrice").trim());
				} catch (NumberFormatException e) {
					groupClassPrice = 0;
					errorMsgs.add("團體課程價格請填數字.");
				}
				String groupClassDetail = req.getParameter("groupClassDetail");
				if (groupClassDetail == null || groupClassDetail.trim().length() == 0) {
					errorMsgs.add("團體課程詳情請勿空白");
				}
				
				Part part = req.getPart("groupClassPic");
				InputStream in = part.getInputStream();
				byte[] groupClassPic = null;
				if(in.available()!=0) {				
					groupClassPic = new byte[in.available()];
					in.read(groupClassPic);
					in.close();
				}else {
					errorMsgs.add("照片請勿空白");
				}			

				/***************************2.開始新增資料***************************************/
				GroupClassService groupClassSvc = new GroupClassService();
				GroupClassVO groupClassVO = groupClassSvc.addGroupClass(proId, classTypeNo, className, loc, groupMax, groupMin, groupClassPrice, groupClassDetail, groupClassPic);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/groupclass/listAllGroupClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/groupclass/addGroupClass.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				Integer groupClassNo = new Integer(req.getParameter("groupClassNo"));
				
				/***************************2.開始刪除資料***************************************/
				GroupClassService groupClassSvc = new GroupClassService();
				groupClassSvc.deleteGroupClass(groupClassNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/groupclass/listAllGroupClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/groupclass/listAllGroupClass.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listGroupHours_ByGroupClassNo_A".equals(action) || "listGroupHours_ByGroupClassNo_B".equals(action)) {
			System.out.print("1111");			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer groupClassNo = new Integer(req.getParameter("groupClassNo"));
				System.out.print(groupClassNo);
				/*************************** 2.開始查詢資料 ****************************************/
				GroupClassService groupClassSvc = new GroupClassService();
				Set<GroupHourVO> set = groupClassSvc.getGroupHoursByGroupClassNo(groupClassNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listGroupHours_ByGroupClassNo", set);    // 資料庫取出的set物件,存入request

				String url = null;
				if ("listGroupHours_ByGroupClassNo_A".equals(action))
					url = "/back-end/groupclass/listGroupHours_ByGroupClassNo.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listGroupHours_ByGroupClassNo_B".equals(action))
					url = "/back-end/groupclass/listAllGroupClass.jsp";              // 成功轉交 dept/listAllDept.jsp
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.print("1111");
				/*************************** 其他可能的錯誤處理 ***********************************/
			}catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("delete_GroupClass".equals(action)) { // 來自/dept/listAllDept.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer groupClassNo = new Integer(req.getParameter("groupClassNo"));
				
				/***************************2.開始刪除資料***************************************/
				GroupClassService groupClassSvc = new GroupClassService();
				groupClassSvc.deleteGroupClass(groupClassNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/groupclass/listAllGroupClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/groupclass/listAllGroupClass.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listGroupClasses_ByProId_A".equals(action) || "listGroupClasses_ByProId_B".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer proId = new Integer(req.getParameter("proId"));

				/*************************** 2.開始查詢資料 ****************************************/
				GroupClassService groupClassSvc = new GroupClassService();
				Set<GroupClassVO> set = groupClassSvc.getGroupClassesByProId(proId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listGroupClasses_ByProId", set); // 資料庫取出的set物件,存入request

				String url = null;
				if ("listGroupClasses_ByProId_A".equals(action))
					url = "/back-end/groupclass/listGroupClasses_ByProId.jsp"; // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listGroupClasses_ByProId_B".equals(action))
					url = "/front-end/groupclass/listGroupClasses_ByProId.jsp"; // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
