package cn.itcast.web.util.servlet.server;

import cn.itcast.web.util.domain.EntryForm;
import cn.itcast.web.util.domain.UserSixGrade;
import cn.itcast.web.util.servlet.impl.EntryformServletimpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/entryform/*")
public class EntryformServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        System.out.println("EntryformServlet test路由------------------------");
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        String[] arr = requestURI.split("/");
        System.out.println(arr[arr.length-1]);
        EntryformServletimpl serDao = new EntryformServletimpl();
        /**
         *
         */
        if(arr[arr.length-1].equals("add")){
             int count =serDao.findIsExit(req,resp);
            System.out.println("数据库已存在："+count);
            resp.setContentType("text/html;charset=utf-8");
            //resp.setContentType("application/json;charset=utf-8");//json格式
            Map<String,Object> map = new HashMap<String, Object>();
            if(count==0){
                map.put("Exit",true);
                map.put("msg","报名成功！");
                EntryForm entryform = serDao.baoMing(req,resp);
                map.put("entryform",entryform);
            }else{
                map.put("Exit",false);
                map.put("msg","您早已报过名！");
                EntryForm entryform = serDao.findAEntryInfo(req,resp);
                map.put("entryform",entryform);
            }
            //讲map装环卫json
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(),map);

        }else if(arr[arr.length-1].equals("seachGrade")){
            int count =serDao.findIsExit(req,resp);
            System.out.println("数据库已存在："+count);
            resp.setContentType("text/html;charset=utf-8");
            //resp.setContentType("application/json;charset=utf-8");//json格式
            Map<String,Object> map = new HashMap<String, Object>();
            if(count==0){
                map.put("Exit",false);
                map.put("msg","您还没有报过名！请先报名！");
            }else{
                EntryForm entryform = serDao.findAGradeInfo(req,resp);
                if (entryform==null){
                    map.put("Exit",false);
                    map.put("msg","准考证错误！！");
                }else {
                    map.put("Exit",true);
                    map.put("msg","正在努力查询您的成绩！");
                    System.out.println(entryform.getE_grade());
                    if (entryform.getE_grade()==null){
                        map.put("e_grade",false);
                        map.put("entryform",entryform);
                    }else {
                        map.put("e_grade",true);
                        map.put("entryform",entryform);
                    }
                }

            }
            //讲map装环卫json
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(),map);
        }else if(arr[arr.length-1].equals("seachGradeList")){
            List<UserSixGrade> list = serDao.findAddInfoList(req, resp);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("msg","请求数据成功");
            map.put("list",list);
            //讲map装环卫json
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.writeValue(resp.getWriter(),map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(arr[arr.length-1].equals("updateGradeById")){
            int count = serDao.updateGradeById(req,resp);
            if (count==0){
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("count","0");
                //讲map装环卫json
                ObjectMapper mapper = new ObjectMapper();
                try {
                    mapper.writeValue(resp.getWriter(),map);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                List<UserSixGrade> list = serDao.findAddInfoList(req, resp);
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("msg","请求数据成功");
                map.put("list",list);
                //讲map装环卫json
                ObjectMapper mapper = new ObjectMapper();
                try {
                    mapper.writeValue(resp.getWriter(),map);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
