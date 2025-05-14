package com.blog.handler.security;

import com.alibaba.fastjson.JSON;
import com.blog.enums.AppHttpCodeEnum;
import com.blog.result.Result;
import com.blog.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        //InsufficientAuthenticationException
        //BadCredentialsException
        Result result = null;
        if(authException instanceof BadCredentialsException){
            result = Result.error(AppHttpCodeEnum.LOGIN_ERROR);
        }else if(authException instanceof InsufficientAuthenticationException){
            result = Result.error(AppHttpCodeEnum.NEED_LOGIN);
        }else{
            result = Result.error(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}