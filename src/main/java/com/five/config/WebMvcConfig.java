package com.five.config;

import com.five.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建并添加登录拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/")  // 设置需要拦截的路径，这里设置为根路径
                .excludePathPatterns("/index.html")  // 设置排除拦截的路径，这里设置为 /index.html
                .excludePathPatterns("/static/**");  // 设置排除拦截的路径，这里设置为静态资源路径，如：/static/**
    }
}


