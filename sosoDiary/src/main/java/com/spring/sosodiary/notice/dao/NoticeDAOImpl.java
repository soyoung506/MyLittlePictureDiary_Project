package com.spring.sosodiary.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.sosodiary.diary.vo.CommentVO;
import com.spring.sosodiary.diary.vo.DiaryVO;
import com.spring.sosodiary.diary.vo.GroupVO;
import com.spring.sosodiary.notice.vo.NoticeVO;


@Repository("noticeDAO")
public class NoticeDAOImpl implements NoticeDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<NoticeVO> noticeList(Map<String, Integer> pagingMap) throws DataAccessException {
		List<NoticeVO> noticeList=sqlSession.selectList("mapper.notice.noticeList", pagingMap);
		return noticeList;
	}

	@Override
	public int noticeTotal(Map<String, Integer> pagingMap) throws DataAccessException {
		int noticeTotal=sqlSession.selectOne("mapper.notice.noticeTotal", pagingMap);
		return noticeTotal;
	}

	@Override
	public List<NoticeVO> adminNotice(int notiCategory) throws DataAccessException {
		List<NoticeVO> noticeList=sqlSession.selectList("mapper.notice.adminNotice", notiCategory);
		return noticeList;
	}

	@Override
	public void delNotice(int notiNo) throws DataAccessException {
		sqlSession.delete("mapper.notice.delNotice", notiNo);
	}

	@Override
	public void addNotice(NoticeVO noticeVO) throws DataAccessException {
		int notiNo=sqlSession.selectOne("mapper.notice.getNotiNo");
		notiNo++;
		noticeVO.setNotiNo(notiNo);
		sqlSession.insert("mapper.notice.addNotice", noticeVO);
	}


	



} 




















