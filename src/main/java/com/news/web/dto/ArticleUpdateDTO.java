package com.news.web.dto;

import lombok.Data;

/**
 * @author Paul Lee
 * <p>
 * 修改页面中Article类
 */
@Data
public class ArticleUpdateDTO {

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章所属分类
     */
    private String oneCategoryName;

    /**
     * 外键 关联一级分类id
     */
    private Integer oneCategoryId;
}
