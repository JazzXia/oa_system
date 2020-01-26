$(function () {
    showInfo();
    setInterval(function(){
        showInfo();
    },60000);

});


function showInfo() {
    var url="https://api.st.link/angelia/2019ncov";
    var data={};
    $.ajax({
        url:url,
        data:data,
        type: "POST",
        dataType : "json",
        success : function(result) {
            if (result.errcode == 0) {
                var info = result.news;
                showLatestNews(info);

            } else {
                if (result.errcode == 1)
                {
                    layer.msg("1")
                }
            }
        },
        error : function(e) {
            layer.msg("权限不足,不能登录");
            location.href = "login.html";
        }
    })
}

/**
 * 		    							<li>
 <!--<i><img src="images/head.jpg"></i>-->
 <dl>
 <dt>章泽天</dt>
 <dd>所属分区：营销1区</dd>
 </dl>
 <!--<b><img src="images/honor_1.png"></b>-->
 </li>
 <li>
 <!--<i><img src="images/head.jpg"></i>-->
 <dl>
 <dt>鹿晗</dt>
 <dd>所属分区：营销11区</dd>
 </dl>
 <!--<b><img src="images/honor_2.png"></b>-->
 </li>
 <li>
 <!--<i><img src="images/head.jpg"></i>-->
 <dl>
 <dt>关晓彤</dt>
 <dd>所属分区：营销3区</dd>
 </dl>
 <!--<b><img src="images/honor_3.png"></b>-->
 </li>
 *
 */

function showLatestNews(info) {
    for (var i = 0 ; i < info.length ; i++){
        var time = timetrans(info[i].timestamp);
        $("#news").append(
            "<li><dl>"
            +"<dt>"+time+"</dt>"
            +"<dd>"+info[i].message+"</dd>"
        );
    }

}