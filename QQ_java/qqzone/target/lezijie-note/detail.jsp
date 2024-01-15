<%--
  Created by IntelliJ IDEA.
  User: 47086
  Date: 2021/12/7
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ page language="java" %>
<%@ page import="dao.UserDao" %>
<%@ page import="po.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="po.Note" %>
<%@ page import="util.DBUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.BaseDao" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.sql.*" %>
<html lang="zh"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QQ空间</title>
    <link href="statics/css/note.css" rel="stylesheet">
    <link href="statics/css/clean-list.css" rel="stylesheet">
    <link href="statics/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="statics/sweetalert/sweetalert2.min.css" rel="stylesheet">
    <script src="statics/js/jquery-1.11.3.js"></script>
    <script src="statics/bootstrap/js/bootstrap.js"></script>
    <script src="statics/sweetalert/sweetalert2.min.js"></script>
    <!-- 配置文件 -->
    <script type="text/javascript" src="./主页_files/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="./主页_files/ueditor.all.js"></script>
    <style type="text/css">
        body {
            padding-top: 60px;
            padding-bottom: 40px;
            background: url(statics/images/old.jpg) repeat;
        }
    </style>
</head>
<%
    Object username=request.getParameter("username");

    UserDao userDao=new UserDao();
    User user=new User();
    user=userDao.queryUserByName(username.toString());

%>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" style="font-size:25px" href="main"> QQ怀旧个人志</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="main"><i class="glyphicon glyphicon-cloud"></i>&nbsp;主&nbsp;&nbsp;页</a></li>
                <li><a href="upload.jsp?id=<%=user.getU_id()%>"><i class="glyphicon glyphicon-pencil"></i>&nbsp;发表个人志</a></li>

            </ul>
            <form class="navbar-form navbar-right" role="search" action="main">
                <div class="form-group">
                    <input type="hidden" name="act" value="searchKey">
                    <input type="text" name="val" class="form-control" placeholder="搜索云记">
                </div>
                <button type="submit" class="btn btn-default">查询</button>
            </form>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row-fluid">
        <div class="col-md-3">
            <div class="data_list">
                <div class="data_list_title"><span class="glyphicon glyphicon-user"></span>&nbsp;个人中心&nbsp;&nbsp;&nbsp;&nbsp;<a href="logout">退出</a></div>
                <div class="userimg">
                    <img id="userimg" src="statics/images/c.png">
                </div>

                <div id="username" class="nick">
                    欢迎！
                    <%
                        out.write(user.getU_name()+"<br/>");
                    %>
                </div>
                <div class="mood">(以后的你会感谢现在努力的你)</div>
            </div>
            <div class="data_list">
                <div class="data_list_title">
					<span class="glyphicon glyphicon-calendar">
					</span>&nbsp;日志日期
                </div>

                <div>
                    <ul class="nav nav-pills nav-stacked">

                        <li><a href="main?act=searchDate&amp;val=2016%E5%B9%B408%E6%9C%88&amp;valStr=2016%E5%B9%B408%E6%9C%88">2016年08月 <span class="badge">24</span></a></li>

                        <li><a href="main?act=searchDate&amp;val=2016%E5%B9%B407%E6%9C%88&amp;valStr=2016%E5%B9%B407%E6%9C%88">2016年07月 <span class="badge">1</span></a></li>

                        <li><a href="main?act=searchDate&amp;val=2016%E5%B9%B406%E6%9C%88&amp;valStr=2016%E5%B9%B406%E6%9C%88">2016年06月 <span class="badge">1</span></a></li>

                    </ul>
                </div>

            </div>
            <div class="data_list">
                <div class="data_list_title">
					<span class="glyphicon glyphicon-list-alt">
					</span>&nbsp;日志类别
                </div>

                <div>
                    <ul class="nav nav-pills nav-stacked">

                        <li><a href="main?act=searchType&amp;val=5&amp;valStr=test">test <span class="badge">0</span></a></li>

                        <li><a href="main?act=searchType&amp;val=3&amp;valStr=%E5%B0%9A%E5%AD%A6%E5%A0%82%E7%AC%94%E8%AE%B0">笔记 <span class="badge">12</span></a></li>

                        <li><a href="main?act=searchType&amp;val=2&amp;valStr=%E6%8A%80%E6%9C%AF">技术 <span class="badge">5</span></a></li>

                        <li><a href="main?act=searchType&amp;val=4&amp;valStr=%E8%80%81%E8%A3%B4%E8%AF%AD%E5%BD%95">语录 <span class="badge">9</span></a></li>

                    </ul>
                </div>
            </div>
        </div>
    </div>


    <div class="col-md-9" >

        <%
            Object id =request.getParameter("id");

            ArrayList<Note> NoteList=new ArrayList<>();
            NoteList=userDao.queryNotesById(user.getU_id());
        %>
        <div style="font-family: 楷体;font-size: 30px;background-color: snow">
                     <%=NoteList.get(Integer.valueOf(id.toString())).getContent()%>

    </div>

    </div>



</div>

</body></html>