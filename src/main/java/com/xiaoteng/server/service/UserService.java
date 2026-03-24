package com.xiaoteng.server.service;


import com.xiaoteng.data.dto.UserLoginDTO;
import com.xiaoteng.data.entity.SysUser;
import com.xiaoteng.data.vo.UserLoginVO;


public interface UserService {
    
    /**
     * 用户登录
     */
    UserLoginVO login(UserLoginDTO userLoginDTO);
    
    SysUser queryUserById(Integer id);
}