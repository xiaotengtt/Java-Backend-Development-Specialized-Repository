package com.xiaoteng.config;

import com.xiaoteng.inteceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    
    
    private final LoginInterceptor loginInterceptor;
    
    public LoginInterceptorConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                //要拦截哪些路径
                .addPathPatterns("/api/**")
                //不拦截哪些路径
                .excludePathPatterns(
                        "/api/login/**",
                        "/register",
                        "/error",
                        "/static/**",
                        "/css/**",
                        "/js/**",
                        "/images/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
