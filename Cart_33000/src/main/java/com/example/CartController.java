package com.example;
import com.example.pojo.*;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Resource
    CartFeignClient cartFeignClient;
    @PostMapping("/getCartAll")//显示该购物车内容
    List<Cart> getCartAll(@RequestParam(value = "userId") String userId,@RequestParam(value = "businessId") int businessId){
        return cartFeignClient.getCartAll(userId,businessId);
    }
    @PostMapping("/insertFoodIntoCart")//添加商品到购物车
    int insertFoodIntoCart(@RequestParam(value = "userId") String userId,@RequestParam(value = "businessId") int businessId,@RequestParam(value = "foodId") int foodId){
        return cartFeignClient.insertFoodIntoCart(userId,businessId,foodId);
    }
    @PostMapping("/deleteCart")
    int deleteCart(@RequestParam(value = "userId") String userId,@RequestParam(value = "businessId") int businessId,@RequestParam(value = "foodId")int foodId){
        return cartFeignClient.deleteCart(userId,businessId,foodId);
    }
    @PostMapping("/updateCart")
    int updateCart(@RequestParam(value = "userId") String userId,@RequestParam(value = "businessId") int businessId,@RequestParam(value = "foodId") int foodId,@RequestParam(value = "quantity") int quantity){
        return cartFeignClient.updateCart(userId,businessId,foodId,quantity);
    }
}
