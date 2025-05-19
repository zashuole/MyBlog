package com.blog.service;

import com.blog.pojo.dto.UserLoginDto;
import com.blog.pojo.vo.MenuDisplayVo;
import com.blog.pojo.vo.MenusVo;
import com.blog.pojo.vo.UserRABCInfoVO;

import java.util.List;
import java.util.Map;

public interface LoginService {
    Map<String,String> login(UserLoginDto userLoginDto);

    UserRABCInfoVO getInfo();

    MenusVo getRouters();
}
