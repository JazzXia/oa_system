var SUCCESS = 200;
var ERROR = 403;
$(function () {
    limitPower();
});

function limitPower() {
    var roleType = cookie("roleType");

    if (roleType == "N"){
        $("li").remove("#empManager");
        $("li").remove("#deptManager");
    }
}