package com.dssmp.library.service;

import com.dssmp.library.model.User;

import java.util.List;

/**
 * Created by b3st9u on 16/4/7.
 */
public interface UserService {
    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> queryAllUser();

    /**
     * 用户登陆
     *
     * @param user
     * @return
     */
    boolean login(User user);

    /**
     * 用户登陆
     *
     * @param user
     * @return
     */
    User login(String username, String password);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean delete(int id);

    /**
     * 统计记录数
     *
     * @return
     */
    int countRow();
}
