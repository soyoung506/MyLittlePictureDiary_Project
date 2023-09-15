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
    <link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
</head>

<body>
    <footer>
        <div class="footerBox">
			<ul>
				<li><a href="${contextPath}/common/notice.do">공지사항</a></li>
				<li><a href="${contextPath}/common/QnA.do">자주 묻는 질문</a></li>
				<li><a href="${contextPath}/common/intro.do">제작자</a></li>
		    </ul>
            <h2>Copyright © 2023 - All right reserved by soso24</h2>
        </div>
    </footer>
</body>
</html>