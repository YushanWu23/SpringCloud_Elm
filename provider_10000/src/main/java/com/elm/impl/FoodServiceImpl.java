package com.elm.impl;

import com.elm.dao.IFoodDao;
import com.elm.pojo.Food;
import com.elm.service.IFoodService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodServiceImpl implements IFoodService {
    @Resource
    IFoodDao iFoodDao;
    @Override
    public List<Food> getFoodByBusinessId(int businessId){
        return iFoodDao.findFoodByBusinessBusinessId(businessId);
    }
    @Override
    public Food getByFoodId(int foodId){
        return iFoodDao.findFoodByFoodId(foodId);
    }
}
