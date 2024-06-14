package com.elm.impl;

import com.elm.dao.IUserDao;
import com.elm.pojo.User;
import com.elm.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao iUserDao;
    @Override
    public User login(String userId, String pwd) {
        return iUserDao.findUserByUserIdAndPassword(userId, pwd);
    }
    @Override
    public int register(String userId,String password,String userName,int userSex) {
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setUserName(userName);
        user.setUserSex(userSex);
        iUserDao.save(user);
        return 1;
    }
    @Override//根据用户编号查询用户表返回的行数
    public int getUserInfo(String userId) {
        long count = iUserDao.countByUserId(userId);
        return (int) count;
    }
}
