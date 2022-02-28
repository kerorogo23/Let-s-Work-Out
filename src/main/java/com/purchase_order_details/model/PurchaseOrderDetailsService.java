package com.purchase_order_details.model;

import java.util.List;



public class PurchaseOrderDetailsService {


	private PurchaseOrderDetailsDAO_interface dao;

	public PurchaseOrderDetailsService() {
		dao = new PurchaseOrderDetailsJDBCDAO();
	}

	public PurchaseOrderDetailsVO addPod(Integer product_no ,Integer quantity, Integer p_price) {

		PurchaseOrderDetailsVO podVO = new PurchaseOrderDetailsVO();
		podVO.setProduct_no(product_no);
		podVO.setQuantity(quantity);
		podVO.setP_price(p_price);
		
		dao.insert(podVO);

		return podVO;
	}

	public PurchaseOrderDetailsVO updatePod(Integer quantity,Integer p_price) {

		PurchaseOrderDetailsVO podVO = new PurchaseOrderDetailsVO();

		
		
		podVO.setQuantity(quantity);
		podVO.setP_price(p_price);
		
		dao.update(podVO);

		return podVO;
	}

	public void deletePOD(Integer po_no) {
		dao.delete(po_no);
	}

	public PurchaseOrderDetailsVO getOnePOD(Integer po_no) {
		return dao.findByPrimaryKey(po_no);
	}


	public List<PurchaseOrderDetailsVO> getAll() {
		return dao.getAll();
	}
	
	public List<PurchaseOrderDetailsVO> getPODByPN(Integer po_no) {
		return dao.getPODByPN(po_no);
	}
}
