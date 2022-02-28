package com.purchase_order.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_category.model.ProductCategoryService;
import com.purchase_order.model.*;
import com.purchase_order_details.model.PurchaseOrderDetailsService;
import com.purchase_order_details.model.PurchaseOrderDetailsVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PurchaseOrderServlet extends HttpServlet {

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
				String str = req.getParameter("po_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/purchase_order/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				Integer po_no = null;
				try {
					po_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/purchase_order/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.開始查詢資料 *****************************************/
				PurchaseOrderService purchaseorderSvc = new PurchaseOrderService();
				PurchaseOrderVO purchaseorderVO = purchaseorderSvc.getOneOrder(po_no);
				if (purchaseorderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/purchase_order/pcselect_page.jsp");
					failureView.forward(req, res);
					return;//
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("purchaseorderVO", purchaseorderVO); //  資料庫取出的empVO物件,存入req
				String url = "/back-end/purchase_order/listOneOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/purchase_order/pcselect_page.jsp");
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
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());

				java.sql.Timestamp po_time =  new java.sql.Timestamp(new java.util.Date().getTime());
				
				String po_payment =req.getParameter("po_payment");
				
				String po_delivery = req.getParameter("po_delivery");
				
				Integer po_total =new Integer(req.getParameter("po_total").trim());
				
				
				
				String purchase_detail = req.getParameter("purchase_detail");
				
				Integer po_status = new Integer(req.getParameter("po_status").trim());
//				if(p_status != 0  p_status != 1) {
//					errorMsgs.add("商品狀態請勿空白");
//				}
				

				

				PurchaseOrderVO purchaseorderVO = new PurchaseOrderVO();
				purchaseorderVO.setMem_id(mem_id);
				purchaseorderVO.setPo_time(po_time);
				purchaseorderVO.setPo_payment(po_payment);
				purchaseorderVO.setPo_delivery(po_delivery);
				purchaseorderVO.setPo_total(po_total);
				purchaseorderVO.setPurchase_detail(purchase_detail);
				purchaseorderVO.setPo_status(po_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("purchaseorderVO", purchaseorderVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/purchase_order/addOrder.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				PurchaseOrderService purchaseorderSvc = new PurchaseOrderService();
				PurchaseOrderVO purchaseorderVO1 = new PurchaseOrderVO();
				purchaseorderVO1 = purchaseorderSvc.addOrder( mem_id,  po_time, po_payment,  po_delivery, po_total,
						 purchase_detail, po_status);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/purchase_order/listAllOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/addOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // �Ӧ�listAllProduct.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//				String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer po_no = new Integer(req.getParameter("po_no"));

				/*************************** 2.�}�l�R����� ***************************************/
//				PurchaseOrderDetailsService purchaseorderdetails = new PurchaseOrderDetailsService();
//				purchaseorderdetails.deletePOD(po_no);
				PurchaseOrderService purchaseorderSvc = new PurchaseOrderService();
//				PurchaseOrderVO purchaseorderVO = purchaseorderSvc.getOneOrder(po_no);
				purchaseorderSvc.deleteOrder(po_no);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
//				PurchaseOrderDetailsService purchaseorderdetailsSvc = new PurchaseOrderDetailsService();

//				if(requestURL.equals("/back-end/purchase_order_details/listOrderByPN.jsp") || requestURL.equals("/back-end/purchase_order_details/listAllPD.jsp"))
//					req.setAttribute("listOrderByPN", purchaseorderdetailsSvc.getProductsByPC(productVO.getP_category_no()));
				
				String url ="/back-end/purchase_order/listAllOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				String url ="/back-end/purchase_order/listAllOrder.jsp";

				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.
						getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		
		if ("search".equals(action)) { // �Ӧ�listAllProduct.jsp

	

				Integer po_no = new Integer(req.getParameter("po_no"));

				PurchaseOrderService purchaseorderSvc = new PurchaseOrderService();
				PurchaseOrderVO POVO = new PurchaseOrderVO();
				POVO = purchaseorderSvc.getOneOrder(po_no);
				req.setAttribute("purchaseorderVO", POVO);
					
				String url ="/back-end/purchase_order/listOneOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
	}
		if ("gopay".equals(action)) { // 來自的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				HttpSession session = req.getSession();
				Integer mem_id = Integer.valueOf(req.getParameter("mem_id"));
				Integer po_total = Integer.valueOf(req.getParameter("po_total"));

				java.sql.Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());

				PurchaseOrderVO purchaseorderVO = new PurchaseOrderVO();
				purchaseorderVO.setMem_id(mem_id);
				purchaseorderVO.setPo_total(po_total);
				purchaseorderVO.setPo_time(timestamp);

				/*************************** 2.開始新增資料 ***************************************/

//		Map<Integer, Integer> testmap = (Map<Integer, Integer>) session.getAttribute("testmap");

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				session.setAttribute("purchaseorderVO", purchaseorderVO);
//		session.setAttribute("testmap", testmap);

				String url = "/front-end/order/insertorder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Order/orderselectpageF.jsp");
//				failureView.forward(req, res);
//			}
		}
		if ("insertorderinfo".equals(action) && (req.getParameter("po_payment")).equals("貨到付款") && (req.getParameter("po_delivery")).equals("宅配")) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				HttpSession session = req.getSession();

				Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
				Integer po_total = Integer.valueOf(req.getParameter("po_total"));
				Timestamp po_time = Timestamp.valueOf(req.getParameter("po_time"));
				String po_delivery = req.getParameter("po_delivery");
				String po_payment = req.getParameter("po_payment");
				Integer po_status = Integer.valueOf(req.getParameter("po_status"));
				
				String purchase_detail = req.getParameter("purchase_detail1");
//				String orderDelivery;
//				if (po_delivery.equals("宅配"))
//					{orderDelivery = req.getParameter("po_delivery");}
//				else
//					{orderDelivery = req.getParameter("po_delivery1");}

				PurchaseOrderVO purchaseorderVO = new PurchaseOrderVO();
				purchaseorderVO.setMem_id(mem_id);
				purchaseorderVO.setPo_total(po_total);
				purchaseorderVO.setPo_time(po_time);
				purchaseorderVO.setPo_payment(po_payment);
				purchaseorderVO.setPo_status(po_status);
				purchaseorderVO.setPo_delivery(po_delivery);
				purchaseorderVO.setPurchase_detail(purchase_detail);
				
				/*************************** 2.開始新增資料 ***************************************/
				PurchaseOrderService purchaseorderSvc = new PurchaseOrderService();
				// 新建訂單
//				purchaseorderVO = purchaseorderSvc.addOrder(mem_id,po_time,po_payment,po_delivery,po_total,purchase_detail,po_status);
				
				
				
				ProductService productSvc = new ProductService();
				ProductVO productVO = new ProductVO();
				List<PurchaseOrderDetailsVO> PODlist= new ArrayList<PurchaseOrderDetailsVO>();
				
				
				
				
				
				
				// 更新訂單資訊
//				orderBuyVO = orderBuySvc.updateOrderbuyInfo(orderOther, orderRecipient, orderEmail, orderDelivery,
//						orderTel, orderBuyVO.getOrderID());
//		System.out.println("pay:"+orderPaying);
//		System.out.println("send:"+orderSend);
//		System.out.println("id:"+ orderBuyVO.getOrderID());
//				purchaseorderVO = purchaseorderSvc.updateOrderbuypss(orderPaying, orderSend, 2, orderBuyVO.getOrderID());
//				purchaseorderVO = purchaseorderSvc.getOneOrder(purchaseorderVO.getPo_no());
				// 新建訂單細項
				PurchaseOrderDetailsService PODSvc = new PurchaseOrderDetailsService();
				// 物品細項的map取值(物品ID,物品數量)
//				Map<Integer, Integer> testmap = (Map<Integer, Integer>) session.getAttribute("testmap");
//				Set<Integer> setitemID = (Set<Integer>) testmap.keySet();
//				for (Integer itemid : setitemID) {
////			System.out.println(itemid + ":" + testmap.get(itemid));
//					if (testmap.get(itemid) == 0)
//						continue;
//					commodityDeVO = commoDeSvc.addOrderDe(itemid, testmap.get(itemid), orderBuyVO.getOrderID());
//				}
		Map<Integer, Integer> shoppingList = (Map<Integer, Integer>) session.getAttribute("shoppingList");
		Set<Integer> setproudct_no = (Set<Integer>)shoppingList.keySet();
		for(Integer product_no: setproudct_no) {
//			System.out.println(itemid + ":" + shoppingList.get(itemid));
			if(shoppingList.get(product_no)==0)
				continue;
			productVO = productSvc.getOneProduct(product_no);
			PurchaseOrderDetailsVO PODVO = new PurchaseOrderDetailsVO();
			PODVO.setP_price((productVO.getP_price())*shoppingList.get(product_no));
			PODVO.setProduct_no(product_no);
			PODVO.setQuantity(shoppingList.get(product_no));
			System.out.println((productVO.getP_price())*shoppingList.get(product_no));
			System.out.println(product_no + "=======");
			System.out.println(shoppingList.get(product_no));
//			PODVO = PODSvc.addPod( product_no, shoppingList.get(product_no), (productVO.getP_price())*shoppingList.get(product_no));
			PODlist.add(PODVO);
		}
		
		System.out.println(PODlist);
		purchaseorderSvc.insertWithPods(purchaseorderVO, PODlist);
		shoppingList.clear();
		session.setAttribute("count",0);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//		session.setAttribute("orderBuyVO2", orderBuyVO2);
				session.setAttribute("purchaseorderVO", purchaseorderVO);
//				session.setAttribute("purchaseorderdetailsVO", PODVO);

				String url = "/front-end/product/shop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/order/insertorder.jsp");
//				failureView.forward(req, res);
//			}
		}
		if ("insertorderinfo".equals(action) && (req.getParameter("po_payment")).equals("貨到付款") && (req.getParameter("po_delivery")).equals("超商取貨")) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				HttpSession session = req.getSession();

				Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
				Integer po_total = Integer.valueOf(req.getParameter("po_total"));
				Timestamp po_time = Timestamp.valueOf(req.getParameter("po_time"));
				String po_delivery = req.getParameter("po_delivery");
				String po_payment = req.getParameter("po_payment");
				Integer po_status = Integer.valueOf(req.getParameter("po_status"));
				
				String purchase_detail = req.getParameter("purchase_detail2");
