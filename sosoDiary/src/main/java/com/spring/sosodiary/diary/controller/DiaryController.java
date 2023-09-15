package com.spring.sosodiary.diary.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.sosodiary.diary.vo.CommentVO;
import com.spring.sosodiary.diary.vo.DiaryVO;

public interface DiaryController {
	
	public ModelAndView myDiary(@RequestParam("section") String section, @RequestParam("pageNum") String pageNum, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView allDiary(@RequestParam("section") String section, @RequestParam("pageNum") String pageNum, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView ourDiary(@RequestParam("section") String section, @RequestParam("pageNum") String pageNum, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView saveDrawing(@RequestParam HashMap<Object, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addDiary(@ModelAttribute("diaryVO") DiaryVO diaryVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView diaryInner(@RequestParam("diaNo") int diaNo, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addComment(@ModelAttribute("commentVO") CommentVO commentVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView delComment(@RequestParam("comNo") int comNo, @RequestParam("diaNo") int diaNo, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView delDiary(@RequestParam("diaNo") int diaNo, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
