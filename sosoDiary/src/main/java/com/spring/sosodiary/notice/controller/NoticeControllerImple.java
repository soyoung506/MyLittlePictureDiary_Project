package com.spring.sosodiary.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.sosodiary.notice.service.NoticeService;
import com.spring.sosodiary.notice.vo.NoticeVO;

@Controller("noticeController")
public class NoticeControllerImple extends MultiActionController implements NoticeController {

	@Autowired
	private NoticeService noticeService;
	@Autowired
	private NoticeVO noticeVO;
	
	// 공지사항 페이지
	@Override
	@RequestMapping(value = "/common/notice.do", method = RequestMethod.GET)
	public ModelAndView noticePage(String section, String pageNum, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		String msg = request.getParameter("msg");
		System.out.println(section);
		int _section = Integer.parseInt((section == null) ? "1" : section);
		int _pageNum = Integer.parseInt((pageNum == null) ? "1" : pageNum);
		Map<String, Integer> pagingMap = new HashMap<String, Integer>();
		pagingMap.put("section", _section);
		pagingMap.put("pageNum", _pageNum);
		pagingMap.put("notiCategory",1);
		Map noticeMap=noticeService.getNotice(pagingMap);
		noticeMap.put("section", _section);
		noticeMap.put("pageNum", _pageNum);
		mav.addObject("msg", msg);
		mav.addObject("noticeMap", noticeMap);
		mav.setViewName(viewName);
		return mav;
	}

	// 자주 묻는 질문 페이지
	@Override
	@RequestMapping(value = "/common/QnA.do", method = RequestMethod.GET)
	public ModelAndView QnAPage(String section, String pageNum, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		String msg = request.getParameter("msg");
		int _section = Integer.parseInt((section == null) ? "1" : section);
		int _pageNum = Integer.parseInt((pageNum == null) ? "1" : pageNum);
		Map<String, Integer> pagingMap = new HashMap<String, Integer>();
		pagingMap.put("section", _section);
		pagingMap.put("pageNum", _pageNum);
		pagingMap.put("notiCategory",2);
		Map noticeMap=noticeService.getNotice(pagingMap);
		noticeMap.put("section", _section);
		noticeMap.put("pageNum", _pageNum);
		mav.addObject("msg", msg);
		mav.addObject("noticeMap", noticeMap);
		mav.setViewName(viewName);
		return mav;
	}
	
	// 제작자 소개 페이지
	@RequestMapping(value = "/common/intro.do", method = RequestMethod.GET)
	public ModelAndView introPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);
		return mav;
	}
	
	
	// 관리페이지
	@Override
	@RequestMapping(value = "/admin/adminPage.do", method = RequestMethod.GET)
	public ModelAndView adminPage(String notiCategory, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		int _notiCategory = Integer.parseInt((notiCategory == null) ? "1" : notiCategory);
		String msg = request.getParameter("msg");
		List<NoticeVO> noticeList=noticeService.adminNotice(_notiCategory);
		mav.addObject("msg", msg);
		mav.addObject("notiCategory", _notiCategory);
		mav.addObject("noticeList", noticeList);
		mav.setViewName(viewName);
		return mav;
	}

	// 공지사항 삭제
	@Override
	@RequestMapping(value = "/admin/delNotice.do", method = RequestMethod.GET)
	public ModelAndView delNotice(int notiNo, int notiCategory, RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		noticeService.delNotice(notiNo);
		redirect.addAttribute("msg", "delNotice");
		mav.setViewName("redirect:/admin/adminPage.do?notiCategory="+notiCategory);
		return mav;
	}

	// 공지사항 작성 페이지
	@RequestMapping(value = "/admin/writeNotice.do", method = RequestMethod.GET)
	public ModelAndView writeNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);
		return mav;
	}

	// 공지사항 삭제
	@Override
	@RequestMapping(value = "/admin/addNotice.do", method = RequestMethod.POST)
	public ModelAndView addNotice(NoticeVO noticeVO, RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		int notiCategory=noticeVO.getNotiCategory();
		noticeService.addNotice(noticeVO);
		redirect.addAttribute("msg", "addNotice");
		mav.setViewName("redirect:/admin/adminPage.do?notiCategory="+notiCategory);
		return mav;
	}
	

	

	



}
