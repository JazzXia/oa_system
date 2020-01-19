var SUCCESS = 200;
var ERROR = 404;
$(function () {
    listDeptInfo();
    showInfoDetail();
});

function showInfoDetail() {
    var a = GetRequest();
    var dutyId = a["dutyId"];
    var data={ dutyId : dutyId};

    $.ajax({
        url : "/linkDutyInfo/detail",
        headers: {
            'token':cookie('token')
        },
        data : data,
        type : "get",
        async: true,
        dataType : "json",
        success : function(result) {
            if (result.code == SUCCESS) {
                //var deptId = $("#addEmp select").val(result.data.dutyId);
                var dutyName = $("#dutyName").val(result.data.dutyName);
                var dutyDescription = $("#description").html(result.data.dutyDescription);
            } else {
                layer.msg(result.msg)
            }
        },
        error : function(e) {
            layer.msg("权限不足,未登录");
        }
    });
}


//展示所有的部门
function listDeptInfo() {
    var data = {pageNum:1,pageSize:1000};
    $.ajax({
        url : "/dept/showAll",
        headers: {
            'token':cookie('token')
        },
        data : data,
        type : "GET",
        async: true,
        dataType : "json",
        success : function(result) {
            if (result.code == SUCCESS) {
                var info = result.data.list;
                for(var i = 0; i<info.length;i++){

                    $("#addEmp select").append(
                        "<option value='"+info[i].deptId+"'>"+info[i].deptName+"</option>"
                    );
                }
                layui.use(['form', 'layedit', 'laydate'], function(){
                    var form = layui.form
                        ,layer = layui.layer
                        ,layedit = layui.layedit
                        ,laydate = layui.laydate;
                });
                layui.use('laydate', function(){
                    var laydate = layui.laydate;
                    //年选择器
                    laydate.render({
                        elem: '#test2'
                    });
                    //时间选择器
                    laydate.render({
                        elem: '#test2_r'
                        ,type: 'time'
                    });
                });

            } else {
                layer.msg(result.msg)
            }
        },
        error : function(e) {
            layer.msg("权限不足,不能登录");
            location.href = "login.html";
        }
    })
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