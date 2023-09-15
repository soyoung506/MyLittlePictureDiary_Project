$(function() {
    let position, x, y;
    let can = document.getElementById('canvas');
    let context = can.getContext('2d');
    let lineWidth = 2;
    let lineColor = 'black';
    let oldColor, oldLineWidth;
    let clearSW = false;
    let sw =false;
    $('.colorChange').change(function() {
        lineColor=$(this).val();
        lineWidth=oldLineWidth;
    });
    $('.penWidth>li>button').click(function() {
        lineWidth=$(this).val();
        oldLineWidth=$(this).val();
        lineColor=$('.colorChange').val();

    });
    $('.penClear>li>button').click(function() {
        lineColor='white';
        lineWidth=$(this).val();
    });
    $('.allClear').click(function() {
        can.width=can.width;
    });
    $('#canvas').on({
        mousedown : function(event) {
            sw = true; 
            position=$(this).offset();
            x=event.pageX - position.left;
            y=event.pageY - position.top;
            context.beginPath();
            context.moveTo(x,y);
        },
        mousemove : function(event) {
            if(sw) {
                position=$(this).offset();
                x=event.pageX - position.left;
                y=event.pageY - position.top;
                context.strokeStyle=lineColor;
                context.lineWidth=lineWidth;
                context.lineTo(x,y);
                context.stroke();
            }
        },
        mouseup : function(event) {
            sw = false;
        }
    });
});