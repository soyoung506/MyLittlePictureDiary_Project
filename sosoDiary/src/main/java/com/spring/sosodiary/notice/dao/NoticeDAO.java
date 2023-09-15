package com.spring.sosodiary.notice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.sosodiary.diary.vo.CommentVO;
import com.spring.sosodiary.diary.vo.DiaryVO;
import com.spring.sosodiary.diary.vo.GroupVO;
import com.spring.sosodiary.notice.vo.NoticeVO;

public interface NoticeDAO {

	public List<NoticeVO> noticeList(Map<String, Integer> pagingMap) throws DataAccessException;
	public int noticeTotal(Map<String, Integer> pagingMap) throws DataAccessException;
	public List<NoticeVO> adminNotice(int notiCategory) throws DataAccessException;
	public void delNotice(int notiNo) throws DataAccessException;
	public void addNotice(NoticeVO noticeVO) throws DataAccessException;
}
