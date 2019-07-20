package com.news.web.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Paul Lee
 * <p>
 * 文章DTO
 */
@Data
public class ArticleDTO {

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
     * 文章作者昵称
     */
    private String author;

    /**
     * 文章所属分类
     */
    private String oneCategoryName;

    /**
     * 外键 关联作者id
     */
    private String authorId;

    /**
     * 外键 关联一级分类id
     */
    private Integer oneCategoryId;

    /**
     * 此文章所有回复DTO集合
     */
    private List<ReplyInArticleDTO> replyDTOList;
}
