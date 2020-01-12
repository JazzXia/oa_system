var SUCCESS = 200;
var ERROR = 403;
$(function () {
    $("#add").click(addDept);
    //$("#user").blur(checkName);
    //$("#pass").blur(checkPassword)
});


function addDept() {
    var url = "/dept/add";
    var deptDesc = $("#deptname").val();
    var deptOrder = $("#deptOrder").val();
    var description = $("#description").val();
    var data = {deptName:deptDesc,deptDesc:description,deptOrder:deptOrder};
    $.ajax({
        url : url,
        data : JSON.stringify(data),
        type : "post",
        headers: {
            'token':cookie('token')
        },
        dataType : "json",
        contentType: "application/json; charset=utf-8",
        success : function(result) {
            if (result.code == SUCCESS) {
                layer.msg(result.msg);
                //location.href = "/BuMenGL_bmtj.html";
            } else {
                var msg = result.msg;
                if (result.code == ERROR) {
                    layer.msg(msg);
                } else {
                    alert(msg)
                }
            }
        },
        error : function(e) {
            alert("网络连接异常",e.msg)
        }
    })
}
