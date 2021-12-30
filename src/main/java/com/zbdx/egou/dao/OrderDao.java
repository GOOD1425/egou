package com.zbdx.egou.dao;

import com.zbdx.egou.pojo.Order;

import java.util.List;

public interface OrderDao {
    Integer insert(Order order);
    Order selectOrder(Integer userId,Integer carId);
    List<Order> selectAllOrder(Integer userId,Integer pageCurrent);
    List<Order> yipay(List<Integer> carIds,Integer pageCurrent);
    Integer pay(Integer orderId);
    Order findOrder(Integer orderId);
}
