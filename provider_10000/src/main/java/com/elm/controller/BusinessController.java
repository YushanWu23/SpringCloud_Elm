package com.elm.controller;

import com.elm.pojo.Business;
import com.elm.service.IBusinessService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/business")
public class BusinessController {
    @Resource
    IBusinessService iBusinessService;
    @GetMapping("/getBusinessByBusinessId")
    public Business getByBusinessId(@RequestParam(value = "businessId") int businessId){
        return iBusinessService.getByBusinessId(businessId);
    }
    @GetMapping("/getByBusinessNameContaining/{businessName}")
    public List<Business> getByBusinessNameContaining(@RequestParam(value = "businessName") String businessName){
        return iBusinessService.getByBusinessNameContaining(businessName);
    }
    @GetMapping("/getAll")
    List<Business> getAll(){
        return iBusinessService.getAll();
    }
    @GetMapping("/getByOrderTypeId")
    List<Business> getByOrderTypeId(@RequestParam(value = "orderTypeId") int orderTypeId){
        return iBusinessService.getByOrderTypeId(orderTypeId);
    }
}
