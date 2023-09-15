// 비밀번호 확인
function checkResetPwd() {
    var pwd=document.getElementById("resetPwd").value;
    var pwdCheck=document.getElementById("resetPwdC").value;
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
        checkPwd.classList.add("checkPwd");
        document.getElementById("resetPwd").value=null;
    }else {
        if(checkNumPwd) {
	        checkPwd.innerHTML="비밀번호는 숫자를 포함하여 8글자 이상만 이용 가능합니다.";
	        checkPwd.classList.add("checkPwd");
	        document.getElementById("resetPwd").value=null;
	    }else {
	        checkPwd.innerHTML=null;
	        checkPwd.classList.remove("checkPwd");
	    }
    }

    
    

    // 비밀번호 일치
    if(pwd!="" && pwdCheck!="") {
        if(pwd!=pwdCheck) {
            checkPwdC.innerHTML="비밀번호가 일치하지 않습니다.";
            checkPwdC.classList.add("checkPwd");
            document.getElementById("resetPwdC").value=null;
        }else {
            checkPwdC.innerHTML=null;
            checkPwdC.classList.remove("checkPwd");
        }
    }

}

