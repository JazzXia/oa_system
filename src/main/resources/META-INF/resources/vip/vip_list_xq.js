var SUCCESS = 200;
$(function () {
    vipDetail();
});

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
                $("#contractNo").html(result.data.contract);
                $("#customerName").html(result.data.customerName);
                $("#ftp").html(result.data.ftp);
                $("#customerId").html(result.data.customerId);
                $("#signDate").html(result.data.signDate);
                $("#payFree").html(result.data.payFree);
                $("#money").html(result.data.payFree);
                $("#contact").html(result.data.customerContact);
                $("#author").html(result.data.author);
                var info = null;
                if (result.data.load == "1"){
                     info = "待制作";
                }else if (result.data.load == "2"){
                    info = "制作中";
                }else if(result.data.load == "3"){
                    info = "修改中";
                }else if(result.data.load == "4"){
                    info = "已完成";
                }
                $("#load").html(info);
                $("#backNo").html(result.data.backNo);
                $("#phone").html(result.data.customerPhone);
                $("#address").html(result.data.customerAddress);
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