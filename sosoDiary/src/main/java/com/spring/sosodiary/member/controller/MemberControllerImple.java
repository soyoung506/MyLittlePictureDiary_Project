package com.spring.sosodiary.member.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.sosodiary.diary.vo.DiaryVO;
import com.spring.sosodiary.diary.vo.GroupVO;
import com.spring.sosodiary.member.service.MemberService;
import com.spring.sosodiary.member.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImple extends MultiActionController implements MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;

	private static String PROFILEIMG_REPO = "D:\\KimSoYoung\\tofit\\profileImg";

	// index 페이지 로드
	@Override
	@RequestMapping(value = "/common/index.do", method = RequestMethod.GET)
	public ModelAndView indexPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		String msg = request.getParameter("msg");
		List<DiaryVO> diaryList=memberService.indexDiary();
		mav.addObject("msg", msg);
		mav.addObject("diaryList", diaryList);
		mav.setViewName(viewName);
		return mav;
	}
	
	// 회원가입 페이지 로드
	@RequestMapping(value = "/member/join.do", method = RequestMethod.GET)
	public ModelAndView joinPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		String msg = request.getParameter("msg");
		mav.addObject("msg", msg);
		mav.setViewName(viewName);
		return mav;
	}
	
	// 회원가입
	@Override
	@RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(MemberVO memberVO, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String result=memberService.addMember(memberVO);
		if(result=="usedId") {
			redirect.addAttribute("msg", "usedId");
			mav.setViewName("redirect:/member/join.do");
		}else if (result=="usedEmail") {
			redirect.addAttribute("msg", "usedEmail");
			mav.setViewName("redirect:/member/join.do");
		}else if (result=="usedName") {
			redirect.addAttribute("msg", "userName");
			mav.setViewName("redirect:/member/join.do");
		}else if (result=="addMember") {
			redirect.addAttribute("msg", "addMember");
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}
	
	// 로그인 페이지 로드
	@RequestMapping(value = "/member/login.do", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value="action", required = false) String action, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session=request.getSession();
		session.setAttribute("action", action);
		String viewName = (String) request.getAttribute("viewName");
		String msg = request.getParameter("msg");
		mav.addObject("msg", msg);
		mav.setViewName(viewName);
		return mav;
	}
	
	// 로그인
	@Override
	@RequestMapping(value = "/member/welcome.do", method = RequestMethod.POST)
	public ModelAndView welcome(MemberVO memberVO, RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		boolean result=memberService.login(memberVO);
		if(result) {
			int userNo=memberService.loginUserNo(memberVO);
			int userAdmin=memberService.loginUserAdmin(memberVO);
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", "true");
			session.setAttribute("logUserNo", userNo);
			session.setAttribute("logUserAdmin", userAdmin);
			String action=(String)session.getAttribute("action");
			session.removeAttribute("action");
			if(action!=null) {
				mav.setViewName("redirect:/diary/"+action+".do");
			}else {
				redirect.addAttribute("msg", "login");
				mav.setViewName("redirect:/common/index.do");
			}
		}else {
			redirect.addAttribute("msg", "noMember");
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}
	
	// 로그아웃
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession(false);
		session.invalidate();
		redirect.addAttribute("msg", "logout");
		mav.setViewName("redirect:/common/index.do");
		return mav;
	}
	
	// 아이디 찾기 페이지 로드
	@RequestMapping(value = "/member/findId.do", method = RequestMethod.GET)
	public ModelAndView findIdPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		String msg = request.getParameter("msg");
		String userId = request.getParameter("userId");
		mav.addObject("msg", msg);
		mav.addObject("userId", userId);
		mav.setViewName(viewName);
		return mav;
	}
	
	// 아이디 찾기
	@Override
	@RequestMapping(value = "/member/getId.do", method = RequestMethod.POST)
	public ModelAndView getId(String userEmail, RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String userId=memberService.getId(userEmail);
		if(userId.equals("noMember")) {
			redirect.addAttribute("msg",userId);
		}else {
			redirect.addAttribute("msg","login");
			redirect.addAttribute("userId",userId);
		}
		mav.setViewName("redirect:/member/findId.do");
		return mav;
	}
	
	// 비번 찾기 페이지 로드
	@RequestMapping(value = "/member/findPwd.do", method = RequestMethod.GET)
	public ModelAndView findPwdPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		String msg = request.getParameter("msg");
		mav.addObject("msg", msg);
		mav.setViewName(viewName);
		return mav;
	}
	
	// 비번 찾기
	@Override
	@RequestMapping(value = "/member/getPwd.do", method = RequestMethod.POST)
	public ModelAndView getPwd(MemberVO memberVO, RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		boolean result=memberService.getPwd(memberVO);
		if(result) {
			String userId=memberVO.getUserId();
			request.setAttribute("userId", userId);
			mav.setViewName("member/resetPwd");
		}else {
			redirect.addAttribute("msg","noMember");
			mav.setViewName("redirect:/member/findPwd.do");
		}
		return mav;
	}
	
	// 비번 재설정
	@Override
	@RequestMapping(value = "/member/resetPwd.do", method = RequestMethod.POST)
	public ModelAndView resetPwd(MemberVO memberVO, RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		memberService.resetPwd(memberVO);
		redirect.addAttribute("msg","modPwd");
		mav.setViewName("redirect:/member/login.do");
		return mav;
	}

	// 마이페이지 로드
	@Override
	@RequestMapping(value = "/member/mypage.do", method = RequestMethod.GET)
	public ModelAndView mypage(String section, String pageNum, HttpServletRequest request,
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
			Map diaryMap=memberService.getMyDiary(pagingMap);
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
	
	// 마이페이지2 로드
	@Override
	@RequestMapping(value = "/member/mypage2.do", method = RequestMethod.GET)
	public ModelAndView mypage2(String section, String pageNum, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		if (session != null && session.getAttribute("isLogon") == "true") {
			int userNo = (Integer)session.getAttribute("logUserNo");
			int _section = Integer.parseInt((section == null) ? "1" : section);
			int _pageNum = Integer.parseInt((pageNum == null) ? "1" : pageNum);
			Map<String, Integer> pagingMap = new HashMap<String, Integer>();
			pagingMap.put("section", _section);
			pagingMap.put("pageNum", _pageNum);
			pagingMap.put("userNo", userNo);
			Map commentMap=memberService.getMyComment(pagingMap);
			commentMap.put("section", _section);
			commentMap.put("pageNum", _pageNum);
			mav.addObject("commentMap", commentMap);
			mav.setViewName(viewName);
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}
	
	// 마이페이지 내 정보
	@Override
	@RequestMapping(value = "/member/memberInfo.do", method = RequestMethod.GET)
	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		if (session != null && session.getAttribute("isLogon") == "true") {
			int userNo = (Integer)session.getAttribute("logUserNo");
			memberVO=memberService.memberInfo(userNo);
			mav.addObject("memberVO", memberVO);
			mav.setViewName(viewName);
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}
	
	// 마이페이지 내 정보 수정
	@Override
	@RequestMapping(value = "/member/modMember.do", method = RequestMethod.POST)
	public ModelAndView modMember(MemberVO memberVO, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		if (session != null && session.getAttribute("isLogon") == "true") {
			memberService.modMember(memberVO);
			redirect.addAttribute("msg", "modMember");
			mav.setViewName("redirect:/member/mypage.do");
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}
	
	// 그룹 가입 페이지 로드
	@RequestMapping(value = "/member/group.do", method = RequestMethod.GET)
	public ModelAndView groupPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		if (session != null && session.getAttribute("isLogon") == "true") {
			String viewName = (String) request.getAttribute("viewName");
			String msg = request.getParameter("msg");
			mav.addObject("msg", msg);
			mav.setViewName(viewName);
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}
	
	// 그룹 가입
	@Override
	@RequestMapping(value = "/member/joinGroup.do", method = RequestMethod.POST)
	public ModelAndView joinGroup(GroupVO groupVO, RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		if (session != null && session.getAttribute("isLogon") == "true") {
			int userNo = (Integer)session.getAttribute("logUserNo");
			groupVO.setUserNo(userNo);
			boolean result=memberService.groupJoin(groupVO);
			if(result) {
				redirect.addAttribute("msg", "joinGroup");
				mav.setViewName("redirect:/diary/ourDiary.do");
			}else {
				redirect.addAttribute("msg", "noGroup");
				mav.setViewName("redirect:/member/group.do");
			}
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}
	
	// 그룹 생성 페이지 로드
	@RequestMapping(value = "/member/createGroup.do", method = RequestMethod.GET)
	public ModelAndView createGroupPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		if (session != null && session.getAttribute("isLogon") == "true") {
			String viewName = (String) request.getAttribute("viewName");
			String msg = request.getParameter("msg");
			mav.addObject("msg", msg);
			mav.setViewName(viewName);
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}
	
	// 그룹 생성
	@Override
	@RequestMapping(value = "/member/addGroup.do", method = RequestMethod.POST)
	public ModelAndView addGroup(GroupVO groupVO, RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		if (session != null && session.getAttribute("isLogon") == "true") {
			int userNo = (Integer)session.getAttribute("logUserNo");
			groupVO.setUserNo(userNo);
			String result=memberService.addGroup(groupVO);
			if(result.equals("usedName")) {
				redirect.addAttribute("msg", "usedName");
				mav.setViewName("redirect:/member/createGroup.do");
			}else {
				redirect.addAttribute("msg", "addGroup");
				mav.setViewName("redirect:/diary/ourDiary.do");
			}
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}

	// 그룹 탈퇴
	@Override
	@RequestMapping(value = "/member/outGroup.do", method = RequestMethod.GET)
	public ModelAndView outGroup(RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		if (session != null && session.getAttribute("isLogon") == "true") {
			int userNo = (Integer)session.getAttribute("logUserNo");
			memberService.outGroup(userNo);
			redirect.addAttribute("msg", "outGroup");
			mav.setViewName("redirect:/member/group.do");
		} else {
			mav.setViewName("redirect:/member/login.do");
		}
		return mav;
	}
	
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
		String newProfileImg = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			newProfileImg = mFile.getOriginalFilename();
			File file = new File(PROFILEIMG_REPO + "\\temp\\" + fileName);
			if (mFile.getSize() != 0) {
				if (!file.exists()) {
					file.getParentFile().mkdirs();
					file.createNewFile();
				}
				mFile.transferTo(new File(PROFILEIMG_REPO + "\\temp\\" + newProfileImg));
			}
		}
		return newProfileImg;
	}


	

	

	


	

	

	

	

	



}
