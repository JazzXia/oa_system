var SUCCESS = 200;
$(function () {
    showDetailInfo();
    $("#startMake").click(updateState);
    $("#startMake2").click(updateState1);
    $("#startMake3").click(updateState2);
    showInfoById();
});

function showInfoById() {
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
                $("#detailInfo1").html(result.data.suggestionOne);
                $("#detailInfo2").html(result.data.suggestionTwo);
                $("#detailInfo3").html(result.data.suggestionThree);
            } else {
                layer.msg(result.msg)
            }
        },
        error : function(e) {
            layer.msg("权限不足,未登录");
        }
    });
}


function updateState2() {
    var url = "customer/updateGd";
    var a = GetRequest();
    var customerId = a["customerId"];
    var suggestionThree = $("#suggestion3").val();
    var load = "4";
    var data = {customerId:customerId,suggestionThree:suggestionThree,load:load};
    $.ajax({
        url : url,
        data : JSON.stringify(data),
        type : "put",
        headers: {
            'token':cookie('token')
        },
        async:false,
        dataType : "json",
        contentType: "application/json; charset=utf-8",
        success : function(result) {
            if (result.code == SUCCESS) {
                layer.msg(result.msg,{icon:1});
            } else {
                var msg = result.msg;
                if (result.code == ERROR) {
                    layer.msg(msg,{icon:2});
                } else {
                    layer.msg(msg)
                }
            }
        },
        error : function(e) {
            layer.msg("网络连接异常")
        }
    })

}

function updateState1() {
    var url = "customer/updateGd";
    var a = GetRequest();
    var customerId = a["customerId"];
    var suggestionTwo = $("#suggestion2").val();
    var load = "3";
    var data = {customerId:customerId,suggestionTwo:suggestionTwo,load:load};
    $.ajax({
        url : url,
        data : JSON.stringify(data),
        type : "put",
        headers: {
            'token':cookie('token')
        },
        async:false,
        dataType : "json",
        contentType: "application/json; charset=utf-8",
        success : function(result) {
            if (result.code == SUCCESS) {
                layer.msg(result.msg,{icon:1});
            } else {
                var msg = result.msg;
                if (result.code == ERROR) {
                    layer.msg(msg,{icon:2});
                } else {
                    layer.msg(msg)
                }
            }
        },
        error : function(e) {
            layer.msg("网络连接异常")
        }
    })

}


function updateState() {
    var url = "customer/updateGd";
    var a = GetRequest();
    var customerId = a["customerId"];
    var suggestionOne = $("#suggestion1").val();
    var load = "2";
    var data = {customerId:customerId,suggestionOne:suggestionOne,load:load};
    $.ajax({
        url : url,
        data : JSON.stringify(data),
        type : "put",
        headers: {
            'token':cookie('token')
        },
        async:false,
        dataType : "json",
        contentType: "application/json; charset=utf-8",
        success : function(result) {
            if (result.code == SUCCESS) {
                layer.msg(result.msg,{icon:1});
            } else {
                var msg = result.msg;
                if (result.code == ERROR) {
                    layer.msg(msg,{icon:2});
                } else {
                    layer.msg(msg)
                }
            }
        },
        error : function(e) {
            layer.msg("网络连接异常")
        }
    })

}




function showDetailInfo() {
    var a = GetRequest();
    var customerId = a["customerId"];
    var data = {customerId: customerId};
    $.ajax({
        url: "/customer/infoById",
        headers: {
            'token': cookie('token')
        },
        data: data,
        type: "get",
        async: true,
        dataType: "json",
        success: function (result) {
            if (result.code == SUCCESS) {
                $("#customerName").html(result.data.customerName);
                $("#signDate").html(result.data.signDate);
                $("#author").html(result.data.author);
                var info = null;
                if (result.data.load == "1") {
                    info = "待制作";
                } else if (result.data.load == "2") {
                    info = "制作中";
                } else if (result.data.load == "3") {
                    info = "修改中";
                } else if (result.data.load == "4") {
                    info = "已完成";
                }
                $("#load").html(info);
                $("#backNo").html(result.data.backNo);
                $("#phone").html(result.data.customerPhone);
                $("#address").html(result.data.customerAddress);
                $("#startMake").attr("href","GongDJC_my2.html?customerId="+customerId);
                $("#startMake2").attr("href","GongDJC_my4.html?customerId="+customerId);
                $("#startMake3").attr("href","GongDJC_my5.html?customerId="+customerId);
            } else {
                layer.msg(result.msg)
            }
        },
        error: function (e) {
            layer.msg("权限不足,未登录");
        }
    });
}


function GetRequest() {
    var url = location.search;
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1])
        }
    }
    return theRequest
}