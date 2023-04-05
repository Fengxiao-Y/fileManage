package com.fx.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
* 公共的异常处理
* */
@ControllerAdvice
public class ExceptionController {
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(UnauthorizedException e){
        return "无权限";
    }

    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(AuthorizationException e){
        return "权限认证失败";
    }
}
