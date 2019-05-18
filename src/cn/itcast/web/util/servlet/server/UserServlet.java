package cn.itcast.web.util.servlet.server;

import cn.itcast.web.util.domain.User;
import cn.itcast.web.util.servlet.impl.UserSetvletimpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        System.out.println("test路由------------------------");
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        String[] arr = requestURI.split("/");
        System.out.println(arr);
        UserSetvletimpl serDao = new UserSetvletimpl();
        /**
         * 添加
         */
        if(arr[arr.length-1].equals("add")){
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String repassword = req.getParameter("repassword");
            String checkcode = req.getParameter("checkcode");
            if (!repassword.equals(password)){
                req.setAttribute("cc_error","密码不一致，请输入密码一致！");
                req.getRequestDispatcher("/register.jsp").forward(req,resp);
            }
            if ("".equals(username)||"".equals(password)||"".equals(repassword)||"".equals(checkcode)){
                req.setAttribute("cc_error","不能为空！！！");
                req.getRequestDispatcher("/register.jsp").forward(req,resp);
            }
            HttpSession session = req.getSession();
            String checkCode_session = (String) session.getAttribute("checkCode_session");
            //得到验证码的判断之后，立即删除
            session.removeAttribute("checkCode_session");
            if (checkCode_session!=null && checkCode_session.equalsIgnoreCase(checkcode)) {//验证码正确
                System.out.println("正确");
                serDao.addUser(req, resp);
            }else{//验证码不正确
                req.setAttribute("cc_error","验证码不正确！");
                req.getRequestDispatcher("/register.jsp").forward(req,resp);
            }
        } else if(arr[arr.length-1].equals("login")){
            /**
             * 登录
             */
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String checkcode = req.getParameter("checkcode");
            if ("".equals(username)||"".equals(password)||"".equals(checkcode)){
                req.setAttribute("cc_error","不能为空！！！");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
            HttpSession session = req.getSession();
            String checkCode_session = (String) session.getAttribute("checkCode_session");
            //得到验证码的判断之后，立即删除
            session.removeAttribute("checkCode_session");
            if (checkCode_session!=null && checkCode_session.equalsIgnoreCase(checkcode)) {//验证码正确
                System.out.println("正确登录");
                User loginUserInfo = serDao.login(req, resp);
                if (loginUserInfo!=null){
                    req.setAttribute("user",loginUserInfo);
                    req.getRequestDispatcher("/index.jsp?loginId="+loginUserInfo.getId()).forward(req,resp);
                }else {
                    req.setAttribute("cc_error","账号或者密码错误！！！！");
                    req.getRequestDispatcher("/login.jsp").forward(req,resp);
                }

            }else{//验证码不正确
                req.setAttribute("cc_error","验证码不正确！");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        } else if(arr[arr.length-1].equals("updateUserInfo")){
            /**
             * 个人信息的修改
             */
            System.out.println("到个人信息的修改：");
            HttpSession session = req.getSession();
            User loginuser = (User) session.getAttribute("user");
            System.out.println("登录的人："+loginuser.getId());
            User user = serDao.updateUserInfo(req, resp);
            if (user!=null){
                resp.setContentType("text/html;charset=utf-8");
                //resp.setContentType("application/json;charset=utf-8");//json格式
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("userExit",true);
                map.put("msg","信息上传成功！");
                //讲map装环卫json
                ObjectMapper mapper = new ObjectMapper();

                mapper.writeValue(resp.getWriter(),map);
            }

        } else if(arr[arr.length-1].equals("updateUserInfoImg")){
            serDao.updateUserInfoImg(req,resp);
            User user = (User) req.getSession().getAttribute("user");
            User userInfo=  serDao.getUserInfoById(user.getId());
            System.out.println(userInfo);
            req.getSession().setAttribute("user",userInfo);
        }else if(arr[arr.length-1].equals("loginOut")){
            req.getSession().removeAttribute("user");
            System.out.println("===========================已退出登录！");
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
