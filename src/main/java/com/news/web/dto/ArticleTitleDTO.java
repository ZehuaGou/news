package com.news.web.dto;

import lombok.Data;

/**
 * @author Paul Lee
 * <p>
 * 文章标题DTO 方便显示标题列表
 */
@Data
public class ArticleTitleDTO {

    /**
     * 文章主键
     */
    private String articleId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章作者昵称
     */
    private String author;

    /**
     * 文章点击量
     */
    private Long clickVolume;
}
