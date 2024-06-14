package com.elm.controller;

import com.elm.pojo.Cart;
import com.elm.service.ICartService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Resource
    ICartService iCartService;
    @PostMapping("/getCartAll")//显示该购物车内容
    List<Cart> getCartAll(@RequestParam(value = "userId") String userId,@RequestParam(value = "businessId") int businessId){
        return iCartService.getCartAll(userId,businessId);
    }
    @PostMapping("/insertFoodIntoCart")//添加商品到购物车
    int insertFoodIntoCart(@RequestParam(value = "userId") String userId,@RequestParam(value = "businessId") int businessId,@RequestParam(value = "foodId") int foodId){
        return iCartService.insertFoodIntoCart(userId,businessId,foodId);
    }
    @PostMapping("/deleteCart")
    int deleteCart(@RequestParam(value = "userId") String userId,@RequestParam(value = "businessId") int businessId,@RequestParam(value = "foodId")int foodId){
        return iCartService.deleteCart(userId,businessId,foodId);
    }
    @PostMapping("/updateCart")
    int updateCart(@RequestParam(value = "userId") String userId,@RequestParam(value = "businessId") int businessId,@RequestParam(value = "foodId") int foodId,@RequestParam(value = "quantity") int quantity){
        return iCartService.updateCart(userId,businessId,foodId,quantity);
    }
}