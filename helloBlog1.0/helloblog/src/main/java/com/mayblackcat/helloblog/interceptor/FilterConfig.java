package com.mayblackcat.helloblog.interceptor;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//拦截器配置
@Configuration
public class FilterConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new AdminFilter())
                .addPathPatterns("/admin/**")    //拦截后台页面
                .excludePathPatterns("/admin")  //排除登录页面拦截
                .excludePathPatterns("/admin/login");
    }
}
