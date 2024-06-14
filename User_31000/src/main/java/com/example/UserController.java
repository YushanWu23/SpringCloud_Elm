package com.example;

import com.example.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserFeignClient userFeignClient;
    @PostMapping("/login")
    User login(@RequestParam(value = "userId") String userId, @RequestParam(value = "pwd") String pwd){
        return userFeignClient.login(userId,pwd);
    }
    @PostMapping("/register")
    int register(@RequestParam(value = "userId") String userId,@RequestParam(value = "password") String password,@RequestParam(value = "userName") String userName,@RequestParam(value = "userSex") int userSex){
        return userFeignClient.register(userId,password,userName,userSex);
    }
    @GetMapping("/getUserInfo")//得到用户信息
    int getUserInfo(@RequestParam(value = "userId") String userId){
        return userFeignClient.getUserInfo(userId);
    }
}

