package com.course.model;
import java.sql.Date;

public class CourseVO implements java.io.Serializable{
	
	
	private Integer courseId;
	private Integer proId;
	private Integer coursePrice;
	private Integer courseHours;
	private String courseDetail;
	
	public Integer getCourseId() {
		return courseId;
	}
	@Override
	public String toString() {
		return "CourseVO [courseId=" + courseId + ", proId=" + proId + ", coursePrice=" + coursePrice + ", courseHours="
				+ courseHours + ", courseDetail=" + courseDetail + "]";
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public Integer getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(Integer coursePrice) {
		this.coursePrice = coursePrice;
	}
	public Integer getCourseHours() {
		return courseHours;
	}
	public void setCourseHours(Integer courseHours) {
		this.courseHours = courseHours;
	}
	public String getCourseDetail() {
		return courseDetail;
	}
	public void setCourseDetail(String courseDetail) {
		this.courseDetail = courseDetail;
	}
	
}
