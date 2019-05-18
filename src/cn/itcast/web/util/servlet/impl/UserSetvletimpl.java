package cn.itcast.web.util.servlet.impl;

import cn.itcast.web.util.dao.UserDao;
import cn.itcast.web.util.dao.impl.UserDaoimpl;
import cn.itcast.web.util.domain.User;
import cn.itcast.web.util.servlet.UserSetvlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserSetvletimpl implements UserSetvlet {
    @Override
    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //req.setCharacterEncoding("utf-8");
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        UserDao dao = new UserDaoimpl();
        dao.addUser(user);

        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }

    @Override
    public User login(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("获取的参数信息："+user);
        UserDao dao = new UserDaoimpl();
        User loginInfo = dao.login(user);

        HttpSession session = req.getSession();
        session.setAttribute("user",loginInfo);

        System.out.println("登录的信息"+loginInfo);
        return loginInfo;
    }

    @Override
    public User updateUserInfo(HttpServletRequest req, HttpServletResponse resp) {
        //原声获取表单提交的数据
//        Map<String, String[]> map = req.getParameterMap();
//        User user = new User();
//        try {
//            BeanUtils.populate(user,map);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        String id = req.getParameter("id");
//        System.out.println("获取的参数信息："+user);
//        System.out.println("id:"+id);
//        UserDao dao = new UserDaoimpl();
//        int count = dao.updateUserbyId(user);
//        User userInfo = dao.findUserInfoById(user.getId());
//        System.out.println(userInfo);

        //获取 ajax  post　过来的 json数据
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String parm = getParm(req);
        System.out.println(parm);
        ObjectMapper mapper = new ObjectMapper();

        User user = null;
        try {
            user = mapper.readValue(parm, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        System.out.println(user.getSchool());
        UserDao dao = new UserDaoimpl();
        int count = dao.updateUserbyId(user);
        User userInfo = dao.findUserInfoById(user.getId());
        System.out.println(userInfo);
        return null;
    }

    @Override
    public int updateUserInfoImg(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        System.out.println(id);
        System.out.println("上传文件、。。。。。");
        System.out.println(UserSetvletimpl.class.getClassLoader().getResource("/").getPath());
        //使用fileupload组件完成文件上传
        //上传的位置
        String path = req.getSession().getServletContext().getRealPath("/uploads/");
        //String path = req.getContextPath()+"/uploads/";
        File file = new File(path);
        if(!file.exists()){
            //创建文件夹
            file.mkdir();
        }

        //解析　　ｒｅｑ对象　获取上传的文件项
        //创建文件磁盘工程　
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析　req　 返回文件项　　需要遍历
        List<FileItem> fileItems = null;
        try {
            fileItems = upload.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        for (FileItem item:fileItems) {
            //进行判断当前的　　item　是都是一个上传文件项
            if (item.isFormField()){
                //是一个普通的表单项
            }else{
                //说明文件上传
                //获取上传文件的名称
                String fileName = item.getName();
                System.out.println("上传的文件名为："+fileName);
                //完成文件上传
                try {
                    // 取扩展名加随机数进行重命名
                    fileName = new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+java.util.UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."),fileName.length());
                    item.write(new File(path,fileName));
                    System.out.println("上传的路径为：");
                    System.out.println(path+fileName);
                    String ph = path+fileName;
                    System.out.println(ph);
                    String[] arr = ph.split("my_java_levelsix_war_exploded");
                    String tou = "http://localhost:8080/levelsix";
                    String b = tou+arr[1];
                    System.out.println(arr[1]);
                    System.out.println(b);
                    UserDao dao = new UserDaoimpl();
                    int count = dao.updateUserHeadimgById(id, b);
                    //删除零食文件
                    item.delete();
                    return 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    @Override
    public User getUserInfoById(int id) {
        UserDao dao = new UserDaoimpl();
        User userInfoById = dao.findUserInfoById(id);
        return userInfoById;
    }

    public static String getParm(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }


}
