package com.xiaoteng.server.service.impl;

import com.xiaoteng.data.dto.UserLoginDTO;
import com.xiaoteng.data.entity.SysUser;
import com.xiaoteng.server.mapper.UserMapper;
import com.xiaoteng.server.service.UserService;
import com.xiaoteng.utils.JWTUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.xiaoteng.data.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    
    
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    
    /**
     * 用户登录方法
     */
    @Override
    public UserLoginVO login(UserLoginDTO userLoginDTO) {
        SysUser sysUser = userMapper.queryUserByUserName(userLoginDTO.getUsername());
        log.info("查询到的角色信息为：{}",sysUser);
        if(sysUser == null){
            log.info("当前用户不存在，跳转注册页面");
            return null;
        }else {
            //用户存在
            log.info("用户存在：{}",userLoginDTO.getUsername());
            String passWord = sysUser.getPassword();
            log.info("查询到的用户密码为：{}",passWord);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            //模拟数据库明文加密
            String bCryptPassword= bCryptPasswordEncoder.encode(passWord);
            if(bCryptPasswordEncoder.matches(userLoginDTO.getPassword(),bCryptPassword)){
                //用户存在且登录成功
                //生成JWT
                String token = JWTUtils.generateToken(sysUser.getId().toString());
                log.info("token：{}",token);
                UserLoginVO userLoginVO = new UserLoginVO();
                BeanUtils.copyProperties(sysUser,userLoginVO);
                userLoginVO.setToken(token);
                return userLoginVO;
            }else {
                return null;
            }
        }
    }
    
    
    /**
     * 根据ID查询用户
     */
    public SysUser queryUserById(Long id) {
        return userMapper.queryUserById(id);
    }
}