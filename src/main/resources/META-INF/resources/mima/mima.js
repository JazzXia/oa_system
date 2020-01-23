var SUCCESS = 200;
$(function () {
    $('#update').click(updatePassword);
});

function updatePassword() {
    var url = "/role/updatePassword";
    var userName = $("#userName").val();
    console.log(userName);
    var prePassword = $("#prePassWord").val();
    console.log(prePassword);
    var newPassword = $("#newPassWord").val();
    var data = {newPassWord:newPassword,userName:userName,prePassWord:prePassword};
    $.ajax({
        url : url,
        data : JSON.stringify(data),
        type : "put",
        headers: {
            'token':cookie('token')
        },
        async:false,
        dataType : "json",
        contentType: "application/json; charset=utf-8",
        success : function(result) {
            if (result.code == SUCCESS) {

                layer.msg(result.msg,{icon:1});
                //delCookie("token");
                //delCookie("id");
                //delCookie("roleType");
                //layer.msg("重新登陆!",{icon:3});
                setInterval(function(){
                    location.href = "My_mim.html";
                },1000);
            } else {
                var msg = result.msg;
                if (result.code == ERROR) {
                    layer.msg(msg,{icon:2});
                } else {
                    layer.msg(msg,{icon:2})
                }
            }
        },
        error : function(e) {
            layer.msg("网络连接异常")
        }
    })
}