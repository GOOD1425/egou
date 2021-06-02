package com.zbdx.egou.service;

import com.zbdx.egou.pojo.Collection;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CollectionService {
    Integer insert(Collection collection);
    List<Collection> selectByUsername(String username);
    Integer deleteCollect(Integer collectId);
}
