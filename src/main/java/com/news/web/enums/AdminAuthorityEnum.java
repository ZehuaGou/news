package com.news.web.enums;

import lombok.Getter;

/**
 * @author Paul Lee
 * <p>
 * 管理员权限枚举类
 */
@Getter
public enum AdminAuthorityEnum {

    /**
     * 一级权限最低
     */
    ONE_AUTHORITY(1, "一级权限"),

    /**
     * 二级权限
     */
    TWO_AUTHORITY(2, "二级权限"),

    /**
     * 三级权限
     */
    THREE_AUTHORITY(3, "三级权限"),
    ;

    /**
     * 权限大小标识
     */
    private Integer code;

    /**
     * 标识代表信息
     */
    private String message;

    AdminAuthorityEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
