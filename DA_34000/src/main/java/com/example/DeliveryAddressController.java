package com.example;

import com.example.pojo.CommonResult;
import com.example.pojo.DeliveryAddress;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/deliveryAddress")
public class DeliveryAddressController {
    @Resource
    DeliveryAddressFeignClient deliveryAddressFeignClient;
    @GetMapping("/getDeliveryAddressByUserId")//根据用户编号查询所属送货地址
    List<DeliveryAddress> getDeliveryAddressByUserId(@RequestParam(value = "userId") String userId){
        return deliveryAddressFeignClient.getDeliveryAddressByUserId(userId);
    }
    @GetMapping("/getDeliveryAddressByDaId")//根据送货地址编号查询送货地址
    DeliveryAddress getDeliveryAddressByDaId(@RequestParam(value = "daId") int daId){
        return deliveryAddressFeignClient.getDeliveryAddressByDaId(daId);
    }
    @PostMapping("/saveDeliveryAddress")
    int saveDeliveryAddress(@RequestParam(value = "contactName") String contactName,@RequestParam(value = "contactSex") int contactSex,@RequestParam(value = "contactTel") String contactTel,@RequestParam(value = "address") String address,@RequestParam(value = "userId") String userId){
        return deliveryAddressFeignClient.saveDeliveryAddress(contactName,contactSex,contactTel,address,userId);
    }
    @PostMapping("/updateDeliveryAddress")
    int updateDeliveryAddress (@RequestParam(value = "daId") int daId,@RequestParam(value = "contactName") String contactName,@RequestParam(value = "contactSex") int contactSex,@RequestParam(value = "contactTel") String contactTel,@RequestParam(value = "address") String address){
        return deliveryAddressFeignClient.updateDeliveryAddress (daId,contactName,contactSex,contactTel,address);
    }
    @GetMapping("/removeDeliveryAddress")
    int removeDeliveryAddress(@RequestParam(value = "daId") int daId,@RequestParam(value = "userId") String userId){
        return deliveryAddressFeignClient.removeDeliveryAddress(daId,userId);
    }
}

