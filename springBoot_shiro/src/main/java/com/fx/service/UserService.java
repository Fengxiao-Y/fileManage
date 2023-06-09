package com.fx.service;

import com.fx.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    //用户登录
    User selUserInfo(String account);
    //获取用户角色信息
    List<String> selUserRolesInfo(String account);
    //获取用户权限信息
    List<String> selUserPsInfo(String account);
}
