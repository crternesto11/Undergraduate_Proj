﻿function getname() {
    var xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlHttp.open("GET", "User.aspx", true); //URL中传递参数
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4)
            var data = xmlHttp.responseText;
        var jsonobj = eval('(' + data + ')');
        for (var i in jsonobj) {
            var it = getQueryString("trans");
            var it2 = it[0] + it[1] + it[2] + it[3] + it[4];
            if (jsonobj[i]["post_id"] == it2) { 
                document.getElementById("div1").innerText = jsonobj[i]["post_content"];
                break;
            }
        }
    }
    xmlHttp.send("");
}
getname();