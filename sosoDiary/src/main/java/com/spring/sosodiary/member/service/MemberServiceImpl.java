package com.spring.sosodiary.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.sosodiary.diary.vo.CommentVO;
import com.spring.sosodiary.diary.vo.DiaryVO;
import com.spring.sosodiary.diary.vo.GroupVO;
import com.spring.sosodiary.member.dao.MemberDAO;
import com.spring.sosodiary.member.vo.MemberVO;


@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public List<DiaryVO> indexDiary() throws DataAccessException {
		List<DiaryVO> diaryList=memberDAO.indexDiary();
		return diaryList;
	}

	@Override
	public String addMember(MemberVO memberVO) throws DataAccessException {
		String result=memberDAO.addMember(memberVO);
		return result;
	}

	@Override
	public boolean login(MemberVO memberVO) throws DataAccessException {
		boolean result=memberDAO.login(memberVO);
		return result;
	}

	@Override
	public int loginUserNo(MemberVO memberVO) throws DataAccessException {
		int userNo=memberDAO.loginUserNo(memberVO);
		return userNo;
	}

	@Override
	public int loginUserAdmin(MemberVO memberVO) throws DataAccessException {
		int userAdmin=memberDAO.loginUserAdmin(memberVO);
		return userAdmin;
	}
	
	@Override
	public String getId(String userEmail) throws DataAccessException {
		String userId=memberDAO.getId(userEmail);
		return userId;
	}

	@Override
	public boolean getPwd(MemberVO memberVO) throws DataAccessException {
		boolean result=memberDAO.getPwd(memberVO);
		return result;
	}

	@Override
	public void resetPwd(MemberVO memberVO) throws DataAccessException {
		memberDAO.resetPwd(memberVO);
	}

	@Override
	public Map getMyDiary(Map<String, Integer> pagingMap) throws DataAccessException {
		Map diaryMap=new HashMap();
		List<DiaryVO> diaryList=memberDAO.myDiary(pagingMap);
		int diaryTotal=memberDAO.myDiaryTotal(pagingMap);
		diaryMap.put("diaryList", diaryList);
		diaryMap.put("diaryTotal", diaryTotal);
		return diaryMap;
	}

	@Override
	public Map getMyComment(Map<String, Integer> pagingMap) throws DataAccessException {
		Map commentMap=new HashMap();
		List<CommentVO> commentList=memberDAO.myComment(pagingMap);
		int commentTotal=memberDAO.myCommentTotal(pagingMap);
		commentMap.put("commentList", commentList);
		commentMap.put("commentTotal", commentTotal);
		return commentMap;
	}

	@Override
	public MemberVO memberInfo(int userNo) throws DataAccessException {
		MemberVO memberVO=memberDAO.memberInfo(userNo);
		return memberVO;
	}

	@Override
	public void modMember(MemberVO memberVO) throws DataAccessException {
		memberDAO.modMember(memberVO);
	}

	@Override
	public boolean groupJoin(GroupVO groupVO) throws DataAccessException {
		boolean result=memberDAO.groupJoin(groupVO);
		return result;
	}

	@Override
	public String addGroup(GroupVO groupVO) throws DataAccessException {
		String result=memberDAO.addGroup(groupVO);
		return result;
	}

	@Override
	public void outGroup(int userNo) throws DataAccessException {
		memberDAO.outGroup(userNo);
	}



	
	
	

	

}
