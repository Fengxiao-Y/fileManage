package com.fx.service.impl;

import com.fx.mapper.UserMapper;
import com.fx.pojo.User;
import com.fx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    //声明mapper层属性
    @Autowired
    private UserMapper userMapper;
    @Override
    public User selUserInfoService(String account) {
        return userMapper.selUserInfoByName(account);
    }
}
