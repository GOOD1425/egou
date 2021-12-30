package com.zbdx.egou.service.impl;

import com.zbdx.egou.dao.OrderDao;
import com.zbdx.egou.pojo.Order;
import com.zbdx.egou.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Override
    public Integer insert(Order order) {
        return orderDao.insert(order);
    }

    @Override
    public Order selectOrder(Integer userId, Integer carId) {
        return orderDao.selectOrder(userId,carId);
    }

    @Override
    public List<Order> selectAllOrder(Integer userId, Integer pageCurrent) {
        if(pageCurrent!=null) pageCurrent=(pageCurrent-1)*3;
        return orderDao.selectAllOrder(userId,pageCurrent);
    }

    @Override
    public Integer pay(Integer orderId) {
        return orderDao.pay(orderId);
    }

    @Override
    public Order findOrder(Integer orderId) {
        return orderDao.findOrder(orderId);
    }

    @Override
    public List<Order> yipay(List<Integer> carIds,Integer pageCurrent) {
        return orderDao.yipay(carIds,pageCurrent);
    }
}
