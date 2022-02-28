package com.course_order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.course.model.CourseService;
import com.course.model.CourseVO;
import com.course_order.model.CourseOrderService;
import com.course_order.model.CourseOrderVO;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.pro.model.ProService;
import com.pro.model.ProVO;
import com.worker.model.WorkerService;
import com.worker.model.WorkerVO;

public class CourseOrderServlet extends HttpServlet {
	
	private CourseOrderService courseOrderSvc = new CourseOrderService();
	private final String COURSE_ORDER_LIST_ONE_COURSE_PAGE = "/back-end/course_order/listOneCourseOrder.jsp";
	private final String COURSE_ORDER_SELECT_PAGE = "/back-end/course_order/select_page.jsp";
	private final String COURSE_ORDER_ADD_PAGE = "/back-end/course_order/addCourseOrder.jsp";
	private final String COURSE_ORDER_LIST_ALL_PAGE = "/back-end/course_order/listAllCourseOrder.jsp";
	private final String COURSE_ORDER_UPDATE_PAGE = "/back-end/course_order/Update_courseOrder_input.jsp";
	private final String COURSE_ORDER_CHECK_PAGE = "/front-end/order_pro/orderCheck.jsp";
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("CourseOrderServlet action:"+action);
		
	
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("courseOrderNo");
			
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(COURSE_ORDER_SELECT_PAGE);
					failureView.forward(req, res);
					return;
				}
		
				Integer courseOrderNo = null;
				try {
					courseOrderNo = Integer.parseInt(str);
				} catch (NumberFormatException e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(COURSE_ORDER_SELECT_PAGE);
					failureView.forward(req, res);
					return;  //輸入錯誤格式因此中斷後面步驟
				}

				/*************************** 2.開始查詢資料 *****************************************/
		
				CourseOrderVO courseOrderVO = courseOrderSvc.getOneCourseOrder(courseOrderNo);
				
				if (courseOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(COURSE_ORDER_SELECT_PAGE);
					failureView.forward(req, res);
					return; //查不到資料因此中斷後面步驟
				}
	
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("courseOrderVO", courseOrderVO); 
				RequestDispatcher successView = req.getRequestDispatcher(COURSE_ORDER_LIST_ONE_COURSE_PAGE);
		
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(COURSE_ORDER_SELECT_PAGE);
				failureView.forward(req, res);
			}
		}
		

		else if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				System.out.println("getOne_For_Update" + req.getParameter("courseOrderNo"));
				Integer courseOrderNo =  Integer.parseInt(req.getParameter("courseOrderNo").trim());
				
				System.out.println("getOne_For_Update" + courseOrderNo);

				/*************************** 2.開始查詢資料 ****************************************/
				CourseOrderVO courseOrderVO = courseOrderSvc.getOneCourseOrder(courseOrderNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("courseOrderVO", courseOrderVO); 
				RequestDispatcher successView = req.getRequestDispatcher(COURSE_ORDER_UPDATE_PAGE);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println("getOne_For_Update catch");
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(COURSE_ORDER_LIST_ALL_PAGE);
				failureView.forward(req, res);
			}
		}
		

		else if ("update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
	

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				//從parameter拿訂單編號
				Integer courseOrderNo =  Integer.parseInt(req.getParameter("courseOrderNo").trim());
				if (courseOrderNo == null || courseOrderNo.toString().trim().length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				
				Integer courseId =  Integer.parseInt(req.getParameter("courseId").trim());
				if (courseId == null || courseId.toString().trim().length() == 0) {
					errorMsgs.add("請輸入課程編號");
				}

				Integer memId =  Integer.parseInt(req.getParameter("memId").trim());
				if (memId == null || memId.toString().trim().length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}

				Integer courseOrderHours =  Integer.parseInt(req.getParameter("courseOrderHours").trim());
				if (courseOrderHours == null || courseOrderHours.toString().trim().length() == 0) {
					errorMsgs.add("請輸入購買時數");
				}

				Integer courseOrderPrice =  Integer.parseInt(req.getParameter("courseOrderPrice").trim());
				if (courseOrderPrice == null || courseOrderPrice.toString().trim().length() == 0) {
					errorMsgs.add("請輸入訂單價格");
				}
				
				Integer courseOrderStatus =  Integer.parseInt(req.getParameter("courseOrderStatus").trim());
				if (courseOrderStatus == null || courseOrderStatus.toString().trim().length() == 0) {
					errorMsgs.add("請輸入訂單狀況");
				}


				CourseOrderVO courseOrderVO = courseOrderSvc.getOneCourseOrder(courseOrderNo);
		
				courseOrderVO.setCourseOrderNo(courseOrderNo);
				courseOrderVO.setCourseId(courseId);
				courseOrderVO.setMemId(memId);
				courseOrderVO.setCourseOrderHours(courseOrderHours);
				courseOrderVO.setCourseOrderPrice(courseOrderPrice);
				courseOrderVO.setCourseOrderStatus(courseOrderStatus);
				
			
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("courseOrderVO", courseOrderVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher(COURSE_ORDER_UPDATE_PAGE);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
	
				CourseOrderService courseOrderSvc = new CourseOrderService();
				courseOrderVO = courseOrderSvc.updateCourseOrder(courseOrderNo, courseId, memId, 
						courseOrderHours, courseOrderPrice, courseOrderStatus); 

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("courseOrderVO", courseOrderVO); 
				RequestDispatcher successView = req.getRequestDispatcher( COURSE_ORDER_LIST_ONE_COURSE_PAGE); 
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(COURSE_ORDER_UPDATE_PAGE);
				failureView.forward(req, res);
			}
		}
		

		else if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			

			try {
				

				
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				System.out.println("insert courseId: "+req.getParameter("courseId").trim());
				Integer courseId =  Integer.parseInt(req.getParameter("courseId").trim());
				if (courseId == null || courseId.toString().trim().length() == 0) {
					errorMsgs.add("請輸入課程編號");
				}
				
				System.out.println("insert memId: "+req.getParameter("memId").trim());
				Integer memId = Integer.parseInt(req.getParameter("memId").trim());
				if (memId == null || memId.toString().trim().length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				
				System.out.println("insert courseOrderHours: "+req.getParameter("courseOrderHours").trim());
				Integer courseOrderHours =  Integer.parseInt(req.getParameter("courseOrderHours").trim());
				if (courseOrderHours == null || courseOrderHours.toString().trim().length() == 0) {
					errorMsgs.add("請輸入購買時數");
				}
				
				System.out.println("insert courseOrderPrice: "+req.getParameter("courseOrderPrice").trim());
				Integer courseOrderPrice =  Integer.parseInt(req.getParameter("courseOrderPrice").trim());
				if (courseOrderPrice == null || courseOrderPrice.toString().trim().length() == 0) {
					errorMsgs.add("請輸入訂單價格");
				}
				
				System.out.println("insert courseOrderStatus: "+req.getParameter("courseOrderStatus").trim());
				Integer courseOrderStatus =  Integer.parseInt(req.getParameter("courseOrderStatus").trim());
				if (courseOrderStatus == null || courseOrderStatus.toString().trim().length() == 0) {
					errorMsgs.add("請輸入訂單狀況");
				}

				System.out.println("1");
				
				CourseOrderVO courseOrderVO = new CourseOrderVO();
		
				courseOrderVO.setCourseId(courseId);
				courseOrderVO.setMemId(memId);
				courseOrderVO.setCourseOrderHours(courseOrderHours);
				courseOrderVO.setCourseOrderPrice(courseOrderPrice);
				courseOrderVO.setCourseOrderStatus(courseOrderStatus);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("courseOrderVO", courseOrderVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher(COURSE_ORDER_ADD_PAGE);
					failureView.forward(req, res);
					System.out.println("2" + errorMsgs.toString());
					return; 
				}


				/*************************** 2.開始新增資料 ***************************************/
				courseOrderVO = courseOrderSvc.addCourseOrder(courseOrderVO);
				System.out.println("insert 3.1" );
				List<CourseOrderVO> courseOrderList = courseOrderSvc.getAll();
				System.out.println("insert 3.2" );
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("list", courseOrderList);
				System.out.println("insert 3.3" );
				RequestDispatcher successView = req.getRequestDispatcher(COURSE_ORDER_LIST_ALL_PAGE);
				successView.forward(req, res);
				System.out.println("insert 3.5" );
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
//				e.printStackTrace();  報錯
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(COURSE_ORDER_ADD_PAGE);
				System.out.println("insert catch"+e.toString());
				failureView.forward(req, res);
				
			}
		} 
		
		else if ("listAll".equals(action)) {
			List<CourseOrderVO> list = courseOrderSvc.getAll();
			req.setAttribute("list", list);
			RequestDispatcher successView = req.getRequestDispatcher(COURSE_ORDER_LIST_ALL_PAGE);
			
			successView.forward(req, res);
		} else {
			HttpSession session = req.getSession();
			String query_string = (String) session.getAttribute("query_string");
			String orderNo = (String) req.getParameter("orderNo");
			if(query_string != null) {
				orderNo = query_string.split("=")[1];
				session.removeAttribute("query_string");
			}
			Integer orderNoInt = Integer.parseInt(orderNo);
			
			System.out.println("===========================");
			System.out.println(query_string);
			System.out.println(orderNo);
			System.out.println("===========================");
			
			CourseOrderService svc = new CourseOrderService();
			CourseOrderVO courseOrderVO = svc.getOneCourseOrder(orderNoInt);
			Integer courseId = courseOrderVO.getCourseId();
			
			CourseService courseSvc = new CourseService();
			CourseVO courseVO = courseSvc.findByPrimaryKey(courseId);
			Integer proId = courseVO.getProId();
			
			ProService proSvc = new ProService();
			ProVO proVO = proSvc.getOnePro(proId);
			Integer workId = proVO.getWorkId();
			
			WorkerService workerSvc = new WorkerService();
			WorkerVO workerVO = workerSvc.getOneWorker(workId);
			String workerName = workerVO.getW_name();
			
			
			req.setAttribute("courseOrderVO", courseOrderVO);
			req.setAttribute("workerName", workerName);
			RequestDispatcher successView = req.getRequestDispatcher(COURSE_ORDER_CHECK_PAGE);
			successView.forward(req, res);
		}
		
	
	}
}
