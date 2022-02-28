package com.groupclass.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

public class GroupClassVO implements java.io.Serializable{
	private Integer groupClassNo;
	private Integer proId;
	private Integer classTypeNo;
	private String className;
	private String loc;
	private Integer groupMax;
	private Integer groupMin;
	private Integer groupClassPrice;
	private String groupClassDetail;
	private byte[] groupClassPic;
	
	public Integer getGroupClassNo() {
		return groupClassNo;
	}
	public void setGroupClassNo(Integer groupClassNo) {
		this.groupClassNo = groupClassNo;
	}
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public Integer getClassTypeNo() {
		return classTypeNo;
	}
	public void setClassTypeNo(Integer classTypeNo) {
		this.classTypeNo = classTypeNo;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public Integer getGroupMax() {
		return groupMax;
	}
	public void setGroupMax(Integer groupMax) {
		this.groupMax = groupMax;
	}
	public Integer getGroupMin() {
		return groupMin;
	}
	public void setGroupMin(Integer groupMin) {
		this.groupMin = groupMin;
	}
	public Integer getGroupClassPrice() {
		return groupClassPrice;
	}
	public void setGroupClassPrice(Integer groupClassPrice) {
		this.groupClassPrice = groupClassPrice;
	}
	public String getGroupClassDetail() {
		return groupClassDetail;
	}
	public void setGroupClassDetail(String groupClassDetail) {
		this.groupClassDetail = groupClassDetail;
	}
	public byte[] getGroupClassPic() {
		return groupClassPic;
	}
	public void setGroupClassPic(byte[] groupClassPic) {
		this.groupClassPic = groupClassPic;
		
	}
	public void setGroupClassPic(InputStream in) throws IOException{
		byte[] groupClassPic = new byte[in.available()];
		in.read(groupClassPic);
		in.close();
		this.groupClassPic = groupClassPic;
		
	}
}
