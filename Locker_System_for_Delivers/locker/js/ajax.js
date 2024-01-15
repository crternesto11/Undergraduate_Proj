/* 纯JavaScript封装ajax
 *
 * @author 石双元
 * @since 1.0.0
 * @date  2021.10
 */

function ajax(option) {
    // method, url, data, timeout, success, error
    var xhr;
    var str = data2str(option.data);
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
    if (option.type.toLowerCase() === 'post') {
        xhr.open(option.type, option.url, true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send(str);
    } else if (option.type.toLowerCase() === 'get') {
        xhr.open(option.type, option.url + '?' + str, true);
        xhr.send();
    }
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            clearTimeout(timer);
            if (xhr.status >= 200 && xhr.status < 300 || xhr === 304) {
                option.success(xhr);
            } else {
                option.error(xhr);
            }
        }
    };
    if (option.timeout) {
        var timer = setTimeout(function () {
            xhr.abort();
            clearTimeout(timer);
        }, option.timeout)
    }
}

// 将对象转化成用于传输的字符串
function data2str(data) {
    var res = [];
    data.t = new Date().getTime();
    for (var key in data) {
        res.push(encodeURIComponent(key) + '=' + encodeURIComponent(data[key]));
    }
    return res.join('&');
}


//调用方法：
ajax({
    method: 'GET',
    url: '1.txt',
    data: {//请求数据
    },
    timeout: 2000,
    success: (res) => {
        console.log('成功返回', res.response)
    },
    error: err => {
        console.log('错误信息', err)
    }
})