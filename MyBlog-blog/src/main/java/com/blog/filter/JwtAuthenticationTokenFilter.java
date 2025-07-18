package com.blog.filter;

import com.alibaba.fastjson.JSON;
import com.blog.enums.AppHttpCodeEnum;
import com.blog.pojo.entity.LoginUser;
import com.blog.result.Result;
import com.blog.utils.JwtUtils;
import com.blog.utils.RedisCache;
import com.blog.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean finished = false;
        //获取请求头中的token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            //说明该接口不需要登录  直接放行
            filterChain.doFilter(request, response);
        } else {//解析获取userid
            Claims claims = null;
            try {
                claims = JwtUtils.parseJWT(token);
            } catch (Exception e) {
                e.printStackTrace();
                //token超时  token非法
                //响应告诉前端需要重新登录
                Result result = Result.error(AppHttpCodeEnum.NEED_LOGIN);
                WebUtils.renderString(response, JSON.toJSONString(result));
                finished = true;
            }
            if (!finished) {
                String userId = (String) claims.get("userId");
                //从redis中获取用户信息
                LoginUser loginUser = redisCache.getCacheObject("bloglogin:" + userId);
                //如果获取不到
                if(Objects.isNull(loginUser)){
                    //说明登录过期  提示重新登录
                    Result result = Result.error(AppHttpCodeEnum.NEED_LOGIN);
                    WebUtils.renderString(response, JSON.toJSONString(result));
                } else {//存入SecurityContextHolder
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                }
            }
        }

    }


}