// 아이디 확인
function checkJoinId() {
    var id=document.getElementById("joinId").value;
    var checkId=document.getElementById("checkId");
    var checkNumId=true;

    for(var i=0; i<9; i++) {
        if(id.indexOf(i)!=-1) {
            checkNumId=false;
        }
    }
    // 아이디 글자수 및 소문자, 숫자 입력 
    if(id.length<6) {
        checkId.innerHTML="아이디는 숫자를 포함하여 6글자 이상만 이용 가능합니다.";
        checkId.classList.add("checkJoin");
        document.getElementById("joinId").value=null;
    }else {
        if(checkNumId) {
            checkId.innerHTML="아이디는 숫자를 포함하여 6글자 이상만 이용 가능합니다.";
            checkId.classList.add("checkJoin");
            document.getElementById("joinId").value=null;
        }else {
            checkId.innerHTML=null;
            checkId.classList.remove("checkJoin");
        }
    }

}

// 비밀번호 확인
function checkJoinPwd() {
    var pwd=document.getElementById("joinPwd").value;
    var pwdCheck=document.getElementById("joinPwdC").value;
    var checkPwd=document.getElementById("checkPwd");
    var checkPwdC=document.getElementById("checkPwdC");
    var checkNumPwd=true;

	for(var i=0; i<9; i++) {
        if(pwd.indexOf(i)!=-1) {
            checkNumPwd=false;
        }
    }
    // 비밀번호 글자수 및 영문자, 숫자 입력
    if(pwd.length<8) {
        checkPwd.innerHTML="비밀번호는 숫자를 포함하여 8글자 이상만 이용 가능합니다.";
        checkPwd.classList.add("checkJoin");
        document.getElementById("joinPwd").value=null;
    }else {
        if(checkNumPwd) {
	        checkPwd.innerHTML="비밀번호는 숫자를 포함하여 8글자 이상만 이용 가능합니다.";
	        checkPwd.classList.add("checkJoin");
	        document.getElementById("joinPwd").value=null;
	    }else {
	        checkPwd.innerHTML=null;
	        checkPwd.classList.remove("checkJoin");
	    }
    }

    
    

    // 비밀번호 일치
    if(pwd!="" && pwdCheck!="") {
        if(pwd!=pwdCheck) {
            checkPwdC.innerHTML="비밀번호가 일치하지 않습니다.";
            checkPwdC.classList.add("checkJoin");
            document.getElementById("joinPwdC").value=null;
        }else {
            checkPwdC.innerHTML=null;
            checkPwdC.classList.remove("checkJoin");
        }
    }

}

// 약관 팝업창
$(function() {
    // 이용약관 팝업창 오픈
    $('.agreeUsing').click(function() {
        $('.popup1').fadeIn();
    });
    // 개인정보 이용 및 수집 동의 오픈
    $('.agreePersonal').click(function() {
        $('.popup2').fadeIn();
    });
    // 팝업창 클로즈
    $('.popup_close').click(function() {
        $('.popup').fadeOut();
    })

    // 팝업창 클로즈버튼 호버
    $('.popup_close').hover(function() {
        $(this).stop(true).animate({
            backgroundColor : '#000',
            color : '#FFF'
        }, 300);
    }, function() {
        $(this).stop(true).animate({
            backgroundColor : '#FFF',
            color : '#000'
        }, 300);
    });

});

