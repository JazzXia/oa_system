var SUCCESS = 200;
$(function () {
    $("#search1").click(listVipInfo);
    $("#search2").click(listVipInfo);
    listVipInfo();
});

function listVipInfo() {

        // var deptId = $("#classifyinfo select").val();
        // if (deptId.length == 0){
        //     deptId = null;
        // }
        // var nickName = $("#nickName").val();
        // if (nickName.length == 0){
        //     nickName = null;
        // }
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var customerName = $("#name").val();
        var userId = null;
        if (cookie("roleType") == "N"){
            userId = cookie("id");
        }
        var data = {customerName:customerName,endTime:endTime,startTime:startTime,userId:userId};
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
 *                 <tr>
 <td></td>
 <!--<td> <a>修改</a>丨<a>添加</a>丨<a  href="javascript:;" onclick="Vip_hf()">回访</a>丨<a>效果</a>丨<a>续费</a>丨<a href="javascript:;" style="color:#205b9e;" onclick="Vip_xq()">详情</a> </td>-->
 <td><a  href="javascript:;" onclick="Vip_hf()">回访</a>丨<a>效果</a>丨<a>续费</a>丨<a href="javascript:;" style="color:#205b9e;" onclick="Vip_xq()">详情</a> </td>
 <td>190507004</td>
 // <td>全网营销</td>
 <td>贵州九树子美容服务有限公司</td>
 <td>域名：cgqyzx.com</td>
 <td>81048555555</td>
 <td>2019-05-08</td>
 <!--<td>gzwagnid666com</td>-->
 <td>8888（私转公）</td>
 <td>2600</td>
 <td>彭庆双 杨鑫</td>
 </tr>
 * @param info
 */


function showAllList(info) {
    $(".wangid_conbox  table tr:not(:first)").empty("");
    for (var i = 0; i < info.length ; i++){

        $(".wangid_conbox table tbody").prepend(
            "<tr> "
            +"<td><a  href='javascript:;' style='color:#9e3b3e;' onclick='Vip_hf("+JSON.stringify(info[i].customerId)+")'>回访</a>丨<a href='javascript:;' style='color:#205b9e;' onclick='Vip_xq("+JSON.stringify(info[i].customerId)+");'>详情</a> </td>"
            +"<td>"+info[i].contract+"</td>>"
            +"<td>"+info[i].author+"</td>>"
            +"<td>"+info[i].customerName+"</td>"
            +"<td>"+info[i].ftp+"</td>"
            +"<td>"+info[i].customerId+"</td>"
            +"<td>"+info[i].signDate+"</td>"
            +"<td>"+info[i].payFree+"</td>"
            +"<td>"+info[i].payFree+"</td>"
            +"<td>"+info[i].customerContact+"</td>"
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
            ,toolbar: '#toolbarDemo' //指向自定义工具栏模板选择器
            ,defaultToolbar:['filter', 'exports']
            ,limits:[10, 20, 30, 40, 50]//分页显示每页条目下拉选择
            ,cellMinWidth: 60//定义全局最小单元格宽度，其余自动分配宽度
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
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //年选择器
        laydate.render({
            elem: '#test2'
            ,type: 'month'
        });

        //年月选择器
        laydate.render({
            elem: '#test3'
            ,type: 'month'
        });
    });
}


//  iframe层  详情信息
function Vip_xq(id){
    //iframe层
    layer.open({
        type: 2,//层类型
        title: "详情信息",//标题
        closeBtn: 1, //不显示关闭按钮
        shade: [0.3],//遮罩
        skin: 'demo_class_color',//iframe皮肤
        shadeClose:Boolean,//点击遮罩关闭
        area: ['800px', '460px'],
        // offset: 'rb', //右下角弹出
        // time: 2000, //2秒后自动关闭
        anim: 5,//动画
        content: ['vip_list_xq.html?customerId='+id, 'no'] //iframe的url，no代表不显示滚动条
    });

}
//  iframe层  回访记录
function Vip_hf(id){
    //iframe层
    layer.open({
        type: 2,//层类型
        title: "详情信息",//标题
        closeBtn: 1, //不显示关闭按钮
        shade: [0.3],//遮罩
        skin: 'demo_class_color',//iframe皮肤
        shadeClose:Boolean,//点击遮罩关闭
        area: ['800px', '460px'],
        // offset: 'rb', //右下角弹出
        // time: 2000, //2秒后自动关闭
        anim: 5,//动画
        content: ['vip_list_hf.html?customerId='+id, 'no'], //iframe的url，no代表不显示滚动条
    });

}
