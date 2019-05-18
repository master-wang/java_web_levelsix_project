package cn.itcast.web.util.filter;

import cn.itcast.web.util.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录验证的过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将至转换城Httpserlvet 的req
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        //获取资源请求的路径
        String uri = req.getRequestURI();
        System.out.println(uri);
        //判断是否包含登录相关的资源师
        if(uri.contains("/register.jsp") ||uri.contains("/login.jsp") || uri.contains("/user/")|| uri.contains("/findUserAjaxServlet") || uri.contains("/checkCodeServlet")|| uri.contains("/css/")|| uri.contains("/imgs/")|| uri.contains("/js/")){
            //证明用户就想去登录
            filterChain.doFilter(servletRequest,servletResponse);
        }else if (uri.contains("/admin/index.jsp")||uri.contains("/admin/entryform.jsp")||uri.contains("/admin/sixlist.jsp")||uri.contains("/admin/userlist.jsp")){
            //判断是否登录
            User user = (User) req.getSession().getAttribute("user");
            System.out.println("过滤器");
            if(user != null&&user.getIs_admin().equals("true")){
                //登录了
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                //登录失败
                req.setAttribute("login_error","你好！你不是管理员！");
                req.getRequestDispatcher("/login.jsp").forward(req,servletResponse);
            }
        }else {
            //判断是否登录
            Object user = req.getSession().getAttribute("user");
            if(user != null){
                //登录了
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                //登录失败
                req.setAttribute("login_error","你好！请先登录！");
                req.getRequestDispatcher("/login.jsp").forward(req,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