//				String orderDelivery;
//				if (po_delivery.equals("宅配"))
//					{orderDelivery = req.getParameter("po_delivery");}
//				else
//					{orderDelivery = req.getParameter("po_delivery1");}

				PurchaseOrderVO purchaseorderVO = new PurchaseOrderVO();
				purchaseorderVO.setMem_id(mem_id);
				purchaseorderVO.setPo_total(po_total);
				purchaseorderVO.setPo_time(po_time);
				purchaseorderVO.setPo_payment(po_payment);
				purchaseorderVO.setPo_status(po_status);
				purchaseorderVO.setPo_delivery(po_delivery);
				purchaseorderVO.setPurchase_detail(purchase_detail);
				
				/*************************** 2.開始新增資料 ***************************************/
				PurchaseOrderService purchaseorderSvc = new PurchaseOrderService();
				// 新建訂單
//				purchaseorderVO = purchaseorderSvc.addOrder(mem_id,po_time,po_payment,po_delivery,po_total,purchase_detail,po_status);
				
				
				
				ProductService productSvc = new ProductService();
				ProductVO productVO = new ProductVO();
				List<PurchaseOrderDetailsVO> PODlist= new ArrayList<PurchaseOrderDetailsVO>();
				
				
				
				
				
				
				// 更新訂單資訊
