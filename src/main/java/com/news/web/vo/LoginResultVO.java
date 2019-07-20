package com.news.web.vo;

import lombok.Data;

/**
 * @author Paul Lee
 * <p>
 * 登录结果VO
 */
@Data
public class LoginResultVO {

    /**
     * 是否登录成功
     */
    private Boolean success;

    /**
     * 携带消息
     */
    private String message;

    public LoginResultVO() {
    }

    public LoginResultVO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
