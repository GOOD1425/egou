package com.zbdx.egou.service.impl;

import com.zbdx.egou.dao.CollectionDao;
import com.zbdx.egou.pojo.Collection;
import com.zbdx.egou.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionDao collectionDao;
    @Override
    public Integer insert(Collection collection) {
        return collectionDao.insert(collection);
    }

    @Override
    public List<Collection> selectByUsername(String username) {
        return collectionDao.selectByUsername(username);
    }

    @Override
    public Integer deleteCollect(Integer collectId) {
        return collectionDao.deleteCollect(collectId);
    }
}
