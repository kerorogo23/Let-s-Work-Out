package com.purchase_order.model;

import java.sql.Date;
import java.sql.Timestamp;

public class PurchaseOrderVO  implements java.io.Serializable{
	private Integer po_no;
	private Integer mem_id;
	private Timestamp po_time;
	private String po_payment;
	private String po_delivery;
	private Integer po_total;
	private String purchase_detail;
	private Integer po_status;
	public Integer getPo_no() {
		return po_no;
	}
	public void setPo_no(Integer po_no) {
		this.po_no = po_no;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Timestamp getPo_time() {
		return po_time;
	}
	public void setPo_time(Timestamp po_time) {
		this.po_time = po_time;
	}
	public String getPo_payment() {
		return po_payment;
	}
	public void setPo_payment(String po_payment) {
		this.po_payment = po_payment;
	}
	public String getPo_delivery() {
		return po_delivery;
	}
	public void setPo_delivery(String po_delivery) {
		this.po_delivery = po_delivery;
	}
	public Integer getPo_total() {
		return po_total;
	}
	public void setPo_total(Integer po_total) {
		this.po_total = po_total;
	}
	public String getPurchase_detail() {
		return purchase_detail;
	}
	public void setPurchase_detail(String purchase_detail) {
		this.purchase_detail = purchase_detail;
	}
	public Integer getPo_status() {
		return po_status;
	}
	public void setPo_status(Integer po_status) {
		this.po_status = po_status;
	}

}
