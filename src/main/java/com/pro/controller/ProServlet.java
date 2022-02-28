package com.pro.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.course_schedule.model.CourseScheduleService;
import com.course_schedule.model.CourseScheduleVO;
import com.pro.model.*;
import com.worker.model.WorkerService;
import com.worker.model.WorkerVO;

@MultipartConfig
public class ProServlet extends HttpServlet {

	private ProService proSvc = new ProService();
	private WorkerService workerSvc = new WorkerService();
	private final String PRO_LIST_ALL_PAGE = "/back-end/pro/listAllPro.jsp";
	private final String FRONTEND_PRO_LIST_ALL_PAGE = "/front-end/pro/ProIndex.jsp";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("ProServlet action:" + action);

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String proIdStr = req.getParameter("proId");
			System.out.println("proId: " + proIdStr);
			if (proIdStr == null || proIdStr.length() == 0) {
				errorMsgs.add("請輸入教練編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pro/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer proId = null;
			try {
				proId = Integer.parseInt(proIdStr);
			} catch (NumberFormatException e) {
				errorMsgs.add("教練編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pro/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ProService proSvc = new ProService();
			ProVO proVO = proSvc.getOnePro(proId);
			if (proVO == null) {
				errorMsgs.add("查無資料");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pro/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("proVO", proVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/pro/listOnePro.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/pro/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 ****************************************/
			Integer pro_ID = new Integer(req.getParameter("proId"));
			System.out.print(pro_ID);

			/*************************** 2.開始查詢資料 ****************************************/
			ProService proSvc = new ProService();
			ProVO proVO = proSvc.getOnePro(pro_ID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("proVO", proVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/pro/update_pro_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/pro/listAllPro.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer proId = Integer.parseInt(req.getParameter("proId").trim());

			Integer workId = Integer.parseInt(req.getParameter("workId"));
//				Integer work_ID = new Integer(req.getParameter("work_ID").trim());
//				Integer work_IDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (work_ID == null || work_ID.trim().length() == 0) {
//					errorMsgs.add("員工編號: 請勿空白");
//				} else if(!work_ID.trim().matches(work_IDReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }

			String proResume = req.getParameter("proResume").trim();
			if (proResume == null || proResume.trim().length() == 0) {
				errorMsgs.add("簡介請勿空白");
			}

			String expr = req.getParameter("expr").trim();
			if (expr == null || expr.trim().length() == 0) {
				errorMsgs.add("經歷請勿空白");
			}

			Part part = req.getPart("pro_photo");
			InputStream in = part.getInputStream();
			byte[] pro_photo = null;
			if (in.available() != 0) {
				pro_photo = new byte[in.available()];
				in.read(pro_photo);
				in.close();
			} else {
				ProService proSvc = new ProService();
				pro_photo = proSvc.getOnePro(proId).getProPhoto();
			}

			ProVO proVO = new ProVO();
			proVO.setProId(proId);
			proVO.setWorkId(workId);
			proVO.setProResume(proResume);
			proVO.setExpr(expr);
			proVO.setProPhoto(pro_photo);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("proVO", proVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pro/update_pro_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ProService proSvc = new ProService();
			proVO = proSvc.updatePro(proVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("proVO", proVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/pro/listOnePro.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/pro/update_pro_input.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				// Integer pro_ID = Integer.parseInt(req.getParameter("pro_ID").trim());

				Integer work_ID = Integer.parseInt(req.getParameter("work_ID"));

//				Integer work_IDReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (work_ID == null || work_ID.trim().length() == 0) {
//					errorMsgs.add("員工編號: 請勿空白");
//				} else if(!work_ID.trim().matches(work_IDReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }

				String pro_resume = req.getParameter("pro_resume").trim();
				if (pro_resume == null || pro_resume.trim().length() == 0) {
					errorMsgs.add("簡介請勿空白");
				}

				String expr = req.getParameter("expr").trim();
				if (expr == null || expr.trim().length() == 0) {
					errorMsgs.add("經歷請勿空白");
				}

				Part part = req.getPart("pro_photo");
				InputStream in = part.getInputStream();
				byte[] pro_photo = null;
				if (in.available() != 0) {
					pro_photo = new byte[in.available()];
					in.read(pro_photo);
					in.close();
				} else {
					errorMsgs.add("圖片請勿空白");
				}

				ProVO proVO = new ProVO();
//				proVO.setPro_ID(pro_ID);
				proVO.setWorkId(work_ID);
				proVO.setProResume(pro_resume);
				proVO.setExpr(expr);
				proVO.setProPhoto(pro_photo);

//				 Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", proVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pro/addPro.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				ProService proSvc = new ProService();
				proVO = proSvc.addPro(proVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/pro/listAllPro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/pro/addPro.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer pro_ID = new Integer(req.getParameter("pro_ID"));

				/*************************** 2.開始刪除資料 ***************************************/
				ProService proSvc = new ProService();
				proSvc.deletePro(pro_ID);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/pro/listAllPro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pro/listAllPro.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listAll".equals(action)) {
			List<ProVO> list = proSvc.getAll();
			req.setAttribute("list", list);
			RequestDispatcher successView = req.getRequestDispatcher(PRO_LIST_ALL_PAGE);
			successView.forward(req, res);
		}

		if ("listAllPros".equals(action)|| action==null || action.isEmpty()) {
			List<ProVO> list = proSvc.getAll();
			req.setAttribute("list", list);

			List<WorkerVO> workerList = workerSvc.getAll();
			Map<Integer, String> workerMap = getWorkerMap(workerList);
			req.setAttribute("workerMap", workerMap);

			
			RequestDispatcher successView = req.getRequestDispatcher(FRONTEND_PRO_LIST_ALL_PAGE);
			successView.forward(req, res);																			
		}	

		
	}	
									

			
			

	private Map<Integer, String> getWorkerMap(List<WorkerVO> workerList) {
		Map<Integer, String> map = new HashMap<Integer, String>();

		for (WorkerVO workerVO : workerList) {
			map.put(workerVO.getWork_id(), workerVO.getW_name());
		}

		return map;
	}
}
