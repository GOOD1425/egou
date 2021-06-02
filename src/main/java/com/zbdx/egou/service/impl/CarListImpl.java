package com.zbdx.egou.service.impl;

import com.zbdx.egou.dao.BrandDao;
import com.zbdx.egou.service.CarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CarListImpl implements CarList {

    @Autowired
    BrandDao brandDao;


    @Override
    public List<List<String>> selectByFirst( ) {
        List<List<String>> res=new ArrayList<>();
        for (char i = 'A'; i <='Z'; i++) {
          List<String> list = brandDao.selectByFirst(String.valueOf(i));
          if(list.size()!=0){
              res.add(list);
          }
        }
        return res;
    }

    @Override
    public List<Map<String,String>> selectAll() {
        return brandDao.selectAll();
    }

    @Override
    public List<Map<String,String>> selectByBrandId(String brandId) {
        return brandDao.selectByBrandId(brandId);
    }
}
