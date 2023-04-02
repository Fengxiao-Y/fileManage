package com.fx.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("myController")
public class MyCotroller {
    //声明单元方法：完成用户登录认证
    @RequestMapping("userLogin")
    @ResponseBody
    public String userLogin(String account,String pwd){
        System.out.println("登录认证单元方法执行："+account+"=="+pwd);
        //处理请求
        //响应结果
        //获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        //封装请求数据到token对象中
        AuthenticationToken token = new UsernamePasswordToken(account,pwd);
        try {
            subject.login(token);
            return "登录成功";
        } catch (AuthenticationException e){
            System.out.println("登录失败");
            return "登录失败";
        }
    }
}
