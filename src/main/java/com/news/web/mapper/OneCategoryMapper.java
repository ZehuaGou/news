package com.news.web.mapper;

import com.news.web.model.OneCategory;
import com.news.web.model.OneCategoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneCategoryMapper {
    long countByExample(OneCategoryExample example);

    int deleteByExample(OneCategoryExample example);

    int deleteByPrimaryKey(Integer oneCategoryId);

    int insert(OneCategory record);

    int insertSelective(OneCategory record);

    List<OneCategory> selectByExample(OneCategoryExample example);

    OneCategory selectByPrimaryKey(Integer oneCategoryId);

    int updateByExampleSelective(@Param("record") OneCategory record, @Param("example") OneCategoryExample example);

    int updateByExample(@Param("record") OneCategory record, @Param("example") OneCategoryExample example);

    int updateByPrimaryKeySelective(OneCategory record);

    int updateByPrimaryKey(OneCategory record);
}