<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="mem" value="${memberVO}" />
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
    <link rel="stylesheet" href="${contextPath}/resources/css/join.css">
    <script src="${contextPath}/resources/js/jquery-3.6.3.min.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/resources/js/joincheck.js"></script>
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
        
        
     // 비밀번호 수정
     function modPwd(){
     	var pwd=document.getElementById("joinPwd");
     	var pwdC=document.getElementById("joinPwdC");
     	var oriPwd=document.getElementById("originPwd");
     	
     	pwd.setAttribute("required","required");
     	pwd.setAttribute("name","userPwd");
     	pwdC.removeAttribute("disabled");
     	pwdC.setAttribute("required","required");
     	oriPwd.parentNode.removeChild(oriPwd);
     }
    </script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- main 시작 -->
    <div class="content">
        <section class="join">
            <h1>회원정보 수정</h1>
            <form action="${contextPath}/member/modMember.do" method="post">
                <fieldset class="joinText">
                    <legend class="blind">회원정보수정</legend>
                    <input type="text" value="${mem.userId}" disabled/>
                    <input type="password" id="joinPwd" placeholder="비밀번호 (8자리 이상의 영문자, 숫자)" onchange="checkJoinPwd(); modPwd()" />
                    <p id="checkPwd"></p>
                    <input type="password" id="joinPwdC" placeholder="비밀번호 확인" disabled onchange="checkJoinPwd()" />
                    <p id="checkPwdC"></p>
                    <input type="email" value="${mem.userEmail}" name="userEmail" required/>
                    <input type="text" value="${mem.userName}" disabled/>
                    <input type="text" value="가입일 : ${mem.userDate}" disabled/>
                    <input type="hidden" value="${mem.userNo}" name="userNo"/>
                    <input type="hidden" value="${mem.userPwd}" name="userPwd" id="originPwd"/>
                </fieldset>
                <input type="submit" value="수정">
            </form>
        </section>
    </div>
    <!-- main 종료 -->

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>