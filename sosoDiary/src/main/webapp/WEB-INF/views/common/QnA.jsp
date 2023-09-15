<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="noticeList" value="${noticeMap.noticeList}" />
<c:set var="noticeTotal" value="${noticeMap.noticeTotal}" />
<c:set var="section" value="${noticeMap.section}" />
<c:set var="pageNum" value="${noticeMap.pageNum}" />
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
    <link rel="stylesheet" href="${contextPath}/resources/css/notice.css">
    <script src="${contextPath}/resources/js/jquery-3.6.3.min.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/resources/js/jquery-animate-css-rotate-scale.js"></script>
    <title>나의 소소한 그림일기</title>
    <script>
        $(function() {
            var degree=0;
            $('h2.notiTitle').on('click', function(){
                degree+=180
                divHeight=$('div.notiContents').height();
                if($(this).next().is(':hidden') == false){
                    $(this).next().slideUp(500);
                    // $(this).children('img').animate({
                    //     rotate: degree
                    // },500);
                    // $(this).css("background-color", "rgba(0, 0, 0, 0)")
                    $(this).stop(true).animate({
                        backgroundColor : 'rgba(0, 0, 0, 0)'
                    },500);
                }else {
                    $('div.notiContents').slideUp(500);
                    $('h2.notiTitle').stop(true).animate({
                        backgroundColor : 'rgba(0, 0, 0, 0)'
                    },500);
                    $(this).next().slideDown(500);
                    // $(this).children('img').animate({
                    //     rotate: degree
                    // },500);
                    // $(this).css("background-color", "#E9EDC9")
                    $(this).stop(true).animate({
                        backgroundColor : '#E9EDC9'
                    },500);
                }
            });
        });
    </script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- main 시작 -->
    <div class="content">
        <section class="notice">
            <h1>자주 묻는 질문</h1>
            <ul class="list">
            	<c:choose>
                   	<c:when test="${empty noticeList}">
                   		<li class="noNotice">자주 묻는 질문이 없습니다.</li>
                   	</c:when>
					<c:otherwise>
						<c:forEach var="notice" items="${noticeList}">
							<li>
			                    <h2 class="notiTitle">
			                        <span>${notice.notiTitle}</span>
			                        <img src="${contextPath}/resources/images/icon-down.png" alt="열림 또는 닫힘 표시">
			                    </h2>
			                    <div class="notiContents">
			                        <p>${notice.notiContent}</p>
			                        <p>${notice.notiDate}</p>
			                    </div>
			                </li>
						</c:forEach>
					</c:otherwise>                        	
                </c:choose>
            </ul>
        </section>
        <section class="arrow_box">
			<ul>
				<c:if test="${noticeTotal !=0}">
					<c:if test="${noticeTotal%10==0}">
						<c:set var="noticeTotal" value="${noticeTotal-1}" />
					</c:if>
					<fmt:parseNumber var="total" value="${noticeTotal/50+1}" integerOnly="true" />
					
					<c:choose>
						<c:when test="${section > 1}">
							<li><a href="${contextPath}/common/notice.do?section=${section-1}&pageNum=5">
								<img src="${contextPath}/resources/images/left-arrow.png" alt="왼쪽 화살표"></a></li>
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${section==total}">
							<c:set var="endVal" value="${(noticeTotal-(50*(section-1)))/10+1}" />
						</c:when>
						<c:otherwise>
							<c:set var="endVal" value="5" />
						</c:otherwise>
					</c:choose>
					<c:forEach var="page" begin="1" end="${endVal}" step="1">
						<c:choose>
							<c:when test="${page==pageNum}">
								<li><a class="nowPage" href="${contextPath}/common/notice.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${contextPath}/common/notice.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<c:choose>
						<c:when test="${section!=total}">
							<li><a href="${contextPath}/common/notice.do?section=${section+1}&pageNum=1">
								<img src="${contextPath}/resources/images/right-arrow.png" alt="오른쪽 화살표"></a></li>
						</c:when>
					</c:choose>
				</c:if>
			</ul>
		</section>
    </div>
    <!-- main 종료 -->

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>