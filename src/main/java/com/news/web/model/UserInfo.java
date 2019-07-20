package com.news.web.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Paul Lee
 * <p>
 * 用户信息实体类
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class UserInfo {

    /**
     * 用户主键
     */
    @Id
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
