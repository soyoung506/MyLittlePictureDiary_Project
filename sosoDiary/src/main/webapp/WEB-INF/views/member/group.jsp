<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<c:when test="${msg=='noGroup'}">
			<script>
				window.onload=function () {
					Swal.fire({
	                    icon: 'error',
	                    title:'그룹 가입에 실패했습니다.',
	                    text: '그룹이 존재하지 않거나 비밀번호가 일치하지 않습니다.',
	                });
				}
			</script>
		</c:when>
		<c:when test="${msg=='outGroup'}">
			<script>
				window.onload=function () {
					Swal.fire({
	                    icon: 'success',
	                    title:'그룹에서 탈퇴되었습니다',
	                    text: '새로운 그룹에 가입해 보세요!',
	                });
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
            <h1>그룹 가입</h1>
            <a class="gBtn" href="${contextPath}/member/createGroup.do">그룹 만들기</a>
            <form action="${contextPath}/member/joinGroup.do" method="post">
                <fieldset class="loginText">
                    <legend class="blind">그룹 코드 입력</legend>
                    <input type="text" name="gName" placeholder="그룹 이름" autocomplete='off' required/>
                    <input type="password" name="gPwd" placeholder="비밀번호" required/>
                </fieldset>
                <input type="submit" value="가입">
            </form>
        </section>
    </div>
    <!-- main 종료 -->

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>