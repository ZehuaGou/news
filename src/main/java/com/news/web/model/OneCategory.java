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
 * 一级分类实体类
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class OneCategory {

    /**
     * 自动递增
     */
    @Id
    private Integer oneCategoryId;

    /**
     * 一级分类名称
     */
    private String name;

    /**
     * 分类介绍
     */
    private String introduction;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
