<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.itcast.web.util.servlet.impl.AdminSixServletimpl" %>
<%@ page import="cn.itcast.web.util.domain.Six" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: master
  Date: 19-5-15
  Time: 下午9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>六级发布管理</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.js"></script>
</head>
<body>
<%
    AdminSixServletimpl serDao = new AdminSixServletimpl();
    List<Six> sixList =  serDao.findAllSixList();
    System.out.println("获取的到的所有ｓｉｘ：");
    System.out.println(sixList);
    request.setAttribute("sixList",sixList);
%>
<div style="width: 100%;height: 100px;text-align: center">
    <h1>六级考试发布管理<a href="/levelsix/index.jsp">前端首页</a><a href="/levelsix/admin/index.jsp">后台首页</a></h1>
    <button type="button" class="btn btn-primary " data-toggle="modal" data-target="#myModal">发布新的六级考试</button>
</div>
<div>
    <table border="1" width="500" align="center" class="table table-striped table-hover " style="width:80%;padding: 50px;">
        <tr>
            <th>id</th>
            <th>发布人</th>
            <th>考试开始时间</th>
            <th>考试结束时间</th>
            <th>合格分数线</th>
            <th>是否结束</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${sixList}" var="six" varStatus="s">
            <tr>
                <td>${six.s_id}</td>
                <td>${six.release}</td>
                <td>${six.start_time}</td>
                <td>${six.end_time}</td>
                <td>${six.fractional_line}</td>
                <c:if test="${six.is_ending=='false'}">
                    <td><button class="btn btn-default">报名已结束</button></td>
                    <td><button class="btn btn-danger" type="button" class="btn btn-primary " data-toggle="modal" data-target="#openmyModal" onclick="openSixById(${six.s_id})">开启报名</button>||<button class="btn btn-danger" onclick="deleteSixById(${six.s_id})" type="button" class="btn btn-primary " data-toggle="modal" data-target="#delmyModal">删除</button></td>
                </c:if>
                <c:if test="${six.is_ending=='true'}">
                    <td><button class="btn btn-primary">正在报名时期</button></td>
                    <td><button class="btn btn-danger" type="button" class="btn btn-primary " data-toggle="modal" data-target="#downmyModal" onclick="downSixById(${six.s_id})">关闭报名</button></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>

 <!-- 添加六级-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加六级考试</h4>
            </div>
            <form id="sixaddform"  action="${pageContext.request.contextPath}/admin/six/add">
            <div class="modal-body">

                    <div class="form-group">
                        <label for="exampleInputrelease">发布人:</label>
                        <input type="text" name="release" class="form-control" id="exampleInputrelease" placeholder="发布人">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputfen">合格分数线:</label>
                        <input type="text" name="fractional_line" class="form-control" id="exampleInputfen" placeholder="合格分数线">
                    </div>
                    <div class="form-group">
                        <label for="start_time">考试开始时间:</label>
                        <input type="datetime-local" name="start_time" class="form-control" id="start_time" placeholder="考试开始时间">
                    </div>
                    <div class="form-group">
                        <label for="end_time">考试结束时间:</label>
                        <input type="datetime-local" name="end_time" class="form-control" id="end_time" placeholder="考试结束时间">
                    </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="sixAdd">确定添加</button>
            </div>
            </form>
        </div>
    </div>
</div>
<!-- 删除六级-->
<div class="modal fade" id="delmyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="delmyModalLabel">删除</h4>
            </div>
            <form id="sixDeleteform"  action="${pageContext.request.contextPath}/admin/six/deleteById">
                <input type="hidden" value="-1" id="inputSixDeleId" name="sixDelId">
                <div class="modal-body">
                    确认删除这条考试记录吗？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="sixDel">确定删除</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 开启报名-->
<div class="modal fade" id="openmyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="openmyModalLabel">开启报名</h4>
            </div>
            <form id="sixOpenform"  action="${pageContext.request.contextPath}/admin/six/openById">
                <input type="hidden" value="-1" id="inputSixOpenId" name="sixOpenId">
                <div class="modal-body">
                    确认要开启这场考试报名吗？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="sixOpen">确定开启</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 关闭报名-->
<div class="modal fade" id="downmyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="downdownmyModalLabel">关闭报名</h4>
            </div>
            <form id="sixdownform"  action="${pageContext.request.contextPath}/admin/six/downById">
                <input type="hidden" value="-1" id="inputSixdownId" name="sixdownId">
                <div class="modal-body">
                    确认要关闭这场考试报名吗？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="sixdown">确定关闭</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    window.onload=function () {
        document.getElementById("sixAdd").onclick=function () {
            document.getElementById("sixaddform").submit();
        }
        document.getElementById("sixDel").onclick=function () {
            document.getElementById("sixDeleteform").submit();
        }
        document.getElementById("sixOpen").onclick=function () {
            document.getElementById("sixOpenform").submit();
        }
        document.getElementById("sixdown").onclick=function () {
            document.getElementById("sixdownform").submit();
        }
    }
    function deleteSixById(id) {
        console.log(id);
        $("#inputSixDeleId").val(id);
    }
    function openSixById(id) {
        console.log(id);
        $("#inputSixOpenId").val(id);
    }
    function downSixById(id) {
        console.log(id);
        $("#inputSixdownId").val(id);
    }
</script>
</body>
</html>
