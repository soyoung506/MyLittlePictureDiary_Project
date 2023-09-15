<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/writeDiary.css">
    <script src="${contextPath}/resources/js/jquery-3.6.3.min.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/resources/js/canvas.js"></script>
    <script src="${contextPath}/resources/js/html2canvas.min.js"></script>
    <script src="${contextPath}/resources/js/writeDiary.js"></script>
    <title>나의 소소한 그림일기</title>
</head>
<body>
     <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- main 시작 -->
    <div class="content">
        <section class="drawing">
            <h1>공지사항 및 자주 묻는 질문 작성</h1>
        </section>
        <section class="writing">
            <form action="${contextPath}/admin/addNotice.do" method="post" name="diary">
                <fieldset class="selectOption">
                    <legend class="blind">옵션 선택</legend>
                    <select name="notiCategory" id="publicArea">
                        <option value="1" selected>공지사항</option>
                        <option value="2">자주 묻는 질문</option>
                    </select>
                </fieldset>
                <fieldset class="writeDiary">
                    <legend class="blind">내용 작성</legend>
                    <input type="text" name="notiTitle" id="diaryTitle" placeholder="제목" maxlength="30" autocomplete='off' required>
                    <textarea name="notiContent" id="diaryContent" placeholder="내용" maxlength="2000" required></textarea>
                </fieldset>
                <input type="submit" class="download" value="저장">
            </form>
        </section>

    </div>
    <!-- main 종료 -->

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>