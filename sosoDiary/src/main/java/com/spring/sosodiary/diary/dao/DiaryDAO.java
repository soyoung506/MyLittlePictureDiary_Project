package com.spring.sosodiary.diary.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.sosodiary.diary.vo.CommentVO;
import com.spring.sosodiary.diary.vo.DiaryVO;
import com.spring.sosodiary.diary.vo.GroupVO;

public interface DiaryDAO {

	public List<DiaryVO> myDiary(Map<String, Integer> pagingMap) throws DataAccessException;
	public int myDiaryTotal(Map<String, Integer> pagingMap) throws DataAccessException;
	public List<DiaryVO> allDiary(Map<String, Integer> pagingMap) throws DataAccessException;
	public int allDiaryTotal(Map<String, Integer> pagingMap) throws DataAccessException;
	public List<DiaryVO> ourDiary(Map<String, Integer> pagingMap) throws DataAccessException;
	public int ourDiaryTotal(Map<String, Integer> pagingMap) throws DataAccessException;
	public GroupVO getGroupInfo(Map<String, Integer> pagingMap) throws DataAccessException;
	public int getGroup(int userNo) throws DataAccessException;
	public int addDiary(DiaryVO diaryVO) throws DataAccessException;
	public int getDiaNo() throws DataAccessException;
	public void setView(int diaNo) throws DataAccessException;
	public DiaryVO getDiary(int diaNo) throws DataAccessException;
	public List<CommentVO> getComment(int diaNo) throws DataAccessException;
	public void addComment(CommentVO commentVO) throws DataAccessException;
	public void delComment(int comNo) throws DataAccessException;
	public void delDiary(int diaNo) throws DataAccessException;
}