//				orderBuyVO = orderBuySvc.updateOrderbuyInfo(orderOther, orderRecipient, orderEmail, orderDelivery,
//						orderTel, orderBuyVO.getOrderID());
//		System.out.println("pay:"+orderPaying);
//		System.out.println("send:"+orderSend);
//		System.out.println("id:"+ orderBuyVO.getOrderID());
//				purchaseorderVO = purchaseorderSvc.updateOrderbuypss(orderPaying, orderSend, 2, orderBuyVO.getOrderID());
//				purchaseorderVO = purchaseorderSvc.getOneOrder(purchaseorderVO.getPo_no());
				// 新建訂單細項
				PurchaseOrderDetailsService PODSvc = new PurchaseOrderDetailsService();
				// 物品細項的map取值(物品ID,物品數量)
//				Map<Integer, Integer> testmap = (Map<Integer, Integer>) session.getAttribute("testmap");
//				Set<Integer> setitemID = (Set<Integer>) testmap.keySet();
//				for (Integer itemid : setitemID) {
////			System.out.println(itemid + ":" + testmap.get(itemid));
//					if (testmap.get(itemid) == 0)
//						continue;
//					commodityDeVO = commoDeSvc.addOrderDe(itemid, testmap.get(itemid), orderBuyVO.getOrderID());
//				}
		Map<Integer, Integer> shoppingList = (Map<Integer, Integer>) session.getAttribute("shoppingList");
		Set<Integer> setproudct_no = (Set<Integer>)shoppingList.keySet();
		for(Integer product_no: setproudct_no) {
//			System.out.println(itemid + ":" + shoppingList.get(itemid));
			if(shoppingList.get(product_no)==0)
				continue;
			productVO = productSvc.getOneProduct(product_no);
			PurchaseOrderDetailsVO PODVO = new PurchaseOrderDetailsVO();
			PODVO.setP_price((productVO.getP_price())*shoppingList.get(product_no));
			PODVO.setProduct_no(product_no);
			PODVO.setQuantity(shoppingList.get(product_no));
			System.out.println((productVO.getP_price())*shoppingList.get(product_no));
			System.out.println(product_no + "=======");
			System.out.println(shoppingList.get(product_no));
//			PODVO = PODSvc.addPod( product_no, shoppingList.get(product_no), (productVO.getP_price())*shoppingList.get(product_no));
			PODlist.add(PODVO);
		}
		
		System.out.println(PODlist);
		purchaseorderSvc.insertWithPods(purchaseorderVO, PODlist);
		shoppingList.clear();
		session.setAttribute("count",0);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//		session.setAttribute("orderBuyVO2", orderBuyVO2);
				session.setAttribute("purchaseorderVO", purchaseorderVO);
