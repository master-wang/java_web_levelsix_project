<%--
  Created by IntelliJ IDEA.
  User: master
  Date: 19-5-15
  Time: 下午6:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>六级考试后台管理</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/admin.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.js"></script>
</head>
<body>
    <div class="top">
        <h1>管理员，欢迎进入六级考试管理后台<a href="/levelsix/index.jsp">前端首页</a></h1>
    </div>
    <div class="main">
        <div class="nav">
            <div class="menu">
                <a href="/levelsix/admin/userlist.jsp"><button class="btn btn-primary">考生管理</button></a>
            </div>
            <div class="menu">
                <a href="/levelsix/admin/sixlist.jsp"><button class="btn btn-primary">六级考试发布管理</button></a>
            </div>
            <div class="menu">
                <a href="/levelsix/admin/entryform.jsp"><button class="btn btn-primary">成绩管理</button></a>
            </div>
            <div class="menu">
                <button class="btn btn-primary">新功能请稍等...</button>
            </div>
        </div>
    </div>
</body>
</html>
