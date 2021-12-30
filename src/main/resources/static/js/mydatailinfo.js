$(document).ready(function () {
    var text = $("#user_context", parent.document).text();
    var type = $("#type", parent.parent.document).val();
    $.post("/user/selectMyInfo", {username: text, type: type}, function (data) {
        $("#NickNameTxt").text(data.username)
        $("#NickName").val(data.username)
        $("#CityIDTxt").text(data.city)
        $("#AddressTxt").text(data.telephone)
        $("#AddsName").val(data.telephone)
        $("#userId").val(data.userId)
    })
    var flag=true;
    $("#NickName").bind("blur",function (){

        var name=/^[\u4E00-\u9FA5A-Za-z0-9_]+$/;
        var nickname=$("#NickName").val()
        if (!name.test(nickname)){
            $("#nickalert").attr("style", "display:block")
             flag=false;
        }else {
            $("#nickalert").attr("style", "display:none")
            flag=true
        }
    })
    var flag1=true;
    $("#AddsName").bind("blur",function (){
        var phone =  $("#AddsName").val();
        var pattern = /^1[34578]\d{9}$/;
        if (!pattern.test(phone)){
            flag1=false
            $("#phonealert").attr("style", "display:block")
        }else {
            flag1=true
            $("#phonealert").attr("style", "display:none")
        }
    })
    $("#UserEditBtn").bind("click", function () {
        $("#userProfileText").attr("style", "display:none")
        $("#userProfileInput").attr("style", "display:block")
    })
    $("#UserSaveBtn").click(function () {
        if (flag&&flag1){
            var phone =  $("#AddsName").val();
            var nickname=$("#NickName").val()
            var userId=$("#userId").val()
            $.post("/user/updateInfo", {username: nickname, phone: phone,userId:userId}, function (data) {
                    if(data=="ok"){
                        alert("修改成功！！")
                    }
            })
        }
    })
})
