package com.spring.sosodiary.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.sosodiary.diary.vo.CommentVO;
import com.spring.sosodiary.diary.vo.DiaryVO;
import com.spring.sosodiary.diary.vo.GroupVO;
import com.spring.sosodiary.member.vo.MemberVO;


@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<DiaryVO> indexDiary() throws DataAccessException {
		List<DiaryVO> diaryList=sqlSession.selectList("mapper.member.indexDiary");
		return diaryList;
	}

	@Override
	public String addMember(MemberVO memberVO) throws DataAccessException {
		String result=null;
		String checkId=sqlSession.selectOne("mapper.member.checkId", memberVO);
		String checkEmail=sqlSession.selectOne("mapper.member.checkEmail", memberVO);
		String checkName=sqlSession.selectOne("mapper.member.checkName", memberVO);
		if(checkId.equals("true")) {
			result="usedId";
		}else if(checkEmail.equals("true")) {
			result="usedEmail";
		}else if(checkName.equals("true")) {
			result="usedName";
		}else {
			int userNo=sqlSession.selectOne("mapper.member.getUserNo");
			userNo++;
			memberVO.setUserNo(userNo);
			sqlSession.insert("mapper.member.addMember", memberVO);
			result="addMember";
		}
		return result;
	}

	@Override
	public boolean login(MemberVO memberVO) throws DataAccessException {
		boolean result=sqlSession.selectOne("mapper.member.login", memberVO);
		return result;
	}

	@Override
	public int loginUserNo(MemberVO memberVO) throws DataAccessException {
		int userNo=sqlSession.selectOne("mapper.member.loginUserNo", memberVO);
		return userNo;
	}
	
	@Override
	public int loginUserAdmin(MemberVO memberVO) throws DataAccessException {
		int userAdmin=sqlSession.selectOne("mapper.member.loginUserAdmin", memberVO);
		return userAdmin;
	}

	@Override
	public String getId(String userEmail) throws DataAccessException {
		String userId=sqlSession.selectOne("mapper.member.getId", userEmail);
		return userId;
	}

	@Override
	public boolean getPwd(MemberVO memberVO) throws DataAccessException {
		boolean result=false;
		result=sqlSession.selectOne("mapper.member.getPwd", memberVO);
		return result;
	}

	@Override
	public void resetPwd(MemberVO memberVO) throws DataAccessException {
		sqlSession.update("mapper.member.resetPwd", memberVO);
	}

	@Override
	public List<DiaryVO> myDiary(Map<String, Integer> pagingMap) throws DataAccessException {
		List<DiaryVO> diaryList=sqlSession.selectList("mapper.member.myDiary", pagingMap);
		return diaryList;
	}

	@Override
	public int myDiaryTotal(Map<String, Integer> pagingMap) throws DataAccessException {
		int diaryTotal=sqlSession.selectOne("mapper.member.myDiaryTotal", pagingMap);
		return diaryTotal;
	}

	@Override
	public List<CommentVO> myComment(Map<String, Integer> pagingMap) throws DataAccessException {
		List<CommentVO> commentList=sqlSession.selectList("mapper.member.myComment", pagingMap);
		return commentList;
	}

	@Override
	public int myCommentTotal(Map<String, Integer> pagingMap) throws DataAccessException {
		int commentTotal=sqlSession.selectOne("mapper.member.myCommentTotal", pagingMap);
		return commentTotal;
	}

	@Override
	public MemberVO memberInfo(int userNo) throws DataAccessException {
		MemberVO memberVO=sqlSession.selectOne("mapper.member.memberInfo", userNo);
		return memberVO;
	}

	@Override
	public void modMember(MemberVO memberVO) throws DataAccessException {
		sqlSession.update("mapper.member.modMember", memberVO);
	}

	@Override
	public boolean groupJoin(GroupVO groupVO) throws DataAccessException {
		boolean result=sqlSession.selectOne("mapper.member.group", groupVO);
		if(result) {
			int gNo=sqlSession.selectOne("mapper.member.gNo", groupVO);
			groupVO.setgNo(gNo);
			sqlSession.update("mapper.member.groupJoin", groupVO);
		}
		return result;
	}

	@Override
	public String addGroup(GroupVO groupVO) throws DataAccessException {
		String result=null;
		boolean checkName=sqlSession.selectOne("mapper.member.gName", groupVO);
		if(checkName) {
			result="usedName";
		}else {
			int gNo=sqlSession.selectOne("mapper.member.getgNo");
			gNo++;
			groupVO.setgNo(gNo);
			sqlSession.insert("mapper.member.addGroup", groupVO);
			sqlSession.update("mapper.member.groupJoin", groupVO);
			result="addGroup";
		}
		return result;
	}

	@Override
	public void outGroup(int userNo) throws DataAccessException {
		sqlSession.update("mapper.member.outGroup", userNo);
	}

	

	



} 




















