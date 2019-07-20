package com.news.web.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Paul Lee
 * <p>
 * 回复信息实体类
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class Reply {

    /**
     * 回复主键
     */
    @Id
    private String replyId;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
