package com.fx.config;

import com.fx.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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
        //2.返回defaultWebSecurityManager对象
        return defaultWebSecurityManager;
    }

    //配置shiro内置拦截器
    /*@Bean
    public DefaultShiroFilterDefinition shiroFilterChainDefinition(){

        return null;
    }*/
}
