package com.spring.sosodiary.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("profileVO")
public class MemberVO {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String userEmail;
	private Date userDate;
	private int userAdmin;
	private int gNo;
	
	
	public MemberVO() {
	}


	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPwd() {
		return userPwd;
	}


	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public Date getUserDate() {
		return userDate;
	}


	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}


	public int getUserAdmin() {
		return userAdmin;
	}


	public void setUserAdmin(int userAdmin) {
		this.userAdmin = userAdmin;
	}


	public int getgNo() {
		return gNo;
	}


	public void setgNo(int gNo) {
		this.gNo = gNo;
	}
	
	

	
}
