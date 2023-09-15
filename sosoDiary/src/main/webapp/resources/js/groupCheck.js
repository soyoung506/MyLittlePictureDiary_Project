// 그룹명 공백제거
function nameCheck(obj) {
    var str_space = /\s/;  
    var checkId=document.getElementById("checkId");
    if(str_space.exec(obj.value)) { 
    	checkId.innerHTML="공백은 제거됩니다.";
        checkId.classList.add("checkJoin");
        obj.focus();
        obj.value = obj.value.replace(' ',''); 
        return false;
    }
}

// 비밀번호 확인
function checkJoinPwd() {
    var pwd=document.getElementById("joinPwd").value;
    var pwdCheck=document.getElementById("joinPwdC").value;
    var checkPwd=document.getElementById("checkPwd");
    var checkPwdC=document.getElementById("checkPwdC");

    // 비밀번호 글자수 입력
    if(pwd.length<6) {
        checkPwd.innerHTML="비밀번호는 6글자 이상만 이용 가능합니다.";
        checkPwd.classList.add("checkJoin");
        document.getElementById("joinPwd").value=null;
    }else {
        checkPwd.innerHTML=null;
        checkPwd.classList.remove("checkJoin");

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

