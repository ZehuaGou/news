package com.news.web.form;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paul Lee
 * <p>
 * 保存文章Form
 */
@Data
public class ArticleSaveForm {

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章作者昵称
     */
    private String author;

    /**
     * 外键 关联作者id
     */
    private String authorId;

    /**
     * 外键 关联一级分类id
     */
    private Integer oneCategoryId;

    /**
     * 该文章下存储的所有图片路径
     */
    private List<String> imgNames = new ArrayList<>();
}
