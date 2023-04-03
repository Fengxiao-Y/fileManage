package com.fx.realm;

import com.fx.pojo.User;
import com.fx.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {
    //声明业务层属性
    @Autowired
    private UserService userService;

    //自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //自定义登录认证的方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.获取用户的身份
        String account = token.getPrincipal().toString();
        //2.调用业务层获取个人信息（查数据库）
        User user = userService.selUserInfoService(account);
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
