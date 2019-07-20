package com.news.web.config;

import com.news.web.enums.LoginInterEnum;
import com.news.web.intercepors.UserLoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paul Lee
 */
@Configuration
@Slf4j
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private UserLoginInterceptor userLoginInterceptor;

    /**
     * 用户登录不拦截的一些路径
     */
    private static final List<String> USER_PATH;

    /**
     * 管理员登录不拦截的一些路径
     */
    private static final List<String> ADMIN_PATH;

    static {
        USER_PATH = new ArrayList<>(LoginInterEnum.USER.getExclude());
        USER_PATH.addAll(LoginInterEnum.RESOURCES.getExclude());

        ADMIN_PATH = new ArrayList<>(LoginInterEnum.ADMIN.getExclude());
        ADMIN_PATH.addAll(LoginInterEnum.RESOURCES.getExclude());
    }

    /**
     * 这个方法是用来配置静态资源的，比如html，js，css，等等
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    /**
     * 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        USER_PATH.addAll(ADMIN_PATH);

        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，
        // 因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(userLoginInterceptor).addPathPatterns("/**").
                excludePathPatterns(USER_PATH);
    }
}