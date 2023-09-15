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
    <link rel="stylesheet" href="${contextPath}/resources/css/login.css">
    <script src="${contextPath}/resources/js/jquery-3.6.3.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
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
    	<c:when test="${msg=='addMember'}">
			<script>
				window.onload=function () {
					Swal.fire({
	                    icon: 'success',
	                    title:'회원가입이 완료되었습니다.',
	                    text: '나만의 그림일기를 완성해 보세요!',
	                });
				}
			</script>
		</c:when>
		<c:when test="${msg=='modPwd'}">
			<script>
				window.onload=function () {
					Swal.fire({
	                    icon: 'success',
	                    title:'비밀번호가 재설정되었습니다.',
	                    text: '다시 로그인해주세요.',
	                });
				}
			</script>
		</c:when>
		<c:when test="${msg=='noMember'}">
			<script>
				window.onload=function () {
					var checkLogin=document.getElementById("checkLogin");
					checkLogin.innerHTML="아이디 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.";
					checkLogin.classList.add("checkLogin");
				}
			</script>
		</c:when>
	</c:choose>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- main 시작 -->
    <div class="content">
        <section class="login">
            <h1>로그인</h1>
            <form action="${contextPath}/member/welcome.do" method="post" name="loginForm">
                <fieldset class="loginText">
                    <legend class="blind">로그인정보 입력</legend>
                    <input type="text" name="userId" placeholder="아이디" maxlength="20" autocomplete='off' required />
                    <input type="password" name="userPwd" placeholder="비밀번호" required />
                    <p id="checkLogin"></p>
                </fieldset>
                <input type="submit" value="로그인">
            </form>
        </section>
        <section class="findInfo">
            <div>
                <h2><a href="${contextPath}/member/join.do">회원가입</a></h2>
                <p>
                    <a href="${contextPath}/member/findId.do" class="findId">아이디 찾기</a>
                    <a href="${contextPath}/member/findPwd.do" class="findPwd">비밀번호 찾기</a>
                </p>
            </div>
        </section>
    </div>
    <!-- main 종료 -->

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>