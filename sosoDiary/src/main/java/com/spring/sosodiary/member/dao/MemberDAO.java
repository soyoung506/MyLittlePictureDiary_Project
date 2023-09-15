package com.spring.sosodiary.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.sosodiary.diary.vo.CommentVO;
import com.spring.sosodiary.diary.vo.DiaryVO;
import com.spring.sosodiary.diary.vo.GroupVO;
import com.spring.sosodiary.member.vo.MemberVO;

public interface MemberDAO {
	public List<DiaryVO> indexDiary() throws DataAccessException;
	public String addMember(MemberVO memberVO) throws DataAccessException;
	public boolean login(MemberVO memberVO) throws DataAccessException;
	public int loginUserNo(MemberVO memberVO) throws DataAccessException;
	public int loginUserAdmin(MemberVO memberVO) throws DataAccessException;
	public String getId(String userEmail) throws DataAccessException;
	public boolean getPwd(MemberVO memberVO) throws DataAccessException;
	public void resetPwd(MemberVO memberVO) throws DataAccessException;
	public List<DiaryVO> myDiary(Map<String, Integer> pagingMap) throws DataAccessException;
	public int myDiaryTotal(Map<String, Integer> pagingMap) throws DataAccessException;
	public List<CommentVO> myComment(Map<String, Integer> pagingMap) throws DataAccessException;
	public int myCommentTotal(Map<String, Integer> pagingMap) throws DataAccessException;
	public MemberVO memberInfo(int userNo) throws DataAccessException;
	public void modMember(MemberVO memberVO) throws DataAccessException;
	public boolean groupJoin(GroupVO groupVO) throws DataAccessException;
	public String addGroup(GroupVO groupVO) throws DataAccessException;
	public void outGroup(int userNo) throws DataAccessException;
}
