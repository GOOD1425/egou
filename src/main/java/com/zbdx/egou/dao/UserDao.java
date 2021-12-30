package com.zbdx.egou.dao;


import com.zbdx.egou.pojo.User;

public interface UserDao {
     User selectByUsername(String username);
     Integer insert(String username, String password, String city, String phone);
     User checkLogin(String username,String password);
     Integer updateInfo(String username,String phone,Integer userId );
}
