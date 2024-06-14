package com.example;

import com.example.pojo.Business;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/business")
public class BusinessController {
    @Resource
    BusinessFeignClient businessFeignClient;
    @GetMapping("/getBusinessByBusinessId")
    public Business getByBusinessId(@RequestParam(value = "businessId") int businessId){
        return businessFeignClient.getByBusinessId(businessId);
    }
    @GetMapping("/getByBusinessNameContaining/{businessName}")
    public List<Business> getByBusinessNameContaining(@RequestParam(value = "businessName") String businessName){
        return businessFeignClient.getByBusinessNameContaining(businessName);
    }
    @GetMapping("/getAll")
    List<Business> getAll(){
        return businessFeignClient.getAll();
    }
    @GetMapping("/getByOrderTypeId")
    List<Business> getByOrderTypeId(@RequestParam(value = "orderTypeId") int orderTypeId){
        return businessFeignClient.getByOrderTypeId(orderTypeId);
    }
}
