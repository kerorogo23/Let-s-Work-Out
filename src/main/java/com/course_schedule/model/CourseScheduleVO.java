package com.course_schedule.model;
import java.sql.Date;
import java.sql.Timestamp;

public class CourseScheduleVO implements java.io.Serializable{
	
	
	private Integer scheduleNo;
	private Integer courseOrderNo;
	private Timestamp reserveTime;
	private Integer courseStatus;
	private Timestamp createdTime;
	
	public Integer getScheduleNo() {
		return scheduleNo;
	}
	
	@Override
	public String toString() {
		return "CourseScheduleVO [scheduleNo=" + scheduleNo + ", courseOrderNo=" + courseOrderNo + ", reserveTime=" + reserveTime + ", courseStatus="
				+ courseStatus + ", createdTime=" + createdTime + "]";
	}
	
	public void setScheduleNo(Integer scheduleNo) {
		this.scheduleNo = scheduleNo;
	}
	
	public Integer getCourseOrderNo() {
		return courseOrderNo;
	}
	
	public void setCourseOrderNo(Integer courseOrderNo) {
		this.courseOrderNo = courseOrderNo;
	}
	
	public Timestamp getReserveTime() {
		return reserveTime;
	}
	
	public void setReserveTime(Timestamp reserveTime) {
		this.reserveTime = reserveTime;
	}
	
	public Integer getCourseStatus() {
		return courseStatus;
	}
	
	public void setCourseStatus(Integer courseStatus) {
		this.courseStatus = courseStatus;
	}
	
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
		
}
