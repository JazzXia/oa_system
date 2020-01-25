var SUCCESS = 200;
$(function () {
    $("#saveInfo").click(addVipInfo);
});


function addVipInfo() {
    var url = "/customer/add";
    var author = $("#author").val();
    var customerAddress = $("#address").val();
    var customerContact = $("#contact").val();
    var customerName = $("#companyName").val();
    var customerPhone = $("#phone").val();
    var ftp = $("#ftp").val();
    var payFree = $("#payFree").val();
    var remark = $("#remark").val();
    var signDate = $("#test1").val();
    var userId = cookie("id");

    var data = {author:author,
        customerAddress:customerAddress,
        customerContact:customerContact,
        customerName:customerName,
        customerPhone:customerPhone,
        ftp:ftp,payFree:payFree,remark:remark,signDate:signDate,userId:userId};
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
                    location.href = "vip_luru.html"
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
            layer.msg(msg,{icon:2})
        }
    })
}

