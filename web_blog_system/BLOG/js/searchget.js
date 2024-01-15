function getmy() {
    var xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlHttp.open("GET", "User.aspx", true); //URL中传递参数
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4)
            var data = xmlHttp.responseText;
        var jsonobj = eval('(' + data + ')');

        for (var i in jsonobj) {
            if (jsonobj[i]["poster_id"] == getQueryString("trans")) {
                document.getElementById("div1").innerText = jsonobj[i]["post_content"];
                break;
            }
        }
    }
    xmlHttp.send("");
}
getmy();