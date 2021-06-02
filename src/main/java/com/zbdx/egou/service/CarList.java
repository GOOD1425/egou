package com.zbdx.egou.service;

import java.util.List;
import java.util.Map;

public interface CarList {
    List<List<String>> selectByFirst();
    List<Map<String,String>> selectAll();
    List<Map<String,String>> selectByBrandId(String brandId );
}
