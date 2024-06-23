package com.example;

import com.example.pojo.Orders;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(name = "Provider")
@LoadBalancerClient
public interface OrdersFeignClient {
    @PostMapping("/orders/createOrders")
    int createOrders(@RequestParam(value = "userId") String userId,
                     @RequestParam(value = "businessId") int businessId,
                     @RequestParam(value = "daId") int daId,
                     @RequestParam(value = "orderTotal") Double orderTotal );
    @GetMapping("/orders/getOrdersByOrdersId")
    Orders getOrdersByOrdersId(@RequestParam(value = "orderId") int orderId);
    @GetMapping("/orders/getOrdersByUserId")
    List<Orders> getOrdersByUserId(@RequestParam(value = "userId") String userId);
    @GetMapping("/orders/updateOrderState")
    int updateOrderState(@RequestParam(value = "orderId") int orderId);
}
