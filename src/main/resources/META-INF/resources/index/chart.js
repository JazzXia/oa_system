// $(function () {
//     showEffictInfo();
// });
//
// function showEffictInfo() {
//     var url = "http://lab.isaaclin.cn/nCoV/api/area"
//     var data={};
//     $.ajax({
//         url:url,
//         data:data,
//         type: "get",
//         dataType : "json",
//         success: function (result) {
//             var info = result.results;
//             console.log(info);
//             showLatestNews(info);
//
//         },
//         error: function (e) {
//             layer.msg("权限不足,不能登录");
//             location.href = "login.html";
//         }
//     })
// }