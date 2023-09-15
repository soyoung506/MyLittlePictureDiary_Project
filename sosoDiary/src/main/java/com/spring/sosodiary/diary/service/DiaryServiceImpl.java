package com.spring.sosodiary.diary.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.sosodiary.diary.dao.DiaryDAO;
import com.spring.sosodiary.diary.vo.CommentVO;
import com.spring.sosodiary.diary.vo.DiaryVO;
import com.spring.sosodiary.diary.vo.GroupVO;


@Service("diaryService")
public class DiaryServiceImpl implements DiaryService{
	@Autowired
	private DiaryDAO diaryDAO;

	@Override
	public Map getMyDiary(Map<String, Integer> pagingMap) throws DataAccessException {
		Map diaryMap=new HashMap();
		List<DiaryVO> diaryList=diaryDAO.myDiary(pagingMap);
		int diaryTotal=diaryDAO.myDiaryTotal(pagingMap);
		diaryMap.put("diaryList", diaryList);
		diaryMap.put("diaryTotal", diaryTotal);
		return diaryMap;
	}

	@Override
	public Map getAllDiary(Map<String, Integer> pagingMap) throws DataAccessException {
		Map diaryMap=new HashMap();
		List<DiaryVO> diaryList=diaryDAO.allDiary(pagingMap);
		int diaryTotal=diaryDAO.allDiaryTotal(pagingMap);
		diaryMap.put("diaryList", diaryList);
		diaryMap.put("diaryTotal", diaryTotal);
		return diaryMap;
	}

	@Override
	public Map getOurDiary(Map<String, Integer> pagingMap) throws DataAccessException {
		Map diaryMap=new HashMap();
		List<DiaryVO> diaryList=diaryDAO.ourDiary(pagingMap);
		int diaryTotal=diaryDAO.ourDiaryTotal(pagingMap);
		GroupVO groupVO=diaryDAO.getGroupInfo(pagingMap);
		diaryMap.put("diaryList", diaryList);
		diaryMap.put("diaryTotal", diaryTotal);
		diaryMap.put("groupVO", groupVO);
		return diaryMap;
	}

	@Override
	public int getGroup(int userNo) throws DataAccessException {
		int gNo=diaryDAO.getGroup(userNo);
		return gNo;
	}

	@Override
	public GroupVO getGroupInfo(Map<String, Integer> gNoMap) throws DataAccessException {
		GroupVO groupVO=diaryDAO.getGroupInfo(gNoMap);
		return groupVO;
	}

	@Override
	public int addDiary(DiaryVO diaryVO) throws DataAccessException {
		int diaNo=diaryDAO.addDiary(diaryVO);
		return diaNo;
	}

	@Override
	public int getDiaNo() throws DataAccessException {
		int diaNo=diaryDAO.getDiaNo();
		return diaNo;
	}

	@Override
	public void setView(int diaNo) throws DataAccessException {
		diaryDAO.setView(diaNo);
	}
	
	@Override
	public Map getDiaryMap(int diaNo) throws DataAccessException {
		Map diaryMap=new HashMap();
		List<CommentVO> commentList=diaryDAO.getComment(diaNo);
		DiaryVO diaryVO=diaryDAO.getDiary(diaNo);
		diaryMap.put("commentList", commentList);
		diaryMap.put("diaryVO", diaryVO);
		return diaryMap;
	}

	@Override
	public void addComment(CommentVO commentVO) throws DataAccessException {
		diaryDAO.addComment(commentVO);
	}

	@Override
	public void delComment(int comNo) throws DataAccessException {
		diaryDAO.delComment(comNo);
	}

	@Override
	public void delDiary(int diaNo) throws DataAccessException {
		diaryDAO.delDiary(diaNo);
	}

	


	

}
