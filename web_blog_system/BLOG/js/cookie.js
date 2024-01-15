/***
  * @param {string} cookieName Cookie名称
  * @param {string} cookieValue Cookie值
  */
function SetCookie(cookieName, cookieValue) {
    /*设置Cookie值*/
    document.cookie = cookieName + "=" + escape(cookieValue)
}
function login1() {
    var username = $("user").innerText;
    if (username.length > 0 && username) {
        SetCookie("username", username);
        /*跳转到b.html页面*/
        document.location = "index.html";
    }
}
function login2() {
    var username = $("user").innerText;
    if (username.length > 0 && username) {
        SetCookie("username", username);
        /*跳转到b.html页面*/
        document.location = "contact.html";
    }
}
function login3() {
    var username = $("user").innerText;
    if (username.length > 0 && username) {
        SetCookie("username", username);
        /*跳转到b.html页面*/
        document.location = "about.html";
    }
}
function ReadCookie(cookie_name) {
    //判断是否存在cookie
    if (document.cookie.length > 0) {
        //查询cookie开始部分
        cookie_start = document.cookie.indexOf(cookie_name + "=")
        //如果存在
        if (cookie_start != -1) {
            //计算结束部分
            cookie_start = cookie_start + cookie_name.length + 1
            cookie_end = document.cookie.indexOf(";", cookie_start)
            //如果已经是最后一个cookie值，则取cookie长度
            if (cookie_end == -1) {
                cookie_end = document.cookie.length
            }
            //获取cookie值，unescape对特殊字符解密
            return unescape(document.cookie.substring(cookie_start, cookie_end))
        }
    }
    //其它情况返回空
    return ""
}
function init() {
    var username = ReadCookie("username");
    if (username && username.length > 0) {
        $("user").innerHTML = username;
    } else {
        $("user").innerHTML = "登录/注册";
    }
}
function $(id) {
    return document.getElementById(id);
}