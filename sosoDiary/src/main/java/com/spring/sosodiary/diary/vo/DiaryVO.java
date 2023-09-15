package com.spring.sosodiary.diary.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("diaryVO")
public class DiaryVO {
	private int diaNo;
	private String diaTitle;
	private String diaContent;
	private int diaView;
	private String diaDraw;
	private int diaPublic;
	private Date diaDate;
	private int gNo;
	private int userNo;
	private String userName;
	private String gName;

	
	public DiaryVO() {
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



	public String getDiaContent() {
		return diaContent;
	}



	public void setDiaContent(String diaContent) {
		this.diaContent = diaContent;
	}



	public int getDiaView() {
		return diaView;
	}



	public void setDiaView(int diaView) {
		this.diaView = diaView;
	}



	public String getDiaDraw() {
		try {
			if(diaDraw != null && diaDraw.length() !=0) {
				diaDraw=URLDecoder.decode(diaDraw, "utf-8");
			}else {
				diaDraw=null;
			}
		}catch (UnsupportedEncodingException e) {
			System.out.println("DiaryVO 이미지 로딩 중 에러 발생");
		}
		return diaDraw;
	}



	public void setDiaDraw(String diaDraw) {
		try {
			if(diaDraw != null && diaDraw.length() !=0) {
				diaDraw=URLDecoder.decode(diaDraw, "utf-8");
			}else {
				diaDraw=null;
			}
		}catch (UnsupportedEncodingException e) {
			System.out.println("DiaryVO 이미지 저장 중 에러 발생");
		}
		this.diaDraw = diaDraw;
	}



	public int getDiaPublic() {
		return diaPublic;
	}



	public void setDiaPublic(int diaPublic) {
		this.diaPublic = diaPublic;
	}



	public Date getDiaDate() {
		return diaDate;
	}



	public void setDiaDate(Date diaDate) {
		this.diaDate = diaDate;
	}



	public int getgNo() {
		return gNo;
	}



	public void setgNo(int gNo) {
		this.gNo = gNo;
	}



	public int getUserNo() {
		return userNo;
	}



	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getgName() {
		return gName;
	}



	public void setgName(String gName) {
		this.gName = gName;
	}




	
}
