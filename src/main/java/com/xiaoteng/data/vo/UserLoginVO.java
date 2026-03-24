package com.xiaoteng.data.vo;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 登录返回对象
 */
@Data
public class UserLoginVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户ID
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
     * 头像
     */
    private String avatar;
    
    /**
     * 登录token
     */
    private String token;
    
    /**
     * token类型
     */
    private String tokenType;
    
    /**
     * 过期时间，单位：秒
     */
    private Long expiresIn;
    
    /**
     * 角色列表
     */
    private List<String> roles;
    
    /**
     * 权限标识列表
     */
    private List<String> permissions;
}
