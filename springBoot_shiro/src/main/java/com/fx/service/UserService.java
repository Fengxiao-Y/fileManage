package com.fx.service;

import com.fx.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    //用户登录
    User selUserInfoService(String account);
}
