package com.product_photo.model;

import java.sql.Blob;

public class ProductPhotoVO  implements java.io.Serializable {
	private Integer p_photo_no;
	private Integer product_no;
	private byte[] p_photo;
	public Integer getP_photo_no() {
		return p_photo_no;
	}
	public void setP_photo_no(Integer p_photo_no) {
		this.p_photo_no = p_photo_no;
	}
	public Integer getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}
	public byte[] getP_photo() {
		return p_photo;
	}
	public void setP_photo(byte[] p_photo) {
		this.p_photo = p_photo;
	}
	
	}
