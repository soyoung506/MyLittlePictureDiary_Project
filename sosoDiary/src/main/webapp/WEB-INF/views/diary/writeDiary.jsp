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
    <script>
            function addDiary(obj){
            	saveDrawing($('#canvas'), obj);
    		}
            
           function saveDrawing(target, obj) {
	           	if (target != null && target.length > 0) {
	           		var t = target[0];
	           		html2canvas(t).then(function(canvas) {
	           			var myImg = canvas.toDataURL("image/jpeg");
	           			myImg = myImg.replace("data:image/jpeg;base64,", "");
	
	           			$.ajax({
	           				type : "POST",
	           				data : {
	           					"imgSrc" : myImg
	           				},
	           				dataType : "text",
	           				url : "${contextPath}/diary/saveDrawing.do",
	           				success : function(data) {
	           					console.log(data);
	           					obj.action="${contextPath}/diary/addDiary.do";
	           	    			obj.submit();
	           				},
	           				error : function(a, b, c) {
	           					alert("error");
	           				}
	           			});
	           		});
	           	}
           }
    </script>
</head>
<body>
     <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <!-- main 시작 -->
    <div class="content">
        <section class="drawing">
            <h1>나의 그림 일기</h1>
            <div class="canvasBox">
                <div class="tool">
                    <ul class="penColor pentool">
                        <input type="color" class="colorChange">
                    </ul>
                    <ul class="penWidth pentool">
                        <li><div class="now">굵기</div></li>
                        <li><button value="2"><div class="pen2"></div></button></li>
                        <li><button value="7"><div class="pen7"></div></button></li>
                        <li><button value="12"><div class="pen12"></div></button></li>
                        <li><button value="20"><div class="pen20"></div></button></li>
                    </ul>
                    <ul class="penClear pentool">
                        <li><div class="now">지우개</div></li>
                        <li><button value="2"><div class="clear2"></div></button></li>
                        <li><button value="7"><div class="clear7"></div></button></li>
                        <li><button value="15"><div class="clear15"></div></button></li>
                        <li><button value="25"><div class="clear25"></div></button></li>
                    </ul>
                    <div class="allClear">모두<br>지우기</div>
                </div>
                <canvas id="canvas" width="1000" height="500"></canvas>
                
            </div>
        </section>
        <section class="writing">
            <form action="" method="post" name="diary">
                <fieldset class="selectOption">
                    <legend class="blind">옵션 선택</legend>
                    <select name="diaPublic" id="publicArea" onchange="selectPublic()">
                        <option value="1" selected>비공개</option>
                        <option value="2">모두공개</option>
                   		<c:if test="${groupVO.gNo!=0}">
                   			<option value="3">그룹공개</option>
                   		</c:if>
                    </select>
                    <select name="gNo" id="groupArea" disabled>
                        <c:choose>
                       		<c:when test="${groupVO.gNo==0}">
                       			<option value="null" selected>그룹이 없습니다</option>
                       		</c:when>
                       		<c:when test="${groupVO.gNo!=0}">
                       			<option value="${groupVO.gNo}" selected>${groupVO.gName}</option>
                       		</c:when>
                       	</c:choose>
                    </select>
                </fieldset>
                <fieldset class="writeDiary">
                    <legend class="blind">일기 작성</legend>
                    <input type="text" name="diaTitle" id="diaryTitle" placeholder="제목" maxlength="30" autocomplete='off' required>
                    <textarea name="diaContent" id="diaryContent" placeholder="내용" maxlength="2000" required></textarea>
                </fieldset>
                <input type="button" class="download" value="저장" onclick="addDiary(this.form)">
            </form>
        </section>

    </div>
    <!-- main 종료 -->

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>