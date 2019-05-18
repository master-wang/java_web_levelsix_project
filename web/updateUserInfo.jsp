<%@ page import="cn.itcast.web.util.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>更新个人信息</title>
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
            height: ８00px;
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
<%
    User loginUser = (User) request.getSession().getAttribute("user");
    request.setAttribute("loginUser",loginUser);
%>
<div id="title">
    <h1>六级考试学生信息的修改</h1>
</div>
<div id="box">
        <div style="width: 100%;text-align: center">
            <h1>修改我的信息</h1>
        </div>
        <input type="hidden" name="id" value="${user.id}" id="loginId">
        <div class="form-group">
            <label for="exampleInputname">名字:</label>
            <input type="text" name="name"  class="form-control" id="exampleInputname" value="${loginUser.name}" placeholder="名字">
        </div>
        <div class="form-group">
            <label>性别:</label>
            <c:if test="${loginUser.sex=='male'}">
                <span for="exampleInputsex1">男:</span>
                <input type="radio" name="sex" id="exampleInputsex1" value="male" checked="checked"　>
                <span for="exampleInputsex2">女：</span>
                <input type="radio" name="sex"  id="exampleInputsex2" value="female">
            </c:if>
            <c:if test="${loginUser.sex=='female'}">
                <span for="exampleInputsex1">男:</span>
                <input type="radio" name="sex" id="exampleInputsex1" value="male" 　>
                <span for="exampleInputsex2">女：</span>
                <input type="radio" name="sex"  id="exampleInputsex2" value="female" checked="checked">
            </c:if>
            <c:if test="${loginUser.sex=='NULL'}">
                <span for="exampleInputsex1">男:</span>
                <input type="radio" name="sex" id="exampleInputsex1" value="male" 　>
                <span for="exampleInputsex2">女：</span>
                <input type="radio" name="sex"  id="exampleInputsex2" value="female">
            </c:if>
        </div>
        <div class="form-group">
            <label for="exampleInputschool">学校:</label>
            <input type="text" name="school"  class="form-control" id="exampleInputschool" value="${loginUser.school}" placeholder="学校">
        </div>
        <div class="form-group">
            <label for="exampleInputmajor">专业:</label>
            <input type="text" name="major"  class="form-control" id="exampleInputmajor" value="${loginUser.major}" placeholder="专业">
        </div>
        <div class="form-group">
            <label for="exampleInputgrade">班级:</label>
            <input type="text" name="grade"  class="form-control" id="exampleInputgrade" value="${loginUser.grade}" placeholder="班级">
        </div>
        <div class="form-group">
            <label for="studentId">学号:</label>
            <input type="text" name="studentId"  class="form-control" id="studentId" value="${loginUser.studentId}" placeholder="学号">
        </div>
        <div class="form-group">
            <label for="idCardNo">身份证:</label>
            <input type="text" name="idCardNo"  class="form-control" id="idCardNo" value="${loginUser.idCardNo}" placeholder="省份证">
        </div>
        <div class="form-group">
            <label for="idCardfile">头像:</label>
            <input type="file" name="uploadimg"  class="form-control" id="idCardfile" >
        </div>

        <div style="width: 100%;text-align: center;margin-top: 30px;">
            <button onclick="updateUserInfo()" class="btn btn-default" >确认修改</button>
        </div>
        <div>
            　<a href="/levelsix/index.jsp">不修改，退出</a>
        </div>

    <div style="color: red;font-size: 18px"><%= request.getAttribute("cc_error") ==null? "":request.getAttribute("cc_error") %></div>
    <div style="color: red;font-size: 18px"><%= request.getAttribute("login_error") ==null?"": request.getAttribute("login_error") %></div>
</div>

<script>
    $(document).ready(function () {

        var id = getQueryString("loginId");
        console.log(id)
        $("#loginId").val(id);

    })
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
    function updateUserInfo() {
        var data = {
            "id":$("#box input[name='id']").val(),
            "name":$("#box input[name='name']").val(),
            "sex":$("#box input[name='sex']:checked").val(),
            "school":$("#box input[name='school']").val(),
            "major":$("#box input[name='major']").val(),
            "grade":$("#box input[name='grade']").val(),
            "studentId":$("#box input[name='studentId']").val(),
            "idCardNo":$("#box input[name='idCardNo']").val()
        }
        data=JSON.stringify(data);
        var formData = new FormData();
        for(var index = 0; index < $('#idCardfile')[0].files.length; index++){
            formData.append('file', $('#idCardfile')[0].files[index]);
        }
        console.log(formData);
        console.log(data);
        if ($("#box input[name='name']").val()==""||
            $("#box input[name='sex']:checked").val()==""||
            $("#box input[name='school']").val()==""||
            $("#box input[name='major']").val()==""||
            $("#box input[name='grade']").val()==""||
            $("#box input[name='studentId']").val()==""||
            $("#box input[name='idCardNo']").val()==""||$('#idCardfile')[0].files.length==0
        ){
            alert("信息不能为空！！");
        } else {
            $.ajax({
                url:'/levelsix/user/updateUserInfo',
                type:'post',
                data:data,
                dataType:'json',
                contentType:'application/json',
                success:function(data){
                    console.log(data);
                    userupImg()
                },
                error:function(data){
                    console.log("zou err");
                    console.log(data)
                    userupImg()
                }
            });
        }
    }
    function userupImg(){
        var formData = new FormData();
        for(var index = 0; index < $('#idCardfile')[0].files.length; index++){
            formData.append('file', $('#idCardfile')[0].files[index]);
        }
        formData.append('id', $("#box input[name='id']").val());
        $.ajax({
            url:'/levelsix/user/updateUserInfoImg?id='+$("#box input[name='id']").val(),
            type:'post',
            cache: false,
            data:formData,
            processData: false,
            contentType: false,
            enctype:"multipart/form-data",
            success:function(data){
                console.log("图片成功！")
            },
            error:function(data){
                console.log("图片走失败")
                alert("修改信息成功！即将返回首页。")
                location.href="/levelsix/index.jsp?loginId="+$("#box input[name='id']").val();
            }
        });
    }
</script>
</body>
</html>

