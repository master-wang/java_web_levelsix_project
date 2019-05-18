package cn.itcast.web.util.servlet;

import cn.itcast.web.util.domain.EntryForm;
import cn.itcast.web.util.domain.UserSixGrade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface EntryformServlet {
    int findIsExit(HttpServletRequest req, HttpServletResponse resp);

    EntryForm baoMing(HttpServletRequest req, HttpServletResponse resp);

    EntryForm findAEntryInfo(HttpServletRequest req, HttpServletResponse resp);

    EntryForm findAGradeInfo(HttpServletRequest req, HttpServletResponse resp);

    List<UserSixGrade> findAddInfoList(HttpServletRequest req, HttpServletResponse resp);

    int updateGradeById(HttpServletRequest req, HttpServletResponse resp);
}
