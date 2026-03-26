package com.xiaoteng.server.controller.user;

import com.xiaoteng.data.dto.UserAddressDTO;
import com.xiaoteng.result.Result;
import com.xiaoteng.server.service.UserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * 用户地址Controller
 * 实现用户对地址表CRUD
 */

@RestController
@Slf4j
@RequestMapping("/user-address")
public class UserAddressController {
    
    private UserAddressService userAddressService;
    
    @Autowired
    public void setUserAddressService(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }
    
    
    /**
     * 根据用户ID查询用户地址
     */
    
    @GetMapping("/{id}")
    public Result<List<UserAddressDTO>> addressQuery(@PathVariable Long id) {
        
        List<UserAddressDTO> userAddressVOS = userAddressService.addressQuery(id);
        return Result.success(userAddressVOS);
        
    }
    
    /**
     *
     * 新增地址
     */
    @PostMapping("/{id}")
    public Result<Void> addAddress(@RequestBody UserAddressDTO userAddressDTO) {
        
        userAddressService.addAddress(userAddressDTO);
        return Result.success();
    }
    
    
    /**
     * 删除地址
     */
    @DeleteMapping("/id")    //此id为地址id
    public Result<Void> deleteAddress(Long id) {
        
        userAddressService.deleAddress(id);
        return Result.success("地址删除成功");
    }
    
    
    /**
     * 修改地址
     * 修改方法统一操作，先删除后新增
     */
    @PutMapping("/{id}")
    public Result<Void> updateAddress(@RequestBody UserAddressDTO userAddressDTO,@PathVariable Long id) {
        
        userAddressService.updateAddress(userAddressDTO,id);
        return Result.success();
    }
    
}
