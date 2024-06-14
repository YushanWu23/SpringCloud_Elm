package com.elm.service;

import com.elm.pojo.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrdersService {
    int createOrders(String userId,int businessId,int daId,Double orderTotal);
    Orders getOrdersByOrdersId(int orderId);
    List<Orders> getOrdersByUserId(String userId);
    int updateOrderState( int orderId);
}
