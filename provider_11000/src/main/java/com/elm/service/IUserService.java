package com.elm.service;

import com.elm.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    User login(String userId,String pwd);
    int register(String userId,String password,String userName,int userSex);
    int getUserInfo(String userId);
}
