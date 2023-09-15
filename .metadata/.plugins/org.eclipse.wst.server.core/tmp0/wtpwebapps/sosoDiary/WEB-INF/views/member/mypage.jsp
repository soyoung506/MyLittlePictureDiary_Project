<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="diaryList" value="${diaryMap.diaryList}" />
<c:set var="diaryTotal" value="${diaryMap.diaryTotal}" />
<c:set var="section" value="${diaryMap.section}" />
<c:set var="pageNum" value="${diaryMap.pageNum}" />
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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <c:choose>
		<c:when test="${msg=='modMember'}">
			<script>
				window.onload=function () {
					Swal.fire({
	                    icon: 'success',
	                    title:'회원정보가 수정되었습니다.',
	                    text: '나만의 그림일기를 완성해 보세요!',
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

                <!--탭 일기 모아보기 시작-->
                <div id="tab1" class="pro_detail pro_tab  pic">
                    <div class="view_layout diaryList">
                        <ul>
                        	<c:choose>
	                        	<c:when test="${empty diaryList}">
	                        		<li class="noDiary">작성한 일기가 없습니다.</li>
	                        	</c:when>
								<c:otherwise>
									<c:forEach var="diary" items="${diaryList}">
										<li>
			                                <a href="${contextPath}/diary/diaryInner.do?diaNo=${diary.diaNo}">
			                                    <div class="innerBox">
			                                        <div class="innerTop">
			                                        	<c:choose>
			                                        		<c:when test="${diary.diaPublic==1}">
			                                        			<span>나만공개</span>
			                                        		</c:when>
			                                        		<c:when test="${diary.diaPublic==2}">
			                                        			<span>모두공개</span>
			                                        		</c:when>
			                                        		<c:when test="${diary.diaPublic==3}">
			                                        			<span>${diary.gName}</span>
			                                        		</c:when>
			                                        	</c:choose>
			                                            <span>조회수 ${diary.diaView}</span>
			                                            <span>${diary.diaDate}</span>
			                                        </div>
			                                        <img src="${contextPath}/diaryimage?diaNo=${diary.diaNo}" alt="그림">
			                                        <p>${diary.diaTitle}</p>
			                                        <p class="p_content">${diary.diaContent}</p>
			                                    </div>
			                                </a>
			                            </li>
									</c:forEach>
								</c:otherwise>                        	
                        	</c:choose>
                        </ul>
                    </div>
                </div>
                <!--탭 일기 모아보기 끝-->
                <div class="arrow_box">
                	<ul>
						<c:if test="${diaryTotal !=0}">
							<c:if test="${diaryTotal%6==0}">
								<c:set var="diaryTotal" value="${diaryTotal-1}" />
							</c:if>
							<fmt:parseNumber var="total" value="${diaryTotal/30+1}" integerOnly="true" />
							
							<c:choose>
								<c:when test="${section > 1}">
									<li><a href="${contextPath}/member/mypage.do?section=${section-1}&pageNum=5">
										<img src="${contextPath}/resources/images/left-arrow.png" alt="왼쪽 화살표"></a></li>
								</c:when>
							</c:choose>
							
							<c:choose>
								<c:when test="${section==total}">
									<c:set var="endVal" value="${(diaryTotal-(30*(section-1)))/6+1}" />
								</c:when>
								<c:otherwise>
									<c:set var="endVal" value="5" />
								</c:otherwise>
							</c:choose>
							<c:forEach var="page" begin="1" end="${endVal}" step="1">
								<c:choose>
									<c:when test="${page==pageNum}">
										<li><a class="nowPage" href="${contextPath}/member/mypage.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${contextPath}/member/mypage.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a></li>
									</c:otherwise>
								</c:choose>									
							</c:forEach>
							
							<c:choose>
								<c:when test="${section!=total}">
									<li><a href="${contextPath}/member/mypage.do?section=${section+1}&pageNum=1">
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