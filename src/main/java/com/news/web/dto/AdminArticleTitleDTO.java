package com.news.web.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Paul Lee
 *
 * 管理员界面下文章DTO
 */
@Data
public class AdminArticleTitleDTO {
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
     * 文章点击量
     */
    private Long clickVolume;

    /**
     * 创建时间
     */
    private Date createTime;
}
