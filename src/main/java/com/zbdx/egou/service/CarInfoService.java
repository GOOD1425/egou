package com.zbdx.egou.service;

import com.zbdx.egou.pojo.CarInfo;

import java.util.Date;
import java.util.List;

public interface CarInfoService {
    String insertCarInfo(CarInfo carInfo);
    CarInfo selectById(Integer carId);
    List<CarInfo> selectByIds(List<Integer> carIds,Integer pageCurrent);
     List<CarInfo> byPage(Integer pageCurrent);
     List<CarInfo> selectAll();
    List<CarInfo>  selectByArg(String ctype, Double sprice, Double eprice, String model, Date sage, Date eage,  Double mileage, List<String> colors,Integer pageCurrent);
    List<CarInfo> selectByNameType(Integer pageCurrent,Integer belong,String type);
    Integer delete(Integer carId);
}
