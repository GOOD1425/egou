package com.zbdx.egou.service.impl;

import com.zbdx.egou.dao.CarInfoDao;
import com.zbdx.egou.pojo.CarInfo;
import com.zbdx.egou.service.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarInfoServiceImpl implements CarInfoService {
    @Autowired
    CarInfoDao carInfoDao;
    @Override
    public String insertCarInfo(CarInfo carInfo) {
        return carInfoDao.insertCarInfo(carInfo).toString();
    }

    @Override
    public List<CarInfo> selectByIds(List<Integer> carIds,Integer pageCurrent) {
        if(pageCurrent!=null) pageCurrent=(pageCurrent-1)*3;
        return carInfoDao.selectByIds(carIds,pageCurrent);
    }

    @Override
    public CarInfo selectById(Integer carId) {
        return carInfoDao.selectById(carId);
    }

    @Override
    public List<CarInfo> byPage(Integer pageCurrent) {
        return carInfoDao.byPage((pageCurrent-1)*10);
    }

    @Override
    public List<CarInfo> selectAll() {
        return carInfoDao.selectAll();
    }

    @Override
    public List<CarInfo> selectByArg(String ctype, Double sprice, Double eprice, String model, Date sage, Date eage,Double mileage, List<String> colors,Integer pageCurrent) {
        if(pageCurrent!=null) pageCurrent=(pageCurrent-1)*10;
        return carInfoDao.selectByArg( ctype,  sprice,  eprice,  model,  sage,  eage,  mileage, colors,pageCurrent);
    }

    @Override
    public List<CarInfo> selectByNameType(Integer pageCurrent,Integer belong, String type) {
        if(pageCurrent!=null) pageCurrent=(pageCurrent-1)*3;
        return carInfoDao.selectByNameType(pageCurrent,belong,type);
    }

    @Override
    public Integer delete(Integer carId) {
        return carInfoDao.delete(carId);
    }
}
