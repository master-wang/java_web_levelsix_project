package cn.itcast.web.util.servlet.impl;

import cn.itcast.web.util.dao.UserDao;
import cn.itcast.web.util.dao.impl.UserDaoimpl;
import cn.itcast.web.util.domain.PageBean;
import cn.itcast.web.util.domain.User;
import cn.itcast.web.util.servlet.AdminUserServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class AdminUserServletimpl implements AdminUserServlet {

    @Override
    public void deleteUser(int id) {
        UserDao dao = new UserDaoimpl();
        dao.deleteUser(id);

    }

    @Override
    public void findUsersByPage(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String currentPage = req.getParameter("currentPage");//当前页码
        String rows = req.getParameter("rows"); //每页的记录数

        if (currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)){
            rows ="5";
        }

        //获取条件查询的参数
        Map<String, String[]> condition = req.getParameterMap();



        UserDao dao = new UserDaoimpl();
        PageBean<User> pb = new PageBean<User>();

        pb.setCurrentPage(Integer.parseInt(currentPage));
        pb.setRows(Integer.parseInt(rows));

        int totalCount = dao.findTotalCount(condition);

        pb.setTotalCount(totalCount);
        //计算开始的记录索引
        int start = (Integer.parseInt(currentPage)-1)*Integer.parseInt(rows);

        List<User> list = dao.findByPage(start,Integer.parseInt(rows),condition);

        pb.setList(list);

        int totalPage = totalCount % Integer.parseInt(rows) == 0 ? totalCount /Integer.parseInt(rows):totalCount /Integer.parseInt(rows)+1 ;
        pb.setTotalPage(totalPage);
        System.out.println(pb);
        req.setAttribute("pb",pb);
        req.setAttribute("condition",condition);

        try {
            req.getRequestDispatcher("/admin/userlist.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delManyUserByArr(HttpServletRequest req, HttpServletResponse resp) {
        String[] uids = req.getParameterValues("uid");
        UserDao dao = new UserDaoimpl();
        dao.delManyUser(uids);

        try {
            resp.sendRedirect(req.getContextPath()+"/admin/userlist.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
