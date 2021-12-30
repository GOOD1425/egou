
$(document).ready(function(){
    $.post("/getIp",function (data) {
        $("#tc_top_currentCityName").text(data)
    })
    $("#ul_menu li").each(function (i,n) {
        $(n).removeClass("current")
        if(i===0) {
            $(n).addClass("current")
        }
    })
    $.post("user/checkLogin",function (data) {
    })

})
$("#login-car").bind("click",function (){
    var context=$("#login-car").text()
    if(context=="登录"){
        $("#J_iframe").attr("src","/login")
    }else{
        $.post("/user/selectByUsername",
            {username:context},
            function (data) {
                $("#J_iframe").attr("src","/myinfo")
                $("#J_iframe").find("#user_context").text(context)
            }
       )}
})
$("#index").click(function (){
    var context=$("#login-car").text()
        $.post("/index",
            function () {
                $("#J_iframe",parent.document).attr("src","/buy")
            }
        )}
)
$("#myOrder").click(function (){
    var context=$("#login-car").text()
    if(context=="登录"){
        $("#J_iframe").attr("src","/login")
    }else{
        $.post("/user/selectByUsername",
            {username:context},
            function (data) {
                $("#J_iframe").attr("src","/myinfo")
                $("#J_iframe").find("#user_context").text(context)
            }
        )}
})
$("#store").click(function (){
    window.open('/store')
})
$(document).ready(function(){
    $("#J_iframe").load(function(){//用于每次刷新时控制iframe高度初始化
        var iframeHeight;
        iframeHeight = $(this).contents().find("body").height();
        $(this).height(  iframeHeight+100 );
    });

});
$("#ul_menu li").each(function (i,n) {
    $(n).click(function () {
        if (i === 0) {
            $("#ul_menu li").eq(1).removeClass("current")
            $(n).addClass("current")
            $.post("/buycar/all", function () {
                $("#J_iframe").attr("src", "/buy")
            })
        } else {
            $("#ul_menu li").eq(0).removeClass("current")
            $(n).addClass("current")
            $("#J_iframe").attr("src", "/sell")
        }
    })
})
$("#buy").bind("click",function () {
    $("#ul_menu li",parent.document).each(function (i,n) {
         if(i==0){
            $(n).addClass("current")
            $("#J_iframe").attr("src","/buy")
        }else{
            $(n).removeClass("current")
        }
    })
})
$("#sell").bind("click",function () {
    $("#ul_menu li",parent.document).each(function (i,n) {
      if(i==1){
            $(n).addClass("current")
          $("#J_iframe").attr("src","/sell")
        }else{
            $(n).removeClass("current")
        }
    })
})