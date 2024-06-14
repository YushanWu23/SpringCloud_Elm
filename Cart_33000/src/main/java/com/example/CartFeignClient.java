package com.example;

import com.example.pojo.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "Provider")
public interface CartFeignClient {
    @PostMapping("/cart/getCartAll")//显示该购物车内容
    List<Cart> getCartAll(@RequestParam(value = "userId") String userId, @RequestParam(value = "businessId") int businessId);
    @PostMapping("/cart/insertFoodIntoCart")//添加商品到购物车
    int insertFoodIntoCart(@RequestParam(value = "userId") String userId,@RequestParam(value = "businessId") int businessId,@RequestParam(value = "foodId") int foodId);
    @PostMapping("/cart/deleteCart")
    int deleteCart(@RequestParam(value = "userId") String userId,@RequestParam(value = "businessId") int businessId,@RequestParam(value = "foodId") int foodId);
    @PostMapping("/cart/updateCart")
    int updateCart(@RequestParam(value = "userId") String userId,@RequestParam(value = "businessId") int businessId,@RequestParam(value = "foodId") int foodId,@RequestParam(value = "quantity") int quantity);
}
