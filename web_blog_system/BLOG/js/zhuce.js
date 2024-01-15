function login() {
    var Name = document.getElementById("zhuce-name").value;
    var Password = document.getElementById("zhuce-password").value;
    var data = "Name=" + Name + "&Password=" + Password;
    //data = encodeURI(data);
    xmlhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlhttp.open("post", "zhuce.aspx", true);
    //如果使用POST提交 不设置这条语句,url页面 Request.Form["key"] 是取不到值的！
    xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlhttp.send(data); //如果是post提交,需要把数据发送过去
    xmlhttp.onreadystatechange = state_Change;
}
function state_Change() {
    if (xmlhttp.readyState == 4) {
        if (xmlhttp.status == 200) {
            document.getElementById("div1").innerText = xmlhttp.responseText;
        }
    }
}