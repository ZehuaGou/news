package com.news.web.form;

import lombok.Data;

import java.util.Date;

/**
 * @author Paul Lee
 * <p>
 * 修改用户信息Form类
 */
@Data
public class UserUpdateForm {

    /**
     * 用户主键
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 性别 男/女 M/F
     */
    private String sex;

    /**
     * 生日
     */
    private Date birthday;
}
