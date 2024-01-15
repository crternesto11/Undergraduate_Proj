function showData() {
    var xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlHttp.open("GET", "User.aspx", true); //URL中传递参数
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4)
            var data = xmlHttp.responseText;
        var jsonobj = eval('(' + data + ')');
        var obj = document.getElementById("add").getElementsByTagName("li");
        var middy = getQueryString("trans");
        var j = 0;
        for (var i = 0; i < jsonobj.length; i++) {
            if (jsonobj[i]["users_id"] == middy) {
                obj[j].innerHTML = jsonobj[i]["post_title"];
                j++;
            }
        }

        if (j == 0) {
            obj[0].innerHTML = "尚未发帖";
        }
    }
    xmlHttp.send("");
}
showData();