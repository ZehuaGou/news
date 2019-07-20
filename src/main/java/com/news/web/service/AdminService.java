package com.news.web.service;

import com.news.web.form.AdminLoginForm;
import com.news.web.model.AdminInfo;

/**
 * @author Paul Lee
 * <p>
 * 管理员业务层类
 */
public interface AdminService {

    /**
     * 判断用户名密码是否正确
     *
     * @param form 包含管理员登录信息的form对象
     * @return 此用户的所有信息AdminInfo对象
     */
    AdminInfo checkUserLogin(AdminLoginForm form);
}
