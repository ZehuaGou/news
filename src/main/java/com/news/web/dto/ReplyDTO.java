package com.news.web.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Paul Lee
 * <p>
 * 回复DTO
 */
@Data
public class ReplyDTO {

    /**
     * 回复内容
     */
    private String content;

    /**
     * 外键 关联文章id
     */
    private String articleId;

    /**
     * 相应的文章标题
     */
    private String articleTitle;

    /**
     * 创建时间
     */
    private Date createTime;
}
