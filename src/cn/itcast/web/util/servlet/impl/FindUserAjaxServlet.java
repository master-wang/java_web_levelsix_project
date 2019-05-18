package cn.itcast.web.util.servlet.impl;

import cn.itcast.web.util.dao.UserDao;
import cn.itcast.web.util.dao.impl.UserDaoimpl;
import cn.itcast.web.util.domain.EntryForm;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findUserAjaxServlet")
public class FindUserAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        System.out.println(name);
        UserDao dao = new UserDaoimpl();
        int count = dao.findUserByUsername(name);
        resp.setContentType("text/html;charset=utf-8");
        //resp.setContentType("application/json;charset=utf-8");//json格式
        Map<String,Object> map = new HashMap<String, Object>();
        if (count==1){
            map.put("userExit",true);
            map.put("msg","此用户名已被注册，请换一个！！");
        }else {
            map.put("userExit",false);
            map.put("msg","此用户名可用！！");
        }


        //讲map装环卫json
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),map);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
