package com.spring.sosodiary.notice.service;

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
import com.spring.sosodiary.notice.dao.NoticeDAO;
import com.spring.sosodiary.notice.vo.NoticeVO;


@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeDAO noticeDAO;

	@Override
	public Map getNotice(Map<String, Integer> pagingMap) throws DataAccessException {
		Map noticeMap=new HashMap();
		List<NoticeVO> noticeList=noticeDAO.noticeList(pagingMap);
		int noticeTotal=noticeDAO.noticeTotal(pagingMap);
		noticeMap.put("noticeList", noticeList);
		noticeMap.put("noticeTotal", noticeTotal);
		return noticeMap;
	}

	@Override
	public List<NoticeVO> adminNotice(int notiCategory) throws DataAccessException {
		List<NoticeVO> noticeList=noticeDAO.adminNotice(notiCategory);
		return noticeList;
	}

	@Override
	public void delNotice(int notiNo) throws DataAccessException {
		noticeDAO.delNotice(notiNo);
	}

	@Override
	public void addNotice(NoticeVO noticeVO) throws DataAccessException {
		noticeDAO.addNotice(noticeVO);
	}



	

}
