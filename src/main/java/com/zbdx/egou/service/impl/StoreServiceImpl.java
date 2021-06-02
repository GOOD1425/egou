package com.zbdx.egou.service.impl;

import com.zbdx.egou.dao.StoreDao;
import com.zbdx.egou.pojo.Store;
import com.zbdx.egou.service.StoreService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreDao storeDao;
    @Override
    public Integer selectBysname(String sname) {
        return storeDao.selectBysname(sname);
    }

    @Override
    public Integer selectByTel(String tel) {
        return storeDao.selectByTel(tel);
    }

    @Override
    public Integer insert(Store store) {
        return storeDao.insert(store);
    }

    @Override
    public Store selectByUsername(String username) {
        return storeDao.selectByUsername(username);
    }
}
