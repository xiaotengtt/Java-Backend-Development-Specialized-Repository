package com.xiaoteng.inteceptor;


import com.xiaoteng.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    
    
    /**
     * 登录状态校验拦截器实现
     *
     *
     */
    
    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws IOException {
        
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write("{\"code\":401,\"message\":\"未提供Token\"}");
            return false;
        }
        
        String token = authHeader.substring(7);
        if (!JWTUtils.validateToken(token)) {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write("{\"code\":401,\"message\":\"Token无效或过期\"}");
            return false;
        }
        
        // 可选：把用户信息放入 request，供 Controller 使用
        Claims claims = JWTUtils.getClaims(token);
        request.setAttribute("userId", claims.getSubject());
        
        return true;
    }
}
