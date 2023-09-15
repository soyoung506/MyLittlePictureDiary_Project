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
    <script src="${contextPath}/resources/js/resetPassword.js"></script>
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
            <h1>비밀번호 재설정</h1>
            <form action="${contextPath}/member/resetPwd.do" method="post" name="resetPwdForm">
                <fieldset class="findPwdText">
                    <legend class="blind">새 비밀번호 정보 입력</legend>
                    <input type="password" name="userPwd" id="resetPwd" placeholder="비밀번호 (8자리 이상의 영문자, 숫자)" required onchange="checkResetPwd()" />
                    <p id="checkPwd"></p>
                    <input type="password" id="resetPwdC" placeholder="비밀번호 확인" required onchange="checkResetPwd()" />
                    <p id="checkPwdC"></p>
                    <input type="hidden" name="userId" value="${userId}">
                </fieldset>
                <input type="submit" value="비밀번호 재설정">
            </form>
        </section>
    </div>
    <!-- main 종료 -->

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>