package com.zbdx.egou.dao;

import com.zbdx.egou.pojo.Store;

public interface StoreDao {
    Integer  selectBysname(String sname);
    Integer  selectByTel(String tel);
    Integer insert(Store store);
    Store  selectByUsername(String username);
}
