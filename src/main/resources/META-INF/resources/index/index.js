var SUCCESS = 200;
var ERROR = 404;
$(function () {
    showUserInfo();
    $('#exit').click(logout);
});

function logout() {
    var data = {};

    $.ajax({
        url : "role/logout",
        headers: {
            'token':cookie('token')
        },
        data : data,
        type : "GET",
        dataType : "json",
        success : function(result) {
            if (result.code == SUCCESS) {
                delCookie("token");
                delCookie("id");
                delCookie("roleType");
                layer.msg("已退出");
                location.href = "login.html";
            } else {
                if (result.code == ERROR)
                {
                    layer.msg(result.msg)
                }
            }
        },
        error : function(e) {
            layer.msg("权限不足,不能登录");
            location.href = "login.html";
        }
    })

}



function showUserInfo() {
    var id = cookie("id");
    var data = {userId:id};
    $.ajax({
        url : "role/info",
        headers: {
            'token':cookie('token')
        },
        data : data,
        type : "GET",
        dataType : "json",
        success : function(result) {
            if (result.code == SUCCESS) {
                var info = result.data;
                $("#name").html(info.nickName);
                $("#dept").html(info.deptName);
                $("#level").html(info.dutyName);
                $("#emp_no").html(info.empNo);
                $("#image").attr("src", "head_image/" + info.imageName);
               // console.log(info.imageName)
            } else {
                if (result.code == ERROR)
                {
                    layer.msg(result.msg)
                }
            }
        },
        error : function(e) {
            layer.msg("权限不足,不能登录");
            location.href = "login.html";
        }
    })
}

