var SUCCESS = 200;
var ERROR = 404;
$(function () {
    listDutyInfo();
});


function listDutyInfo() {
    var url="linkDutyInfo/showAll";
    var data = {};
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
            location.href = "login.html";
        }
    })
}

/**
 *
 * 			<tr>
 <td></td>
 <td>技术部</td>
 <td>程序员</td>
 <td>开发网站程序</td>
 <td>0</td>
 <td><a href="#" class="jianl_list_img" onclick=" YuanG_IMG()"><img src="images/jl.jpg"></a></td>
 </tr>
 *
 *
 * @param info
 */
function showAllList(info) {
    for (var i = 0; i<info.length;i++){
        if (! info[i].dutyDescription) {

            info[i].dutyDescription = "无";
        }
        $(".showAllList  table tbody").append(
            "<tr><td></td>"
            +"<td>"+info[i].dutyId+"</td>>"
            +"<td>"+info[i].deptName+"</td>"
            +"<td>"+info[i].dutyName+"</td>"
            +"<td>"+info[i].dutyDescription+"</td>"
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
                    var data={};
                    $.ajax({
                        url : "/linkDutyInfo/delete/"+obj.data.yx,
                        headers: {
                            'token':cookie('token')
                        },
                        data : data,
                        type : "delete",
                        async: true,
                        dataType : "json",
                        success : function(result) {
                            if (result.code == SUCCESS) {
                                layer.msg(result.msg,{icon:1});
                                setInterval(function(){
                                    location.href = "BuMenGL_list.html";
                                },1000);

                            } else {
                                layer.msg(result.msg,{icon:2})
                            }
                        },
                        error : function(e) {
                            layer.msg("权限不足,未登录");
                            //location.href = "login.html";
                        }
                    });
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){
                layer.open({
                    type: 2,
                    skin: 'layui-layer-molv',
                    title: '修改职位信息',
                    content:['/BuMenGL_zwxg.html'+'?dutyId='+obj.data.yx,'true'] ,//不允许出现滚动条
                    area:['800px', '600px']
                });
                //layer.msg('修改操作');
            }
        });
        //监听头工具栏事件
        table.on('toolbar(mylist)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            switch(obj.event) {
                case 'getCheckLength':
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else {

                        for (var i = 0; i < data.length; i++) {
                            var id = {};
                            $.ajax({
                                url: "/linkDutyInfo/delete/" + data[i].yx,
                                headers: {
                                    'token': cookie('token')
                                },
                                data: data,
                                type: "delete",
                                async: true,
                                dataType: "json",
                                success: function (result) {
                                    if (result.code == SUCCESS) {
                                        layer.msg(result.msg, {icon: 1});

                                    } else {
                                        layer.msg(result.msg, {icon: 2})
                                    }
                                },
                                error: function (e) {
                                    layer.msg("权限不足,未登录");
                                    //location.href = "login.html";
                                }
                            });
                        }
                            setInterval(function () {
                                location.href = "BuMenGL_list.html";
                            }, 1000);
                        }
                        break;
                    }

        });
    });
}