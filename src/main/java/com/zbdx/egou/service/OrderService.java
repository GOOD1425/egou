package com.zbdx.egou.service;

import com.zbdx.egou.pojo.Order;

import java.util.List;

public interface OrderService {
     Integer insert(Order order);
     Order selectOrder(Integer userId,Integer carId);
     List<Order> selectAllOrder(Integer userId, Integer pageCurrent);
     Integer pay(Integer orderId);
     Order findOrder(Integer orderId);
     List<Order> yipay(List<Integer> carIds,Integer pageCurrent);
}
