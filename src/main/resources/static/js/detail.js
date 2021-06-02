$(document).ready(function(){
    var carId=$("#carId").val()
    $.post("/buycar/selectById",{carId:carId},function (data) {
        console.log(data)
        $("#taoche-details-pic img").attr("src",data.appearpic)
        $(".title span[name='tittle']").text(data.type+"|"+data.brand)
        var prodate=new Date(data.prodate);
        var deploy=new Date(data.update);
        $(".summary-attrs dd[name='date']").text(prodate.getFullYear()+"年"+(prodate.getMonth()+1)+"月")
        $(".summary-attrs dd[name='mileage']").text(data.mileage+" 万公里")
        $(".summary-attrs dd[name='area']").text(data.area)
        $("#deploy-time").text("发布时间："+deploy.getFullYear()+"-"+(deploy.getMonth()+1) +"-"+deploy.getDate())
        var now=new Date();
        var  year=now.getFullYear()-prodate.getFullYear()
        var  mouth=now.getMonth()-prodate.getMonth()
        if(year>0){
            var xs=year+"年"+mouth+"个月"
        }else{
            var xs=mouth+"个月"
        }
        $("#xs").text(xs)
        $("#smileage").text(data.mileage)
        var belongType=data.belongType;
        if(belongType=="user"){
            $("#belongType").text("私人")
        }else if (belongType=="admin") {
            $("#belongType").text("商家")
        }
        $("#sarea").text(data.area)
    })
    $("#collect").click(function () {
       var islogin=$("#login-car",parent.document).text()
        if(islogin=="登录"){
            $("#J_iframe",parent.document).attr("src","/login")
        }else{
            $.post("/collect",{username:islogin,carId:carId},function (data){
                if(data=="ok"){
                    alert("收藏成功")
                }else {
                    alert("收藏失败")
                }
            })
        }
    })
    $("#order").click(function (){

    })
})