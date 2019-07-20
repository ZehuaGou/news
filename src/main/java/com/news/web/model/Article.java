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
 * 文章实体类
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class Article {

    /**
     * 文章主键
     */
    @Id
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
     * 外键 关联作者id
     */
    private String authorId;

    /**
     * 外键 关联一级分类id
     */
    private Integer oneCategoryId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
