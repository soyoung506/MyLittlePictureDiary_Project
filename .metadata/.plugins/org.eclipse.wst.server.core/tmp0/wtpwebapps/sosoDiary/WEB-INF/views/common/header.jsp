<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>나의 소소한 그림일기</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
</head>

<body>
    <header>
        <div class="headerBox">
            <div class="title">
                <h1><a href="${contextPath}/common/index.do">나의 소소한 그림일기 ' U '</a></h1>
            </div>    
            <div class="navBox">
            	<c:choose>
	            	<c:when test="${isLogon == null || isLogon == ''}">
	            		<nav>
		                    <ul>
		                        <li><a href="${contextPath}/member/login.do?action=myDiary">나만의 일기</a></li>
		                        <li><a href="${contextPath}/member/login.do?action=ourDiary">우리의 일기</a></li>
		                        <li><a href="${contextPath}/diary/allDiary.do">모두의 일기</a></li>
		                        <li><a href="${contextPath}/member/join.do">회원가입</a></li>
		                        <li><a href="${contextPath}/member/login.do">로그인</a></li>
		                    </ul>
		                </nav>
	            	</c:when>
	            	<c:when test="${logUserAdmin == '1'}">
	            		<nav>
		                    <ul>
		                    	<li><a href="${contextPath}/admin/adminPage.do">관리페이지</a></li>
		                        <li><a href="${contextPath}/member/logout.do">로그아웃</a></li>
		                    </ul>
		                </nav>
	            	</c:when>
	            	<c:otherwise>
	            		<nav>
		                    <ul>
		                        <li><a href="${contextPath}/diary/myDiary.do">나만의 일기</a></li>
		                        <li><a href="${contextPath}/diary/ourDiary.do">우리의 일기</a></li>
		                        <li><a href="${contextPath}/diary/allDiary.do">모두의 일기</a></li>
		                        <li><a href="${contextPath}/diary/writeDiary.do">일기 쓰기</a></li>
		                        <li><a href="${contextPath}/member/mypage.do">마이페이지</a></li>
		                        <li><a href="${contextPath}/member/logout.do">로그아웃</a></li>
		                    </ul>
		                </nav>
	            	</c:otherwise>
            	</c:choose>
            </div>
        </div>
    </header>
</body>
</html>