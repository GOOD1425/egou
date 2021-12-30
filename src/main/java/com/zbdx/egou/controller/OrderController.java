package com.zbdx.egou.controller;

import com.zbdx.egou.pojo.*;
import com.zbdx.egou.service.CarInfoService;
import com.zbdx.egou.service.OrderService;
import com.zbdx.egou.service.StoreService;
import com.zbdx.egou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    CarInfoService carInfoService;
    @Autowired
    StoreService storeService;
    @RequestMapping("/addOrder")
    @ResponseBody
    public String insert(String username,Integer carId){
        User user = userService.selectByUsername(username);
        CarInfo carInfo = carInfoService.selectById(carId);
        Order order=new Order();
        order.setCarId(carInfo.getCarId()).setUserId(user.getUserId()).setDue(carInfo.getPrice()*10000).setDeposit(2000).setIsDelete(0).setDate(new Date());
        Integer insert = orderService.insert(order);
        if (insert!=0){
            return "ok";
        }
        return "error";
    }
    @RequestMapping("/selectOrder")
    @ResponseBody
    public String selectOrder(String username,Integer carId){
        User user = userService.selectByUsername(username);
        Order order = orderService.selectOrder(user.getUserId(), carId);
        if (order!=null){
            return "ok";
        }
        return "error";
    }
    @RequestMapping("/carBelong")
    @ResponseBody
    public String carBelong(String username,Integer carId,String type){
        if(type.equals("user")){
            User user = userService.selectByUsername(username);
            CarInfo info = carInfoService.carBelong(user.getUserId(), carId, type);
            if(info==null){
                return "ok";
            }
        }else {
            Store user = storeService.selectByUsername(username);
            CarInfo info = carInfoService.carBelong(user.getStoreId(), carId, type);
            if(info==null){
                return "ok";
            }
        }
        return "error";
    }
    @RequestMapping("/selectAllOrder")
    @ResponseBody
    public  List<VoOrder>  selectAllOrder(String username,Integer pageCurrent,String type){
        User user=null;
        if(type.equals("user")){
             user = userService.selectByUsername(username);
        }
        List<Order> list=orderService.selectAllOrder(user.getUserId(),pageCurrent);
        List<VoOrder> res=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CarInfo carInfo=carInfoService.selectById(list.get(i).getCarId());
            VoOrder voOrder=new VoOrder();
            voOrder.setOrder(list.get(i));
            voOrder.setCarInfo(carInfo);
            res.add(voOrder);
        }
        return res;
    }
    @RequestMapping("/pay")
    @ResponseBody
    public  String  selectAllOrder1(Integer orderId){
        Integer pay = orderService.pay(orderId);
        if(pay!=0){
            return "ok";
        }
        return "error";
    }
    @RequestMapping("/yipay")
    @ResponseBody
    public  List<VoOrder>  selectAllOrder2(String username,Integer pageCurrent,String type){
        User user=null;
        if(type.equals("user")){
            user = userService.selectByUsername(username);
        }
        List<CarInfo> carInfos = carInfoService.selectByNameType(null, user.getUserId(), type);
        List<Integer> carIds=new ArrayList<>();
        for (int i = 0; i < carInfos.size(); i++) {
            carIds.add(carInfos.get(i).getCarId());
        }
        List<Order> list = orderService.yipay(carIds, pageCurrent);
        List<VoOrder> res=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CarInfo carInfo=carInfoService.selectById(list.get(i).getCarId());
            VoOrder voOrder=new VoOrder();
            voOrder.setOrder(list.get(i));
            voOrder.setCarInfo(carInfo);
            res.add(voOrder);
        }
        return res;
    }
}
