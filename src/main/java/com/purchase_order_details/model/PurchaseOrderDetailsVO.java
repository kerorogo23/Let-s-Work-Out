package com.purchase_order_details.model;

import java.sql.Date;

public class PurchaseOrderDetailsVO implements java.io.Serializable {
	
	private Integer po_no;
	private Integer product_no;
	private Integer quantity;
	private Integer p_price;
	public Integer getPo_no() {
		return po_no;
	}
	public void setPo_no(Integer po_no) {
		this.po_no = po_no;
	}
	public Integer getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getP_price() {
		return p_price;
	}
	public void setP_price(Integer p_price) {
		this.p_price = p_price;
	}

}
