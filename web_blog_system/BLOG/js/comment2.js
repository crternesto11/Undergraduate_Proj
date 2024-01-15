/*document.write("<script language=javascript src='benye.js'></script>");*/
function comment() {

    var xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlHttp.open("get", "addcomment.aspx", true); //URL中传递参数
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4)
            var data = xmlHttp.responseText;
        var jsonobj = eval('(' + data + ')');
        var str = "<table border=1px >";
        for (var i in jsonobj) {
            if (jsonobj[i]["post_title"] == getQueryString("trans")) {
                str = str + "<tr>";
                str = str + "<td>" + jsonobj[i]["replyer_id"] + "</td>"
                    + "<td>" + jsonobj[i]["reply_content"] + "</td>";
                str = str + "</tr>";
            }
        }
        
        str = str + "</table>";
        document.getElementById("comment_content").innerHTML = str;
    }
    xmlHttp.send("");
}
comment();




