var SUCCESS = 200;
var ERROR = 404;
$(function () {
    showEmpDetail();
    listDeptInfo();
    layui.use('form', function() {
        var form = layui.form;
        form.on('select(deptIdInfo)', function (data) {
            layui.use('form', showDeptInfo);
        });
    });
});

function showEmpDetail() {
    var a = GetRequest();
    var empNo = a["empNo"];
    var data = {empNo:empNo};
    $.ajax({
        url : "/role/showEmpInfo",
        headers: {
            'token':cookie('token')
        },
        data : data,
        type : "get",
        async: true,
        dataType : "json",
        success : function(result) {
            if (result.code == SUCCESS) {
                $("#userName").val(result.data.username);
                $("#nickName").val(result.data.nickName);
                $("#userEmail").val(result.data.userEmail);
                $("#webUrl").val(result.data.webUrl);
                var sex = result.data.callself;
                if (sex == "M"){
                    $("input[name=sex]:eq(0)").prop("checked",'checked');
                } else if (sex == "W"){
                    $("input[name=sex]:eq(1)").prop("checked",'checked');
                }
                var roleType = result.data.roleType;
                if (roleType == "SM"){
                    $("#box").prop("checked",true);
                }else {
                    $("#box").prop("checked",false);
                }
            } else {
                layer.msg(result.msg)
            }
        },
        error : function(e) {
            layer.msg("权限不足,未登录");
        }
    });
}

function showDeptInfo() {
    var deptId = $("#deptinfo select").val();
    $("#dutyinfo select").empty();
    setTimeout(function () {
        listDutyInfo();
    }, 1000);
}


function listDutyInfo() {

    layui.use(['element', 'layer','form','layedit', 'laydate'], function () {
        var element = layui.element,
            form = layui.form,
            layer = layui.layer,
            layedit = layui.layedit,
            laydate = layui.laydate;

        var deptId = $("#deptinfo select").val();
        //console.log(deptId);
        //console.log($("#deptinfo select").find("option:selected").text());
        var data = {deptId: deptId};
        $.ajax({
            url : "/linkDutyInfo/dutylist",
            headers: {
                'token':cookie('token')
            },
            data : data,
            type : "GET",
            async: false,
            dataType : "json",
            success : function(result) {
                if (result.code == SUCCESS) {
                    var info = result.data;
                    for(var i = 0; i<info.length;i++){
                        $("#dutyinfo select").append(
                            "<option value='"+info[i].dutyId+"'>"+info[i].dutyName+"</option>"
                        );
                    }

                } else {
                    layer.msg(result.msg)
                }
            },
            error : function(e) {
                layer.msg("权限不足,不能登录");
            }
        });
        element.init();
        form.render();
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
                    $("#deptinfo select").append(
                        "<option value='"+info[i].deptId+"'>"+info[i].deptName+"</option>"
                    );
                }
                layui.use(['form', 'layedit', 'laydate'], function(){
                    var form = layui.form
                        ,layer = layui.layer
                        ,layedit = layui.layedit
                        ,laydate = layui.laydate;
                });

            } else {
                layer.msg(result.msg)
            }
        },
        error : function(e) {
            layer.msg("权限不足,不能登录");
        }
    });

    setTimeout(function () {
        listDutyInfo();
    }, 1000);
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