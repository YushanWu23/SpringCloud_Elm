package com.elm.impl;

import com.elm.dao.IBusinessDao;
import com.elm.pojo.Business;
import com.elm.service.IBusinessService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessServiceImpl implements IBusinessService {
    @Resource
    IBusinessDao iBusinessDao;
    @Override
    public Business getByBusinessId(int businessId){
        return iBusinessDao.findBusinessByBusinessId(businessId);
    }
    @Override
    public List<Business> getByBusinessNameContaining(String businessName){
        return  iBusinessDao.findBusinessesByBusinessNameContaining(businessName);
    }
    @Override
    public List<Business> getAll(){
        return iBusinessDao.findAll();
    }
    @Override
    public List<Business> getByOrderTypeId(int orderTypeId){
        return iBusinessDao.findBusinessesByOrderTypeId(orderTypeId);
    }
}
