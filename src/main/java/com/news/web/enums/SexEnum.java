package com.news.web.enums;

import lombok.Getter;

/**
 * 性别枚举
 *
 * @author Paul Lee
 */
@Getter
public enum SexEnum implements CodeEnum{
    /**
     * 男性
     */
    MALE("M", "男"),

    /**
     * 女性
     */
    FEMALE("F", "女"),
    ;

    /**
     * 性别标识
     */
    private String code;

    /**
     * 代表信息
     */
    private String message;

    SexEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
