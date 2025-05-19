package com.blog.controller;

import com.blog.enums.AppHttpCodeEnum;
import com.blog.exception.SystemException;
import com.blog.pojo.dto.UserLoginDto;
import com.blog.pojo.vo.MenuDisplayVo;
import com.blog.pojo.vo.MenusVo;
import com.blog.pojo.vo.UserRABCInfoVO;
import com.blog.result.Result;
import com.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public Result<Map<String, String>> login(@RequestBody UserLoginDto userLoginDto) {
        if (!StringUtils.hasText(userLoginDto.getUserName())) {
            //提示，必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return Result.success(loginService.login(userLoginDto));
    }

    @GetMapping("/getInfo")
    public Result<UserRABCInfoVO> getInfo() {
        return Result.success(loginService.getInfo());
    }

    @GetMapping("/getRouters")
    public Result<MenusVo> getRouters() {
        return Result.success(loginService.getRouters());
    }
}
