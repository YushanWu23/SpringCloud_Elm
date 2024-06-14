package com.elm.service;

import com.elm.pojo.OrderDetailet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderDetailetService {
    List<OrderDetailet> getByOrderId(int orderId);
    int saveOrderDetailetBatch(List<OrderDetailet> list);// 批量保存订单明细对象列表
}
