package com.product.model;
import java.sql.Date;

public class ProductVO implements java.io.Serializable{
	private Integer product_no;
	private Integer p_category_no;
	private Integer p_price;
	private Integer p_status;
	private String p_name;
	private String p_detail;
	private Date p_upload_time;
	public Integer getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}
	public Integer getP_category_no() {
		return p_category_no;
	}
	public void setP_category_no(Integer p_category_no) {
		this.p_category_no = p_category_no;
	}
	public Integer getP_price() {
		return p_price;
	}
	public void setP_price(Integer p_price) {
		this.p_price = p_price;
	}
	public Integer getP_status() {
		return p_status;
	}
	public void setP_status(Integer p_status) {
		this.p_status = p_status;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_detail() {
		return p_detail;
	}
	public void setP_detail(String p_detail) {
		this.p_detail = p_detail;
	}
	public Date getP_upload_time() {
		return p_upload_time;
	}
	public void setP_upload_time(Date p_upload_time) {
		this.p_upload_time = p_upload_time;
	}
}
	