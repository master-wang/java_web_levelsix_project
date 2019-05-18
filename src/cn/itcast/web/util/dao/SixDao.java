package cn.itcast.web.util.dao;

import cn.itcast.web.util.domain.Six;

import java.util.List;

public interface SixDao {
    void addSix(Six six);

    List findAllSixList();

    void deleteSixById(int parseInt);

    void updateOpenSixById(int id);

    void updateDownSixById(int id);
}
