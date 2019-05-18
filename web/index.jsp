<%@ page import="cn.itcast.web.util.domain.User" %>
<%@ page import="cn.itcast.web.util.servlet.impl.AdminSixServletimpl" %>
<%@ page import="cn.itcast.web.util.domain.Six" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: master
  Date: 19-5-12
  Time: 下午3:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>六级考试信息首页</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/index.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.js"></script>
  </head>
  <body>
  <%
    //用户信息
    User loginUser = (User) request.getSession().getAttribute("user");
    request.setAttribute("loginUser",loginUser);
  //六级信息
    AdminSixServletimpl serDao = new AdminSixServletimpl();
    List<Six> sixList =  serDao.findAllSixList();
    System.out.println("获取的到的所有ｓｉｘ：");
    System.out.println(sixList);
    request.setAttribute("sixList",sixList);
    //报名信息
  %>
  <div id="top">
      <h1>六级考试成绩报名、成绩查询首页</h1>
  </div>
  <div id="moddle">
    <div class="main">
      <div id="userInfo">
        <div class="aside">
          <div class="denglu" id="UserIofo">
            <div style="width: 100%;height: 150px;text-align: center">
            <h2>${loginUser.name}的信息</h2>
              <c:if test="${loginUser.headImg=='NULL'}">
                <img src="/imgs/default_headimg.jpg" alt="">
              </c:if>
              <c:if test="${loginUser.headImg!='NULL'}">
                <img src="${loginUser.headImg}" alt="">
              </c:if>
            </div>
            <hr>
            <input type="hidden" value="${loginUser.id}" id="userId">
            <c:if test="${loginUser.is_admin=='true'}">
              <label for="">
                <span>管理员你好:</span><a href="/levelsix/admin/index.jsp">后台管理界面</a>
              </label>
            </c:if>
            <label for="">
              <span>昵称:</span>${loginUser.name}
            </label>
            <label for="">
              <c:if test="${loginUser.sex=='male'}">
                <span>性别:</span>男
              </c:if>
              <c:if test="${loginUser.sex=='female'}">
                <span>性别:</span>女
              </c:if>
              <c:if test="${loginUser.sex=='NULL'}">
                <span>性别:</span>
              </c:if>
            </label>
            <label for="">
              <span>学校:</span>${loginUser.school}
            </label>
            <label for="">
              <span>专业:</span>${loginUser.major}
            </label>
            <label for="">
              <span>年级:</span>${loginUser.grade}
            </label>
            <label for="">
              <span>学号:</span>${loginUser.studentId}
            </label>
            <label for="">
              <span>身份证:</span>${loginUser.idCardNo}
            </label>
            <label for="">
              <span><a href="/levelsix/updateUserInfo.jsp?loginId=${user.id}">完善个人信息</a></span>
            </label>
            <label for="">
              <span><a href="/levelsix/user/loginOut">退出登录</a></span>
            </label>
          </div>
        </div>
      </div>
      <div class="sixinfo">
        <div class="list-group">
          <a href="#" class="list-group-item active">
            六级考试列表：
          </a>
          <c:forEach items="${sixList}" var="six" varStatus="s">
            <a class="list-group-item">报名时间：${six.start_time}~${six.end_time}
              <c:if test="${six.is_ending=='false'}">
                <button class="btn btn-default">报名已结束</button><button class="btn btn-danger" type="button" class="btn btn-primary " data-toggle="modal" data-target="#seamyModal" onclick="seachById(${six.s_id})">查询成绩</button>
              </c:if>
              <c:if test="${six.is_ending=='true'}">
                <button class="btn btn-primary"　id="sixEntryBaoming" onclick="canyuBaoming(${six.s_id})">点击报名</button>
                <button class="btn btn-danger" type="button" class="btn btn-primary " data-toggle="modal" data-target="#seamyModal" onclick="seachById(${six.s_id})">查询成绩</button>
              </c:if>
            </a>
          </c:forEach>
        </div>
      </div>
    </div>
    <div class="ticketnumber">
      <div class="tip" style="color: blue">

      </div>
      准考证号为：
      <div class="number">
        点击报名才有！
      </div>
    </div>
  </div>
  <div id="footer">
      志吕公司版权所有＠
  </div>
  <!-- 删除六级-->
  <div class="modal fade" id="seamyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="seamyModalLabel">输入你的准考证号即可查询成绩</h4>
        </div>
          <input type="hidden" value="-1" id="inputSixDeleId" name="sixDelId">
          <div class="modal-body">
            <label for="" class="form-group">
              <input type="hidden" id="deachSixId">
              <input type="text" id="seach_tic" class="form-control" placeholder="输入你的准考证号" aria-describedby="sizing-addon2">
            </label>
            <div class="levelsixgrade">

            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="button" class="btn btn-primary" id="entrySea" onclick="seachCommit()">查询成绩</button>
          </div>
      </div>
    </div>
  </div>
  </body>
  <script>
    $(document).ready(function(){
      function getUserInfuById() {
        var id = getQueryString("loginId");
        console.log(id);
      }
      getUserInfuById();
      function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
      }
    })

    function canyuBaoming(sid) {
      var uid = $("#userId").val();
      var data = {
        "s_id":sid,
        "u_id":uid
      }
      //data = JSON.stringify(data);
      console.log(data);
      $.ajax({
        url:'/levelsix/entryform/add',
        type:'post',
        data:data,
        success:function(data){
          console.log(data);
          var mm = JSON.parse(data);
          console.log(mm);
          $(".ticketnumber .tip").html(mm.msg);
          $(".ticketnumber .number").html(mm.entryform.ticket_number);
        },
        error:function(data){
          console.log("zou err");
          console.log(data)
        }
      });
    }
    function seachById(id) {
        $("#deachSixId").val(id);
    }
    function seachCommit() {
      if ( $("#seach_tic").val()==""){
        alert("准考证号不能为空！");
        return;
      }
      var data={
        "s_id":$("#deachSixId").val(),
        "u_id":$("#userId").val(),
        "ticket_number":$("#seach_tic").val()
      }
      console.log(data);
      $.ajax({
        url:'/levelsix/entryform/seachGrade',
        type:'post',
        data:data,
        success:function(data){
          console.log(data);
          var mm = JSON.parse(data);
          console.log(mm);
          if (mm.Exit == false) {
            $(".levelsixgrade").eq(0).html(mm.msg);
          }else if (mm.e_grade == false) {
            $(".levelsixgrade").eq(0).html("抱歉，成绩还没有出来，请稍等！");
          }else{
            $(".levelsixgrade").eq(0).html("你好！　您的成绩为："+mm.entryform.e_grade+",是否合格："+mm.entryform.is_qualified);
          }
        },
        error:function(data){
          console.log("zou err");
          console.log(data)
        }
      });
    }
  </script>
</html>
