package com.spring.sosodiary.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.sosodiary.diary.vo.GroupVO;
import com.spring.sosodiary.member.vo.MemberVO;

public interface MemberController {
	public ModelAndView indexPage(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addMember(@ModelAttribute("memberVO") MemberVO memberVO, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView welcome(@ModelAttribute("memberVO") MemberVO memberVO, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView getId(@RequestParam("userEmail") String userEmail, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView getPwd(@ModelAttribute("memberVO") MemberVO memberVO, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView resetPwd(@ModelAttribute("memberVO") MemberVO memberVO, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView mypage(@RequestParam("section") String section, @RequestParam("pageNum") String pageNum, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView mypage2(@RequestParam("section") String section, @RequestParam("pageNum") String pageNum, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modMember(@ModelAttribute("memberVO") MemberVO memberVO, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView joinGroup(@ModelAttribute("groupVO") GroupVO groupVO, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addGroup(@ModelAttribute("groupVO") GroupVO groupVO, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView outGroup(RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception;
//	public ModelAndView gotoModi(HttpServletRequest request, HttpServletResponse response) throws Exception;
//	public ResponseEntity modProfile(MultipartHttpServletRequest multipartRequest, @ModelAttribute("profileVO") MemberVO profileVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
//	public ModelAndView removeUser(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception;
}
