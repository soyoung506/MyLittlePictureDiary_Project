package com.spring.sosodiary.diary.vo;

import org.springframework.stereotype.Component;

@Component("groupVO")
public class GroupVO {
	private int gNo;
	private String gName;
	private String gPwd;
	private String gIntro;
	private int userNo;

	
	public GroupVO() {
	}


	public int getgNo() {
		return gNo;
	}


	public void setgNo(int gNo) {
		this.gNo = gNo;
	}


	public String getgName() {
		return gName;
	}


	public void setgName(String gName) {
		this.gName = gName;
	}


	public String getgPwd() {
		return gPwd;
	}


	public void setgPwd(String gPwd) {
		this.gPwd = gPwd;
	}


	public String getgIntro() {
		return gIntro;
	}


	public void setgIntro(String gIntro) {
		this.gIntro = gIntro;
	}


	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
	


	
}
