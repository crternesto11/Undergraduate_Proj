function showData() {
    var xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlHttp.open("GET", "User.aspx", true); //URL中传递参数
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4)
            var data = xmlHttp.responseText;
        var jsonobj = eval('(' + data + ')');
        var obj = document.getElementById("add").getElementsByTagName("li");
        for (var i = 0; i < jsonobj.length; i++) {
            var inertext = jsonobj[i]["post_id"] + "   " + jsonobj[i]["post_title"]
            obj[i].innerText = inertext;
        }
    }
    xmlHttp.send("");
}
showData();