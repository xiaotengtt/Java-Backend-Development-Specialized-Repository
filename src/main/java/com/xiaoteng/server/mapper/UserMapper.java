package com.xiaoteng.server.mapper;

import com.xiaoteng.data.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    
    
    //根据用户ID查询信息
    @Select("SELECT * FROM loginauthentication_schema.sys_user WHERE ID = #{id}")
    SysUser queryUserById(Long id);
    
    
    //根据用户名、手机号、邮箱查询用户信息
    @Select("SELECT * FROM loginauthentication_schema.sys_user WHERE username = #{userName}")
    SysUser queryUserByUserName(String userName);
}