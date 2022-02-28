package com.pro.model;
import java.sql.Date;

public class ProVO implements java.io.Serializable{
	
	
	private Integer proId;
	private Integer workId;
	private String proResume;
	private String expr;
	private byte[] proPhoto;
	
	public Integer getProId() {
		return proId;
	}
	@Override
	public String toString() {
		return "ProVO [proId=" + proId + ", workId=" + workId + ", proResume=" + proResume + ", expr="
				+ expr + ", proPhoto=" + proPhoto + "]";
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public Integer getWorkId() {
		return workId;
	}
	public void setWorkId(Integer workId) {
		this.workId = workId;
	}
	public String getProResume() {
		return proResume;
	}
	public void setProResume(String proResume) {
		this.proResume = proResume;
	}
	public String getExpr() {
		return expr;
	}
	public void setExpr(String expr) {
		this.expr = expr;
	}
	public byte[] getProPhoto() {
		return proPhoto;
	}
	public void setProPhoto(byte[] proPhoto) {
		this.proPhoto = proPhoto;
	}
	
	
}
