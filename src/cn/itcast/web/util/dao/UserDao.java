package cn.itcast.web.util.dao;

import cn.itcast.web.util.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * 登录
     * @param loginUser
     * @return
     */
    User login(User loginUser);

    /**
     * 添加
     * @param user
     */
    void addUser(User user);

    /**
     * 通过　username 查询用户是否存在
     * @param name
     * @return
     */
    int findUserByUsername(String name);
    /**
     * 查询用户表
     * @return
     */
    List userList();

    /**
     * 用户的删除
     *
     */
    void deleteUser(int id);

    /**
     * 用户的查询
     * @param id
     * @return
     */
    User findUserInfoById(int id);

    /**
     * 用户的修改
     * @param user
     */
    int updateUserbyId(User user);

    /**
     * 删除选中的多个用户
     */

    void delManyUser(String[] uids);

    /**
     * 查询总的记录
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);

    int updateUserHeadimgById(String id, String ph);

}
