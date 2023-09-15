package com.spring.sosodiary.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.sosodiary.diary.vo.CommentVO;
import com.spring.sosodiary.diary.vo.DiaryVO;
import com.spring.sosodiary.diary.vo.GroupVO;
import com.spring.sosodiary.notice.vo.NoticeVO;

public interface NoticeService {

	public Map getNotice(Map<String, Integer> pagingMap) throws DataAccessException;
	public List<NoticeVO> adminNotice(int notiCategory) throws DataAccessException;
	public void delNotice(int notiNo) throws DataAccessException;
	public void addNotice(NoticeVO noticeVO) throws DataAccessException;
}
