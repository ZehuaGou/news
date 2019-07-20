package com.news.web.service.impl;

import com.news.web.form.AdminLoginForm;
import com.news.web.mapper.AdminInfoMapper;
import com.news.web.model.AdminInfo;
import com.news.web.model.AdminInfoExample;
import com.news.web.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Paul Lee
 * <p>
 * 管理员业务层实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminInfoMapper mapper;

    @Override
    public AdminInfo checkUserLogin(AdminLoginForm form) {
        AdminInfoExample example = new AdminInfoExample();
        AdminInfoExample.Criteria criteria = example.createCriteria();
        criteria.andJobNumberEqualTo(form.getJobNumber());
        criteria.andPasswordEqualTo(form.getPassword());

        List<AdminInfo> result = mapper.selectByExample(example);

        return result.size() == 0 ? null : result.get(0);
    }
}
