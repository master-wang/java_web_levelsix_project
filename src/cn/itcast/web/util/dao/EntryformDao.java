package cn.itcast.web.util.dao;

import cn.itcast.web.util.domain.EntryForm;

import java.util.List;

public interface EntryformDao {
    int findExit(String uid, String sid);

    void updateEntryByUS(String uid, String sid);

    EntryForm findEnterInfoByUS(String uid, String sid);

    EntryForm findGradeInfoByUS(String uid, String sid, String ticket_number);

    List finfAllRntryList(Integer s_id);

    int updateGradeByIds(int parseInt, int parseInt1, int parseInt2, String e_grade, String is_qualified);
}
