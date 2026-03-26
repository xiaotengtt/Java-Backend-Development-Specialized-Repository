package com.xiaoteng.inteceptor;

import com.xiaoteng.context.UserContext;
import com.xiaoteng.context.current.CurrentUser;
import com.xiaoteng.data.entity.SysUser;
import com.xiaoteng.server.service.impl.UserServiceImpl;
import com.xiaoteng.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.io.IOException;


/**
 * 1. 拦截进入的请求：
 * 凡是被配置到这个拦截器处理范围内的请求，都会先进入 preHandle()。
 * <p>
 * 2. 读取请求头中的 Token：
 * 从 HttpServletRequest 里取 Authorization。
 *
 * 3. 校验 Token 格式：
 * 要求必须是 Bearer xxx 这种格式，否则直接返回 401。
 *
 * 4. 校验 Token 是否有效：
 * 调用 JWTUtils.validateToken(token) 判断 token 是否有效、是否过期；无效则返回 401。
 *
 * 5. 解析 Token 中的用户标识：
 * 从 claims 里拿 subject，并转成 userId。
 * <p>
 * 6. 校验用户是否真实存在：
 * 根据 userId 查数据库，确认这个用户还存在。
 *
 * 7. 校验用户状态是否正常：
 * 检查是否被禁用、是否被逻辑删除。
 *
 * 8. 构建当前登录用户对象：
 * 把数据库里的 SysUser 信息组装成 CurrentUser，例如：
 *
 * userId
 *
 * username
 *
 * nickname
 *
 * realName
 *
 * phone
 *
 * email
 *
 * gender
 *
 * avatar
 *
 * status
 *
 * loginIp
 *
 * 9. 保存当前用户上下文：
 * 把 CurrentUser 放进 UserContext，这样后续 Service 层可以直接取当前登录用户。
 *
 * 10. 把用户信息放入 request：
 * 通过 request.setAttribute(...) 放入：
 *
 * currentUser
 *
 * userId
 *
 * username
 *
 * 这样 Controller 也可以直接从 request 中取。
 *
 * 11. 请求结束后清理 ThreadLocal：
 * 在 afterCompletion() 里调用 UserContext.clear()，避免线程复用导致数据串用
 */


@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    
    private final UserServiceImpl userServiceImpl;
    
    @Autowired
    public LoginInterceptor(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    
    
    /**
     * 功能一：用户登录校验
     * 功能二：生成Context
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws IOException {
        
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            writeUnauthorized(response, "未提供Token");
            return false;
        }
        
        String token = authHeader.substring(7);
        if (!JWTUtils.validateToken(token)) {
            writeUnauthorized(response, "Token无效或过期");
            return false;
        }
        
        Claims claims = JWTUtils.getClaims(token);
        String subject = claims.getSubject();
        
        if (subject == null || subject.isBlank()) {
            writeUnauthorized(response, "Token中用户标识缺失");
            return false;
        }
        
        Long userId;
        try {
            userId = Long.valueOf(subject);
        } catch (NumberFormatException e) {
            writeUnauthorized(response, "Token中的用户标识格式错误");
            return false;
        }
        
        SysUser sysUser = userServiceImpl.queryUserById(userId);
        if (sysUser == null) {
            writeUnauthorized(response, "用户不存在");
            return false;
        }
        
        if (sysUser.getStatus() == null || sysUser.getStatus() != 1) {
            writeUnauthorized(response, "账号已禁用");
            return false;
        }
        
        if (sysUser.getDeleted() != null && sysUser.getDeleted() == 1) {
            writeUnauthorized(response, "账号已删除");
            return false;
        }
        
        CurrentUser currentUser = buildCurrentUser(sysUser, request);
        
        UserContext.setCurrentUser(currentUser);
        
        request.setAttribute("currentUser", currentUser);
        request.setAttribute("userId", currentUser.getUserId());
        request.setAttribute("username", currentUser.getUsername());
        
        return true;
    }
    
    /**
     * 清理Context
     */
    
    @Override
    public void afterCompletion(HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull Object handler,
                                Exception ex) {
        UserContext.clear();
    }
    
    /**
     * 当前登录对象的生成——信息放入方法
     */
    private CurrentUser buildCurrentUser(SysUser sysUser, HttpServletRequest request) {
        CurrentUser currentUser = new CurrentUser();
        currentUser.setUserId(sysUser.getId());
        currentUser.setUsername(sysUser.getUsername());
        currentUser.setNickname(sysUser.getNickname());
        currentUser.setRealName(sysUser.getRealName());
        currentUser.setPhone(sysUser.getPhone());
        currentUser.setEmail(sysUser.getEmail());
        currentUser.setGender(sysUser.getGender());
        currentUser.setAvatar(sysUser.getAvatar());
        currentUser.setStatus(sysUser.getStatus());
        currentUser.setLoginIp(getClientIp(request));
        return currentUser;
    }
    
    private void writeUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\":401,\"message\":\"" + message + "\"}");
    }
    
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isBlank() && !"unknown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            return index > 0 ? ip.substring(0, index).trim() : ip.trim();
        }
        
        ip = request.getHeader("Proxy-Client-IP");
        if (ip != null && !ip.isBlank() && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip != null && !ip.isBlank() && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        
        return request.getRemoteAddr();
    }
}
