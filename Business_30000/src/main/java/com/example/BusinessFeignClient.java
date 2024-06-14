package com.example;

import com.example.pojo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "Provider")
public interface BusinessFeignClient {
    @GetMapping("/business/getBusinessByBusinessId")
    Business getByBusinessId(@RequestParam(value = "businessId") int businessId);
    @GetMapping("/business/getByBusinessNameContaining/{businessName}")
    List<Business> getByBusinessNameContaining(@RequestParam(value = "businessName") String businessName);
    @GetMapping("/business/getAll")
    List<Business> getAll();
    @GetMapping("/business/getByOrderTypeId")
    List<Business> getByOrderTypeId(@RequestParam(value = "orderTypeId") int orderTypeId);
}
