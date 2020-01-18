var SUCCESS = 200;
var ERROR = 404;
$(function () {
    $("#add").click(updateInfo);
    showDetail();
});

function updateInfo() {
    var a = GetRequest();
    var deptId = a["deptId"];
    var deptDesc = $("#deptname").val();
    var deptOrder = $("#deptOrder").val();
    var description = $("#description").val();
    var data = {deptId:deptId,deptName:deptDesc,deptDesc:description,deptOrder:deptOrder};
    $.ajax({
        url : "/dept/updateDeptInfo",
        headers: {
            'token':cookie('token')
        },
        data : data,
        type : "put",
        async: false,
        dataType : "json",
        success : function(result) {
            if (result.code == SUCCESS) {

                layer.msg(result.msg,{icon:1});

                setInterval(function(){
                location.href = "BuMenGL_bmxg.html?deptId="+deptId;
                },1000);

            } else {
                layer.msg(result.msg,{icon:2})
            }
        },
        error : function(e) {
            layer.msg("权限不足,未登录",{icon:2});
        }
     });
}


function showDetail() {
    var a = GetRequest();
    var deptId = a["deptId"];
    var data={ deptId : deptId};
    //console.log(data[i].id);
    $.ajax({
        url : "/dept/detail",
        headers: {
            'token':cookie('token')
        },
        data : data,
        type : "get",
        async: true,
        dataType : "json",
        success : function(result) {
            if (result.code == SUCCESS) {
                $("#deptname").val(result.data.deptName);
                $("#description").html(result.data.deptDesc);
                $("#deptOrder").val(result.data.deptOrder);

            } else {
                layer.msg(result.msg)
            }
        },
        error : function(e) {
            layer.msg("权限不足,未登录");
        }
    });
}




function GetRequest() {
    var url = location.search;
    var theRequest = new Object();
    if(url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1])
        }
    }
    return theRequest
}