//				session.setAttribute("purchaseorderdetailsVO", PODVO);

				String url = "/front-end/product/shop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/order/insertorder.jsp");
//				failureView.forward(req, res);
//			}
		}
		if ("insertorderinfo".equals(action) && (req.getParameter("po_payment")).equals("信用卡") && (req.getParameter("po_delivery")).equals("宅配")) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				HttpSession session = req.getSession();

				Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
				Integer po_total = Integer.valueOf(req.getParameter("po_total"));
				Timestamp po_time = Timestamp.valueOf(req.getParameter("po_time"));
				String po_delivery = req.getParameter("po_delivery");
				String po_payment = req.getParameter("po_payment");
				Integer po_status = Integer.valueOf(req.getParameter("po_status"));
				
				String purchase_detail = req.getParameter("purchase_detail1");
				String purchase_detailC = req.getParameter("purchase_detailC");
				String totalD = purchase_detail + "  信用卡卡號: " + purchase_detailC;
				System.out.println(purchase_detailC);
//				String orderDelivery;
//				if (po_delivery.equals("宅配"))
//					{orderDelivery = req.getParameter("po_delivery");}
//				else
//					{orderDelivery = req.getParameter("po_delivery1");}

				PurchaseOrderVO purchaseorderVO = new PurchaseOrderVO();
				purchaseorderVO.setMem_id(mem_id);
				purchaseorderVO.setPo_total(po_total);
				purchaseorderVO.setPo_time(po_time);
				purchaseorderVO.setPo_payment(po_payment);
				purchaseorderVO.setPo_status(po_status);
				purchaseorderVO.setPo_delivery(po_delivery);
				purchaseorderVO.setPurchase_detail(totalD);
				/*************************** 2.開始新增資料 ***************************************/
				PurchaseOrderService purchaseorderSvc = new PurchaseOrderService();
				// 新建訂單
//				purchaseorderVO = purchaseorderSvc.addOrder(mem_id,po_time,po_payment,po_delivery,po_total,purchase_detail,po_status);
				
				
				
				ProductService productSvc = new ProductService();
				ProductVO productVO = new ProductVO();
				List<PurchaseOrderDetailsVO> PODlist= new ArrayList<PurchaseOrderDetailsVO>();
				
				
				
				
				
				
				// 更新訂單資訊
//				orderBuyVO = orderBuySvc.updateOrderbuyInfo(orderOther, orderRecipient, orderEmail, orderDelivery,
//						orderTel, orderBuyVO.getOrderID());
//		System.out.println("pay:"+orderPaying);
//		System.out.println("send:"+orderSend);
//		System.out.println("id:"+ orderBuyVO.getOrderID());
//				purchaseorderVO = purchaseorderSvc.updateOrderbuypss(orderPaying, orderSend, 2, orderBuyVO.getOrderID());
//				purchaseorderVO = purchaseorderSvc.getOneOrder(purchaseorderVO.getPo_no());
				// 新建訂單細項
				PurchaseOrderDetailsService PODSvc = new PurchaseOrderDetailsService();
				// 物品細項的map取值(物品ID,物品數量)
