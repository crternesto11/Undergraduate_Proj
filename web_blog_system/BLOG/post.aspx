<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="post.aspx.cs" Inherits="postpost.post" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
<%--    <script type="text/javascript">
        var arr = [/垃圾/g, /sb/g,/靠/g,/tmd/g, /nmd/g, /妈的/g, /狗屎/g];		
        let str = title + content;
        for (var i = 0; i < arr.length; i++) {
         //str = str.replace(arr[i], "*");
        if (str.match(arr[i])) {
              alert("您发布的帖子存在违规词");
              title = ""
              content=""
              return;
        }
            } 
            list.innerHTML = str + list.innerHTML; ipt.value = ""
		
    </script>--%>
    <script>history.go(-1);</script>
</body>
</html>
