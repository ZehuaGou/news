package com.news.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.web.utils.KeyUtil;
import com.news.web.dto.ArticleTitleDTO;
import com.news.web.dto.UserDTO;
import com.news.web.form.UserLoginForm;
import com.news.web.form.UserSaveForm;
import com.news.web.form.UserUpdateForm;
import com.news.web.mapper.UserInfoMapper;
import com.news.web.model.UserInfo;
import com.news.web.model.UserInfoExample;
import com.news.web.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Paul Lee
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper mapper;

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private ReplyServiceImpl replyService;

    @Override
    public int save(UserSaveForm form) {
        UserInfo userInfo = new UserInfo();

        BeanUtils.copyProperties(form, userInfo);
        userInfo.setUserId(KeyUtil.genUniqueKey());

        return mapper.insert(userInfo);
    }

    @Override
    public UserInfo checkUserLogin(UserLoginForm form) {

        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(form.getUsername());
        criteria.andPasswordEqualTo(form.getPassword());

        List<UserInfo> result = mapper.selectByExample(example);

        if (result.size() != 0) {
            UserInfo userInfo = result.get(0);

            //获得上次登录时间
            Date lastTime = userInfo.getCurrentLoginTime();
            //将本次登录时间存入当前时间
            userInfo.setCurrentLoginTime(new Date());
            //将上次登录时间更新
            userInfo.setLastLoginTime(lastTime);
            //修改当前用户信息
            mapper.updateByPrimaryKeySelective(userInfo);

            return userInfo;
        }

        return null;
    }

    @Override
    public UserDTO getOne(String id) {

        UserInfo userInfo = mapper.selectByPrimaryKey(id);

        UserDTO userDTO = new UserDTO();

        BeanUtils.copyProperties(userInfo, userDTO);

        List<ArticleTitleDTO> titleDTOList = articleService.listByAuthor(id);

        userDTO.setArticleTitleDTOList(titleDTOList);

        return userDTO;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int remove(String userId) {

        //删除此用户所有文章
        articleService.removeByAuthor(userId);
        //删除此用户所有的回复
        replyService.removeByAuthor(userId);

        return mapper.deleteByPrimaryKey(userId);
    }

    @Override
    public UserInfo update(UserUpdateForm form) {

        UserInfo userInfo = new UserInfo();

        BeanUtils.copyProperties(form, userInfo);

        mapper.updateByPrimaryKeySelective(userInfo);

        return mapper.selectByPrimaryKey(userInfo.getUserId());
    }

    @Override
    public PageInfo<UserDTO> pageAll(Integer pageNum, Integer pageSize) {

        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();

        example.setOrderByClause("registration_time DESC");

        PageHelper.startPage(pageNum, pageSize, true);

        List<UserInfo> articleList = mapper.selectByExample(example);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(articleList);

        return user2userDTO(pageInfo);
    }

    /**
     * 将UserInfo分页对象转化为UserDTO分页对象
     *
     * @param pageInfo
     * @return
     */
    private PageInfo<UserDTO> user2userDTO(PageInfo<UserInfo> pageInfo) {

        PageInfo<UserDTO> result = new PageInfo<>();

        List<UserDTO> list = new ArrayList<>();

        for (UserInfo userInfo : pageInfo.getList()) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userInfo, userDTO);
            list.add(userDTO);
        }

        BeanUtils.copyProperties(pageInfo, result);
        result.setList(list);

        return result;
    }
}
