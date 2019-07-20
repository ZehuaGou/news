package com.news.web.service;

import com.news.web.dto.OneCategoryDTO;
import com.news.web.form.OneCategoryUpdateForm;

import java.util.List;

/**
 * @author Paul Lee
 * <p>
 * 一级分类业务层接口
 */
public interface OneCategoryService {

    /**
     * 查询所有一级分类信息
     *
     * @return 一级分类DTO集合
     */
    List<OneCategoryDTO> listAll();

    /**
     * 根据id修改相应一级分类
     *
     * @param form
     * @return
     */
    OneCategoryDTO update(OneCategoryUpdateForm form);

    /**
     * 根据主键查询相应一级分类信息
     *
     * @param id
     * @return
     */
    OneCategoryDTO getOne(Integer id);
}
