$(document).ready(function(){
})
$("#reg-link").bind("click",function () {
    $("#login").attr("style","display:none")
    $("#reg").attr("style","display:block")
})
$("#login-link").bind("click",function () {
    $("#login").attr("style","display:block")
    $("#reg").attr("style","display:none")
})
/*检查*/
var flag=true;
$("#mobile").blur(function (){
    var phone = $("#mobile").val();
    var pattern = /^1[34578]\d{9}$/;
    if(phone==''){
         flag=false;
        $(".jyts-mobile").removeClass("jyts")
    }else if(!pattern.test(phone)){
         flag=false;
        $(".jyts-mobile").removeClass("jyts")
    }else{
        flag=true
        $(".jyts-mobile").addClass("jyts")
    }
})

$("#username").blur(function (){
    var username = $("#username").val();
    var pattern = /^1[34578]\d{9}$/;
    if(username==''){
        flag=false;
        $(".jyts-mobile1").removeClass("jyts")
    }else if(!pattern.test(username)){
        flag=false;
        $(".jyts-mobile1").removeClass("jyts")
    }else{
        flag=true
        $(".jyts-mobile1").addClass("jyts")
    }
})
$("#password1").focus(function (){
    $(".jyts-password1").removeClass("jyts")
})
$("#password1").blur(function () {
    var p1=$("#password1").val()
    var  pattern=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/;
    if(pattern.test(p1)){
        flag=true
        $(".jyts-password1").addClass("jyts")
    }else{
        flag=false;
        $(".jyts-password1").removeClass("jyts")
    }
})
$("#password2").blur(function () {
    var p1=$("#password1").val()
    var p2=$("#password2").val()
    if(p1!=p2){
        flag=false;
        $(".jyts-password2").removeClass("jyts")
    }else{
        flag=true
        $(".jyts-password2").addClass("jyts")
    }
})
$(document).keyup(function(event){
    var p1=$("#password1").val()
    var p2=$("#password2").val()
    var phone = $("#mobile").val()
    var username=$("#username").val()
    var password=$("#password").val()
    if(p1!==''&&p2!==''&&phone!==''){
        $("#reg-btn").removeClass("disabled")
    }else{
        $("#reg-btn").addClass("disabled")
    }
    if(username!==''&&password!==''){
        $("#user-btn2").removeClass("disabled")
    }else{
        $("#user-btn2").addClass("disabled")
    }
})

/*注册*/
$("#reg-btn").click(function () {
    var username=$("#mobile").val()
    var password1=$("#password1").val()
    var  password2=$("#password2").val()
    if(flag==true){
        if(password1===password2&&username!==''){
            $.post("/user/reg",
                {username:username,password:password1,phone:username },
                function (data) {
                    if(data==1){
                        $("#J_iframe", parent.document).attr("src","/login")
                    }else {
                        alert("注册失败！！！！")
                    }
                }),
                "json"
        }
    }
})
/*登录*/
$("#user-btn2").click(function () {
    var username=$("#username").val()
    var password=$("#password").val()
    if(username!=''&&password!=''){
        $.ajax({
            url: "/user/checkLogin",
            data:  {username:username,password:password},
            type:"POST",
            async:false,
            success: function (data) {
                if(data!="ok"){
                    $(".jyts-password").removeClass("jyts")
                }else{
                    $(".jyts-password").addClass("jyts")
                    $("#login-car",parent.document).text(username)
                    $("#type",parent.document).val("user")
                    $("#J_iframe",parent.document).attr("src","/buy")
                }
            }
        });
    }
})
