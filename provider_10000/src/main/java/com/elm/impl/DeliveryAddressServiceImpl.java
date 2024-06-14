package com.elm.impl;

import com.elm.dao.IDeliveryAddressDao;
import com.elm.dao.IUserDao;
import com.elm.pojo.DeliveryAddress;
import com.elm.pojo.User;
import com.elm.service.IDeliveryAddressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryAddressServiceImpl implements IDeliveryAddressService {
    @Resource
    IDeliveryAddressDao iDeliveryAddressDao;
    @Resource
    IUserDao iUserDao;
    @Override
    public List<DeliveryAddress> getDeliveryAddressByUserId(String userId){
        return iDeliveryAddressDao.findDeliveryAddressesByUserUserId(userId);
    }
    @Override
    public DeliveryAddress getDeliveryAddressByDaId(int daId){
        return iDeliveryAddressDao.findDeliveryAddressByDaId(daId);
    }
    @Override
    public int saveDeliveryAddress(String contactName,int contactSex,String contactTel,String address,String userId){
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setContactName(contactName);
        deliveryAddress.setContactSex(contactSex);
        deliveryAddress.setContactTel(contactTel);
        deliveryAddress.setAddress(address);
        User user = iUserDao.findUserByUserId(userId);
        deliveryAddress.setUser(user);
        iDeliveryAddressDao.save(deliveryAddress);
        return 1;
    }
    @Override
    public int updateDeliveryAddress(int daId,String contactName,int contactSex,String contactTel,String address){
        DeliveryAddress deliveryAddress = iDeliveryAddressDao.findDeliveryAddressByDaId(daId);
        deliveryAddress.setContactName(contactName);
        deliveryAddress.setContactSex(contactSex);
        deliveryAddress.setContactTel(contactTel);
        deliveryAddress.setAddress(address);
        iDeliveryAddressDao.save(deliveryAddress);
        return 1;
    }
    @Override
    public int removeDeliveryAddress(int daId,String userId){
        DeliveryAddress deliveryAddress = iDeliveryAddressDao.findDeliveryAddressByDaId(daId);
        User user = iUserDao.findUserByUserId(userId);
        user.removeDeliveryAddress(deliveryAddress);
        iUserDao.save(user);
        return 1;
    }
}
