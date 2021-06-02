package com.zbdx.egou.dao;

import java.util.List;
import java.util.Map;

public interface BrandDao {
    List<String> selectByFirst(String first);
    Integer selectBrandId(String brandName);
    List<Map<String,String>> selectAll();
    List<Map<String,String>> selectByBrandId(String brandId);
}
