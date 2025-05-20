package com.blog.service.serviceImpl;

import com.blog.enums.AppHttpCodeEnum;
import com.blog.exception.SystemException;
import com.blog.mapper.RoleMapper;
import com.blog.mapper.UserMapper;
import com.blog.pojo.dto.RegisterUserDto;
import com.blog.pojo.dto.UpdateUserInfoDto;
import com.blog.pojo.dto.UserDto;
import com.blog.pojo.entity.User;
import com.blog.pojo.vo.UserInfoVo;
import com.blog.result.PageBean;
import com.blog.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserInfoVo getById(Long userId) {
        User user = userMapper.getById(userId);
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(user, userInfoVo);
        return userInfoVo;
    }

    @Override
    public void updateUserInfo(UpdateUserInfoDto updateUserInfoDto) {
        User user = userMapper.getById(updateUserInfoDto.getId());
        BeanUtils.copyProperties(updateUserInfoDto, user);
        userMapper.update(user);
    }

    @Override
    public void register(RegisterUserDto registerUserDto) {
        //对数据进行非空判断
        if(!StringUtils.hasText(registerUserDto.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(registerUserDto.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(registerUserDto.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(registerUserDto.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //对数据进行是否存在的判断
        User user = userMapper.getByNickNameorUserName(registerUserDto);
        if(user != null){
            throw new SystemException(AppHttpCodeEnum.USERNAMEORNICKNAME_EXIST);
        }
        //对密码进行加密
        String encodePassword = passwordEncoder.encode(registerUserDto.getPassword());
        registerUserDto.setPassword(encodePassword);
        User registerUser = new User();
        BeanUtils.copyProperties(registerUserDto, registerUser);
        //存入数据库
        userMapper.insert(registerUser);
    }

    @Override
    public PageBean list(int pageNum, int pageSize) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        Page<User> userList = userMapper.getlist();
        return new PageBean(userList.getTotal(), userList.getResult());
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setDelFlag(0);
        user.setType("0");
        userMapper.insertUser(user);
        List<Long> roleIds = userDto.getRoleIds();
        for (Long roleId : roleIds) {
            roleMapper.insertUserRole(user.getId(),roleId);
        }
    }

    @Override
    public void delete(Long userId) {
        userMapper.deleteById(userId);
        roleMapper.deleteUserRoleById(userId);
    }
}
