package com.groupclass.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.groupclass.model.GroupClassService;
import com.groupclass.model.GroupClassVO;
import com.grouphour.model.GroupHourVO;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.pro.model.ProService;
import com.pro.model.ProVO;
import com.worker.model.WorkerService;
import com.worker.model.WorkerVO;

public class GroupClassServlet2 extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("application/json;charset=utf-8");
		if ("listGroupHours_ByGroupClassNo_A".equals(action) || "listGroupHours_ByGroupClassNo_B".equals(action)) {
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer groupClassNo = new Integer(req.getParameter("groupClassNo"));
//				System.out.print(groupClassNo);
				/*************************** 2.開始查詢資料 ****************************************/
				GroupClassService groupClassSvc = new GroupClassService();
				Set<GroupHourVO> set = groupClassSvc.getGroupHoursByGroupClassNo(groupClassNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				req.setAttribute("listGroupHours_ByGroupClassNo", set);    // 資料庫取出的set物件,存入request
//				Gson gson = new Gson();
				Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String json = gsonBuilder.toJson(set);
				PrintWriter out = null;
				try {
					out = res.getWriter();
					out.write(json);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (out != null) {
						out.close();
					}
				}
//				System.out.print("1111");
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if("getOne_For_Display".equals(action)) {	
			try { 
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				System.out.print("111111");
				String str = req.getParameter("groupClassNo");
				Integer groupClassNo = new Integer(str);

				/***************************2.開始查詢資料*****************************************/
				GroupClassService groupClassSvc = new GroupClassService();
				GroupClassVO groupClassVO = groupClassSvc.getOneGroupClass(groupClassNo);
//				System.out.print(groupClassVO);
				/***************************3.查詢完成，準備轉交(Send the Success view)*************/
				Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String json = gsonBuilder.toJson(groupClassVO);
				PrintWriter out = null;
				try {
					out = res.getWriter();
					out.write(json);
//					System.out.print("111111");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (out != null) {
						out.close();
					}
				}
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupclass/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOnePro_For_Display".equals(action)) {	
			try { 
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				System.out.print("111111");
				String str = req.getParameter("proId");
				Integer proId = new Integer(str);

				/***************************2.開始查詢資料*****************************************/
				ProService proSvc = new ProService();
				ProVO proVO = proSvc.getOnePro(proId);
//				System.out.print(groupClassVO);
				/***************************3.查詢完成，準備轉交(Send the Success view)*************/
				Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String json = gsonBuilder.toJson(proVO);
				PrintWriter out = null;
				try {
					out = res.getWriter();
					out.write(json);
//					System.out.print("111111");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (out != null) {
						out.close();
					}
				}
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupclass/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOneProName_For_Display".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer work_id = Integer.parseInt(req.getParameter("work_id"));
			

				/*************************** 2.開始查詢資料 ****************************************/
				WorkerService workerSvc = new WorkerService();
				WorkerVO workerVO = workerSvc.getOneWorker(work_id);


				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String json = gsonBuilder.toJson(workerVO);
				PrintWriter out = null;
				try {
					out = res.getWriter();
					out.write(json);
//					System.out.print("111111");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (out != null) {
						out.close();
					}
				}

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/worker/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
