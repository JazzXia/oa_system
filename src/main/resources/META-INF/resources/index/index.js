var SUCCESS = 200;
var ERROR = 1;
$(function () {
    showUserInfo();
});

function showUserInfo() {
    var url = "role/info";
    var data = {userId:'20200112110339TYPO6S'};
    $.getJSON(url, data, function (result) {
        if (result.code == SUCCESS) {
            var info = result.data;
            $("#name").html(info.username);
            console.log(info.username+"威爷是天才!")
        } else {
            alert(result.msg)
        }
    })
}

