package cn.itcast.web.util.servlet.server;

import cn.itcast.web.util.servlet.impl.AdminSixServletimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/six/*")
public class AdminSixServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        System.out.println("admin test路由------------------------");
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        String[] arr = requestURI.split("/");
        System.out.println(arr[arr.length-1]);

        AdminSixServletimpl serDao = new AdminSixServletimpl();
        /**
         * 删除
         */
        if(arr[arr.length-1].equals("add")){
            serDao.addSix(req,resp);
        }else if(arr[arr.length-1].equals("deleteById")){
            serDao.delSixById(req,resp);
        }else if(arr[arr.length-1].equals("openById")){
            serDao.updateSixIsopenById(req,resp);
        }else if(arr[arr.length-1].equals("downById")){
            serDao.updateSixIsdownById(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
