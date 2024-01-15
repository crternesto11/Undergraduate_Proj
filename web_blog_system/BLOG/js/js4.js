function showData2() {
    var xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlHttp.open("GET", "comment.aspx", true); //URL中传递参数
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4)
            var data = xmlHttp.responseText;
        var jsonobj = eval('(' + data + ')');
        var obj = document.getElementById("add2").getElementsByTagName("li");
        var middy = getQueryString("trans"); 
        var middy = parseInt(middy);
      
        var j = 0;
        for (var i = 0; i < jsonobj.length; i++) {   
            
            if (jsonobj[i]["replyer_id"] == middy) {
                var tt = jsonobj[i]["reply_content"];
                tt = tt + "       对应帖子：" + jsonobj[i]["post_id"];
                obj[j].innerHTML =  tt;
                j++;
            }
        }

        if (j == 0) {
            obj[0].innerHTML = "尚未发表评论";
        }
    }
    xmlHttp.send("");
}
showData2();