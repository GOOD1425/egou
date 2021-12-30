package com.zbdx.egou.service.impl;

import com.zbdx.egou.dao.UserDao;
import com.zbdx.egou.pojo.User;
import com.zbdx.egou.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public String regUser(String username, String password, String city, String phone) {
        User user=userDao.selectByUsername(username);
        String result="0";
        if(user==null){
           result=userDao.insert(username,password,city,phone).toString();
        }
        return result;
    }

    @Override
    public User checkLogin(String username, String password) {
        User user = userDao.checkLogin(username, password);
        return user;
    }

    @Override
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    @Override
    public Integer updateInfo(String username, String phone, Integer userId) {

        return userDao.updateInfo(username,phone,userId);
    }
}