//				Map<Integer, Integer> testmap = (Map<Integer, Integer>) session.getAttribute("testmap");
//				Set<Integer> setitemID = (Set<Integer>) testmap.keySet();
//				for (Integer itemid : setitemID) {
////			System.out.println(itemid + ":" + testmap.get(itemid));
//					if (testmap.get(itemid) == 0)
//						continue;
//					commodityDeVO = commoDeSvc.addOrderDe(itemid, testmap.get(itemid), orderBuyVO.getOrderID());
//				}
		Map<Integer, Integer> shoppingList = (Map<Integer, Integer>) session.getAttribute("shoppingList");
		Set<Integer> setproudct_no = (Set<Integer>)shoppingList.keySet();
		for(Integer product_no: setproudct_no) {
//			System.out.println(itemid + ":" + shoppingList.get(itemid));
			if(shoppingList.get(product_no)==0)
				continue;
			productVO = productSvc.getOneProduct(product_no);
			PurchaseOrderDetailsVO PODVO = new PurchaseOrderDetailsVO();
			PODVO.setP_price((productVO.getP_price())*shoppingList.get(product_no));
			PODVO.setProduct_no(product_no);
			PODVO.setQuantity(shoppingList.get(product_no));
			System.out.println((productVO.getP_price())*shoppingList.get(product_no));
			System.out.println(product_no + "=======");
			System.out.println(shoppingList.get(product_no));
//			PODVO = PODSvc.addPod( product_no, shoppingList.get(product_no), (productVO.getP_price())*shoppingList.get(product_no));
			PODlist.add(PODVO);
		}
		
		System.out.println(PODlist);
		purchaseorderSvc.insertWithPods(purchaseorderVO, PODlist);
		shoppingList.clear();
		session.setAttribute("count",0);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//		session.setAttribute("orderBuyVO2", orderBuyVO2);
				session.setAttribute("purchaseorderVO", purchaseorderVO);
//				session.setAttribute("purchaseorderdetailsVO", PODVO);

				String url = "/front-end/product/shop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/order/insertorder.jsp");
//				failureView.forward(req, res);
//			}
		}
		if ("insertorderinfo".equals(action) && (req.getParameter("po_payment")).equals("信用卡") && (req.getParameter("po_delivery")).equals("超商取貨")) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				HttpSession session = req.getSession();

				Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
				Integer po_total = Integer.valueOf(req.getParameter("po_total"));
				Timestamp po_time = Timestamp.valueOf(req.getParameter("po_time"));
				String po_delivery = req.getParameter("po_delivery");
				String po_payment = req.getParameter("po_payment");
				Integer po_status = Integer.valueOf(req.getParameter("po_status"));
				
				String purchase_detail = req.getParameter("purchase_detail2");
				String purchase_detailC = req.getParameter("purchase_detailC");
				String totalD = purchase_detail + "  信用卡卡號: " + purchase_detailC;
				System.out.println(purchase_detailC);
//				String orderDelivery;
//				if (po_delivery.equals("宅配"))
//					{orderDelivery = req.getParameter("po_delivery");}
//				else
//					{orderDelivery = req.getParameter("po_delivery1");}

				PurchaseOrderVO purchaseorderVO = new PurchaseOrderVO();
				purchaseorderVO.setMem_id(mem_id);
				purchaseorderVO.setPo_total(po_total);
				purchaseorderVO.setPo_time(po_time);
				purchaseorderVO.setPo_payment(po_payment);
				purchaseorderVO.setPo_status(po_status);
				purchaseorderVO.setPo_delivery(po_delivery);
				purchaseorderVO.setPurchase_detail(totalD);
				/*************************** 2.開始新增資料 ***************************************/
				PurchaseOrderService purchaseorderSvc = new PurchaseOrderService();
				// 新建訂單
//				purchaseorderVO = purchaseorderSvc.addOrder(mem_id,po_time,po_payment,po_delivery,po_total,purchase_detail,po_status);
				
				
				
				ProductService productSvc = new ProductService();
				ProductVO productVO = new ProductVO();
				List<PurchaseOrderDetailsVO> PODlist= new ArrayList<PurchaseOrderDetailsVO>();
				
				
				
				
				
				
				// 更新訂單資訊
//				orderBuyVO = orderBuySvc.updateOrderbuyInfo(orderOther, orderRecipient, orderEmail, orderDelivery,
//						orderTel, orderBuyVO.getOrderID());
//		System.out.println("pay:"+orderPaying);
//		System.out.println("send:"+orderSend);
//		System.out.println("id:"+ orderBuyVO.getOrderID());
//				purchaseorderVO = purchaseorderSvc.updateOrderbuypss(orderPaying, orderSend, 2, orderBuyVO.getOrderID());
//				purchaseorderVO = purchaseorderSvc.getOneOrder(purchaseorderVO.getPo_no());
				// 新建訂單細項
				PurchaseOrderDetailsService PODSvc = new PurchaseOrderDetailsService();
				// 物品細項的map取值(物品ID,物品數量)
