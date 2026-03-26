package com.xiaoteng.context.current;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CurrentUser implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * 当前登录用户ID
     */
    private Long userId;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 昵称
     */
    private String nickname;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 性别 0未知 1男 2女
     */
    private Integer gender;
    
    /**
     * 头像URL
     */
    private String avatar;
    
    /**
     * 状态 1启用 0禁用
     */
    private Integer status;
    
    /**
     * 本次请求IP / 当前登录IP
     */
    private String loginIp;
}
