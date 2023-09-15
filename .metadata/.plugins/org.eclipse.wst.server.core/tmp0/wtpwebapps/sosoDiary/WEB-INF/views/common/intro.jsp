<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/intro.css">
    <script src="${contextPath}/resources/js/jquery-3.6.3.min.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.min.js"></script>
    <title>나의 소소한 그림일기</title>
    <script>
        $(function() {
            $('.notion>a').hover(function() {
                $(this).stop(true).animate({
                    backgroundColor : '#FFB500',
                    color : '#FFF'
                }, 300);
            }, function() {
                $(this).stop(true).animate({
                    backgroundColor : '#FFF',
                    color : '#000'
                }, 300);
            });
        });
    </script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- main 시작 -->
    <div class="content">
        <section class="intro">
            <h1>누가 만들었나요~?</h1>
            <ul>
                <li>
                    <h2><img src="${contextPath}/resources/images/profileKim.jpg" alt="김소영 프로필"></h2>
                    <div class="introText">
                        <h3>김소영</h3>
                        <div>
                            <p>웹 개발자</p>
                            <p><a href="#">다른 웹페이지 구경하기</a></p>
                            <p>dbslzhs125@naver.com</p>
                        </div>
                    </div>
                </li>
            </ul>
        </section>
        <section class="procedure">
            <h1>나의 소소한 그림 일기 제작과정</h1>
            <ul>
                <li><img src="${contextPath}/resources/images/notion1.jpg" alt="노션이미지"></li>
                <li><img src="${contextPath}/resources/images/notion2.png" alt="노션이미지"></li>
                <li><img src="${contextPath}/resources/images/notion3.png" alt="노션이미지"></li>
            </ul>
        </section>
    </div>
    <!-- main 종료 -->

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>