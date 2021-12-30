$(document).ready(function(){
    getCar();
    $("#ul_menu li",parent.document).each(function (i,n) {
        $(n).removeClass("current")
        if(i==0 ){
            $(n).addClass("current")
        }
    })
    $.post("/getCarType",function (data) {
        var list=$(".ul_C")
        for (var i = 0; i < data.length; i++) {
            for (var j = 0; j < data[i].length; j++) {
                var ele=document.createElement("li");
                ele.innerHTML="<a href='javascript:void(0)'    class=ctype title="+data[i][j] +">"+data[i][j]+"</a>"
                list[i].appendChild(ele)
            }
        }
        $(".ul_C a").each(function (i,n) {
            $(n).click(function () {
                $(".ul_C a").each(function (j,m) {
                    $(m).removeClass("current-sel")
                })
                $(n).addClass("current-sel")
                getCar();
            })
        })
    })

    function getCar() {
        var res=document.querySelectorAll(".current-sel")
        $("#resultArg").val("")
        var age=$("#a_age a").text()
        var mileage=$("#a_mileage a").text()
        var color=$("#a_color a").text()
        console.log(res)
        $("#resultArg").val(
            "ctype:"+res[0].innerHTML+";cj:"+res[1].innerHTML+";jb:"+res[2].innerHTML+";age:"+age+";mileage:"+mileage+";color:"+color
        )
        console.log( $("#resultArg").val())
        var arg= $("#resultArg").val();
        $("#args").val(arg)

        $("#J_iframe").attr("src","/item")
    }
    $(".ul_E li").each(function (i,n) {
       $(n).mouseover(function () {
           $(".ul_E li").each(function (i,n) {
               $(n).children('a').removeClass("current")
           })
           $(n).children('a').addClass("current")
           $(".ul_C").each(function (j,m) {
               $(m).attr("style","display:none")
               if(i===j){
                   $(m).attr("style","display:block")
               }
           })
       })
    })
    $(".ul_jb  li").each(function (i,n) {
        $(n).click(function () {
            $(".ul_jb li").each(function (i,n) {
                $(n).children("a").removeClass("current-sel")
            })
            $(n).children("a").addClass("current-sel")
           getCar();
        })
    })
    $(".ul_cj li").each(function (i,n) {
        $(n).click(function () {
            $(".ul_cj li").each(function (i,n) {
                $(n).children("a").removeClass("current-sel")
            })
            $(n).children("a").addClass("current-sel")
          getCar();
        })
    })
    var flag=true;
    /*$("#suvId").click(function () {
        if(flag===true){
            $("#suvDiv").attr("style","display:block")
            $("#suvId i").removeClass("bi-chevron-down")
            $("#suvId i").addClass("bi-chevron-up")
            flag=false;
        }else {
            $("#suvId i").removeClass("bi-chevron-up")
            $("#suvId i").addClass("bi-chevron-down")
            $("#suvDiv").attr("style","display:none")
            flag=true
        }
    })*/
    /*车价*/
    var cj=true
   /* $("#userPrice").click(function () {

        if(cj===true){
            $("#priceDiv").attr("style","display:block")
            $("#userPrice i").removeClass("bi-chevron-down")
            $("#userPrice i").addClass("bi-chevron-up")
            cj=false;
        }else {
            $("#userPrice i").removeClass("bi-chevron-up")
            $("#userPrice i").addClass("bi-chevron-down")
            $("#priceDiv").attr("style","display:none")
            cj=true
        }
    })*/
    $("#levelMoreCheckId").click(function () {
        $("#levelMoreCheckId").attr("style","display:none")
        $(".ul_jb ").attr("style","display:none")
        $("#levelDiv").attr("style","display:block")
    })
    $("#levelDiv li").each(function (i,n) {
        $(n).children("i").click(function () {
            $(this).toggleClass("checked")
            var length=$(".checked").length
            if(parseInt(length)>0){
               $(".ddbox_ok ").removeClass("btn_disabled_bg")
            }else{
                $(".ddbox_ok ").addClass("btn_disabled_bg")
            }
        })
    })
    $(" #levelDiv .ddbox_cancel").click(function () {
        $(".ul_jb ").attr("style","display:block")
        $("#levelDiv").attr("style","display:none")
        $("#levelMoreCheckId").attr("style","display:block")
    })
    /*确定  级别*/
    $(" #levelDiv .ddbox_ok").click(function () {
        $(".ul_jb ").attr("style","display:block")
        $("#levelDiv").attr("style","display:none")
        $("#levelMoreCheckId").attr("style","display:block")
        
    })
    var cl=true
    $("#a_age").click(function () {
        if(cl===true){
            $("#div_age").attr("style","display:block")
            $("#a_age i").removeClass("bi-chevron-down")
            $("#a_age i").addClass("bi-chevron-up")
            $("#li_age").addClass("current")
            cl=false
        }else{
            $("#div_age").attr("style","display:none")
            $("#a_age i").removeClass("bi-chevron-up")
            $("#a_age i").addClass("bi-chevron-down")
            $("#li_age").removeClass("current")
            cl=true
        }
    })
    $("#div_age li").each(function (i,n) {
        $(n).click(function () {
            $("#div_age").attr("style","display:none")
            $("#a_age i").removeClass("bi-chevron-up")
            $("#a_age i").addClass("bi-chevron-down")
            $("#li_age").removeClass("current")
            cl=true
            var xltext=$(n).children("a").text()
            if(xltext==="不限"){
                $("#a_age a").text("车龄")
                getCar();
            }else{
                $("#a_age a").text(xltext)

                getCar();
            }
        })
    })
    var lc=true
    $("#a_mileage").click(function () {
        if(lc===true){
            $("#div_mileage").attr("style","display:block")
            $("#a_mileage i").removeClass("bi-chevron-down")
            $("#a_mileage i").addClass("bi-chevron-up")
            $("#li_mileage").addClass("current")
            lc=false
        }else{
            $("#div_mileage").attr("style","display:none")
            $("#a_mileage i").removeClass("bi-chevron-up")
            $("#a_mileage i").addClass("bi-chevron-down")
            $("#li_mileage").removeClass("current")
            lc=true
        }
    })
    $("#div_mileage li").each(function (i,n) {
        $(n).click(function () {
            $("#div_mileage").attr("style","display:none")
            $("#a_mileage i").removeClass("bi-chevron-up")
            $("#a_mileage i").addClass("bi-chevron-down")
            $("#li_mileage").removeClass("current")
            cl=true
            var lctext=$(n).children("a").text()
            if(lctext==="不限"){
                $("#a_mileage a").text("里程")
                getCar();
            }else{
                $("#a_mileage a").text(lctext)
                getCar();
            }
        })
    })
    var ac=true
    $("#a_color").click(function () {
        if(ac===true){
            $("#div_color").attr("style","display:block")
            $("#a_color i").removeClass("bi-chevron-down")
            $("#a_color i").addClass("bi-chevron-up")
            $("#li_color").addClass("current")
            ac=false
        }else{
            $("#div_color").attr("style","display:none")
            $("#a_color i").removeClass("bi-chevron-up")
            $("#a_color i").addClass("bi-chevron-down")
            $("#li_color").removeClass("current")
            ac=true
        }
    })
    $("#div_color li").each(function (i,n) {
        $(n).children("i").click(function () {
            $(this).toggleClass("checked")
            var len=$("#div_color .checked").length
            if(parseInt(len)>0){
                $("#div_color .ddbox_ok ").removeClass("btn_disabled_bg")
            }else{
                $("#div_color .ddbox_ok ").addClass("btn_disabled_bg")
            }
        })
    })
    $("#div_color .ddbox_ok ").click(function () {
        if($("#div_color .ddbox_ok ").hasClass("btn_disabled_bg")){

        }else{
            var colortext=''
            var color=$("#ul_color .checked")
            var len=color.length
            color.each(function (i,n) {
                var cl=$.trim($(n).parent("li").text())
                if(i===(parseInt(len)-1)){
                    colortext=colortext+cl
                }else{
                    colortext=colortext+cl+'+'
                }
            })
            $("#li_color").addClass("active")
            $("#div_color").attr("style","display:none")
            $("#a_color i").removeClass("bi-chevron-up")
            $("#a_color i").addClass("bi-chevron-down")
            $("#li_color").removeClass("current")
           $("#a_color a").text(colortext)
            getCar();
        }
    })
    $("#div_color .ddbox_cancel ").click(function () {
        var color=$("#ul_color .checked")
        if(color.length==0){
            $("#a_color a").text("颜色")
            $("#li_color").removeClass("active")
            getCar();
        }
        $("#div_color").attr("style","display:none")
        $("#a_color i").removeClass("bi-chevron-up")
        $("#a_color i").addClass("bi-chevron-down")
        $("#li_color").removeClass("current")
        ac=true
    })
    var ny=true
/*    $("#a_energy").click(function () {
        if(ny===true){
            $("#div_energy").attr("style","display:block")
            $("#a_energy i").removeClass("bi-chevron-down")
            $("#a_energy i").addClass("bi-chevron-up")
            $("#li_energy").addClass("current")
            ny=false
        }else{
            $("#div_energy").attr("style","display:none")
            $("#a_energy i").removeClass("bi-chevron-up")
            $("#a_energy i").addClass("bi-chevron-down")
            $("#li_energy").removeClass("current")
            ny=true
        }
    })*/
/*    $("#div_energy li").each(function (i,n) {
        $(n).click(function () {
            $("#div_energy").attr("style","display:none")
            $("#a_energy i").removeClass("bi-chevron-up")
            $("#a_energy i").addClass("bi-chevron-down")
            $("#li_energy").removeClass("current")
            ny=true
            var xltext=$(n).children("a").text()
            if(xltext==="不限"){
                $("#a_energy a").text("能源")
            }else{
                $("#a_energy a").text(xltext)
            }
        })
    })*/
    var zw=true
    $("#a_pedestal").click(function () {
        if(zw===true){
            $("#div_pedestal").attr("style","display:block")
            $("#a_pedestal i").removeClass("bi-chevron-down")
            $("#a_pedestal i").addClass("bi-chevron-up")
            $("#li_pedestal").addClass("current")
            zw=false
        }else{
            $("#div_pedestal").attr("style","display:none")
            $("#a_pedestal i").removeClass("bi-chevron-up")
            $("#a_pedestal i").addClass("bi-chevron-down")
            $("#li_pedestal").removeClass("current")
            zw=true
        }
    })
    $("#div_pedestal li").each(function (i,n) {
        $(n).click(function () {
            $("#div_pedestal").attr("style","display:none")
            $("#a_pedestal i").removeClass("bi-chevron-up")
            $("#a_pedestal i").addClass("bi-chevron-down")
            $("#li_pedestal").removeClass("current")
            zw=true
            var xltext=$(n).children("a").text()
            if(xltext==="不限"){
                $("#a_pedestal a").text("座位")
            }else{
                $("#a_pedestal a").text(xltext)
            }
        })
    })
})
$(document).ready(function(){
    $("#J_iframe").load(function(){//用于每次刷新时控制iframe高度初始化
        var iframeHeight;
        iframeHeight = $(this).contents().find("body").height();
        $(this).height(  iframeHeight+400 );
    });

});
$(document).keyup(function(event){
    var min=parseInt($("#price_min").val())
    var max=parseInt($("#price_max").val())
    if (min!=''&&max!=''&&(max>=min)){
        $("#aprice_ok").addClass("current")
    }else{
        $("#aprice_ok").removeClass("current")
    }
})
$(document).bind('click', function(e) {
    var e = e || window.event; //浏览器兼容性
    var elem = e.target || e.srcElement;
    while (elem) { //循环判断至跟节点，防止点击的是div子元素
        if (elem.id && (elem.id == 'div_age' || elem.id == 'a_age')) {
            return;
        }
        elem = elem.parentNode;
    }
    $("#div_age").attr("style","display:none")
    $("#a_age i").removeClass("bi-chevron-up")
    $("#a_age i").addClass("bi-chevron-down")
    $("#li_age").removeClass("current")
    cl=true
});
$(document).bind('click', function(e) {
    var e = e || window.event; //浏览器兼容性
    var elem = e.target || e.srcElement;
    while (elem) { //循环判断至跟节点，防止点击的是div子元素
        if(elem.id && (elem.id == 'div_energy' || elem.id == 'a_energy')){
            return;
        }
        elem = elem.parentNode;
    }
    $("#div_energy").attr("style","display:none")
    $("#a_energy i").removeClass("bi-chevron-up")
    $("#a_energy i").addClass("bi-chevron-down")
    $("#li_energy").removeClass("current")
    ny=true //点击的不是div或其子元素
});
$(document).bind('click', function(e) {
    var e = e || window.event; //浏览器兼容性
    var elem = e.target || e.srcElement;
    while (elem) { //循环判断至跟节点，防止点击的是div子元素
        if(elem.id && (elem.id == 'div_mileage' || elem.id == 'a_mileage')){
            return;
        }
        elem = elem.parentNode;
    }
    $("#div_mileage").attr("style","display:none")
    $("#a_mileage i").removeClass("bi-chevron-up")
    $("#a_mileage i").addClass("bi-chevron-down")
    $("#li_mileage").removeClass("current")
    lc=true
});
$(document).bind('click', function(e) {
    var e = e || window.event; //浏览器兼容性
    var elem = e.target || e.srcElement;
    while (elem) { //循环判断至跟节点，防止点击的是div子元素
        if(elem.id && (elem.id == 'div_color' || elem.id == 'a_color')){
            return;
        }
        elem = elem.parentNode;
    }
    $("#div_color").attr("style","display:none")
    $("#a_color i").removeClass("bi-chevron-up")
    $("#a_color i").addClass("bi-chevron-down")
    $("#li_color").removeClass("current")
    ac=true
});
$(document).bind('click', function(e) {
    var e = e || window.event; //浏览器兼容性
    var elem = e.target || e.srcElement;
    while (elem) { //循环判断至跟节点，防止点击的是div子元素
        if(elem.id && (elem.id == 'div_pedestal' || elem.id == 'a_pedestal')){
            return;
        }
        elem = elem.parentNode;
    }
    $("#div_pedestal").attr("style","display:none")
    $("#a_pedestal i").removeClass("bi-chevron-up")
    $("#a_pedestal i").addClass("bi-chevron-down")
    $("#li_pedestal").removeClass("current")
    zw=true
});
