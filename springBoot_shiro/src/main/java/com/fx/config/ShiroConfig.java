package com.fx.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.fx.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    //声明自定义的realm属性
    @Autowired
    private MyRealm myRealm;
    //1.声明方法：配置SecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        //1.创建defaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //创建MD5加密对象，并设置相关属性
        HashedCredentialsMatcher macther = new HashedCredentialsMatcher();
        macther.setHashAlgorithmName("md5");
        macther.setHashIterations(2);
        //将MD5对象存储到myRealm对象中
        myRealm.setCredentialsMatcher(macther);
        //将自定义的MyRealm赋值给defaultWebSecurityManager对象
        defaultWebSecurityManager.setRealm(myRealm);
        //设置rememberMe功能的Cookie
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        //2.返回defaultWebSecurityManager对象
        return defaultWebSecurityManager;
    }

    //创建自定义的Cookie
    public SimpleCookie rememberMeCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
//        cookie跨域时使用（分布式）
//        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30*24*60*60);
        return cookie;
    }

    //创建shiro的cookie管理对象
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("cookieKey".getBytes());
        return cookieRememberMeManager;
    }

    //配置shiro内置拦截器
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        //设置不认证可以访问的资源
        definition.addPathDefinition("/myController/userLogin","anon");
        definition.addPathDefinition("/myController/login","anon");
        //配置退出过滤器
        definition.addPathDefinition("/logout","logout");
        //设置需要进行登录认证的拦截范围
        definition.addPathDefinition("/**","authc");
        definition.addPathDefinition("/**","user");
        return definition;
    }

    //页面按钮拦截器
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
