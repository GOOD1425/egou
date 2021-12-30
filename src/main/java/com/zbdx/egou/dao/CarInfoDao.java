package com.zbdx.egou.dao;

import com.zbdx.egou.pojo.CarInfo;

import java.util.Date;
import java.util.List;

public interface CarInfoDao {
    Integer insertCarInfo(CarInfo carInfo);
   CarInfo selectById(Integer carId);
    List<CarInfo> selectByIds(List<Integer> carIds,Integer pageCurrent);
    List<CarInfo> selectByNameType(Integer pageCurrent,Integer belong,String type);
    List<CarInfo> byPage(Integer start);
    List<CarInfo> selectAll();
    List<CarInfo>   selectByArg(String ctype, Double sprice,Double eprice, String model, Date sage,Date eage, Double mileage, List<String> colors,Integer pageCurrent);
    Integer  delete(Integer carId);
    Integer pay(Integer carId,Integer belong,String belongType);
    CarInfo carBelong(Integer belong,Integer carId,String type);
}
