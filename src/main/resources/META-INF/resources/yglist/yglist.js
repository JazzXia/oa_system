var SUCCESS = 200;
$(function () {
    listDeptInfo();
    listEmpList();
    //showEmpInfo();
    $('#search').click(listEmpList);
});



// function showEmpInfo() {
//
// }



function listEmpList() {
    var deptId = $("#classifyinfo select").val();
    if (deptId.length == 0){
        deptId = null;
    }
    var nickName = $("#nickName").val();
    if (nickName.length == 0){
        nickName = null;
    }
    var data = {deptId:deptId,nickName:nickName};
    $.ajax({
        url : "/role/list",
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
            location.href = "login.html";
        }
    })
}

/**
 * <tr>
 <td></td>
 <td>1</td>
 <td>技术部</td>
 <td>杨俊</td>
 <td>技术总监（CTO）</td>
 <td>81601001</td>
 <td>男</td>
 <td>经理</td>
 <td>管理员</td>
 <td><a href="#" class="jianl_list_img" onclick=" YuanG_IMG()"><img src="images/jl.jpg"></a></td>
 </tr>
 * @param info
 */
function showAllList(info) {
    $(".wangid_conbox  table tr:not(:first)").empty("");
    for (var i = 0; i < info.length ; i++){
        if (info[i].callself == "M"){
            info[i].callself = "男"
        }else if (info[i].callself == "W"){
            info[i].callself = "女"
        }else{
            layer.msg("出现问题",{icon:2})
        }

        $(".wangid_conbox table tbody").prepend(
            "<tr><td></td> "
            +"<td>"+info[i].deptNo+"</td>>"
            +"<td>"+info[i].deptName+"</td>"
            +"<td>"+info[i].nickName+"</td>"
            +"<td>"+info[i].dutyName+"</td>"
            +"<td>"+info[i].empNo+"</td>"
            +"<td>"+info[i].callself+"</td>"
            +"<td>"+info[i].roleTypeName+"</td>"
            +"<td>"+info[i].roleTypeName+"</td>"
            +"<td>"+info[i].imageName+"</td>"
            +"<td></td></tr>"
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
            ,toolbar: '#toolbarDemo'//工具栏
            ,defaultToolbar:['filter', 'exports']
            ,limits:[10, 20, 30, 40, 50]//分页显示每页条目下拉选择
            ,cellMinWidth: 60//定义全局最小单元格宽度，其余自动分配宽度
        });
        //监听行工具事件
        table.on('tool(mylist)', function(obj){ //注：tool 是工具条事件名，mylist 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){

                    
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){
                layer.msg('修改操作');
            }
        });
        //监听头工具栏事件
        table.on('toolbar(mylist)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'getCheckLength':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        layer.msg('删除');
                    }
                    break;
            };
        });
    });
    //  iframe层  详情信息
    function YuanG_IMG(){
        //iframe层
        layer.open({
            type: 2,//层类型
            title: "简历图片",//标题
            closeBtn: 1, //不显示关闭按钮
            shade: [0.3],//遮罩
            skin: 'demo_class_color',//iframe皮肤
            shadeClose:Boolean,//点击遮罩关闭
            area: ['800px', '460px'],
            // offset: 'rb', //右下角弹出
            // time: 2000, //2秒后自动关闭
            anim: 5,//动画
            content: ['YuanGong_jl.html', 'no'], //iframe的url，no代表不显示滚动条
        });

    }


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
                    $("#classifyinfo select").append(
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

