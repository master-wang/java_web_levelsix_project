package cn.itcast.web.util.dao.impl;


import cn.itcast.web.util.dao.EntryformDao;
import cn.itcast.web.util.domain.EntryForm;
import cn.itcast.web.util.domain.UserSixGrade;
import cn.itcast.web.util.util.JDBCDBPoolUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

public class EntryformDaoimpl implements EntryformDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCDBPoolUtils.getDatasource());
    @Override
    public int findExit(String uid, String sid) {
        String sql = "select count(*) from entryform where u_id =? and s_id = ?";
        Integer count = template.queryForObject(sql, Integer.class, Integer.parseInt(uid), Integer.parseInt(sid));
        return count;
    }

    @Override
    public void updateEntryByUS(String uid, String sid) {
        String sql = "insert into `entryform`(`u_id`,`s_id`,`G_time`,`ticket_number`) values (?,?,?,?)";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));
        String sb;
        String checkCode = "";
        for (int i =0;i<7;i++){
            sb=getCharInfo();
            checkCode+=sb;
        }
        System.out.println(checkCode);
        int count = template.update(sql, Integer.parseInt(uid), Integer.parseInt(sid), df.format(new Date()), checkCode);
        System.out.println("已添加："+count);
    }

    @Override
    public EntryForm findEnterInfoByUS(String uid, String sid) {
        String sql = "select * from entryform where u_id = ? and s_id = ?";
        EntryForm entryform = template.queryForObject(sql, new BeanPropertyRowMapper<EntryForm>(EntryForm.class), Integer.parseInt(uid),Integer.parseInt(sid));
        System.out.println("获得到的已经报名的信息：");
        System.out.println(entryform);
        return entryform;
    }

    @Override
    public EntryForm findGradeInfoByUS(String uid, String sid, String ticket_number) {
        try {
            String sql = "select * from entryform where u_id = ? and s_id = ? and ticket_number = ?";
            EntryForm entryform = template.queryForObject(sql, new BeanPropertyRowMapper<EntryForm>(EntryForm.class), Integer.parseInt(uid),Integer.parseInt(sid),ticket_number);
            System.out.println("获得到的查询信息：");
            System.out.println(entryform);
            return entryform;
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List finfAllRntryList(Integer s_id) {
        String sql = "select e_id,G_time,e_grade,is_qualified,id,username,`name`,sex,school,major,grade,six.s_id,start_time,end_time,ticket_number from `user`,six,entryform where six.s_id=? and u_id=user.id and entryform.s_id=six.s_id ";
        List<UserSixGrade> list = template.query(sql, new BeanPropertyRowMapper<UserSixGrade>(UserSixGrade.class),s_id);
        return list;
    }

    @Override
    public int updateGradeByIds(int e, int u, int s, String e_grade, String is_qualified) {
        String sql = "update entryform set e_grade=?,is_qualified=? where e_id=? and u_id = ? and s_id=?";
        int count = template.update(sql, e_grade, is_qualified,e, u, s);
        return count;
    }

    public static String getCharInfo(){
        StringBuffer sb=new StringBuffer();
        //产生A-Z的ASCII码
        long result=Math.round(Math.random()*25+97);
        //将ASCII码转换成字符
        sb.append(String.valueOf((char)result));

        String a = sb+"";
        return  a;
    }
}
