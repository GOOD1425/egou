package com.zbdx.egou.service;

import com.zbdx.egou.pojo.Store;

public interface StoreService {
    Integer selectBysname(String sname);
    Integer selectByTel(String tel);
    Integer insert(Store store);
    Store selectByUsername(String username);
}
