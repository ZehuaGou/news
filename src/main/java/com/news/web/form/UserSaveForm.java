package com.news.web.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Paul Lee
 * <p>
 * 注册用户Form
 */
@Data
public class UserSaveForm {

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
