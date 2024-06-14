package com.elm.controller;

import com.elm.pojo.User;
import com.elm.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private IUserService iUserService;
    @PostMapping("/login")
    User login(@RequestParam(value = "userId") String userId, @RequestParam(value = "pwd") String pwd){
        return iUserService.login(userId,pwd);
    }
    @PostMapping("/register")
    int register(@RequestParam(value = "userId") String userId,@RequestParam(value = "password") String password,@RequestParam(value = "userName") String userName,@RequestParam(value = "userSex") int userSex){
        return iUserService.register(userId,password,userName,userSex);
    }
    @GetMapping("/getUserInfo")//得到用户信息
    int getUserInfo(@RequestParam(value = "userId") String userId){
        return iUserService.getUserInfo(userId);
    }
}
