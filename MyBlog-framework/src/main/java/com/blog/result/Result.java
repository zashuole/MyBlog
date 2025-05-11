package com.blog.result;

import com.blog.enums.AppHttpCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * @param <T> 返回数据类型
 */
@Data
public class Result<T> implements Serializable {

    private Integer code; // 状态码
    private String msg;   // 返回信息
    private T data;       // 返回数据

    // 成功，无数据
    public static <T> Result<T> success() {
        return success(null);
    }

    // 成功，有数据
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = AppHttpCodeEnum.SUCCESS.getCode();
        result.msg = AppHttpCodeEnum.SUCCESS.getMsg();
        result.data = data;
        return result;
    }

    // 失败，自定义错误信息
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.code = AppHttpCodeEnum.SYSTEM_ERROR.getCode();
        result.msg = msg;
        return result;
    }

    // 失败，使用枚举
    public static <T> Result<T> error(AppHttpCodeEnum codeEnum) {
        Result<T> result = new Result<>();
        result.code = codeEnum.getCode();
        result.msg = codeEnum.getMsg();
        return result;
    }

    // 失败，自定义枚举和额外信息
    public static <T> Result<T> error(AppHttpCodeEnum codeEnum, String customMsg) {
        Result<T> result = new Result<>();
        result.code = codeEnum.getCode();
        result.msg = customMsg;
        return result;
    }
}
