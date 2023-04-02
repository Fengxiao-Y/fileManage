package com.fx.test;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestMD5 {
    public static void main(String[] args) {
        //声明要被加密的字符串
        String password = "Admin@123";
        //使用MD5加密
        Md5Hash md5Hash= new Md5Hash(password);
        System.out.println(md5Hash.toHex());
        //使用加盐加密保证数据的安全性
        Md5Hash md5Hash2 = new Md5Hash(password, "22222");
        System.out.println("加盐后的结果："+md5Hash2.toHex());
        //使用迭代并加盐保证数据安全
        Md5Hash md5Hash3 = new Md5Hash(password, "22222", 2);
        System.out.println("迭代加盐后的结果："+md5Hash3.toHex());
    }
}
