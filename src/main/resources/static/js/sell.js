window.onload=function(){
    $("#sell").bind("click", function () {
        var type = $("#type", parent.document).val()
        var username = $("#login-car", parent.document).text()
        $.ajax({
            url: "/user/selectByUsername",
            data: {username: username},
            type:"POST",
            async:false,
            success: function (data) {
                if (data =="error") {
                  $("#J_iframe",parent.document).attr("src","/login")
                    //    $("#J_iframe",parent.document).attr("src","/sellcar")
                } else {
                    $("#J_iframe",parent.document).attr("src","/sellcar")
                }
            }
        });

    })
}
$(document).ready(function(){
    $("#ul_menu li",parent.document).each(function (i,n) {
        $(n).removeClass("current")
        if(i==2) {
            $(n).addClass("current")
        }
    })
})