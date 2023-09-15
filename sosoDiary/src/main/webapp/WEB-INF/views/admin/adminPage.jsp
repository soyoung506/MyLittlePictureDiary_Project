<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test="${notiCategory==1}"><c:set var="noti" value="공지사항" /></c:when>
	<c:otherwise><c:set var="noti" value="자주 묻는 질문" /></c:otherwise>
</c:choose>
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
    <script type="text/javascript">
	    function fn_delNotice(notiNo,notiCategory) {
		    Swal.fire({
		      title: '해당 내용을 삭제하시겠습니까?',
		      text: "삭제된 내용은 복구되지 않습니다.",
		      icon: 'warning',
		      showCancelButton: true,
		      confirmButtonColor: '#3085d6',
		      cancelButtonColor: '#d33',
		      confirmButtonText: '삭제',
		      cancelButtonText: '취소',
		    }).then((result) => {
		      if (result.isConfirmed) {
		        location.href='${contextPath}/admin/delNotice.do?notiNo='+notiNo+'&notiCategory='+notiCategory;
		      }
		    });
		}	
    </script>
    <c:choose>
		<c:when test="${msg=='delNotice'}">
			<script>
				window.onload=function () {
					Swal.fire({
	                    icon: 'success',
	                    title:'${noti}이 삭제되었습니다.',
	                    text: '새로운 ${noti}을 작성해 보세요.',
	                });
				}
			</script>
		</c:when>
		<c:when test="${msg=='addNotice'}">
			<script>
				window.onload=function () {
					Swal.fire({
	                    icon: 'success',
	                    title:'${noti}이 추가되었습니다.',
	                    text: '${noti}을 확인해 보세요.',
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
                    <h2>${noti} 관리</h2>
                </div>
                <!--탭 메뉴 시작-->
                <div class="mypage_tabs one">
                    <ul class="tabs">
                        <li class="tab" data-tab="tab1">
                            <a href="${contextPath}/admin/adminPage.do?notiCategory=1">공지사항 관리</a>
                        </li>
                        <li class="tab" data-tab="tab2">
                            <a href="${contextPath}/admin/adminPage.do?notiCategory=2">자주 묻는 질문 관리</a>
                        </li>
                        <li class="tab" data-tab="tab3">
                            <a href="${contextPath}/admin/writeNotice.do">공지사항 작성</a>
                        </li>
                    </ul>
                </div>
                <!--탭 메뉴 끝-->

                <div id="tab2" class="pro_tab notiTBL">
                    <div class="tbdiv">
                        <table>
                            <caption>관리페이지</caption>
                            <colgroup>
                                <col style="width:130px;">
                                <col style="width:300px;">
                                <col style="width:700px;">
                                <col style="width:80px;">
                            </colgroup>
                            <tbody>
                                <tr class="my_comment">
                                    <th>날짜</th>
                                    <th>제목</th>
                                    <th>내용</th>
                                    <th>삭제</th>
                                </tr>
                                <c:choose>
		                        	<c:when test="${empty noticeList}">
		                        		<tr class="noComment">
											<td colspan="3">${noti}이 없습니다.</td>		                        			
	                        			</tr>
		                        	</c:when>
									<c:otherwise>
										<c:forEach var="notice" items="${noticeList}">
											<tr class="my_comment">
			                                    <td>${notice.notiDate}</td>
			                                    <td>${notice.notiTitle}</td>
			                                    <td>${notice.notiContent}</td>
			                                    <td><button type="button" onclick="fn_delNotice(${notice.notiNo},${notiCategory})">삭제</button></td>
			                                </tr>
										</c:forEach>
									</c:otherwise>                        	
	                        	</c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
	</div>
    <!--콘텐츠 영역 끝-->

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>

</html>