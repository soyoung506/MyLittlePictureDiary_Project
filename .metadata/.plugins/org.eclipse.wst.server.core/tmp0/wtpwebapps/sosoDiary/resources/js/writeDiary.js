$(function() {
    // 펜툴 클릭 시 슬라이드
    let sw1=false;
    let sw2=false;
    $('.penWidth>li').click(function() {
        sw1=!sw1;
        if(sw1) {
            $('.penClear').animate({
                width : 80
            }, 500);
            sw2=false;
            $('.penWidth').animate({
                width : 400
            }, 500);
        }else {
            $('.penWidth').animate({
                width : 80
            }, 500);
        }
    });
    $('.penClear>li').click(function() {
        sw2=!sw2;
        if(sw2) {
            $('.penWidth').animate({
                width : 80
            }, 500);
            sw1=false;
            $('.penClear').animate({
                width : 400
            }, 500);
        }else {
            $('.penClear').animate({
                width : 80
            }, 500);
        }
    });


    // 펜툴 선택 시 현재 선택옵션 보여주기
    $('.penWidth button').click(function() {
        var cloneDiv = $(this).clone();
        $('.penWidth .now').empty();
        $('.penWidth .now').css("border","none");
        $('.penWidth .now').css("padding-top","0");
        cloneDiv.appendTo('.penWidth .now');
    });
    $('.penClear button').click(function() {
        var cloneDiv = $(this).clone();
        $('.penClear .now').empty();
        $('.penClear .now').css("border","none");
        $('.penClear .now').css("padding-top","0");
        cloneDiv.appendTo('.penClear .now');
    });


    // input 클릭 시 아웃라인 생성
    $('input, select, textarea').not('.download').focusin(function() {
        $(this).animate({
            "outline-offset" : 3
        }, 100);
    });
    $('input, select, textarea').not('.download').focusout(function() {
        $(this).animate({
            "outline-offset" : 0
        }, 1);
    });
    
});


        
// 그룹선택 활성화
function selectPublic(){
	
	var publicArea = document.getElementById("publicArea");
    var selectValue = publicArea.options[publicArea.selectedIndex].value;
	var groupArea = document.getElementById("groupArea");
   
    if(selectValue==3) {
	   groupArea.removeAttribute("disabled");
    }else {
	   groupArea.setAttribute("disabled","disabled");
	}

}