package com.elm.service;

import com.elm.pojo.Food;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IFoodService {
    List<Food> getFoodByBusinessId( int businessId);
    Food getByFoodId( int foodId);
}
