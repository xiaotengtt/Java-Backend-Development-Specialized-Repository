package com.xiaoteng.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 统一返回结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * 状态码
     */
    private Integer code;
    
    /**
     * 提示信息
     */
    private String message;
    
    /**
     * 返回数据
     */
    private T data;
    
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }
    
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }
    
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data);
    }
    
    public static <T> Result<T> fail() {
        return new Result<>(500, "操作失败", null);
    }
    
    public static <T> Result<T> fail(String message) {
        return new Result<>(500, message, null);
    }
    
    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }
}
