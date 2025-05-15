package com.blog.aspect;

import com.blog.annotation.AutoFill;
import com.blog.pojo.common.OperationType;
import com.blog.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(* com.blog.mapper.*.*(..)) && @annotation(com.blog.annotation.AutoFill)")
    public void autoFillPointCut() {}

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        log.info("开始进行公共字段填充");
        //先分辨是update操作还是insert操作
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        AutoFill annotation = methodSignature.getMethod().getAnnotation(AutoFill.class);//获取方法的上的注解对象
        OperationType value = annotation.value();//获取数据库操作类型
        //获取当前被拦截方法的参数，实体对象
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0) {
            return;
        }
        Object arg = args[0];
        //反射赋值
        Date now = new Date();
        Long userId = SecurityUtils.getUserId();
        if(value == OperationType.INSERT) {
            //获得四个公共字段的set方法
            Method setCreatTime = arg.getClass().getDeclaredMethod("setCreateTime", Date.class);
            Method setUpdateTime = arg.getClass().getDeclaredMethod("setUpdateTime", Date.class);
            Method setCreatUser = arg.getClass().getDeclaredMethod("setCreateBy", Long.class);
            Method setUpdateUser = arg.getClass().getDeclaredMethod("setUpdateBy", Long.class);
            //设置权限
            setCreatTime.setAccessible(true);
            setUpdateTime.setAccessible(true);
            setCreatUser.setAccessible(true);
            setUpdateUser.setAccessible(true);
            //反射赋值
            setCreatUser.invoke(arg,userId);
            setUpdateUser.invoke(arg,userId);
            setCreatTime.invoke(arg,now);
            setUpdateTime.invoke(arg,now);
        }
        if (value == OperationType.UPDATE){
            //获得两个个公共字段的set方法
            Method setUpdateTime = arg.getClass().getDeclaredMethod("setUpdateTime", Date.class);
            Method setUpdateUser = arg.getClass().getDeclaredMethod("setUpdateBy", Long.class);
            //设置权限
            setUpdateTime.setAccessible(true);
            setUpdateUser.setAccessible(true);
            //反射赋值
            setUpdateUser.invoke(arg,userId);
            setUpdateTime.invoke(arg,now);
        }
        if(value == OperationType.REGISTER) {
            //获得四个公共字段的set方法
            Method setCreatTime = arg.getClass().getDeclaredMethod("setCreateTime", Date.class);
            Method setUpdateTime = arg.getClass().getDeclaredMethod("setUpdateTime", Date.class);//设置权限
            setCreatTime.setAccessible(true);
            setUpdateTime.setAccessible(true);
            //反射赋值
            setCreatTime.invoke(arg,now);
            setUpdateTime.invoke(arg,now);
        }
    }
}
