package com.news.web.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Paul Lee
 * <p>
 * 在文章中的回复DTO
 */
@Data
public class ReplyInArticleDTO {

    /**
     * 回复内容
     */
    private String content;

    /**
     * 回复者昵称
     */
    private String author;

    /**
     * 外键 关联回复者id
     */
    private String authorId;

    /**
     * 创建时间
     */
    private Date createTime;
}
