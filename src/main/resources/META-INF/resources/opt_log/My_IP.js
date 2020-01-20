var SUCCESS = 200;
var ERROR = 500;
$(function () {
    listOptLogInfo();
    $('#search').click(listOptLogInfo)
});

function listOptLogInfo() {
    var url="optLog/info";
    var accountId = cookie("id");
    var optResult = $("#keySearch").val();
    var data = {accountId:accountId, optResult:optResult};
    $.ajax({
        url : url,
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
                showAllList(info);

            } else {
                layer.msg(result.msg)
            }
        },
        error : function(e) {
            layer.msg("权限不足,不能登录");
            //location.href = "login.html";
        }
    })
}

function showAllList(info) {
    for (var i = 0; i<info.length;i++){
        $(".wangid_conbox  table tbody").prepend(
            "<tr><td>"+(info.length-i)+"</td>"
            +"<td>"+info[i].ip+"</td>>"
            +"<td>"+info[i].optResult+"</td>"
            +"<td>"+info[i].operTime+"</td>"
            +"<td>"+info[i].moduleName+"</td></tr>>"
        );
    }

    //静态表格
    layui.use('table',function(){
        var table = layui.table;
        //转换静态表格
        table.init('mylist', {
            height: 'full-130' //高度最大化减去差值,也可以自己设置高度值：如 height:300
            ,count: 50 //数据总数 服务端获得
            ,limit: 10 //每页显示条数 注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
            ,page:true //开启分页
            //   ,toolbar: '#toolbarDemo'//工具栏
            ,defaultToolbar:['filter', 'exports']
            ,limits:[10, 20, 30, 40, 50]//分页显示每页条目下拉选择
            ,cellMinWidth: 60//定义全局最小单元格宽度，其余自动分配宽度
        });
    });


}