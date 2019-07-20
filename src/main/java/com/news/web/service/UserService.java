package com.news.web.service;

import com.github.pagehelper.PageInfo;
import com.news.web.dto.UserDTO;
import com.news.web.dto.UserUpdateDTO;
import com.news.web.form.UserLoginForm;
import com.news.web.form.UserSaveForm;
import com.news.web.form.UserUpdateForm;
import com.news.web.model.UserInfo;

/**
 * @author Paul Lee
 * <p>
 * 关于用户操作业务层接口
 */
public interface UserService {

    /**
     * 存储用户对象
     *
     * @param form 需要保存的用户form对象
     * @return 保存成功的数据数量
     */
    int save(UserSaveForm form);


    /**
     * 判断用户名密码是否正确
     *
     * @param form 包含用户登录信息的form对象
     * @return 此用户的所有信息UserInfo对象
     */
    UserInfo checkUserLogin(UserLoginForm form);

    /**
     * 根据主键查询用户信息
     *
     * @param id 用户主键
     * @return 用户DTO对象
     */
    UserDTO getOne(String id);

    /**
     * 根据用户主键删除用户
     *
     * @param userId
     * @return
     */
    int remove(String userId);

    /**
     * 修改用户信息，并且返回更新后的用户信息
     *
     * @param form 用户修改信息的form对象
     * @return 更新后的用户信息
     */
    UserInfo update(UserUpdateForm form);

    /**
     * 分页查询所有用户信息
     *
     * @param pageNum  页码数
     * @param pageSize 每页数据量
     * @return
     */
    PageInfo<UserDTO> pageAll(Integer pageNum, Integer pageSize);
}
