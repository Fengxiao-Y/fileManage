package com.fx.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class MyRealm extends AuthenticatingRealm {
    //自定义的登录认证方法，shiro的login方法底层会调用该类的认证方法
    //前提是需要配置自定义的realm生效，在ini文件中配置，或者springBoot项目的配置类中
    //注意：该方法只是用来获取要进行比对的信息，底层仍然是使用shiro登录认证逻辑完成
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取要认证的信息
        //1.获取身份
        String principal = token.getPrincipal().toString();
        //2.获取凭证
        String pwd = token.getCredentials().toString();
        System.out.println("请求中的用户信息："+principal+"----"+pwd);
        //3.调用业务层，根据身份获取用户存储在数据库中的信息
        if(principal.equals("zhangsan")){
//            String password="hello";
            String password="3558baf242c0ce1d372bc854fc3ca8a7";
            //创建shiro提供的封装了校验逻辑的对象，将要比对是数据给该对象
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(),password, ByteSource.Util.bytes("yanfengxiao"),"username");
            return info;
        }
        return null;
    }
}
