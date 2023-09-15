package com.spring.sosodiary.diary.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.sosodiary.diary.vo.CommentVO;
import com.spring.sosodiary.diary.vo.DiaryVO;
import com.spring.sosodiary.diary.vo.GroupVO;

public interface DiaryService {

	public Map getMyDiary(Map<String, Integer> pagingMap) throws DataAccessException;
	public Map getAllDiary(Map<String, Integer> pagingMap) throws DataAccessException;
	public Map getOurDiary(Map<String, Integer> pagingMap) throws DataAccessException;
	public int getGroup(int userNo) throws DataAccessException;
	public GroupVO getGroupInfo(Map<String, Integer> gNoMap) throws DataAccessException;
	public int addDiary(DiaryVO diaryVO) throws DataAccessException;
	public int getDiaNo() throws DataAccessException;
	public void setView(int diaNo) throws DataAccessException;
	public Map getDiaryMap(int diaNo) throws DataAccessException;
	public void addComment(CommentVO commentVO) throws DataAccessException;
	public void delComment(int comNo) throws DataAccessException;
	public void delDiary(int diaNo) throws DataAccessException;
}
