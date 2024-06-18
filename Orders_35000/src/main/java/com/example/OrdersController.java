package com.example;

import com.example.pojo.CommonResult;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import com.example.pojo.Orders;
import java.util.List;
@RefreshScope
@RestController
@CrossOrigin
@RequestMapping(value = "/orders")
public class OrdersController {
    @Resource
    OrdersFeignClient ordersFeignClient;
    @Value ("${protectSignal}")
    private Boolean protectSignal;
    @RequestMapping("/test")
    public String test(){
        return "接口调用成功";
    }
    @GetMapping("/getOrdersByUserId")
    List<Orders> getOrdersByUserId(@RequestParam(value = "userId") String userId) throws InterruptedException{//根据用户编号查询此用户的所有订单信息
        if(protectSignal){
            Thread.sleep(50000L);
        }
        return ordersFeignClient.getOrdersByUserId(userId);
    }
    @PostMapping("/createOrders")
    int createOrders(@RequestParam(value = "userId") String userId,@RequestParam(value = "businessId") int businessId,@RequestParam(value = "daId") int daId,@RequestParam(value = "orderTotal") Double orderTotal ){
        return ordersFeignClient.createOrders(userId,businessId,daId,orderTotal);
    }
    @GetMapping("/getOrdersByOrdersId")
    Orders getOrdersByOrdersId(@RequestParam(value = "orderId") int orderId){
        return ordersFeignClient.getOrdersByOrdersId(orderId);
    }

    @GetMapping("/updateOrderState")
    int updateOrderState(@RequestParam(value = "orderId") int orderId){
        return ordersFeignClient.updateOrderState(orderId);
    }
}

