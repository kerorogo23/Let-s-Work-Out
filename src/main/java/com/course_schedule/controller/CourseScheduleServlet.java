package com.course_schedule.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course_order.model.CourseOrderService;
import com.course_schedule.model.CourseScheduleService;
import com.course_schedule.model.CourseScheduleVO;

public class CourseScheduleServlet extends HttpServlet {

	private CourseScheduleService courseScheduleSvc = new CourseScheduleService();
	private final String COURSE_SCHEDULE_LIST_ONE_COURSE_Page = "/back-end/course_schedule/listOneCourseSchedule.jsp";
	private final String COURSE_SCHEDULE_SELECT_PAGE = "/back-end/course_schedule/select_page.jsp";
	private final String COURSE_SCHEDULE_ADD_PAGE = "/back-end/course_schedule/addCourseSchedule.jsp";
	private final String COURSE_SCHEDULE_LIST_ALL_PAGE = "/back-end/course_schedule/listAllCourseSchedule.jsp";
	private final String COURSE_SCHEDULE_UPDATE_PAGE = "/back-end/course_schedule/Update_courseSchedule_input.jsp";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("CourseScheduleServlet action:" + action);

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("scheduleNo");

				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("請輸入預約單編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(COURSE_SCHEDULE_SELECT_PAGE);
					failureView.forward(req, res);
					return;
				}

