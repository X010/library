package com.dssmp.library.service.impl;

import com.dssmp.library.dao.UserDao;
import com.dssmp.library.model.User;
import com.dssmp.library.service.UserService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by b3st9u on 16/4/7.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        int status = 0;
        int userCount = userDao.checkUsername(user.getUsername());
        if (userCount <= 0) {
            int addCount = userDao.addUser(user);
            if (addCount > 0) {
                status = 1;
            }
        } else {
            status = -1;
        }
        return status;
    }

    @Override
    public List<User> queryAllUser() {
        return userDao.queryAllUser();
    }

    @Override
    public boolean login(User user) {
        return userDao.login(user) > 0;
    }

    @Override
    public User login(String username, String password) {
        Preconditions.checkNotNull(username);
        Preconditions.checkNotNull(password);
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        return this.userDao.findUserByUserNameAndPassword(user);
    }

    @Override
    public boolean delete(int id) {
        return userDao.deleteUser(id) > 0;
    }

    @Override
    public int countRow() {
        return this.userDao.countRow();
    }
}
