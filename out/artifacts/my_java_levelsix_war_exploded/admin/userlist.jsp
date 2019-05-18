<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="cn.itcast.web.util.dao.UserDao" %>
<%@ page import="cn.itcast.web.util.domain.User" %>
<%@ page import="cn.itcast.web.util.domain.PageBean" %>
<%@ page import="cn.itcast.web.util.dao.impl.UserDaoimpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>六级用户管理界面</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.js"></script>
    <style>

    </style>
</head>
<body>
<%

//    UserDao2 dao = new UserDao2Impl();
//    List list = dao.userList();
//    request.setAttribute("list",list);
    String currentPage = request.getParameter("currentPage");//当前页码
    String rows = request.getParameter("rows"); //每页的记录数

    if (currentPage == null || "".equals(currentPage)){
        currentPage = "1";
    }
    if (rows == null || "".equals(rows)){
        rows ="5";
    }

    //获取条件查询的参数
    Map<String, String[]> condition = request.getParameterMap();



    UserDao dao = new UserDaoimpl();
    PageBean<User> pb = new PageBean<User>();

    pb.setCurrentPage(Integer.parseInt(currentPage));
    pb.setRows(Integer.parseInt(rows));

    int totalCount = dao.findTotalCount(condition);

    pb.setTotalCount(totalCount);
    //计算开始的记录缩影
    int start = (Integer.parseInt(currentPage)-1)*Integer.parseInt(rows);
    List<User>  list = dao.findByPage(start,Integer.parseInt(rows),condition);
    pb.setList(list);

    int totalPage = totalCount % Integer.parseInt(rows) == 0 ? totalCount /Integer.parseInt(rows):totalCount /Integer.parseInt(rows)+1 ;
    pb.setTotalPage(totalPage);
    System.out.println(pb);
    request.setAttribute("pb",pb);

%>
<div style="width: 100%;height: 100px;text-align: center">
    <h1>用户管理界面<a href="/levelsix/index.jsp">前端首页</a><a href="/levelsix/admin/index.jsp">后台首页</a></h1>
</div>
<div style="width: 100%;height: 100px;text-align: center">
    <form action="${pageContext.request.contextPath}/admin/user/FindUserByPage?currentPage=1&rows=5">
        id:<input type="text" name="id" value="${condition.id[0]}">
        用户名: <input type="text" name="username" value="${condition.username[0]}">
        学校: <input type="text" name="school" value="${condition.school[0]}">
        专业: <input type="text" name="major" value="${condition.major[0]}">
        年级: <input type="text" name="grade" value="${condition.grade[0]}">
        学号: <input type="text" name="studentId" value="${condition.studentId[0]}">
        <input type="submit" class="btn btn-primary" value="查询">
    </form>
    <div>
        <a href="javascript:void(0);" id="udelSeleted"><button class="btn btn-danger">删除选中</button></a>
    </div>
</div>


<form id="uform" action="${pageContext.request.contextPath}/admin/user/delUserManyServletjsp" method="post">
<table border="1" width="500" align="center" class="table table-striped table-hover " style="width:80%;padding: 50px;">
    <tr>
        <th><input type="checkbox" id="ufirstCheck"></th>
        <th>id</th>
        <th>名字</th>
        <th>用户名</th>
        <th>密码</th>
        <th>学校</th>
        <th>专业</th>
        <th>年级</th>
        <th>学号</th>
        <th>省份证</th>
        <th>是否管理员</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${pb.list}" var="user" varStatus="s">
        <tr>
            <td><input type="checkbox" name="uid" value="${user.id}"></td>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.school}</td>
            <td>${user.major}</td>
            <td>${user.grade}</td>
            <td>${user.studentId}</td>
            <td>${user.idCardNo}</td>
            <td>${user.is_admin}</td>
            <td><a href="${pageContext.request.contextPath}/admin/user/deleteUserById?id=${user.id}" style="color: red">删除</a></td>
        </tr>
    </c:forEach>
</table>
</form>
<div  style="width: 100%;height: 100px;text-align: center;margin-top: 100px;">

    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <c:if test="${pb.currentPage-1<=0}">
                    <a href="${pageContext.request.contextPath}/admin/user/FindUserByPage?currentPage=1&rows=${pb.rows}&id=${condition.id[0]}&username=${condition.username[0]}&password=${condition.school[0]}&password=${condition.major[0]}&password=${condition.grade[0]}&password=${condition.studentId[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </c:if>
                <c:if test="${pb.currentPage-1>0}">
                    <a href="${pageContext.request.contextPath}/admin/user/FindUserByPage?currentPage=${pb.currentPage-1}&rows=${pb.rows}&id=${condition.id[0]}&username=${condition.username[0]}&password=${condition.school[0]}&password=${condition.major[0]}&password=${condition.grade[0]}&password=${condition.studentId[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </c:if>

            </li>
            <c:forEach begin="1" end="${pb.totalPage}" var="i">
                <c:if test="${pb.currentPage==i}">
                    <li><a class="active" style="z-index: 3;
                                color: #fff;
                                cursor: default;
                                background-color: #337ab7;
                                border-color: #337ab7;" href="${pageContext.request.contextPath}/admin/user/FindUserByPage?currentPage=${i}&rows=${pb.rows}&id=${condition.id[0]}&username=${condition.username[0]}&password=${condition.school[0]}&password=${condition.major[0]}&password=${condition.grade[0]}&password=${condition.studentId[0]}">${i}</a></li>
                </c:if>
                <c:if test="${pb.currentPage!=i}">
                    <li><a href="${pageContext.request.contextPath}/admin/user/FindUserByPage?currentPage=${i}&rows=${pb.rows}&id=${condition.id[0]}&username=${condition.username[0]}&password=${condition.school[0]}&password=${condition.major[0]}&password=${condition.grade[0]}&password=${condition.studentId[0]}">${i}</a></li>
                </c:if>
            </c:forEach>
            <li>
                <c:if test="${pb.currentPage-1>=pb.totalPage}">
                    <a href="${pageContext.request.contextPath}/admin/user/FindUserByPage?currentPage=${pb.totalPage}&rows=${pb.rows}&id=${condition.id[0]}&username=${condition.username[0]}&password=${condition.school[0]}&password=${condition.major[0]}&password=${condition.grade[0]}&password=${condition.studentId[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </c:if>
                <c:if test="${pb.currentPage<pb.totalPage}">
                    <a href="${pageContext.request.contextPath}/admin/user/FindUserByPage?currentPage=${pb.currentPage+1}&rows=${pb.rows}&id=${condition.id[0]}&username=${condition.username[0]}&password=${condition.school[0]}&password=${condition.major[0]}&password=${condition.grade[0]}&password=${condition.studentId[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </c:if>
            </li>
            <spn>共${pb.totalCount}条记录，共${pb.totalPage}页</spn>
        </ul>
    </nav>

</div>
<script>
    window.onload=function () {
        document.getElementById("udelSeleted").onclick=function () {
            document.getElementById("uform").submit();
        }
        document.getElementById("ufirstCheck").onclick=function (ev) {
            var cbs = document.getElementsByName("uid");
            for (var i = 0 ;i< cbs.length;i++){
                cbs[i].checked = this.checked;
            }
        }
    }
</script>
</body>
</html>
