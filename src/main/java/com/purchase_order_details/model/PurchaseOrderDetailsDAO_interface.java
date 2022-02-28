package com.purchase_order_details.model;

import java.util.*;

public interface PurchaseOrderDetailsDAO_interface {
	public void insert(PurchaseOrderDetailsVO purchaseorderdetailsVO);
	public void update(PurchaseOrderDetailsVO purchaseorderdetailsVO);
	public void delete(Integer po_no);
	public PurchaseOrderDetailsVO findByPrimaryKey(Integer po_no);
	public List<PurchaseOrderDetailsVO> getAll();
	public List<PurchaseOrderDetailsVO> getPODByPN(Integer po_no);
	
	
	
	public void insert2 (PurchaseOrderDetailsVO purchaseorderdetailsVO , java.sql.Connection con);
	

}








   

   
    
    

