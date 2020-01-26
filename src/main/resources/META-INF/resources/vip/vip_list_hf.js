var SUCCESS = 200;
$(function () {
    vipDetail();
    show();
    $("#submit").click(submitInfo);
    $(".VIP_huifang input[id='time']").click(function(){
        $(".VIP_huifang input").val("回访时间 " + show());
    });
});

function show(){
    var mydate = new Date();
    var str = "" + mydate.getFullYear() + "年";
    str += (mydate.getMonth()+1) + "月";
    str += mydate.getDate() + "日 ";
    str += mydate.getHours() + "：";
    str += mydate.getMinutes() + "分";
    str += mydate.getSeconds() + "秒";
    return str;
}


function submitInfo() {
    var a = GetRequest();
    var customerId = a["customerId"];
    var revisitLog = $("#revisitLog").val();
    var revisitTel = $("#revisitTel").val();
    var revisitDate = show();
    var data = {customerId:customerId,revisitLog:revisitLog,revisitDate:revisitDate,revisitTel:revisitTel};
    var url="/revisitLog/add";
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
                    location.href = "vip_list_hf.html?customerId="+customerId;
                },1000);

            } else {
                var msg = result.msg;
                if (result.code == ERROR) {
                    layer.msg(msg);
                } else {
                    layer.msg(msg,{icon:0})
                }
            }
        },
        error : function(e) {
            alert("网络连接异常")
        }
    })

}




function vipDetail() {
    var a = GetRequest();
    var customerId = a["customerId"];
    var data = {customerId:customerId}

    $.ajax({
        url : "/customer/infoById",
        headers: {
            'token':cookie('token')
        },
        data : data,
        type : "get",
        async: true,
        dataType : "json",
        success : function(result) {
            if (result.code == SUCCESS) {
                $("#companyName").html(result.data.customerName);
                $("#customerId").html(result.data.customerId);
                $("#author").html(result.data.author);
            } else {
                layer.msg(result.msg)
            }
        },
        error : function(e) {
            layer.msg("权限不足,未登录");
        }
    });
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