package com.news.web.service.impl;

import com.news.web.dto.OneCategoryDTO;
import com.news.web.form.OneCategoryUpdateForm;
import com.news.web.mapper.OneCategoryMapper;
import com.news.web.model.OneCategory;
import com.news.web.service.OneCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paul Lee
 */
@Service
public class OneCategoryServiceImpl implements OneCategoryService {

    @Autowired
    private OneCategoryMapper mapper;

    @Override
    public List<OneCategoryDTO> listAll() {

        List<OneCategory> oneCategoryList = mapper.selectByExample(null);
        return oneCategory2OneCategoryDTO(oneCategoryList);
    }

    @Override
    public OneCategoryDTO update(OneCategoryUpdateForm form) {

        OneCategory oneCategory = new OneCategory();
        BeanUtils.copyProperties(form, oneCategory);

        mapper.updateByPrimaryKeySelective(oneCategory);


        return getOne(oneCategory.getOneCategoryId());
    }

    @Override
    public OneCategoryDTO getOne(Integer id) {

        OneCategory oneCategory = mapper.selectByPrimaryKey(id);

        return oneCategory2OneCategoryDTO(oneCategory);
    }

    /**
     * 将OneCategory对象集合转换为OneCategoryDTO对象集合
     *
     * @param list OneCategory对象集合
     * @return OneCategoryDTO对象集合
     */
    private List<OneCategoryDTO> oneCategory2OneCategoryDTO(List<OneCategory> list) {
        List<OneCategoryDTO> result = new ArrayList<>();

        for (OneCategory oneCategory : list) {
            OneCategoryDTO dto = new OneCategoryDTO();
            BeanUtils.copyProperties(oneCategory, dto);
            result.add(dto);
        }

        return result;
    }

    /**
     * 将OneCategory对象转换为OneCategoryDTO对象
     *
     * @param oneCategory OneCategory对象
     * @return OneCategoryDTO对象
     */
    private OneCategoryDTO oneCategory2OneCategoryDTO(OneCategory oneCategory) {
        OneCategoryDTO result = new OneCategoryDTO();
        BeanUtils.copyProperties(oneCategory, result);

        return result;
    }
}
