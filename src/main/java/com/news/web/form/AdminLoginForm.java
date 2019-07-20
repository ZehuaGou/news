package com.news.web.form;

import lombok.Data;

/**
 * @author Paul Lee
 * <p>
 * 管理员登录form类
 */
@Data
public class AdminLoginForm {

    /**
     * 管理员工号
     */
    private String jobNumber;

    /**
     * 用户密码
     */
    private String password;
}
