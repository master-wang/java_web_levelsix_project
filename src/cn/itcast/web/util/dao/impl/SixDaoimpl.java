package cn.itcast.web.util.dao.impl;

import cn.itcast.web.util.dao.SixDao;
import cn.itcast.web.util.domain.Six;
import cn.itcast.web.util.util.JDBCDBPoolUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class SixDaoimpl implements SixDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCDBPoolUtils.getDatasource());
    @Override
    public void addSix(Six six) {
        String sql = "insert into `six` (`fractional_line`,`start_time`,`end_time`,`release`) values (?,?,?,?)";
        int count = template.update(sql, six.getFractional_line(), six.getStart_time(), six.getEnd_time(), six.getRelease());
        System.out.println("===================six已添加："+count);
    }

    @Override
    public List findAllSixList() {
        String sql ="select * from `six`";
        List<Map<String, Object>> list = template.queryForList(sql);
        System.out.println(list);
        return list;
    }

    @Override
    public void deleteSixById(int id) {
        String sql = "delete from six where s_id = ?";
        int count = template.update(sql, id);
        System.out.println("============成功删除："+count);
    }

    @Override
    public void updateOpenSixById(int id) {
        String sql = "update six set is_ending = ? where s_id = ?";
        int count = template.update(sql, "true",id);
        System.out.println("一开启报名："+count);
    }

    @Override
    public void updateDownSixById(int id) {
        String sql = "update six set is_ending = ? where s_id = ?";
        int count = template.update(sql, "false",id);
        System.out.println("一关闭报名："+count);
    }
}
