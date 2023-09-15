package com.spring.sosodiary.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.sosodiary.notice.vo.NoticeVO;

public interface NoticeController {
	
	public ModelAndView noticePage(@RequestParam("section") String section, @RequestParam("pageNum") String pageNum, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView QnAPage(@RequestParam("section") String section, @RequestParam("pageNum") String pageNum, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView adminPage(@RequestParam("notiCategory") String notiCategory,  HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView delNotice(@RequestParam("notiNo") int notiNo, @RequestParam("notiCategory") int notiCategory, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addNotice(@ModelAttribute("noticeVO") NoticeVO noticeVO, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
