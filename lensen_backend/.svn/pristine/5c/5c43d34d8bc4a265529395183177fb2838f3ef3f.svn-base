package com.lensen.backend.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 匿名登录过滤特殊字符拦截器，未使用
        // registry.addInterceptor(injectionInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(authHeaderInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public InjectionInterceptor injectionInterceptor() {
        return new InjectionInterceptor();
    }

    @Bean
    public AuthHeaderInterceptor authHeaderInterceptor() {
        return new AuthHeaderInterceptor();
    }
}