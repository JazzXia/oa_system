var SUCCESS = 200;
var ERROR = 404;
$(function () {
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
                //var total = result.data.pages;
                var size = result.data.pageNum;
                var total = result.data.total;
                //console.log(total);
                var num  = result.data.pageSize;
                showAllList(info,size,num,total);

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
 <td>技术部</td>
 <td>负责公司产品的开发与维护，解决公司有关的技术问题</td>
 <td>1</td>
 <td><a href="#" class="jianl_list_img" onclick=" YuanG_IMG()"><img src="images/jl.jpg"></a></td>
 </tr>
 * @param info
 */

function showAllList(info,size,num,total) {
    info.sort(function(a,b){
        return a.deptOrder - b.deptOrder
    })
    for (var i = 0; i < info.length; i++){
        $(".wangid_conbox table tbody").append(
                    "<tr><td></td> "
                    +"<td>"+info[i].deptId+"</td>>"
                    +"<td>"+info[i].deptName+"</td>"
                    +"<td>"+info[i].deptDesc+"</td>"
                    +"<td>"+info[i].deptOrder+"</td>"
                    +"<td></td>"
        );
    }
    layui.use('table',function(){
        var table = layui.table;
        //转换静态表格
        table.init('mylist', {
            height: 'full-130' //高度最大化减去差值,也可以自己设置高度值：如 height:300
            ,count: total //数据总数 服务端获得
            ,limit: 10 //每页显示条数 注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
            ,page:true //开启分页
            ,toolbar: '#toolbarDemo'//工具栏
            ,defaultToolbar:['filter', 'exports']
            ,limits:[10,20,30,40]//分页显示每页条目下拉选择
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
                        url : "/dept/delete/"+obj.data.id,
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
                                    location.href = "BuMenGL_list1.html";
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
                    title: '修改部门信息',
                    content:['/BuMenGL_bmxg.html'+'?deptId='+obj.data.id,'true'] ,//不允许出现滚动条
                    area:['600px', '400px']
                });
                //layer.msg('修改操作');
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

                        for (var i=0; i < data.length;i++){
                            var id={};
                            //console.log(data[i].id);
                            $.ajax({
                                url : "/dept/delete/"+data[i].id,
                                headers: {
                                    'token':cookie('token')
                                },
                                data : id,
                                type : "delete",
                                async: true,
                                dataType : "json",
                                success : function(result) {
                                    if (result.code == SUCCESS) {
                                        layer.msg(result.msg,{icon:1});
                                    }
                                    else {
                                        layer.msg(result.msg,{icon:2})
                                    }
                                },
                                error : function(e) {
                                    layer.msg("权限不足,未登录");
                                }
                            });
                            // obj.del(); //删除对应行（tr）的DOM结构
                        }
                        setInterval(function(){
                            location.href = "BuMenGL_list1.html";
                        },1000);

                        //layer.msg('删除');
                    }

                    break;
            }
        });
    });

}