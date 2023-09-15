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
    <title>나의 소소한 그림일기</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/index.css">
    <script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/resources/js/index.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <c:choose>
		<c:when test="${msg=='login'}">
			<script>
				window.onload=function () {
					Swal.fire({
	                    icon: 'success',
	                    title:'로그인 되었습니다.',
	                    text: '나만의 그림일기를 완성해 보세요!',
	                });
				}
			</script>
		</c:when>
		<c:when test="${msg=='logout'}">
			<script>
				window.onload=function () {
					Swal.fire({
	                    icon: 'success',
	                    title:'로그아웃 되었습니다.',
	                    text: '행복한 하루 보내세요!',
	                });
				}
			</script>
		</c:when>
		<c:when test="${msg=='delete'}">
			<script>
				window.onload=function () {
					Swal.fire({
	                    icon: 'success',
	                    title:'일기가 삭제되었습니다.',
	                    text: '새로운 일기를 작성해 보세요!',
	                });
				}
			</script>
		</c:when>
	</c:choose>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!--콘텐츠 영역 시작-->
    <div class="container">
        <div class="all">
            <div class="slide_contain">
                <div class="slide_box">
                    <ul>
                    	<c:forEach var="diary" items="${diaryList}" >
                    		<li>
	                            <div class="slBox">
	                            <a href="${contextPath}/diary/diaryInner.do?diaNo=${diary.diaNo}">
	                                <img src="${contextPath}/diaryimage?diaNo=${diary.diaNo}" alt="그림일기 그림">
	                                <div class="slide_text">
	                                    <h3>${diary.diaTitle}</h3>
	                                    <p>${diary.diaContent}</p>
	                                </div>
                                </a>
	                            </div>
	                        </li>
                    	</c:forEach>
                    </ul>
                </div>
            </div>
            
            
            <div class="potal_contain">
                <div class="potal_textBox">
                    <h2>세상에서 가장 소중한 나의 그림일기</h2>
                    <a class="btn" href="${contextPath}/diary/writeDiary.do">나만의 일기 쓰러가기</a>
                </div>
                <div class="right_box imgBox"><img src="${contextPath}/resources/images/indexImg1.png" alt="예시 그림"></div>
                <div class="left_top_box imgBox"><img src="${contextPath}/resources/images/indexImg3.png" alt="예시 그림"></div>
                <div class="left_bottom_box imgBox"><img src="${contextPath}/resources/images/indexImg2.png" alt="예시 그림"></div>
            </div>
        </div>
    </div>
    <!--콘텐츠 영역 끝-->

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>