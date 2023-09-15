package com.spring.sosodiary.diary.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.sosodiary.diary.service.DiaryService;
import com.spring.sosodiary.diary.vo.CommentVO;
import com.spring.sosodiary.diary.vo.DiaryVO;
import com.spring.sosodiary.diary.vo.GroupVO;

@Controller("diaryController")
public class DiaryControllerImple extends MultiActionController implements DiaryController {

	@Autowired
	private DiaryService diaryService;
	@Autowired
	private DiaryVO diaryVO;
	@Autowired
	private GroupVO groupVO;

	private static String DIARYIMG_REPO = "D:\\KimSoYoung\\portfolio_SpringProject\\soso\\diaryImage";

	// 나만의 일기장 로드
	@Override
	@RequestMapping(value = "/diary/myDiary.do", method = RequestMethod.GET)
	public ModelAndView myDiary(String section, String pageNum, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		if (session != null && session.getAttribute("isLogon") == "true") {
			int userNo = (Integer)session.getAttribute("logUserNo");
			int _section = Integer.parseInt((section == null) ? "1" : section);
			int _pageNum = Integer.parseInt((pageNum == null) ? "1" : pageNum);
			String msg = request.getParameter("msg");
			Map<String, Integer> pagingMap = new HashMap<String, Integer>();
			pagingMap.put("section", _section);
			pagingMap.put("pageNum", _pageNum);
			pagingMap.put("userNo", userNo);
			Map diaryMap=diaryService.getMyDiary(pagingMap);
			diaryMap.put("section", _section);
			diaryMap.put("pageNum", _pageNum);
			mav.addObject("msg", msg);
			mav.addObject("diaryMap", diaryMap);
			mav.setViewName(viewName);
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}

	// 모두의 일기장 로드
	@Override
	@RequestMapping(value = "/diary/allDiary.do", method = RequestMethod.GET)
	public ModelAndView allDiary(String section, String pageNum, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		int _section = Integer.parseInt((section == null) ? "1" : section);
		int _pageNum = Integer.parseInt((pageNum == null) ? "1" : pageNum);
		String msg = request.getParameter("msg");
		Map<String, Integer> pagingMap = new HashMap<String, Integer>();
		pagingMap.put("section", _section);
		pagingMap.put("pageNum", _pageNum);
		Map diaryMap=diaryService.getAllDiary(pagingMap);
		diaryMap.put("section", _section);
		diaryMap.put("pageNum", _pageNum);
		mav.addObject("msg", msg);
		mav.addObject("diaryMap", diaryMap);
		mav.setViewName(viewName);
		return mav;
	}

	// 우리의 일기장 로드
	@Override
	@RequestMapping(value = "/diary/ourDiary.do", method = RequestMethod.GET)
	public ModelAndView ourDiary(String section, String pageNum, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		if (session != null && session.getAttribute("isLogon") == "true") {
			int userNo = (Integer)session.getAttribute("logUserNo");
			String msg = request.getParameter("msg");
			int gNo=diaryService.getGroup(userNo);
			if(gNo == 0) {
				mav.setViewName("redirect:/member/group.do");
			}else {
				int _section = Integer.parseInt((section == null) ? "1" : section);
				int _pageNum = Integer.parseInt((pageNum == null) ? "1" : pageNum);
				Map<String, Integer> pagingMap = new HashMap<String, Integer>();
				pagingMap.put("section", _section);
				pagingMap.put("pageNum", _pageNum);
				pagingMap.put("gNo", gNo);
				Map diaryMap=diaryService.getOurDiary(pagingMap);
				diaryMap.put("section", _section);
				diaryMap.put("pageNum", _pageNum);
				mav.addObject("msg", msg);
				mav.addObject("diaryMap", diaryMap);
				mav.setViewName(viewName);
			}
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}
	
	// 일기쓰기 페이지 로드
	@RequestMapping(value = "/diary/writeDiary.do", method = RequestMethod.GET)
	public ModelAndView writeDiary(String _section, String _pageNum, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		if (session != null && session.getAttribute("isLogon") == "true") {
			int userNo = (Integer)session.getAttribute("logUserNo");
			int gNo=diaryService.getGroup(userNo);
			Map<String, Integer> gNoMap = new HashMap<String, Integer>();
			gNoMap.put("gNo", gNo);
			GroupVO groupVO=diaryService.getGroupInfo(gNoMap);
			mav.addObject("groupVO", groupVO);
			mav.setViewName(viewName);
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}

	// 일기 작성 (그림저장)
	@Override
	@RequestMapping(value = "/diary/saveDrawing.do", method = RequestMethod.POST)
	public ModelAndView saveDrawing(HashMap<Object, Object> param, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String binaryData=request.getParameter("imgSrc");
		FileOutputStream stream=null;
		try {
			if(binaryData ==null || binaryData.trim().equals("")) {
				throw new Exception();
			}
			binaryData=binaryData.replaceAll("data:image/jpeg;base64,", "");
			byte[] file=Base64.decodeBase64(binaryData);
			stream=new FileOutputStream(DIARYIMG_REPO+"\\temp\\image.jpg");
			stream.write(file);
			stream.close();
			int diaNo=diaryService.getDiaNo();
			File srcFile = new File(DIARYIMG_REPO+"\\temp\\image.jpg");
			File destDir = new File(DIARYIMG_REPO + "\\"+diaNo);
			destDir.mkdir();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
			System.out.println("그림저장");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("그림저장 에러발생");
		}finally {
			if(stream != null) {
				stream.close();
			}
		}
		mav.setViewName("redirect:/common/index.do");
		return mav;
		
	}

	// 일기 작성 (글저장)
	@Override
	@RequestMapping(value = "/diary/addDiary.do", method = RequestMethod.POST)
	public ModelAndView addDiary(DiaryVO diaryVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		if (session != null && session.getAttribute("isLogon") == "true") {
			int userNo = (Integer)session.getAttribute("logUserNo");
			String diaContent=diaryVO.getDiaContent().replace("<br>", "&lt;br>").replace("\r\n", "<br>");
			diaryVO.setDiaContent(diaContent);
			diaryVO.setUserNo(userNo);
			diaryVO.setDiaDraw("image.jpg");
			int diaNo=diaryService.addDiary(diaryVO);
			mav.setViewName("redirect:/diary/diaryInner.do?diaNo="+diaNo);
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}

	
	// 일기 내부
	@Override
	@RequestMapping(value = "/diary/diaryInner.do", method = RequestMethod.GET)
	public ModelAndView diaryInner(int diaNo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		if (session != null && session.getAttribute("isLogon") == "true") {
			int myNo = (Integer)session.getAttribute("logUserNo");
			String msg = request.getParameter("msg");
			diaryService.setView(diaNo);
			Map diaryMap=diaryService.getDiaryMap(diaNo);
			diaryMap.put("myNo", myNo);
			mav.addObject("msg", msg);
			mav.addObject("diaryMap", diaryMap);
			mav.setViewName(viewName);
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}

	// 댓글 작성
	@Override
	@RequestMapping(value = "/diary/addComment.do", method = RequestMethod.POST)
	public ModelAndView addComment(CommentVO commentVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		if (session != null && session.getAttribute("isLogon") == "true") {
			int userNo = (Integer)session.getAttribute("logUserNo");
			commentVO.setUserNo(userNo);
			int diaNo=commentVO.getDiaNo();
			diaryService.addComment(commentVO);
			mav.setViewName("redirect:/diary/diaryInner.do?diaNo="+diaNo);
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}

	// 댓글 삭제
	@Override
	@RequestMapping(value = "/diary/delComment.do", method = RequestMethod.GET)
	public ModelAndView delComment(int comNo, int diaNo, RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		if (session != null && session.getAttribute("isLogon") == "true") {
			diaryService.delComment(comNo);
			redirect.addAttribute("msg", "delete");
			mav.setViewName("redirect:/diary/diaryInner.do?diaNo="+diaNo);
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}

	// 일기 삭제
	@Override
	@RequestMapping(value = "/diary/delDiary.do", method = RequestMethod.GET)
	public ModelAndView delDiary(int diaNo, RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		if (session != null && session.getAttribute("isLogon") == "true") {
			diaryService.delDiary(diaNo);
			File imgDir = new File(DIARYIMG_REPO + "\\" + diaNo);
			FileUtils.deleteDirectory(imgDir);
			redirect.addAttribute("msg", "delete");
			mav.setViewName("redirect:/common/index.do");
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}

	

	

	

	



}
