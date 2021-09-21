package com.example.shop.controller;


import com.example.shop.model.ro.CreateUserReq;
import com.example.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/createUser")
    public int createUser(@RequestBody CreateUserReq req) {
        return userService.createUser(req);
    }
}
