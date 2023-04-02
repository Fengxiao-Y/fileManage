package com.fx.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class ShiroRun {
    public static void main(String[] args) {
        //1.初始化获取SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //2.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        //3.创建Token对象存储需要被认证的信息（web项目中该信息是从浏览器中发送过来的）
        AuthenticationToken token = new UsernamePasswordToken("zhangsan","hello");
        //4.调用login方法完成登录认证，完成登录
        try {
            subject.login(token);
            System.out.println("登录成功");
            //5.判断角色
            boolean result = subject.hasRole("role1");
            System.out.println("角色判断结果："+result);
            //判断权限
            //subject.checkPermission("user:insert");
            boolean permitted = subject.isPermitted("user:insert");
            System.out.println("权限判断结果："+permitted);
        } catch (UnknownAccountException e){
            System.out.println("账号不存在");
        } catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
        } catch (AuthenticationException e){
            e.printStackTrace();
        }
    }
}
