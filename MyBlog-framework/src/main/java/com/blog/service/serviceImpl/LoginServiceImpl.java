package com.blog.service.serviceImpl;

import com.blog.mapper.MenuMapper;
import com.blog.mapper.RoleMapper;
import com.blog.mapper.UserMapper;
import com.blog.pojo.dto.UserLoginDto;
import com.blog.pojo.entity.LoginUser;
import com.blog.pojo.entity.User;
import com.blog.pojo.vo.BlogUserLoginVo;
import com.blog.pojo.vo.UserInfoVo;
import com.blog.pojo.vo.UserRABCInfoVO;
import com.blog.properties.JwtProperties;
import com.blog.result.Result;
import com.blog.service.LoginService;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.JwtUtils;
import com.blog.utils.RedisCache;
import com.blog.utils.SecurityUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String,String> login(UserLoginDto userLoginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getUserName(),userLoginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtils.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);

        return map;
    }

    @Override
    public UserRABCInfoVO getInfo() {
        UserRABCInfoVO userRABCInfoVO = new UserRABCInfoVO();
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        String roles = roleMapper.getRolesByUserId(userId);
        userRABCInfoVO.setRoles(roles);
        //根据id查询其拥有的菜单id
        List<Integer> menuIds = roleMapper.getMenusIdByUserId(userId);
        //获取permissions
        List<String> permissions = new ArrayList<>();
        //如果menus只有一条，即为超级管理员
        if(menuIds.size() == 1&&menuIds.get(0).intValue() == 0){
            //获取全部权限，状态为正常，未被删除的
            permissions = menuMapper.getAllPermsWhoAdmin();
        }else{
            for (Integer menuId : menuIds) {
                //根据id查询并存入permissions
                permissions.add(menuMapper.getPermBymenuId(menuId));
            }
        }
        userRABCInfoVO.setPermissions(permissions);
        //根据用户id查userInfo
        User user = userMapper.getById(userId);
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(user,userInfoVo);
        userRABCInfoVO.setUser(userInfoVo);
        return userRABCInfoVO;
    }
}
