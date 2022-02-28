package com.purchase_order.model;

import java.util.*;


import com.purchase_order_details.model.PurchaseOrderDetailsVO;



public interface PurchaseOrderDAO_interface {

	public void insert(PurchaseOrderVO purchaseorderVO);

	public void update(PurchaseOrderVO purchaseorderVO);

	public void delete(Integer po_no);

	public PurchaseOrderVO findByPrimaryKey(Integer po_no);

	public List<PurchaseOrderVO> getAll();
	 
	public Set<PurchaseOrderDetailsVO> getPodsByPo_no(Integer po_no);
	 //同時新增訂單與訂單明細 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
	  public void insertWithPods(PurchaseOrderVO purchaseorderVO , List<PurchaseOrderDetailsVO> list);

	
	// �U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//    public List<ProductVO> getAll(Map<String, String[]> map); 

}
