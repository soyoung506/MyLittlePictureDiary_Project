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
    <link rel="stylesheet" href="${contextPath}/resources/css/join.css">
    <script src="${contextPath}/resources/js/jquery-3.6.3.min.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/resources/js/groupCheck.js"></script>
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
    	<c:when test="${msg=='usedName'}">
			<script>
				window.onload=function () {
					Swal.fire({
	                    icon: 'error',
	                    title:'이미 존재하는 그룹명입니다.',
	                    text: '다른 그룹명을 작성해주세요.',
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
        <section class="join">
            <h1>그룹 만들기</h1>
            <form action="${contextPath}/member/addGroup.do" method="post">
                <fieldset class="joinText">
                    <legend class="blind">그룹정보 입력</legend>
                    <input type="text" name="gName" id="joinId" placeholder="그룹명 (공백없이 최대 10글자)" maxlength="10" autocomplete='off' required onkeyup="nameCheck(this)" onchange="nameCheck(this)" />
                    <p id="checkId"></p>
                    <input type="password" name="gPwd" id="joinPwd" placeholder="비밀번호 (6자리 이상의 영문자)" maxlength="60" required onchange="checkJoinPwd()" />
                    <p id="checkPwd"></p>
                    <input type="password" id="joinPwdC" placeholder="비밀번호 확인" required onchange="checkJoinPwd()" />
                    <p id="checkPwdC"></p>
                    <input type="text" name="gIntro" id="joinNick" placeholder="그룹 소개" maxlength="60" autocomplete='off' required />
                </fieldset>
                <input type="submit" value="그룹 생성">
            </form>
        </section>
    </div>
    <!-- main 종료 -->
    
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
 
</body>
</html>