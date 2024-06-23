package com.elm.controller;

import com.elm.pojo.Orders;
import com.elm.service.IOrdersService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/orders")
public class OrdersController {
    @Resource
    IOrdersService iOrdersService;
    @PostMapping("/createOrders")
    int createOrders(@RequestParam(value = "userId") String userId,
                     @RequestParam(value = "businessId") int businessId,
                     @RequestParam(value = "daId") int daId,
                     @RequestParam(value = "orderTotal") Double orderTotal ){
        return iOrdersService.createOrders(userId,businessId,daId,orderTotal);
    }
    @GetMapping("/getOrdersByOrdersId")
    Orders getOrdersByOrdersId(@RequestParam(value = "orderId") int orderId){
        return iOrdersService.getOrdersByOrdersId(orderId);
    }
    @GetMapping("/getOrdersByUserId")
    List<Orders> getOrdersByUserId(@RequestParam(value = "userId") String userId) throws InterruptedException{//根据用户编号查询此用户的所有订单信息
        return iOrdersService.getOrdersByUserId(userId);
    }
    @GetMapping("/updateOrderState")
    int updateOrderState(@RequestParam(value = "orderId") int orderId){
        return iOrdersService.updateOrderState(orderId);
    }
}
