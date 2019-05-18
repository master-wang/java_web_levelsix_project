package cn.itcast.web.util.servlet;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AdminSixServlet {

    void addSix(HttpServletRequest req, HttpServletResponse resp);

    List findAllSixList();

    void delSixById(HttpServletRequest req, HttpServletResponse resp);

    void updateSixIsopenById(HttpServletRequest req, HttpServletResponse resp);

    void updateSixIsdownById(HttpServletRequest req, HttpServletResponse resp);
}
