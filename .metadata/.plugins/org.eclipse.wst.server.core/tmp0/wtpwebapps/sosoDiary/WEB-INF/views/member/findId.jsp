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
    <link rel="stylesheet" href="${contextPath}/resources/css/login_findId.css">
    <script src="${contextPath}/resources/js/jquery-3.6.3.min.js"></script>
    <title>나의 소소한 그림일기</title>
    <script>
        $(function() {
            // input 클릭 시 아웃라인 생성
            $('input').focusin(function() {
                $(this).animate({
                    "outline-offset" : 3
                }, 100);
            });
            $('input').focusout(function() {
                $(this).animate({
                    "outline-offset" : 0
                }, 1);
            });
        });
    </script>
    <c:choose>
		<c:when test="${msg=='noMember'}">
			<script>
				window.onload=function () {
					var checkfindId=document.getElementById("checkfindId");
					checkfindId.innerHTML="회원정보가 존재하지 않습니다. 입력하신 내용을 다시 확인해주세요.";
					checkfindId.classList.add("checkfindId");
				}
			</script>
		</c:when>
		<c:when test="${msg=='login'}">
			<script>
				window.onload=function () {
					var checkfindId=document.getElementById("checkfindId");
					checkfindId.innerHTML="회원님의 아이디는 &quot;${userId}&quot; 입니다.";
					checkfindId.classList.add("checkfindId");
				}
			</script>
		</c:when>
	</c:choose>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- main 시작 -->
    <div class="content">
        <section class="findId">
            <h1>아이디 찾기</h1>
            <form action="${contextPath}/member/getId.do" method="post" name="findIdForm">
                <fieldset class="findIdText">
                    <legend class="blind">이메일정보 입력</legend>
                    <input type="email" name="userEmail" placeholder="이메일" autocomplete='off' required />
                    <p id="checkfindId"></p>
                </fieldset>
                <input type="submit" value="아이디 찾기">
            </form>
        </section>
        <section class="findInfo">
            <div>
                <p>
                    <a href="${contextPath}/member/join.do" class="join">회원가입</a>
                    <a href="${contextPath}/member/login.do" class="login">로그인</a>
                </p>
            </div>
        </section>
        
    </div>
    <!-- main 종료 -->

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>