				Integer scheduleNo = null;
				try {
					scheduleNo = Integer.parseInt(str);
				} catch (NumberFormatException e) {
					errorMsgs.add("預約單編號格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(COURSE_SCHEDULE_SELECT_PAGE);
					failureView.forward(req, res);
					return; // 輸入錯誤格式因此中斷後面步驟
				}

				/*************************** 2.開始查詢資料 *****************************************/

				CourseScheduleVO courseScheduleVO = courseScheduleSvc.getOneCourseSchedule(scheduleNo);

				if (courseScheduleVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(COURSE_SCHEDULE_SELECT_PAGE);
					failureView.forward(req, res);
					return; // 查不到資料因此中斷後面步驟
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("courseScheduleVO", courseScheduleVO);
				RequestDispatcher successView = req.getRequestDispatcher(COURSE_SCHEDULE_LIST_ONE_COURSE_Page);

				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(COURSE_SCHEDULE_SELECT_PAGE);
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // 建立errorMsgs物件

			req.setAttribute("errorMsgs", errorMsgs); // 設定來自req的errorMsgs物件的屬性

			try {
				String scheduleNoStr = req.getParameter("scheduleNo");
				Integer scheduleNo = null;
				if (scheduleNoStr == null || scheduleNoStr.trim().isEmpty()) {
					errorMsgs.add("未輸入預約單號碼");
				} else {
					try {
						scheduleNo = Integer.parseInt(scheduleNoStr.trim()); // 取得要修改的預約單號碼資料
					} catch (NumberFormatException nfe) {
						errorMsgs.add("預約單號碼格式錯誤");
					}
				}

				if (!errorMsgs.isEmpty()) {
					List<CourseScheduleVO> list = courseScheduleSvc.getAll();
					req.setAttribute("list", list);
					RequestDispatcher failureView = req.getRequestDispatcher(COURSE_SCHEDULE_LIST_ALL_PAGE); // 錯誤留在LIST
					// All畫面
					failureView.forward(req, res); // req和res未成功轉交
					return;
				}

				CourseScheduleVO courseScheduleVO = courseScheduleSvc.getOneCourseSchedule(scheduleNo); // 從service拿

				req.setAttribute("courseScheduleVO", courseScheduleVO); // 設定來自req的courseScheduleVO物件的屬性

				RequestDispatcher successview = req.getRequestDispatcher(COURSE_SCHEDULE_UPDATE_PAGE);// 成功頁面是轉到update頁面

				successview.forward(req, res);// req和res成功轉交

			} catch (Exception e) { // 例外
				List<CourseScheduleVO> list = courseScheduleSvc.getAll();
				req.setAttribute("list", list);

				errorMsgs.add("無法取得資料:" + e.getMessage()); // 錯誤訊息
				RequestDispatcher failureView = req.getRequestDispatcher(COURSE_SCHEDULE_LIST_ALL_PAGE); // 錯誤留在LIST
																											// All畫面
				failureView.forward(req, res); // req和res未成功轉交
				return;
			}

		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			Integer scheduleNo = null;
			try {
				String scheduleNoStr = req.getParameter("scheduleNo");

				if (scheduleNoStr == null || scheduleNoStr.trim().isEmpty()) {
					errorMsgs.add("未輸入預約單號碼");
				} else {
					try {
						scheduleNo = Integer.parseInt(scheduleNoStr.trim()); // 取得要修改的預約單號碼資料
					} catch (NumberFormatException nfe) {
						errorMsgs.add("預約單號碼格式錯誤");
					}
				}

				Integer courseOrderNo = Integer.parseInt(req.getParameter("courseOrderNo").trim());

				if (courseOrderNo == null || courseOrderNo.toString().trim().length() == 0) {

					errorMsgs.add("請輸入訂單編號");
				}

				String reservedTimeStr = req.getParameter("reserveTime");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Timestamp reserveTime = null;

				try {

					if (reservedTimeStr == null || reservedTimeStr.toString().trim().length() == 0) {

						errorMsgs.add("請輸入預約時間資訊");

					} else {

						Date time = sdf.parse(reservedTimeStr);

						reserveTime = new Timestamp(time.getTime());

					}

				} catch (ParseException pe) {

					errorMsgs.add("請輸入預約時間資訊");

				}

				Integer courseStatus = Integer.parseInt(req.getParameter("courseStatus").trim());

				if (courseStatus == null || courseStatus.toString().trim().length() == 0) {

					errorMsgs.add("請輸入上課狀態(-1, 0, 1)");

				}

				CourseScheduleVO courseScheduleVO = courseScheduleSvc.getOneCourseSchedule(scheduleNo);

				courseScheduleVO.setScheduleNo(scheduleNo);
				courseScheduleVO.setCourseOrderNo(courseOrderNo);
				courseScheduleVO.setReserveTime(reserveTime);
				courseScheduleVO.setCourseStatus(courseStatus);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("courseScheduleVO", courseScheduleVO);
					RequestDispatcher failureView = req.getRequestDispatcher(COURSE_SCHEDULE_UPDATE_PAGE);
					failureView.forward(req, res);
					return;
				}

				courseScheduleVO = courseScheduleSvc.updateCourseSchedule(scheduleNo, courseOrderNo, reserveTime,
						courseStatus);
				req.setAttribute("courseScheduleVO", courseScheduleVO);

				RequestDispatcher successView = req.getRequestDispatcher(COURSE_SCHEDULE_LIST_ONE_COURSE_Page);
				successView.forward(req, res);

			} catch (Exception e) {
				CourseScheduleVO courseScheduleVO = courseScheduleSvc.getOneCourseSchedule(scheduleNo); // 從service拿
				
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView;
				if (courseScheduleVO != null) {
					req.setAttribute("courseScheduleVO", courseScheduleVO); // 設定來自req的courseScheduleVO物件的屬性
					failureView = req.getRequestDispatcher(COURSE_SCHEDULE_UPDATE_PAGE);
				} else {
					List<CourseScheduleVO> list = courseScheduleSvc.getAll();
					req.setAttribute("list", list); 
					failureView = req.getRequestDispatcher(COURSE_SCHEDULE_LIST_ALL_PAGE);
				}
				failureView.forward(req, res);

			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String courseOrderNoStr = req.getParameter("courseOrderNo");

				if (courseOrderNoStr == null || courseOrderNoStr.trim().length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}

				Integer courseOrderNo = null;

				try {
					courseOrderNo = Integer.parseInt(courseOrderNoStr.trim());
				} catch (NumberFormatException nfe) {
					errorMsgs.add("訂單編號格式不正確");
				}

				String reservedTimeStr = req.getParameter("reserveTime");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Timestamp reserveTime = null;
				try {
					if (reservedTimeStr == null || reservedTimeStr.toString().trim().length() == 0) {
						errorMsgs.add("請輸入預約時間資訊");
					} else {
						Date time = sdf.parse(reservedTimeStr);
						reserveTime = new Timestamp(time.getTime());
					}
				} catch (ParseException pe) {
					errorMsgs.add("請輸入預約時間資訊");
				}

				String courseStatusStr = req.getParameter("courseStatus");
				Integer courseStatus = null;
				if (courseStatusStr == null || courseStatusStr.trim().isEmpty()) {
					errorMsgs.add("請輸入上課狀態");
				} else {
					try {
						courseStatus = Integer.parseInt(courseStatusStr.trim());
					} catch (NumberFormatException nfe) {
						errorMsgs.add("上課狀態資料錯誤");
					}

				}

				CourseScheduleVO courseScheduleVO = new CourseScheduleVO();

				courseScheduleVO.setCourseOrderNo(courseOrderNo);
				courseScheduleVO.setReserveTime(reserveTime);
				courseScheduleVO.setCourseStatus(courseStatus);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("courseScheduleVO", courseScheduleVO);
					RequestDispatcher failureView = req.getRequestDispatcher(COURSE_SCHEDULE_ADD_PAGE);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				courseScheduleVO = courseScheduleSvc.addCourseSchedule(courseScheduleVO);
				List<CourseScheduleVO> courseScheduleList = courseScheduleSvc.getAll();
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				req.setAttribute("courseScheduleVO", courseScheduleVO);
				req.setAttribute("list", courseScheduleList);
				RequestDispatcher successView = req.getRequestDispatcher(COURSE_SCHEDULE_LIST_ALL_PAGE);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(COURSE_SCHEDULE_ADD_PAGE);
				failureView.forward(req, res);
			}
		}

		if ("listAll".equals(action)) {
			List<CourseScheduleVO> list = courseScheduleSvc.getAll();
			req.setAttribute("list", list);
			RequestDispatcher successView = req.getRequestDispatcher(COURSE_SCHEDULE_LIST_ALL_PAGE);
			successView.forward(req, res);
		}
	}
}
