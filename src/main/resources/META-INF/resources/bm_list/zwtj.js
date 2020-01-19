var SUCCESS = 200;
var ERROR = 404;
$(function () {
   // $("#add").click(updateInfo);
    listDeptInfo();
});


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