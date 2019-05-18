package cn.itcast.web.util.servlet.impl;

import cn.itcast.web.util.dao.EntryformDao;
import cn.itcast.web.util.dao.impl.EntryformDaoimpl;
import cn.itcast.web.util.domain.EntryForm;
import cn.itcast.web.util.domain.UserSixGrade;
import cn.itcast.web.util.servlet.EntryformServlet;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntryformServletimpl implements EntryformServlet {
    @Override
    public int findIsExit(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String uid = req.getParameter("u_id");
        String sid = req.getParameter("s_id");
        System.out.println("报名。。。。");
        System.out.println(uid+"====="+sid);
        EntryformDao dao = new EntryformDaoimpl();
        int count = dao.findExit(uid,sid);
        return count;
    }

    @Override
    public EntryForm baoMing(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String uid = req.getParameter("u_id");
        String sid = req.getParameter("s_id");
        System.out.println("报名。。。。");
        System.out.println(uid+"====="+sid);
        EntryformDao dao = new EntryformDaoimpl();
        dao.updateEntryByUS(uid,sid);

        EntryForm entryform = dao.findEnterInfoByUS(uid,sid);
        return entryform;
    }

    @Override
    public EntryForm findAEntryInfo(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String uid = req.getParameter("u_id");
        String sid = req.getParameter("s_id");
        System.out.println("报名。。。。");
        System.out.println(uid+"====="+sid);
        EntryformDao dao = new EntryformDaoimpl();

        EntryForm entryform = dao.findEnterInfoByUS(uid,sid);
        return entryform;
    }

    @Override
    public EntryForm findAGradeInfo(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String uid = req.getParameter("u_id");
        String sid = req.getParameter("s_id");
        String ticket_number = req.getParameter("ticket_number");

        System.out.println("报名。。。。");
        System.out.println(uid+"====="+sid);
        EntryformDao dao = new EntryformDaoimpl();

        EntryForm entryform = dao.findGradeInfoByUS(uid,sid,ticket_number);
        return entryform;
    }

    @Override
    public List<UserSixGrade> findAddInfoList(HttpServletRequest req, HttpServletResponse resp) {
        String s_id = req.getParameter("s_id");
        EntryformDao dao = new EntryformDaoimpl();
        List<UserSixGrade> list =  dao.finfAllRntryList(Integer.parseInt(s_id));
        return list;
    }

    @Override
    public int updateGradeById(HttpServletRequest req, HttpServletResponse resp) {
        String e_id = req.getParameter("e_id");
        String u_id = req.getParameter("u_id");
        String s_id = req.getParameter("s_id");
        String e_grade = req.getParameter("e_grade");
        String is_qualified = req.getParameter("is_qualified");
        System.out.println(e_id+u_id+s_id+is_qualified+e_grade);
        EntryformDao dao = new EntryformDaoimpl();
        int count =dao.updateGradeByIds(Integer.parseInt(e_id),Integer.parseInt(u_id),Integer.parseInt(s_id),e_grade,is_qualified);
        return count;
    }
}
