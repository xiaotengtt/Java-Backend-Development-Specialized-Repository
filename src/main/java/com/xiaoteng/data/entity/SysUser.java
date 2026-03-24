package com.xiaoteng.data.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 */
@Data
public class SysUser implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户ID
     */
    private Long id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
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
     * 逻辑删除 0未删除 1已删除
     */
    private Integer deleted;
    
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;
    
    /**
     * 最后登录IP
     */
    private String lastLoginIp;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
