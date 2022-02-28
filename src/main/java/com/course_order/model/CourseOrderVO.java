package com.course_order.model;

import java.sql.*;

public class CourseOrderVO implements java.io.Serializable{
	
	private Integer courseOrderNo;
	private Integer courseId;
	private Integer memId;
	private Integer courseOrderHours;
	private Integer courseOrderPrice;
	private Integer courseOrderStatus;
	private Timestamp createdTime;
	
	public Integer getCourseOrderNo() {
		return courseOrderNo;
	}
	@Override
	public String toString() {
		return "CourseOrderVO [courseOrderNo=" + courseOrderNo + ", courseId=" + courseId + ", memId=" + memId + ", courseOrderHours="
				+ courseOrderHours + ", courseOrderPrice=" + courseOrderPrice + ", createdTime=" + createdTime + "]";
	}
	public void setCourseOrderNo(Integer courseOrderNo) {
		this.courseOrderNo = courseOrderNo;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getCourseOrderHours() {
		return courseOrderHours;
	}
	public void setCourseOrderHours(Integer courseOrderHours) {
		this.courseOrderHours = courseOrderHours;
	}
	public Integer getCourseOrderPrice() {
		return courseOrderPrice;
	}
	public void setCourseOrderPrice(Integer courseOrderPrice) {
		this.courseOrderPrice = courseOrderPrice;
	}
	public Integer getCourseOrderStatus() {
		return courseOrderStatus;
	}
	public void setCourseOrderStatus(Integer courseOrderStatus) {
		this.courseOrderStatus = courseOrderStatus;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	
}
