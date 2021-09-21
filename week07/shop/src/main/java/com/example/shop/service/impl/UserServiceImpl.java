package com.example.shop.service.impl;

import com.example.shop.mapper.UserMapper;
import com.example.shop.model.entity.User;
import com.example.shop.model.enums.UserStatus;
import com.example.shop.model.ro.CreateUserReq;
import com.example.shop.service.UserService;
import com.example.shop.util.IdAgent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public int createUser(CreateUserReq req) {
        User user = new User();
        BeanUtils.copyProperties(req, user);
        user.setId(IdAgent.getIdUtil().getId());
        user.setSalt(String.valueOf((int)Math.random()* (9999-1000) + 1000));
        user.setStatus(UserStatus.DISABLE.getIndex());
        user.setCreateTime(new Date());
        user.setCreatorId(0L);
        userMapper.insert(user);
        return 1;
    }
}
