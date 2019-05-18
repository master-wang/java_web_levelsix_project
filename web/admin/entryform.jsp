<%@ page import="cn.itcast.web.util.servlet.impl.EntryformServletimpl" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.itcast.web.util.domain.EntryForm" %>
<%@ page import="cn.itcast.web.util.dao.EntryformDao" %>
<%@ page import="cn.itcast.web.util.dao.impl.EntryformDaoimpl" %>
<%@ page import="cn.itcast.web.util.dao.SixDao" %>
<%@ page import="cn.itcast.web.util.dao.impl.SixDaoimpl" %><%--
  Created by IntelliJ IDEA.
  User: master
  Date: 19-5-17
  Time: 下午7:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>六级成绩管理</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.js"></script>
</head>
<body>
<%
    EntryformDao dao = new EntryformDaoimpl();
    List list = dao.finfAllRntryList(1);//表的链接查询数据
    System.out.println("获取成绩的ｌｉｓｔ:");
    System.out.println(list);
    request.setAttribute("entryformlist",list);
    SixDao sixDao = new SixDaoimpl();
    List allSixList = sixDao.findAllSixList();
    System.out.println(allSixList);
    request.setAttribute("allSixList",allSixList);
%>
<div style="width: 100%;height: 100px;text-align: center">
    <h1>六级成绩管理<a href="/levelsix/index.jsp">前端首页</a><a href="/levelsix/admin/index.jsp">后台首页</a></h1>
    <select name="" id="gradeId" onchange="cdAllGradeList()">
       <c:forEach  items="${allSixList}" var="six" varStatus="s">
           <option value="${six.s_id}">${six.start_time}~${six.end_time} 的六级考试</option>
       </c:forEach>
    </select>
</div>
<div id="listMain">
    <table  border="1" width="500" align="center" class="table table-striped table-hover " style="width:80%;padding: 50px;">
        <tr>
            <th>序号</th>
            <th>考试的时间</th>
            <th>考试分数</th>
            <th>是否合格</th>
            <th>用户名</th>
            <th>名字</th>
            <th>学校</th>
            <th>专业</th>
            <th>班级</th>
            <th>操作</th>
        </tr>
        <c:forEach  items="${entryformlist}" var="entry" varStatus="s">
            <tr>
               <td>${s.index+1}</td>
               <td>${entry.start_time}~${entry.end_time}</td>
               <td>${entry.e_grade}</td>
               <td>${entry.is_qualified}</td>
               <td>${entry.username}</td>
               <td>${entry.name}</td>
               <td>${entry.school}</td>
               <td>${entry.major}</td>
               <td>${entry.grade}</td>
               <td><button  type="button" class="btn btn-primary " data-toggle="modal" data-target="#seamyModal" onclick="addSomeIdInModel(${entry.e_id},${entry.id},${entry.s_id})" >添加成绩</button></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="modal fade" id="seamyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="seamyModalLabel">六级考试成绩打分</h4>
            </div>
            <div class="modal-body">
                <label for="" class="form-group">
                    <input type="hidden" id="eId">
                    <input type="hidden" id="uId">
                    <input type="hidden" id="sId">
                    <input type="number" id="gradeNumber" class="form-control" placeholder="输入分数" aria-describedby="sizing-addon2">
                </label>
                <label for="" class="form-group">
                    <select name="" id="isquali">
                        <option value="合格">合格</option>
                        <option value="不合格">不合格</option>
                    </select>
                </label>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="entrySea" onclick="gradeCommit()">添加成绩</button>
            </div>
        </div>
    </div>
</div>
<script>
    function cdAllGradeList() {
        var gradeId = $("#gradeId").val();
        console.log(gradeId);
        var data = {
            "s_id":gradeId
        }
        $.ajax({
            url:'/levelsix/entryform/seachGradeList',
            type:'post',
            data:data,
            success:function(data){
                console.log(data);
                var str=`
                <table  border="1" width="500" align="center" class="table table-striped table-hover " style="width:80%;padding: 50px;"><tr>
                    <th>序号</th>
                    <th>考试的时间</th>
                    <th>考试分数</th>
                    <th>是否合格</th>
                    <th>用户名</th>
                    <th>名字</th>
                    <th>学校</th>
                    <th>专业</th>
                    <th>班级</th>
                    <th>操作</th>
                </tr>`;
                var datalist=data.list;
                console.log(datalist);
                for (var i=0;i<datalist.length;i++){
                    str = str + `<tr>
                       <td>`+(i+1)+`</td>
                       <td>`+datalist[i].start_time+`~`+datalist[i].end_time+`</td>
                       <td>`+datalist[i].e_grade+`</td>
                       <td>`+datalist[i].is_qualified+`</td>
                       <td>`+datalist[i].username+`</td>
                       <td>`+datalist[i].name+`</td>
                       <td>`+datalist[i].school+`</td>
                       <td>`+datalist[i].major+`</td>
                       <td>`+datalist[i].grade+`</td>
                       <td>`+`<button  type="button" class="btn btn-primary " data-toggle="modal" data-target="#seamyModal" >添加成绩</button>`+`</td>
                    </tr>`;
                }
                $("#listMain").html(str);
            },
            error:function(data){
                console.log("zou err");
                console.log(data)
            }
        });
    }
    function addSomeIdInModel(e_id,id,s_id) {
        $("#eId").val(e_id)
        $("#uId").val(id)
        $("#sId").val(s_id)
    }
    function gradeCommit() {
        var data = {
            "e_id": $("#eId").val(),
            "u_id":$("#uId").val(),
            "s_id": $("#sId").val(),
            "e_grade":$("#gradeNumber").val(),
            "is_qualified":$("#isquali").val()
        }
        console.log(data);
        $.ajax({
            url:'/levelsix/entryform/updateGradeById',
            type:'post',
            data:data,
            success:function(data){
                console.log(data);
                if (data.count==0){
                    alert("添加成绩失败！")
                } else {
                    alert("添加成功！")
                    var str=`
                <table  border="1" width="500" align="center" class="table table-striped table-hover " style="width:80%;padding: 50px;"><tr>
                    <th>序号</th>
                    <th>考试的时间</th>
                    <th>考试分数</th>
                    <th>是否合格</th>
                    <th>用户名</th>
                    <th>名字</th>
                    <th>学校</th>
                    <th>专业</th>
                    <th>班级</th>
                    <th>操作</th>
                </tr>`;
                    var datalist=data.list;
                    console.log(datalist);
                    for (var i=0;i<datalist.length;i++){
                        str = str + `<tr>
                       <td>`+(i+1)+`</td>
                       <td>`+datalist[i].start_time+`~`+datalist[i].end_time+`</td>
                       <td>`+datalist[i].e_grade+`</td>
                       <td>`+datalist[i].is_qualified+`</td>
                       <td>`+datalist[i].username+`</td>
                       <td>`+datalist[i].name+`</td>
                       <td>`+datalist[i].school+`</td>
                       <td>`+datalist[i].major+`</td>
                       <td>`+datalist[i].grade+`</td>
                       <td>`+`<button  type="button" class="btn btn-primary " data-toggle="modal" data-target="#seamyModal" >添加成绩</button>`+`</td>
                    </tr>`;
                    }
                    $("#listMain").html(str);
                }
            },
            error:function(data){
                console.log("zou err");
                console.log(data)
            }
        });
    }
</script>
</body>
</html>