//				Map<Integer, Integer> testmap = (Map<Integer, Integer>) session.getAttribute("testmap");
//				Set<Integer> setitemID = (Set<Integer>) testmap.keySet();
//				for (Integer itemid : setitemID) {
////			System.out.println(itemid + ":" + testmap.get(itemid));
//					if (testmap.get(itemid) == 0)
//						continue;
//					commodityDeVO = commoDeSvc.addOrderDe(itemid, testmap.get(itemid), orderBuyVO.getOrderID());
//				}
		Map<Integer, Integer> shoppingList = (Map<Integer, Integer>) session.getAttribute("shoppingList");
		Set<Integer> setproudct_no = (Set<Integer>)shoppingList.keySet();
		for(Integer product_no: setproudct_no) {
//			System.out.println(itemid + ":" + shoppingList.get(itemid));
			if(shoppingList.get(product_no)==0)
				continue;
			productVO = productSvc.getOneProduct(product_no);
			PurchaseOrderDetailsVO PODVO = new PurchaseOrderDetailsVO();
			PODVO.setP_price((productVO.getP_price())*shoppingList.get(product_no));
			PODVO.setProduct_no(product_no);
			PODVO.setQuantity(shoppingList.get(product_no));
			System.out.println((productVO.getP_price())*shoppingList.get(product_no));
			System.out.println(product_no + "=======");
			System.out.println(shoppingList.get(product_no));
//			PODVO = PODSvc.addPod( product_no, shoppingList.get(product_no), (productVO.getP_price())*shoppingList.get(product_no));
			PODlist.add(PODVO);
		}
		
		System.out.println(PODlist);
		purchaseorderSvc.insertWithPods(purchaseorderVO, PODlist);
		shoppingList.clear();
		session.setAttribute("count",0);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//		session.setAttribute("orderBuyVO2", orderBuyVO2);
				session.setAttribute("purchaseorderVO", purchaseorderVO);
//				session.setAttribute("purchaseorderdetailsVO", PODVO);

				String url = "/front-end/product/shop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/order/insertorder.jsp");
//				failureView.forward(req, res);
//			}
		}
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer po_no = Integer.valueOf(req.getParameter("po_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				PurchaseOrderService purchaseorderSvc = new PurchaseOrderService();
				PurchaseOrderVO purchaseorderVO = purchaseorderSvc.getOneOrder(po_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("purchaseorderVO", purchaseorderVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/purchase_order/update_Order_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/purchase_order/listAllOrder.jsp");
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
				Integer po_no = new Integer(req.getParameter("po_no").trim());

				Integer mem_id = new Integer(req.getParameter("mem_id").trim());

				java.sql.Timestamp po_time =  new java.sql.Timestamp(new java.util.Date().getTime());
				
				String po_payment =req.getParameter("po_payment");
				
				String po_delivery = req.getParameter("po_delivery");
				
				Integer po_total =new Integer(req.getParameter("po_total").trim());
				
				
				
				String purchase_detail = req.getParameter("purchase_detail");
				
				Integer po_status = new Integer(req.getParameter("po_status").trim());
				
				PurchaseOrderVO purchaseorderVO = new PurchaseOrderVO();
				
				purchaseorderVO.setPo_no(po_no);
				purchaseorderVO.setMem_id(mem_id);
				purchaseorderVO.setPo_time(po_time);
				purchaseorderVO.setPo_payment(po_payment);
				purchaseorderVO.setPo_delivery(po_delivery);
				purchaseorderVO.setPo_total(po_total);
				purchaseorderVO.setPurchase_detail(purchase_detail);
				purchaseorderVO.setPo_status(po_status);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("purchaseorderVO", purchaseorderVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/purchase_order/update_Order_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.開始修改資料 *****************************************/
				PurchaseOrderService purchaseorderSvc = new PurchaseOrderService();
				purchaseorderVO = purchaseorderSvc.updateOrder(po_no, mem_id,   po_time, po_payment,  po_delivery, po_total,
						 purchase_detail, po_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("purchaseorderVO", purchaseorderVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/purchase_order/listAllOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.
						getRequestDispatcher("/back-end/purchase_order/update_Order_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	

	}

}