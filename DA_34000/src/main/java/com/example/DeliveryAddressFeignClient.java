package com.example;

import com.example.pojo.DeliveryAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "Provider")
public interface DeliveryAddressFeignClient {
    @GetMapping("/deliveryAddress/getDeliveryAddressByUserId")//根据用户编号查询所属送货地址
    List<DeliveryAddress> getDeliveryAddressByUserId(@RequestParam(value = "userId") String userId);
    @GetMapping("/deliveryAddress/getDeliveryAddressByDaId")//根据送货地址编号查询送货地址
    DeliveryAddress getDeliveryAddressByDaId(@RequestParam(value = "daId") int daId);
    @PostMapping("/deliveryAddress/saveDeliveryAddress")
    int saveDeliveryAddress(@RequestParam(value = "contactName") String contactName,@RequestParam(value = "contactSex") int contactSex,@RequestParam(value = "contactTel") String contactTel,@RequestParam(value = "address") String address,@RequestParam(value = "userId") String userId);
    @PostMapping("/deliveryAddress/updateDeliveryAddress")
    int updateDeliveryAddress (@RequestParam(value = "daId") int daId,@RequestParam(value = "contactName") String contactName,@RequestParam(value = "contactSex") int contactSex,@RequestParam(value = "contactTel") String contactTel,@RequestParam(value = "address") String address);
    @GetMapping("/deliveryAddress/removeDeliveryAddress")
    int removeDeliveryAddress(@RequestParam(value = "daId") int daId,@RequestParam(value = "userId") String userId);
}
