package com.zbdx.egou.service;

import com.zbdx.egou.pojo.User;

public interface UserService {
     String regUser(String username, String password, String city, String phone);
     User checkLogin(String username, String password);
     User selectByUsername(String username);
}
