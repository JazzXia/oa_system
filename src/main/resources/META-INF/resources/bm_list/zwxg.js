var SUCCESS = 200;
var ERROR = 404;
$(function () {
    listDeptInfo();
    showInfoDetail();
    $("#addDuty").click(updateInfo);
});


function updateInfo(){
    var a = GetRequest();
    var dutyId = a["dutyId"];
    console.log(dutyId);
    var deptId = $("#addEmp select").val();
    console.log(deptId);
    var dutyName = $("#dutyName").val();
    console.log(dutyName);
    var dutyDescription = $("#description").val();
    console.log(dutyDescription);
    var data ={deptId:deptId,dutyDescription:dutyDescription,dutyName:dutyName,dutyId:dutyId};
    var url = "linkDutyInfo/updateDutyInfo";

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

                setInterval(function(){
                    location.href = "BuMenGl_zwxg.html?dutyId="+dutyId;
                },1000);

            } else {
                var msg = result.msg;
                if (result.code == ERROR) {
                    layer.msg(msg,{icon:2});
                } else {
                    layer.msg(msg)
                }
            }
        },
        error : function(e) {
            alert("网络连接异常")
        }
    })

}


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