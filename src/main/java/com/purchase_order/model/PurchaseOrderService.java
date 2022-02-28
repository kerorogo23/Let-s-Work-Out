package com.purchase_order.model;

import java.util.List;

import com.product.model.ProductVO;
import com.purchase_order_details.model.PurchaseOrderDetailsVO;



public class PurchaseOrderService {
	private PurchaseOrderDAO_interface dao;

	public PurchaseOrderService() {
		dao = new PurchaseOrderJDBCDAO();
	}

	public PurchaseOrderVO addOrder(Integer mem_id, java.sql.Timestamp  po_time,String po_payment, String po_delivery,Integer po_total,
		String purchase_detail,Integer po_status) {

		PurchaseOrderVO purchaseorderVO = new PurchaseOrderVO();

		purchaseorderVO.setMem_id(mem_id);
		purchaseorderVO.setPo_time(po_time);
		purchaseorderVO.setPo_payment(po_payment);
		purchaseorderVO.setPo_delivery(po_delivery);
		purchaseorderVO.setPo_total(po_total);
		purchaseorderVO.setPurchase_detail(purchase_detail);
		purchaseorderVO.setPo_status(po_status);
		dao.insert(purchaseorderVO);

		return purchaseorderVO;
	}
//	public viod addOrder(PurchaseOrderVO purchaseorderVO) {
//		Integer newPo_no = dao.insert(purchaseorderVO);
//		
//	
//	}

	public PurchaseOrderVO updateOrder(Integer po_no,Integer mem_id, java.sql.Timestamp  po_time,String po_payment, String po_delivery,Integer po_total,
			String purchase_detail,Integer po_status) {

		PurchaseOrderVO purchaseorderVO = new PurchaseOrderVO();
		purchaseorderVO.setPo_no(po_no);
		purchaseorderVO.setMem_id(mem_id);		
		purchaseorderVO.setPo_time(po_time);
		purchaseorderVO.setPo_payment(po_payment);
		purchaseorderVO.setPo_delivery(po_delivery);
		purchaseorderVO.setPo_total(po_total);
		purchaseorderVO.setPurchase_detail(purchase_detail);
		purchaseorderVO.setPo_status(po_status);
		dao.update(purchaseorderVO);

		return purchaseorderVO;
	}

	public void deleteOrder(Integer po_no) {
		dao.delete(po_no);
	}
	public void insertWithPods(PurchaseOrderVO purchaseorderVO,List<PurchaseOrderDetailsVO> list) {
		dao.insertWithPods( purchaseorderVO, list);
	}
	
	public PurchaseOrderVO getOneOrder(Integer purchaseorder) {
		return dao.findByPrimaryKey(purchaseorder);
	}

	public List<PurchaseOrderVO> getAll() {
		return dao.getAll();
	}
}


