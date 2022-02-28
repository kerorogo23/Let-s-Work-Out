package com.grouporder.model;

import java.sql.Timestamp;

public class GroupOrderVO implements java.io.Serializable{
	private Integer groupOrderNo;
	private Integer memId;
	private Integer groupTimeNo;
	private Timestamp groupOrderTime;
	private Integer groupOrderStatus;
	public Integer getGroupOrderNo() {
		return groupOrderNo;
	}
	public void setGroupOrderNo(Integer groupOrderNo) {
		this.groupOrderNo = groupOrderNo;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getGroupTimeNo() {
		return groupTimeNo;
	}
	public void setGroupTimeNo(Integer groupTimeNo) {
		this.groupTimeNo = groupTimeNo;
	}
	public Timestamp getGroupOrderTime() {
		return groupOrderTime;
	}
	public void setGroupOrderTime(Timestamp groupOrderTime) {
		this.groupOrderTime = groupOrderTime;
	}
	public Integer getGroupOrderStatus() {
		return groupOrderStatus;
	}
	public void setGroupOrderStatus(Integer groupOrderStatus) {
		this.groupOrderStatus = groupOrderStatus;
	}
	
	
}
