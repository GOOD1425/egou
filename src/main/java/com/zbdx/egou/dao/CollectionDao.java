package com.zbdx.egou.dao;

import com.zbdx.egou.pojo.Collection;

import java.util.List;

public interface CollectionDao {
    Integer insert(Collection collection);
    List<Collection> selectByUsername(String username);
    Integer deleteCollect(Integer collectId);
}
