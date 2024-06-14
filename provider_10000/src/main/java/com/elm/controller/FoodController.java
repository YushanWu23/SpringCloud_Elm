package com.elm.controller;

import com.elm.pojo.Food;
import com.elm.service.IFoodService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/food")
public class FoodController {
    @Resource
    IFoodService iFoodService;
    @GetMapping("/getFoodByBusinessId")//根据商家编号查询所属食品信息
    List<Food> getFoodByBusinessId(@RequestParam(value = "businessId") int businessId){
        return iFoodService.getFoodByBusinessId(businessId);
    }
    @GetMapping("/getByFoodId")
    Food getByFoodId(@RequestParam(value = "fooId") int fooId){
        return iFoodService.getByFoodId(fooId);
    }
}
