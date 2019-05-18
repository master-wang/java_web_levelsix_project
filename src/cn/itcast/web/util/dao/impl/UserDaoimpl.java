package cn.itcast.web.util.dao.impl;

import cn.itcast.web.util.dao.UserDao;
import cn.itcast.web.util.domain.User;
import cn.itcast.web.util.util.JDBCDBPoolUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoimpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCDBPoolUtils.getDatasource());
    @Override
    public User login(User loginUser) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
            System.out.println("dao获取数据库得到的ｕｓｅｒ:" + user);
            return user;
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO `user` ( `username`, `password` ) VALUES ( ?, ? );";
        int update = template.update(sql, user.getUsername(), user.getPassword());
        System.out.println("插入成功："+update);

    }

    @Override
    public int findUserByUsername(String name) {
        String sql = "select count(*) from user where username= ? ";
        Integer count = template.queryForObject(sql, Integer.class,name);
        return count;
    }

    @Override
    public List userList() {
        return null;
    }

    @Override
    public void deleteUser(int id) {
        String sql = "delete from user where id = ?";
        int count = template.update(sql, id);
        System.out.println("删除成功"+count);

    }

    @Override
    public User findUserInfoById(int id) {
        String sql = "select * from user WHERE id = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return  user;
    }

    @Override
    public int updateUserbyId(User user) {
        System.out.println(user.getSchool());
        String sql ="update user set name = ? , sex = ?  , school = ? , major = ? , grade = ? , studentId = ? , idCardNo = ? where id = ?";
        int count = template.update(sql,user.getName(),user.getSex(),user.getSchool(),user.getMajor(),user.getGrade(),user.getStudentId(),user.getIdCardNo(),user.getId());
        System.out.println("更新用户信息："+count);
        return count;
    }

    @Override
    public void delManyUser(String[] uids) {
        for (String uid : uids) {
            this.deleteUser(Integer.parseInt(uid));
        }
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from user where 1 =1";
        StringBuilder sb = new StringBuilder(sql);//string类型的
        //便利 map拼接模糊查询的 sql
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<Object>();
        for (String key :keySet){
            //排除分页参数
            if ("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            String s = condition.get(key)[0];
            if(s !=null&&!"".equals(s)){
                sb.append(" and " + key+" like ?");
                params.add("%"+s+"%");
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);
        //sql 传递参数
        Integer count = template.queryForObject(sb.toString(), Integer.class, params.toArray());
        System.out.println(count);
        return count;
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 =1";
        StringBuilder sb = new StringBuilder(sql);//string类型的
        //便利 map拼接模糊查询的 sql
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<Object>();
        for (String key :keySet){
            //排除分页参数
            if ("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            String s = condition.get(key)[0];
            if(s !=null&&!"".equals(s)){
                sb.append(" and " + key+" like ?");
                params.add("%"+s+"%");
            }
        }

        //添加分页的查询
        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        sql=sb.toString();

        System.out.println(sql);
        System.out.println(params);
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }

    @Override
    public int updateUserHeadimgById(String id, String ph) {
        String sql ="update user set headImg = ? where id = ?";
        int count = template.update(sql,ph,Integer.parseInt(id));
        System.out.println("更新头像信息信息："+count);
        return count;
    }
}
