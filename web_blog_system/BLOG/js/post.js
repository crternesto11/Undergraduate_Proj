function post() {
    var login1 = document.getElementById("user").innerText;
    if (login1 == "登录/注册") {
        document.getElementById("contact-name").value = null;
        document.getElementById("contact-body").value = null;
    }
    else {
        document.getElementById("post-user").value = login1;
    }
}
