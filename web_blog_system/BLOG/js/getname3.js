
function getname() {
    var xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlHttp.open("GET", "User.aspx", true); //URL中传递参数
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4)
            var data = xmlHttp.responseText;
        var jsonobj = eval('(' + data + ')');
        var it = getQueryString("trans");
        var it2 = it[0] + it[1] + it[2] + it[3] + it[4];
        for (var i in jsonobj) {
            if (jsonobj[i]["post_id"] == it2) {
                document.getElementById("li_name").innerText = jsonobj[i]["user_name"];
                document.getElementById("number").innerText = jsonobj[i]["post_id"];
                break;
            }
        }
    }
    xmlHttp.send("");
}
getname();
