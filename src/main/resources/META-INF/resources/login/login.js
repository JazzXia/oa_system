var SUCCESS = 200;
var ERROR = 403;
$(function () {
    $("#info").click(login);
    $("#user").blur(checkName);
    $("#pass").blur(checkPassword)
});


function checkPassword() {
    var password = $("#pass").val();
    var rule = /^\w{6,20}$/;
    if (!rule.test(password)) {
        $("#pass").next().html("6~20个字符");
        return false
    }
    $("#pass").next().empty();
    return true
}
function checkName() {
    var username = $("#user").val();
    var rule = /^\w{6,20}$/;
    if (!rule.test(username)) {
        $("#user").next().html("6~20个字符");
        return false
    }
    $("#user").next().empty();
    return true
}

function login() {
    var url = "role/login";
    var userName = $("#user").val();
    var password = $("#pass").val();
    var n = checkPassword() + checkName();
    if (n != 2) {
        return
    }
    var data = {
        userName : userName,
        password : password
    };
    $.ajax({
        url : url,
        data : data,
        type : "post",
        dataType : "json",
        success : function(result) {
            if (result.code == SUCCESS) {
                var user = result.data;
                //console.log(user.token)
                addCookie("token", user.user.token);
                addCookie("id",user.user.userId);
                addCookie("roleType", user.user.roleType);

                layer.msg("登录成功",{icon:1});
                setInterval(function(){
                    location.href = "/child.html";
                },1000);

            } else {
                var msg = result.msg;
                if (result.code == ERROR) {
                    console.log(msg)
                    $("#pass").next().html(msg);
                } else {
                    layer.msg(msg)
                }
            }
        },
        error : function(e) {
            alert("网络连接异常",e.msg)
        }
    })
};

