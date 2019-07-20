package com.news.web.form;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paul Lee
 * <p>
 * 文章修改Form类
 */
@Data
public class ArticleUpdateForm {

    /**
     * 文章主键
     */
    private String articleId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 外键 关联一级分类id
     */
    private Integer oneCategoryId;

    /**
     * 该文章下存储的所有图片路径
     */
    private List<String> imgNames = new ArrayList<>();
}
