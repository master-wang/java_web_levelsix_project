package cn.itcast.web.util.servlet;

import cn.itcast.web.util.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserSetvlet {
    void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    /**
     *
     * @param req
     * @param resp
     * @return
     */
    User login(HttpServletRequest req, HttpServletResponse resp);

    User updateUserInfo(HttpServletRequest req, HttpServletResponse resp);

    int updateUserInfoImg(HttpServletRequest req, HttpServletResponse resp);

    User getUserInfoById(int id);

}
