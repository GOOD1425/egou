/*注册相关*/
var flag1=true;
var flag2=true;
var flag3=true;
var flag4=true;
/*登录**/
var sfalg1=true
var sfalg2=true
$(document).ready(function () {
    $('input[name="sname"]').bind("blur", function () {
        var sname = $('input[name="sname"]').val()
        if (sname == '' || sname == undefined) {
            flag1=false
            $("#p1").removeClass("ng-hide")
        } else {
            $.ajax({
                   url: "/store/checkName",
                    data: {sname: sname},
                    type:"POST",
                    async:false,
                    success: function (data) {
                        if (data =="error") {
                            $("#p2").removeClass("ng-hide")
                            flag1=false
                        } else {
                            flag1=true
                            $("#p1").addClass("ng-hide")
                            $("#p2").addClass("ng-hide")
                        }
                    }
            });
        }
    })
    $('input[name="name"]').bind("blur", function () {
        var name = $('input[name="name"]').val()
        re = /[\u4E00-\u9FA5]/g;
        if (name == '' || name == undefined) {
            flag2=false
            $("#p3").removeClass("ng-hide")
        } else if (!re.test(name)) {
            flag2=false
            $("#p3").addClass("ng-hide")
            $("#p5").removeClass("ng-hide")
        } else {
            flag2=true
            $("#p3").addClass("ng-hide")
            $("#p5").addClass("ng-hide")
        }
    })
    $('input[name="password"]').bind("blur", function () {
        var password = $('input[name="password"]').val()
        if (password == '' || password === undefined) {
            flag3=false
            $("#p9").removeClass("ng-hide")
        } else  {
            flag3=true
            $("#p9").addClass("ng-hide")
        }
    })
    $('input[name="tel"]').bind("blur", function () {
        var tel = $('input[name="tel"]').val()
        var pattern = /^1[34578]\d{9}$/;
        if (tel == '' || tel == undefined) {
            flag4=false
            $("#p6").removeClass("ng-hide")
        } else if (!pattern.test(tel)) {
            flag4=false
            $("#p6").addClass("ng-hide")
            $("#p7").removeClass("ng-hide")
        } else {
            $.ajax({
                url: "/store/checkTel",
                data:  {tel: tel},
                type:"POST",
                async:false,
                success:  function (data) {
                    if (data =="error") {
                        flag4=false
                        $("#p6").addClass("ng-hide")
                        $("#p8").removeClass("ng-hide")
                        $("#p7").addClass("ng-hide")
                    } else {
                        flag4=true
                        $("#p6").addClass("ng-hide")
                        $("#p7").addClass("ng-hide")
                        $("#p8").addClass("ng-hide")
                    }
                }
            });

        }
    })
    $("#lpassword").bind("blur",function () {
        var lpassword=$("#lpassword").val()
        if(lpassword==''||lpassword==undefined){
            sfalg2=false
            $("#perror2").removeClass("ng-hide")
        }else{
            sfalg2=true
            $("#perror2").addClass("ng-hide")
        }
    })
    $("#lname").bind("blur",function () {
        var lname=$("#lname").val()
        if(lname==''||lname==undefined){
            sfalg1=false
            $("#perror1").removeClass("ng-hide")
        }else{
            sfalg1=true
            $("#perror1").addClass("ng-hide")
        }
    })
})
function sRegister() {
    var sname = $('input[name="sname"]').val()
    var name = $('input[name="name"]').val()
    var tel = $('input[name="tel"]').val()
    var password = $('input[name="password"]').val()
    if (sname == '' || sname == undefined) {
        $("#p1").removeClass("ng-hide")
        flag1=false
    }
    if (name == '' || name == undefined) {
        $("#p3").removeClass("ng-hide")
        flag2=false
    }
    if (tel == '' || tel == undefined) {
        $("#p6").removeClass("ng-hide")
        flag4=false
    }
    if (password == '' || password == undefined) {
        $("#p9").removeClass("ng-hide")
        flag3=false
    }
    if(flag1&&flag2&&flag3&&flag4){
        console.log("Ss")
        $.post("/store/reg",
            {sname:sname,name:name,tel:tel,password:password,username:tel},
            function (data) {
                if (data=="ok"){
                    window.location.href="/store"
                }
            })
    }
}
/*登录相关*/
function sLogin() {
    var lname=$("#lname").val()
    var lpassword=$("#lpassword").val()
    if(lname==''||lname==undefined){
        $("#perror1").removeClass("ng-hide")
        sfalg1=false
    }
    if(lpassword==''||lpassword==undefined){
        $("#perror2").removeClass("ng-hide")
        sfalg2=false
    }
    if(sfalg1&&sfalg2){
        $.ajax({
            url: "/store/checkLogin",
            data: {username: lname,password: lpassword},
            type:"POST",
            async:false,
            success: function (data) {
                debugger
                if (data =="error") {
                    $("#epErrorLogin").removeClass("ng-hide")
                } else {
                    $("#type",parent.document).text("admin")
                    $("#epErrorLogin").addClass("ng-hide")
                }
            }
        });
    }
}