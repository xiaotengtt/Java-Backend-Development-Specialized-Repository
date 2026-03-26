package com.xiaoteng.server.service;

import com.xiaoteng.data.dto.UserAddressDTO;
import java.util.List;

public interface UserAddressService {
    List<UserAddressDTO> addressQuery(Long userId);
    
    void addAddress(UserAddressDTO userAddressDTO);
    
    void deleAddress(Long id);
    
    void updateAddress(UserAddressDTO userAddressDTO, Long id);
}
