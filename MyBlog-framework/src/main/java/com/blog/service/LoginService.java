package com.blog.service;

import com.blog.pojo.dto.UserLoginDto;
import com.blog.pojo.vo.UserRABCInfoVO;

import java.util.Map;

public interface LoginService {
    Map<String,String> login(UserLoginDto userLoginDto);

    UserRABCInfoVO getInfo();
}
