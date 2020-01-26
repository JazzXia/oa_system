var SUCCESS = 200;
var ERROR = 400;
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
                $("#roleName").html("级别:"+info.roleTypeName);
                $("#emp").html("员工:"+info.nickName);
                $("#name").html(info.nickName);
                $("#dept").html(info.deptName);
                $("#level").html(info.dutyName);
                $("#emp_no").html(info.empNo);
                $("#image").attr("src", "head_image/" + info.imageName);
               // console.log(info.imageName)
            } else {
                if (result.code == ERROR) {
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




function timetrans(date) {
    var date = new Date(date * 1000);
    var Y = date.getFullYear() + "-";
    var M = (date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date
            .getMonth() + 1)
        + "-";
    var D = (date.getDate() < 10 ? "0" + (date.getDate()) : date.getDate())
        + " ";
    var h = (date.getHours() < 10 ? "0" + date.getHours() : date.getHours())
        + ":";
    var m = (date.getMinutes() < 10 ? "0" + date.getMinutes() : date
            .getMinutes())
        + ":";
    var s = (date.getSeconds() < 10 ? "0" + date.getSeconds() : date
        .getSeconds());
    return Y + M + D + h + m + s
};

