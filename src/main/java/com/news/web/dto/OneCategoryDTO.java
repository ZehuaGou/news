package com.news.web.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Paul Lee
 * <p>
 * 一级分类DTO
 */
@Data
public class OneCategoryDTO {

    /**
     * 自动递增
     */
    private Integer oneCategoryId;

    /**
     * 一级分类名称
     */
    private String name;

    /**
     * 分类介绍
     */
    private String introduction;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
