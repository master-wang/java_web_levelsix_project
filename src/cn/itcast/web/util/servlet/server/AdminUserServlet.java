package cn.itcast.web.util.servlet.server;

import cn.itcast.web.util.servlet.impl.AdminUserServletimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/user/*")
public class AdminUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        System.out.println("admin test路由------------------------");
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        String[] arr = requestURI.split("/");
        System.out.println(arr[arr.length-1]);

        AdminUserServletimpl serDao = new AdminUserServletimpl();
        /**
         * 删除
         */
        if(arr[arr.length-1].equals("deleteUserById")){
            String id = req.getParameter("id");
            serDao.deleteUser(Integer.parseInt(id));
            resp.sendRedirect(req.getContextPath()+"/admin/userlist.jsp");
        }else if(arr[arr.length-1].equals("FindUserByPage")){
            serDao.findUsersByPage(req,resp);
        }else if(arr[arr.length-1].equals("delUserManyServletjsp")){
            serDao.delManyUserByArr(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
