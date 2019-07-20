package com.news.web.form;

import lombok.Data;

/**
 * @author Paul Lee
 * <p>
 * 用户登录form类
 */
@Data
public class UserLoginForm {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}
