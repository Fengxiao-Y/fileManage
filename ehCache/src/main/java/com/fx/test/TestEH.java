package com.fx.test;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.InputStream;

public class TestEH {
    public static void main(String[] args) {
        //获取编译目录下的资源的流对象
        InputStream is = TestEH.class.getClassLoader().getResourceAsStream("chcahe.xm");
        //获取编译目录下的资源的绝对路径
        //1、获取Ehcahe 的缓存管理对象
        CacheManager cacheManager = new CacheManager(is);
        //2、获取缓存对象
        Cache cache = cacheManager.getCache("HelloWordCache");
        //3、创建缓存数据
        Element element = new Element("name", "fx");
        //4、完成缓存
        cache.put(element);
        //5、获取缓存
        Element name = cache.get("name");
        System.out.println(name);
    }
}
