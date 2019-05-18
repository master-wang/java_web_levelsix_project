package cn.itcast.web.util.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminUserServlet {
    void deleteUser(int id);

    void findUsersByPage(HttpServletRequest req, HttpServletResponse resp);

    void delManyUserByArr(HttpServletRequest req, HttpServletResponse resp);
}
