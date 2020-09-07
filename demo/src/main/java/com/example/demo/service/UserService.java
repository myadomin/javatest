package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserService {

    @Resource
    private UserDao userDao;

    public User getUserById(long userId) {
        return userDao.selectUserListById(userId).get(0);
    }

    public List<User> getUserListBySex(String sex) {
        return userDao.selectUserListBySex(sex);
    }

    public Long insert(User user) {
        userDao.insert(user);
        return user.getId();
    }

    public Boolean hasMobileInUserList(String mobile) {
        List<User> list = userDao.selectUserListByMobile(mobile);
        return list.size() > 0 ? true : false;
    }
}