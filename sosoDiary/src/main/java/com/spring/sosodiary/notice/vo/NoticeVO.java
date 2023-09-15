package com.spring.sosodiary.notice.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("noticeVO")
public class NoticeVO {
	private int notiNo;
	private String notiTitle;
	private String notiContent;
	private Date notiDate;
	private int notiCategory;

	
	public NoticeVO() {
	}


	public int getNotiNo() {
		return notiNo;
	}


	public void setNotiNo(int notiNo) {
		this.notiNo = notiNo;
	}


	public String getNotiTitle() {
		return notiTitle;
	}


	public void setNotiTitle(String notiTitle) {
		this.notiTitle = notiTitle;
	}


	public String getNotiContent() {
		return notiContent;
	}


	public void setNotiContent(String notiContent) {
		this.notiContent = notiContent;
	}


	public Date getNotiDate() {
		return notiDate;
	}


	public void setNotiDate(Date notiDate) {
		this.notiDate = notiDate;
	}


	public int getNotiCategory() {
		return notiCategory;
	}


	public void setNotiCategory(int notiCategory) {
		this.notiCategory = notiCategory;
	}


	
	
}
