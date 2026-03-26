package com.xiaoteng.server.service.impl;

import com.xiaoteng.context.UserContext;
import com.xiaoteng.data.dto.UserAddressDTO;
import com.xiaoteng.server.mapper.UserAddressMapper;
import com.xiaoteng.server.service.UserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
public class UserAddressServiceImpl implements UserAddressService {
    
    private final UserAddressMapper userAddressMapper;
    
    @Autowired
    public UserAddressServiceImpl(UserAddressMapper userAddressMapper) {
        this.userAddressMapper = userAddressMapper;
    }
    
    
    /**
     * 查询用户全部地址
     */
    
    @Override
    public List<UserAddressDTO> addressQuery(Long userId) {
        return userAddressMapper.query(userId);
    }
    
    /**
     * 新增地址
     *
     */
    @Override
    public void addAddress(UserAddressDTO userAddressDTO) {
        userAddressMapper.add(userAddressDTO);
    }
    
    /**
     * 删除地址
     */
    @Override
    public void deleAddress(Long id) {
        //参数Id为地址Id，用户Id需要从当前登录的用户的状态信息获取
        Long userId= UserContext.getUserId();
        userAddressMapper.dele(id,userId);
        
        
    }
    
    /**
     * 修改地址
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateAddress(UserAddressDTO userAddressDTO, Long id) {
        deleAddress(id);
        addAddress(userAddressDTO);
    }
}
