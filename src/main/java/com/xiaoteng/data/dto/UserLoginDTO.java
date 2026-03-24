package com.xiaoteng.data.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 用户登录 DTO
 * 前端请求体映射类
 */
@Data
public class UserLoginDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户名/手机号/邮箱，根据系统定义
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 图形验证码，可选
     */
    private String captcha;
    
    /**
     * 验证码唯一标识，可选
     */
    private String captchaKey;
    
    /**
     * 是否记住我，可选
     */
    private Boolean rememberMe;
}
