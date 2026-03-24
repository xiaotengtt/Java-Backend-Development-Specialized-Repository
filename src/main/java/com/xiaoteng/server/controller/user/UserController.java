package com.xiaoteng.server.controller.user;

import com.xiaoteng.data.dto.UserLoginDTO;
import com.xiaoteng.data.entity.SysUser;
import com.xiaoteng.result.Result;
import com.xiaoteng.server.service.impl.UserServiceImpl;
import com.xiaoteng.data.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("UserController")
@RequestMapping("/user")
@Slf4j
public class UserController {

    
    private final UserServiceImpl userServiceImpl;
    
    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    
    /**
     * 用户登录功能的实现
     */
    
    @PostMapping("/login")
    public Result<UserLoginVO> UserLogin(@RequestBody UserLoginDTO userLoginDTO)
    {
        log.info("实现用户登录功能接口，利用拦截器");
        UserLoginVO userLoginVO = userServiceImpl.login(userLoginDTO);
        if(userLoginVO==null)
            return Result.fail(401,"用户密码错误/用户不存在");
        return Result.success(userLoginVO);
    }
    
    /**
     * 用户查询功能的实现
     */
    @GetMapping("/query")
    public Result<SysUser>  queryUserById(@RequestParam("id") Integer id)
    {
        SysUser user = userServiceImpl.queryUserById(id);
        
        if(user == null){
            return Result.fail();
        }
        
        return Result.success(user);
    }
    
}
