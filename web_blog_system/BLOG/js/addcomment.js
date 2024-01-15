/*document.write("<script language=javascript src='benye.js'></script>");*/
function addcomment() {
    var login3 = document.getElementById("user").innerText;
    var ider = document.getElementById("number").innerText;
    var titler = getQueryString("trans");
 
    if (login3 == "登录/注册") {
        document.getElementById("comment-body").value = null;
    }
    else {
        document.getElementById("comment-user").value = login3;
        document.getElementById("comment-ider").value = ider;
        document.getElementById("comment-titler").value = titler;
    }
}
