package com.xiaoteng.server.mapper;

import java.util.List;
import com.xiaoteng.data.dto.UserAddressDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAddressMapper {
    List<UserAddressDTO> query(Long userId);
    
    void add(UserAddressDTO userAddressDTO);
    
    void dele(Long id, Long userId);
}
