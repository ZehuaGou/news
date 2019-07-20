package com.news.web.form;

import lombok.Data;

/**
 * @author Paul Lee
 */
@Data
public class OneCategoryUpdateForm {

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
}
