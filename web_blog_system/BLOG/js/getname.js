
function getname() {
    var xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlHttp.open("GET", "User.aspx", true); //URL中传递参数
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4)
            var data = xmlHttp.responseText;
        var jsonobj = eval('(' + data + ')');
        for (var i in jsonobj) {
            if (jsonobj[i]["post_title"] == getQueryString("trans")) {   
                document.getElementById("li_name").innerText = jsonobj[i]["user_name"];
                break;
            }
        }
    }
    xmlHttp.send("");
}
getname();
