
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.js"></script>
    <style>
        body{
            background-image: url("/imgs/regiser_by.jpg");
            background-attachment: fixed;
            background-size: cover;
        }
        #box{
            width: 500px;
            height: 600px;
            margin-left: 38%;
            margin-top: 100px;
            box-shadow: 0 0 20px #ccc;
            padding: 30px;
        }
        #title{
            width: 100%;
            height: 100px;
            text-align: center;
        }
    </style>
</head>
<body>
<div id="title">
    <h1>六级考试成绩注册</h1>
</div>
<div id="box">
    <form action="/levelsix/user/add" method="post">
        <div style="width: 100%;text-align: center">
            <h1>欢迎注册</h1>
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">用户名:</label>
            <input type="text" name="username" class="form-control" id="exampleInputEmail1" placeholder="用户名" >
            <div id="tip"></div>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">密码:</label>
            <input type="password" name="password"  class="form-control" id="exampleInputPassword1" placeholder="密码">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword2">重复密码:</label>
            <input type="password" name="repassword"  class="form-control" id="exampleInputPassword2" placeholder="重复密码">
        </div>
        <div class="form-group">
            <label for="exampleInputCheck">验证码:</label>
            <input type="text" name="checkcode" class="form-control" id="exampleInputCheck" placeholder="验证码">
        </div>
        <div style="width: 100%;text-align: center">
            <img id="checkCode" src="/levelsix/checkCodeServlet?1" alt=""><a id="change" >看不清，换一张！</a>
        </div>
        <div style="width: 100%;text-align: center;margin-top: 30px;">
            <input type="submit" class="btn btn-default" value="注册">
        </div>
        <div>
            已经注册，去登录　<a href="/levelsix/login.jsp">去登录</a>
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

        $("#exampleInputEmail1").blur(function () {
            var username = $(this).val();
            $.get("/levelsix/findUserAjaxServlet",{username :username},function (data) {
                console.log(data);

                if (data.userExit){
                    $("#tip").html(data.msg);
                    $("#tip").css("color","red");
                } else{
                    $("#tip").html(data.msg);
                    $("#tip").css("color","green");
                }
            },"json")
        })
    }
</script>
</body>
</html>

