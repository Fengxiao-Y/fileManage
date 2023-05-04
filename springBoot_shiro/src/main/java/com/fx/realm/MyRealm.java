package com.fx.realm;

import com.fx.pojo.User;
import com.fx.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {
    //声明业务层属性
    @Autowired
    private UserService userService;

    //自定义授权方法，获取当前登录认证通过的用户的权限信息，返回给shiro使用，shiro来完成授权比对
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从EhCache中获取

        System.out.println("执行自定义授权的方法");
        //获取当前授权用户的身份信息
        String principal = principalCollection.getPrimaryPrincipal().toString();
        System.out.println("当前登录用户的身份信息：" + principal);
        //调用业务层获取用户的角色信息
        List<String> roles = (List<String>) userService.selUserRolesInfo(principal);
        System.out.println("当前查询的用户的角色信息为：" + roles);
        //调用业务层获取用户的权限信息
        List<String> ps = (List<String >)userService.selUserPsInfo(principal);
        System.out.println("当前查询的用户的权限信息为：" + ps);
        //创建对象，存储当前登录用户的权限和角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //存储角色
//        info.addRole("admin");
        info.addRoles(roles);
        //存储权限
        info.addStringPermissions(ps);
        //重新缓存数据到EhCache

        return info;
    }

    //自定义登录认证的方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.获取用户的身份
        String account = token.getPrincipal().toString();
        //2.调用业务层获取个人信息（查数据库）
        User user = userService.selUserInfo(account);
        //3.判断，并将数据封装
        if(user!=null){
            System.out.println("自定义的myRealm执行。。。。。。");
            AuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(),user.getPwd(),
                    ByteSource.Util.bytes(account),token.getPrincipal().toString());
            return info;
        }
        return null;
    }
}
