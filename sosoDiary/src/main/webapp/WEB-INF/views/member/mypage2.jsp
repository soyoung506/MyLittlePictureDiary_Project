<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="commentList" value="${commentMap.commentList}" />
<c:set var="commentTotal" value="${commentMap.commentTotal}" />
<c:set var="section" value="${commentMap.section}" />
<c:set var="pageNum" value="${commentMap.pageNum}" />
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
    <link rel="stylesheet" href="${contextPath}/resources/css/mypage.css">
    <script src="${contextPath}/resources/js/jquery-3.6.4.min.js"></script>
</head>

<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!--콘텐츠 영역 시작-->
    <div class="container">
        <div class="all">
            <!--첫 번째 줄 1, 2-->
            <div class="view_layout">
                <div class="title_box">
                    <h2>MY PAGE</h2>
                </div>
                <!--탭 메뉴 시작-->
                <div class="mypage_tabs one">
                    <ul class="tabs">
                        <li class="tab" data-tab="tab1">
                            <a href="${contextPath}/member/mypage.do">
                                <p>내 일기 모아보기</p>
                            </a>
                        </li>
                        <li class="tab" data-tab="tab2">
                            <a href="${contextPath}/member/mypage2.do">
                                <p>내가 쓴 댓글</p>
                            </a>
                        </li>
                        <li class="tab" data-tab="tab3">
                            <a href="${contextPath}/member/memberInfo.do">
                                <p>회원정보 수정</p>
                            </a>
                        </li>
                    </ul>
                </div>
                <!--탭 메뉴 끝-->

                <!--탭 내가 쓴 댓글 모아보기 시작-->
                <div id="tab2" class="pro_tab">
                    <div class="tbdiv">
                        <table>
                            <caption>내가 쓴 댓글 모아보기</caption>
                            <colgroup>
                                <col style="width:200px;">
                                <col style="width:300px">
                                <col style="width:700px;">
                            </colgroup>
                            <tbody>
                                <tr class="my_comment">
                                    <th>날짜</th>
                                    <th>글 제목</th>
                                    <th>댓글</th>
                                </tr>
                                <c:choose>
		                        	<c:when test="${empty commentList}">
		                        		<tr class="noComment">
		                        			<td colspan="3">작성한 댓글이 없습니다.</td>
	                        			</tr>
		                        	</c:when>
									<c:otherwise>
										<c:forEach var="comment" items="${commentList}">
											<tr class="my_comment">
			                                    <td>${comment.comDate}</td>
			                                    <td><a href="${contextPath}/diary/diaryInner.do?diaNo=${comment.diaNo}">${comment.diaTitle}</a></td>
			                                    <td>${comment.comContent}</td>
			                                </tr>
										</c:forEach>
									</c:otherwise>                        	
	                        	</c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!--탭 내가 쓴 댓글 모아보기 끝-->
                
                <div class="arrow_box">
                	<ul>
						<c:if test="${commentTotal !=0}">
							<c:if test="${commentTotal%10==0}">
								<c:set var="commentTotal" value="${commentTotal-1}" />
							</c:if>
							<fmt:parseNumber var="total" value="${commentTotal/50+1}" integerOnly="true" />
							
							<c:choose>
								<c:when test="${section > 1}">
									<li><a href="${contextPath}/member/mypage2.do?section=${section-1}&pageNum=5">
										<img src="${contextPath}/resources/images/left-arrow.png" alt="왼쪽 화살표"></a></li>
								</c:when>
							</c:choose>
							
							<c:choose>
								<c:when test="${section==total}">
									<c:set var="endVal" value="${(commentTotal-(50*(section-1)))/10+1}" />
								</c:when>
								<c:otherwise>
									<c:set var="endVal" value="5" />
								</c:otherwise>
							</c:choose>
							<c:forEach var="page" begin="1" end="${endVal}" step="1">
								<c:choose>
									<c:when test="${page==pageNum}">
										<li><a class="nowPage" href="${contextPath}/member/mypage2.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${contextPath}/member/mypage2.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a></li>
									</c:otherwise>
								</c:choose>									
							</c:forEach>
							
							<c:choose>
								<c:when test="${section!=total}">
									<li><a href="${contextPath}/member/mypage2.do?section=${section+1}&pageNum=1">
										<img src="${contextPath}/resources/images/right-arrow.png" alt="오른쪽 화살표"></a></li>
								</c:when>
							</c:choose>
						</c:if>
					</ul>
				</div>
            </div>
        </div>
	</div>
    <!--콘텐츠 영역 끝-->

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>

</html>