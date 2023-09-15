<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="diary" value="${diaryMap.diaryVO}" />
<c:set var="comment" value="${diaryMap.commentList}" />
<c:set var="myNo" value="${diaryMap.myNo}" />
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>나의 소소한 그림일기</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/diaryInner.css">
    <script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script type="text/javascript">
    	function delCom(comNo,diaNo) {
    		location.href='${contextPath}/diary/delComment.do?comNo='+comNo+'&diaNo='+diaNo;
		}
    </script>
    <c:choose>
		<c:when test="${msg=='delete'}">
			<script>
				window.onload=function () {
					Swal.fire({
	                    icon: 'success',
	                    title:'댓글이 삭제되었습니다.',
	                    text: '새로운 댓글을 작성해 보세요!',
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
            <h2>${diary.userName}님의 일기</h2>
            <div class="diaryBox_top">
                <div class="contentImg">
                    <img src="${contextPath}/diaryimage?diaNo=${diary.diaNo}" alt="그림일기 그림">
                </div>
                <div class="contentTop">
                    <span>${diary.diaTitle}</span>
                    <span>조회수 ${diary.diaView}</span>
                    <span>${diary.diaDate}</span>
                </div>
                <div class="contentBox">
                    <p class="p_content">${diary.diaContent}
                    </p>
                </div>
            </div>
            <div class="diaryBox_middel">
            	<form action="${contextPath}/diary/addComment.do" method="post">
	                <input type="text" name="comContent" maxlength="250" placeholder="댓글을 입력하세요! 😊" autocomplete='off'>
	                <input type="hidden" value="${diary.diaNo}" name="diaNo">
	                <input type="submit" name="submit" value="등록">
                </form>
            </div>
            <c:if test="${!empty comment}">
	            <div class="commentBox">
	            	<ul>
	            		<c:forEach var="comment" items="${comment}" varStatus="status">
		            		<li>
			            		<div class="index">${status.count}</div>
			            		<div class="comContent">${comment.comContent}</div>
			            		<div class="userName">${comment.userName}</div>
			            		<div class="comDate">${comment.comDate}</div>
			            		<c:if test="${comment.userNo==myNo}">
				            		<button type="button" onclick="delCom(${comment.comNo},${diary.diaNo})">삭제</button>
			            		</c:if>
		            		</li>
	            		</c:forEach>
	            	</ul>
	            </div>
            </c:if>
            <div class="diaryBox_bottom">
                <a href="${contextPath}/common/index.do">게시판으로 돌아가기</a>
                <c:if test="${diary.userNo==myNo}">
                	<a href="${contextPath}/diary/delDiary.do?diaNo=${diary.diaNo}" class="delDiary">일기 삭제하기</a>
               	</c:if>
            </div>
        </div>
    </div>
    <!--콘텐츠 영역 끝-->

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>

</html>