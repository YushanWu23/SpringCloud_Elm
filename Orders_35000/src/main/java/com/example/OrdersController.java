package com.example;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.example.pojo.Orders;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/orders")
public class OrdersController {
    @Resource
    OrdersFeignClient ordersFeignClient;
    @PostMapping("/createOrders")
    int createOrders(@RequestParam(value = "userId") String userId,@RequestParam(value = "businessId") int businessId,@RequestParam(value = "daId") int daId,@RequestParam(value = "orderTotal") Double orderTotal ){
        return ordersFeignClient.createOrders(userId,businessId,daId,orderTotal);
    }
    @GetMapping("/getOrdersByOrdersId")
    Orders getOrdersByOrdersId(@RequestParam(value = "orderId") int orderId){
        return ordersFeignClient.getOrdersByOrdersId(orderId);
    }
    @GetMapping("/getOrdersByUserId")
    List<Orders> getOrdersByUserId(@RequestParam(value = "userId") String userId){//根据用户编号查询此用户的所有订单信息
        return ordersFeignClient.getOrdersByUserId(userId);
    }
    @GetMapping("/updateOrderState")
    int updateOrderState(@RequestParam(value = "orderId") int orderId){
        return ordersFeignClient.updateOrderState(orderId);
    }
}

