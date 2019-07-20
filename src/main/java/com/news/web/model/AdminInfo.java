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
 * 管理员信息实体类
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class AdminInfo {

    /**
     * 管理员主键
     */
    @Id
    private String adminId;

    /**
     * 工号，登录帐号
     */
    private String jobNumber;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 角色权限
     */
    private Integer authority;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
