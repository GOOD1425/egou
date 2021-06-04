$(document).ready(function(){
    var name= $("#login-car", parent.document).text()
    var text = $("#user_context").text(name);
    if(text!=''&&text!=undefined&&text!=null){
        $(".content_wrapper iframe").attr("src","/mycar")
    }
    $(".user_ul li a").each(function (i,n){
        if(i==0){
            $(n).click(function () {
                $(".content_wrapper iframe").attr("src","/mycar")
            })
        }else if(i==1){
            $(n).click(function () {
                $(".content_wrapper iframe").attr("src","/mycollect")
            })
        }else if (i==2){
            $(n).click(function () {
                $(".content_wrapper iframe").attr("src","/myorder")
            })
        }
    })
   function clicka(){
       $(".user_ul li a").each(function (i,n){

       })
   }
})