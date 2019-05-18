package cn.itcast.web.util.servlet.impl;

import cn.itcast.web.util.dao.SixDao;
import cn.itcast.web.util.dao.impl.SixDaoimpl;
import cn.itcast.web.util.domain.Six;
import cn.itcast.web.util.servlet.AdminSixServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class AdminSixServletimpl implements AdminSixServlet {

    @Override
    public void addSix(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> map = req.getParameterMap();
        Six six = new Six();
        try {
            BeanUtils.populate(six,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        SixDao dao = new SixDaoimpl();
        System.out.println("添加ｓｉｘ获取到的参数");
        System.out.println(six);
        dao.addSix(six);
        try {
            resp.sendRedirect(req.getContextPath()+"/admin/sixlist.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List findAllSixList() {
        SixDao dao = new SixDaoimpl();
        List<Six> list =  dao.findAllSixList();
        return list;
    }

    @Override
    public void delSixById(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("sixDelId");
        System.out.println("要删除的six　　ｉｄ："+id);
        SixDao dao = new SixDaoimpl();
        dao.deleteSixById(Integer.parseInt(id));
        try {
            resp.sendRedirect(req.getContextPath()+"/admin/sixlist.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSixIsopenById(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("sixOpenId");
        System.out.println("要开启报名的six　　ｉｄ："+id);
        SixDao dao = new SixDaoimpl();
        dao.updateOpenSixById(Integer.parseInt(id));
        try {
            resp.sendRedirect(req.getContextPath()+"/admin/sixlist.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSixIsdownById(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("sixdownId");
        System.out.println("要关闭报名的six　　ｉｄ："+id);
        SixDao dao = new SixDaoimpl();
        dao.updateDownSixById(Integer.parseInt(id));
        try {
            resp.sendRedirect(req.getContextPath()+"/admin/sixlist.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
