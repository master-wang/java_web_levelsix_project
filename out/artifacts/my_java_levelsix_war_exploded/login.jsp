
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>六级考试成绩登录</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/loginjsp.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.js"></script>
</head>
<body>
    <div id="title">
        <h1>六级考试成绩登录</h1>
    </div>
    <div id="box">
        <form action="/levelsix/user/login" method="post">
            <div style="width: 100%;text-align: center">
                <h1>登录</h1>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">用户名:</label>
                <input type="text" name="username" class="form-control" id="exampleInputEmail1" placeholder="用户名">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">密码:</label>
                <input type="password" name="password"  class="form-control" id="exampleInputPassword1" placeholder="密码">
            </div>
            <div class="form-group">
                <label for="exampleInputCheck">验证码:</label>
                <input type="text" name="checkcode" class="form-control" id="exampleInputCheck" placeholder="验证码">
            </div>
            <div style="width: 100%;text-align: center">
                <img id="checkCode" src="/levelsix/checkCodeServlet?1" alt=""><a id="change" >看不清，换一张！</a>
            </div>
            <div style="width: 100%;text-align: center;margin-top: 30px;">
                <input type="submit" class="btn btn-default" value="登录">
            </div>
            <div>
                还没注册，请先注册　<a href="/levelsix/register.jsp">去注册</a>
            </div>
        </form>
        <div style="color: red;font-size: 18px"><%= request.getAttribute("cc_error") ==null? "":request.getAttribute("cc_error") %></div>
        <div style="color: red;font-size: 18px"><%= request.getAttribute("login_error") ==null?"": request.getAttribute("login_error") %></div>
    </div>

    <script>
        window.onload=function () {
            var img = document.getElementById("checkCode");
            img.onclick=aa;
            var a = document.getElementById("change");
            a.onclick=aa;
            function aa() {
                var date = new Date();
                img.src="/levelsix/checkCodeServlet?"+date;
            }
        }
    </script>
</body>
</html>
