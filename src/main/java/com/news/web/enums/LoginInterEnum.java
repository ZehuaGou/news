package com.news.web.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Paul Lee
 * <p>
 * 登录拦截器路径枚举
 */
@Getter
public enum LoginInterEnum {

    /**
     * 不拦截的静态资源路径
     */
    RESOURCES(new ArrayList<>(Arrays.asList("/mapper/**", "/bootstrap/**",
            "/css/**", "/images/**", "/jquery/**", "/js/**", "/wangEditor/**")),
            "静态资源"),

    /**
     * 用户拦截器不需要拦截的路径
     */
    USER(new ArrayList<>(Arrays.asList("/article/index", "/article/**/detail",
            "/article/**/**/list", "/article/search/**/**", "/user/**")),
            "用户"),

    /**
     * 管理员拦截器不需要拦截的路径
     */
    ADMIN(new ArrayList<>(Collections.singletonList("/admin/login")),
            "管理员"),
    ;


    /**
     * 不需要拦截的路径数组
     */
    private List<String> exclude;

    /**
     * 此枚举代表信息
     */
    private String message;

    LoginInterEnum(List<String> exclude, String message) {
        this.exclude = exclude;
        this.message = message;
    }
}
