var SUCCESS = 200;
var ERROR = 404;
$(function () {
    listDeptInfo();
    //$("#dutyinfo select").click();
    $("#addEmp").click(addEmp);

    setTimeout(function () {
        listDutyInfo();
    }, 1000);

});

function addEmp() {
    var username = $("#userName").val();
    var nickName = $("#nickName").val();
}

function listDutyInfo() {
    var deptId = $("#deptinfo select").val();
    console.log(deptId);
    var data = {deptId: deptId};
    $.ajax({
        url : "/linkDutyInfo/dutylist",
        headers: {
            'token':cookie('token')
        },
        data : data,
        type : "GET",
        async: true,
        dataType : "json",
        success : function(result) {
            if (result.code == SUCCESS) {
                var info = result.data;
                for(var i = 0; i<info.length;i++){
                    $("#dutyinfo select").append(
                      "<option value='"+info[i].dutyId+"'>"+info[i].dutyName+"</option>"
                    );
                }
                layui.use(['element', 'layer','form','layedit', 'laydate'], function () {
                    var element = layui.element,
                        form = layui.form,
                        layer = layui.layer
                        ,layedit = layui.layedit
                        ,laydate = layui.laydate;
                    form.on('select(dutyInfo)', function(data) {
                        console.log(data.value);
                        form.render('select','dutyInfo');
                    });
                });

            } else {
                layer.msg(result.msg)
            }
        },
        error : function(e) {
            layer.msg("权限不足,不能登录");
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
}