package com.members.model;
import java.sql.Date;

public class MembersVO implements java.io.Serializable{
	private Integer memId; 
	private String memName;
	private String memAcc;
	private String memPsw;
	private Date memBir;
	private String sex;
	private String memAddr;
	private String memEmail;
	private String memPhone;
	private String memResume;
	private Date memRegDate;
	private Integer allAuth;
	private Integer artAuth;
	private Integer comAuth;
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemAcc() {
		return memAcc;
	}
	public void setMemAcc(String memAcc) {
		this.memAcc = memAcc;
	}
	public String getMemPsw() {
		return memPsw;
	}
	public void setMemPsw(String memPsw) {
		this.memPsw = memPsw;
	}
	public Date getMemBir() {
		return memBir;
	}
	public void setMemBir(Date memBir) {
		this.memBir = memBir;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMemAddr() {
		return memAddr;
	}
	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemResume() {
		return memResume;
	}
	public void setMemResume(String memResume) {
		this.memResume = memResume;
	}
	public Date getMemRegDate() {
		return memRegDate;
	}
	public void setMemRegDate(Date memRegDate) {
		this.memRegDate = memRegDate;
	}
	public Integer getAllAuth() {
		return allAuth;
	}
	public void setAllAuth(Integer allAuth) {
		this.allAuth = allAuth;
	}
	public Integer getArtAuth() {
		return artAuth;
	}
	public void setArtAuth(Integer artAuth) {
		this.artAuth = artAuth;
	}
	public Integer getComAuth() {
		return comAuth;
	}
	public void setComAuth(Integer comAuth) {
		this.comAuth = comAuth;
	}
	
	
}
