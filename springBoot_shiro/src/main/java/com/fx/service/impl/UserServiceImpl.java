package com.fx.service.impl;

import com.fx.mapper.UserMapper;
import com.fx.pojo.User;
import com.fx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    //声明mapper层属性
    @Autowired
    private UserMapper userMapper;

    //获取用户
    @Override
    public User selUserInfo(String account) {
        return userMapper.selUserInfoByAccount(account);
    }

    //获取用户的角色权限
    @Override
    public List<String> selUserRolesInfo(String account) {
        return userMapper.seluserRolesByAccount(account);
    }
}
