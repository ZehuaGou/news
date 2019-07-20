package com.news.web.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Paul Lee
 *
 * 修改用户的DTO
 */
@Data
public class UserUpdateDTO {

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

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 当前登录时间
     */
    private Date currentLoginTime;

    /**
     * 注册时间
     */
    private Date registrationTime;

    /**
     * 修改信息时间
     */
    private Date updateTime;
}
