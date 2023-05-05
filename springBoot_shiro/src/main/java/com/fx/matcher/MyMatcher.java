package com.fx.matcher;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyMatcher extends HashedCredentialsMatcher {
    //声明缓存对象
    private Ehcache passwordRetryEhcache;

    //声明构造方法获取缓存对象
    public  MyMatcher(EhCacheManager ehCacheManager){
        passwordRetryEhcache=ehCacheManager.getCacheManager().getCache("passwordRetryEhcache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //声明变量记录错误次数
        int i = 0;
        //1、获取用户登录次数的缓存信息
        //获取用户的身份信息（身份信息为缓存数据的键名）
        String account = token.getPrincipal().toString();
        //获取缓存对象
        Element element = passwordRetryEhcache.get(account);
        //2、判断是否有缓存数据
        if(element==null){
            //没有缓存登录错误次数，则创建新的缓存
            Element ele = new Element(account, new AtomicInteger(0));
            passwordRetryEhcache.put(ele);
        } else {
            //有缓存直接递增
            AtomicInteger atomicInteger = (AtomicInteger) element.getObjectValue();
            i = atomicInteger.incrementAndGet();
        }
        //3、判断i值是否4次
        if(i>=4){
            throw new ExcessiveAttemptsException();
        }
        //4、进行本次请求登录
        boolean match = super.doCredentialsMatch(token, info);
        //5、
        if(match){
            passwordRetryEhcache.remove(account);
        }
        return match;
    }
}
