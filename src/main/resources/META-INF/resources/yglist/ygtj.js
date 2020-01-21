var SUCCESS = 200;
var ERROR = 404;
$(function () {
    listDeptInfo();
    //$("#dutyinfo select").click();
    $("#addEmp").click(addEmp);
    layui.use('form', function() {
        var form = layui.form;
        form.on('select(deptIdInfo)', function (data) {
            layui.use('form', showDeptInfo);
        });
    });

});

function showDeptInfo() {
    var deptId = $("#deptinfo select").val();
    $("#dutyinfo select").empty();
    setTimeout(function () {
        listDutyInfo();
    }, 1000);

}

function addEmp() {
    var username = $("#userName").val();
    var nickName = $("#nickName").val();
    var callself = $('input:radio:checked').val();
    var userEmail = $('#userEmail').val();
    var webUrl = $('#webUrl').val();
    var deptId = $("#deptinfo select").val();
    var dutyId = $("#dutyinfo select").val();
    var roleId = null;
    var roleType = null;
    var roleTypeName = null;
    var flag=false;
    $("input[name='test']").each(function(){
        if($(this).is(':checked')){
            flag=true;
        }
    });

    if(flag){
         roleId = "1";
         roleType = "SM";
         roleTypeName = "超级管理员";
    }else{
        roleId = "2";
        roleType = "N";
        roleTypeName = "普通用户";
    }

    var data={username:username,
                nickName:nickName,
                callself:callself,
                userEmail:userEmail,
                webUrl:webUrl,
                deptId:deptId,
                dutyId:dutyId,
                roleId:roleId,
                roleType:roleType,
                roleTypeName:roleTypeName
    };

    var url = "role/add";

    $.ajax({
        url : url,
        data : JSON.stringify(data),
        type : "post",
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
                    location.href = "YuanGong_tj.html"
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
            alert("网络连接异常")
        }
    })




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