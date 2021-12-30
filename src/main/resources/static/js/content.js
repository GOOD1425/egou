$(document).ready(function(){
    $("#ul_menu li",parent.document).each(function (i,n) {
        $(n).removeClass("current")
        if(i==0) {
            $(n).addClass("current")
        }
    })
    $("#sellBtn").bind("click",function () {

    })
})
$("#sellcar").click(function (){
    $("#ul_menu li",parent.document).each(function (i,n) {
        if(i==0){
            $(n).removeClass("current")
        }else if(i==1){
            $(n).removeClass("current")
        }else{
            $(n).addClass("current")
        }
    })
})
