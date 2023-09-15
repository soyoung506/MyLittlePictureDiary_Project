package com.spring.sosodiary.diary.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("commentVO")
public class CommentVO {
	private int comNo;
	private String comContent;
	private Date comDate;
	private int userNo;
	private int diaNo;
	private String diaTitle;
	private String userName;

	
	public CommentVO() {
	}


	public int getComNo() {
		return comNo;
	}


	public void setComNo(int comNo) {
		this.comNo = comNo;
	}


	public String getComContent() {
		return comContent;
	}


	public void setComContent(String comContent) {
		this.comContent = comContent;
	}


	public Date getComDate() {
		return comDate;
	}


	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}


	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	public int getDiaNo() {
		return diaNo;
	}


	public void setDiaNo(int diaNo) {
		this.diaNo = diaNo;
	}


	public String getDiaTitle() {
		return diaTitle;
	}


	public void setDiaTitle(String diaTitle) {
		this.diaTitle = diaTitle;
	}

	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	
}
