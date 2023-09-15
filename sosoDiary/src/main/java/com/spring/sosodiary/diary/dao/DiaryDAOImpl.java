package com.spring.sosodiary.diary.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.sosodiary.diary.vo.CommentVO;
import com.spring.sosodiary.diary.vo.DiaryVO;
import com.spring.sosodiary.diary.vo.GroupVO;


@Repository("diaryDAO")
public class DiaryDAOImpl implements DiaryDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<DiaryVO> myDiary(Map<String, Integer> pagingMap) throws DataAccessException {
		List<DiaryVO> diaryList=sqlSession.selectList("mapper.diary.myDiary", pagingMap);
		return diaryList;
	}
	
	@Override
	public int myDiaryTotal(Map<String, Integer> pagingMap) throws DataAccessException {
		int diaryTotal=sqlSession.selectOne("mapper.diary.myDiaryTotal", pagingMap);
		return diaryTotal;
	}

	@Override
	public List<DiaryVO> allDiary(Map<String, Integer> pagingMap) throws DataAccessException {
		List<DiaryVO> diaryList=sqlSession.selectList("mapper.diary.allDiary", pagingMap);
		int section = pagingMap.get("section");
		int pageNum = pagingMap.get("pageNum");
		/*
		 * System.out.println(diaryList); System.out.println(section);
		 * System.out.println(pageNum);
		 */
		return diaryList;
	}

	@Override
	public int allDiaryTotal(Map<String, Integer> pagingMap) throws DataAccessException {
		int diaryTotal=sqlSession.selectOne("mapper.diary.allDiaryTotal", pagingMap);
		return diaryTotal;
	}

	@Override
	public List<DiaryVO> ourDiary(Map<String, Integer> pagingMap) throws DataAccessException {
		List<DiaryVO> diaryList=sqlSession.selectList("mapper.diary.ourDiary", pagingMap);
		return diaryList;
	}

	@Override
	public int ourDiaryTotal(Map<String, Integer> pagingMap) throws DataAccessException {
		int diaryTotal=sqlSession.selectOne("mapper.diary.ourDiaryTotal", pagingMap);
		return diaryTotal;
	}

	@Override
	public GroupVO getGroupInfo(Map<String, Integer> pagingMap) throws DataAccessException {
		GroupVO groupVO=sqlSession.selectOne("mapper.diary.getGroupInfo", pagingMap);
		return groupVO;
	}

	@Override
	public int getGroup(int userNo) throws DataAccessException {
		int gNo=sqlSession.selectOne("mapper.diary.getGroup", userNo);
		return gNo;
	}

	@Override
	public int addDiary(DiaryVO diaryVO) throws DataAccessException {
		int diaNo=sqlSession.selectOne("mapper.diary.getDiaNo");
		diaNo++;
		diaryVO.setDiaNo(diaNo);
		sqlSession.insert("mapper.diary.addDiary", diaryVO);
		return diaNo;
	}

	@Override
	public int getDiaNo() throws DataAccessException {
		int diaNo=sqlSession.selectOne("mapper.diary.getDiaNo");
		diaNo++;
		return diaNo;
	}

	@Override
	public void setView(int diaNo) throws DataAccessException {
		sqlSession.update("mapper.diary.setView", diaNo);
	}
	
	@Override
	public DiaryVO getDiary(int diaNo) throws DataAccessException {
		DiaryVO diaryVO=sqlSession.selectOne("mapper.diary.getDiary", diaNo);
		return diaryVO;
	}

	@Override
	public List<CommentVO> getComment(int diaNo) throws DataAccessException {
		List<CommentVO> commentList=sqlSession.selectList("mapper.diary.getComment", diaNo);
		return commentList;
	}

	@Override
	public void addComment(CommentVO commentVO) throws DataAccessException {
		int comNo=sqlSession.selectOne("mapper.diary.getComNo");
		comNo++;
		commentVO.setComNo(comNo);
		sqlSession.insert("mapper.diary.addComment", commentVO);
	}

	@Override
	public void delComment(int comNo) throws DataAccessException {
		sqlSession.delete("mapper.diary.delComment", comNo);
	}

	@Override
	public void delDiary(int diaNo) throws DataAccessException {
		sqlSession.delete("mapper.diary.delDiary", diaNo);
		sqlSession.delete("mapper.diary.delDiaryComment", diaNo);
	}

	

	



} 




















