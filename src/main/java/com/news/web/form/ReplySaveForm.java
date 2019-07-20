package com.news.web.form;

import lombok.Data;

/**
 * @author Paul Lee
 *
 * 回复保存Form
 */
@Data
public class ReplySaveForm {

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
     * 外键 关联文章id
     */
    private String articleId;
}
