package com.grouphour.model;

import java.sql.Date;
import java.sql.Timestamp;


public class GroupHourVO implements java.io.Serializable{
	private Integer groupTimeNo;
	private Integer groupClassNo;
	private Date groupDate;
	private String groupStartingTime;
	private Timestamp registStartTime;
	private Timestamp registEndTime;
	private Integer courseStatus;
	public Integer getGroupTimeNo() {
		return groupTimeNo;
	}
	public void setGroupTimeNo(Integer groupTimeNo) {
		this.groupTimeNo = groupTimeNo;
	}
	public Integer getGroupClassNo() {
		return groupClassNo;
	}
	public void setGroupClassNo(Integer groupClassNo) {
		this.groupClassNo = groupClassNo;
	}
	public Date getGroupDate() {
		return groupDate;
	}
	public void setGroupDate(Date groupDate) {
		this.groupDate = groupDate;
	}
	public String getGroupStartingTime() {
		return groupStartingTime;
	}
	public void setGroupStartingTime(String groupStartingTime) {
		this.groupStartingTime = groupStartingTime;
	}
	public void setGroupStartingTime(int groupTime) {
		String groupStartingTime = Integer.toString(groupTime);
		this.groupStartingTime = groupStartingTime;
	}
	public Timestamp getRegistStartTime() {
		return registStartTime;
	}
	public void setRegistStartTime(Timestamp registStartTime) {
		this.registStartTime = registStartTime;
	}
	public void setRegistStartTime(String startTime) {
		registStartTime = java.sql.Timestamp.valueOf(startTime) ;
		this.registStartTime = registStartTime;
	}
	public Timestamp getRegistEndTime() {
		return registEndTime;
	}
	public void setRegistEndTime(Timestamp registEndTime) {
		this.registEndTime = registEndTime;
	}
	public Integer getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(Integer courseStatus) {
		this.courseStatus = courseStatus;
	}
}
