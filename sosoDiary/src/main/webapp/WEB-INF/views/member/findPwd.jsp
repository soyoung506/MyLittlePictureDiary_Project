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
    <link rel="stylesheet" href="${contextPath}/resources/css/login_findPwd.css">
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
					var checkfindPwd=document.getElementById("checkfindPwd");
					checkfindPwd.innerHTML="회원정보가 존재하지 않습니다. 입력하신 내용을 다시 확인해주세요.";
					checkfindPwd.classList.add("checkPwd");
				}
			</script>
		</c:when>
	</c:choose>
	<style type="text/css">
		p.checkPwd {
		    font-family: "pretendard";
		    font-weight: 400;
		    font-size: 1em;
		    color: #ff0000;
		    text-align: left;
		    padding: 10px;
		}
	</style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- main 시작 -->
    <div class="content">
        <section class="findPwd">
            <h1>비밀번호 찾기</h1>
            <form action="${contextPath}/member/getPwd.do" method="post" name="findPwdForm">
                <fieldset class="findPwdText">
                    <legend class="blind">아이디, 이메일 정보 입력</legend>
                    <input type="text" name="userId" placeholder="아이디" autocomplete='off' required />
                    <input type="email" name="userEmail" placeholder="이메일" autocomplete='off' required />
                    <p id="checkfindPwd"></p>
                </fieldset>
                <input type="submit" value="비밀번호 찾기">
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