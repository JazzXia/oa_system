var SUCCESS = 200;
$(function () {
    // $("#load1").click(listVipInfo(1));
    // $("#load2").click(listVipInfo(2));
    // $("#load3").click(listVipInfo(3));
    // $("#load4").click(listVipInfo(4));
    listVipInfo(null);
    // $("#search").click(listVipInfo(null));
});

function listVipInfo(id) {
    var load = id;
    var customerName = $("#name").val();
    var userId = null;
    if (cookie("roleType") == "N"){
        userId = cookie("id");
    }
    var data = {customerName:customerName,userId:userId,load:load};
    $.ajax({
        url : "/customer/allInfo",
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
 <tr>
 <td> 谭振中 杨奇林 </td>
 <td>贵州万家美装饰有限公司</td>
 <td>gzzsgcgs.com</td>
 <td>8558899</td>
 <td>全网营销</td>
 <td>2019-02-05</td>
 <td>肖韦 胡绪雪</td>
 <td>制作中</td>
 <td>制作中，公众号已注册</td>
 <td>已超时，请加快进度</td>
 </tr>
 * @param info
 */


function showAllList(info) {
    $(".wangid_conbox  table tr:not(:first)").empty("");
    for (var i = 0; i < info.length ; i++){

        $("#detail").attr("href","GongDJC_my1.html?customerId="+info[i].customerId);

        var load = null;
        if (info[i].load == "1"){
            load = "待制作";
        }else if (info[i].load  == "2"){
            load = "制作中";
        }else if(info[i].load  == "3"){
            load = "修改中";
        }else if(info[i].load  == "4"){
            load = "已完成";
        }
        if (info[i].suggestionOne == null){
            info[i].suggestionOne = "暂时无建议";
        }

        if (info[i].suggestionTwo == null){
            info[i].suggestionTwo = "暂时无建议";
        }
        if (info[i].suggestionThree == null){
            info[i].suggestionThree = "暂时无建议";
        }
        $(".wangid_conbox table tbody").prepend(
            "<tr>"
            +"<td>"+info[i].customerName+"</td>"
            +"<td>"+info[i].ftp+"</td>>"
            +"<td>"+info[i].customerId+"</td>>"
            +"<td>"+info[i].contract+"</td>"
            +"<td>"+info[i].signDate+"</td>"
            +"<td>"+info[i].author+"</td>"
            +"<td>"+load+"</td>"
            +"<td>"+info[i].suggestionOne+"</td>"
            +"<td>"+info[i].suggestionTwo+"</td>"
            +"<td>"+info[i].suggestionThree+"</td>"
            +"</tr>"
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
            // ,toolbar: '#toolbarDemo' //指向自定义工具栏模板选择器
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
                // layer.open({
                //     type: 2,
                //     skin: 'layui-layer-molv',
                //     title: '修改职位信息',
                //     content:['/GongDJC_my1.html'+'?customerId='+obj.data.company,'true'] ,//不允许出现滚动条
                //     area:['800px', '600px']
                // });
                if (obj.data.addr == "待制作"){
                    location.href = "/GongDJC_my1.html?customerId="+obj.data.company;
                }
                if (obj.data.addr == "制作中"){
                    location.href = "/GongDJC_my2.html?customerId="+obj.data.company;
                }
                if (obj.data.addr == "修改中"){
                    location.href = "/GongDJC_my4.html?customerId="+obj.data.company;
                }
                if (obj.data.addr == "已完成"){
                    location.href = "/GongDJC_my5.html?customerId="+obj.data.company;
                }

                //layer.msg('修改操作');
            }
        });
    });
}
