package com.news.web.enums;

import lombok.Getter;

/**
 * 不同页面，每页的数据数量枚举类
 *
 * @author Paul Lee
 */
@Getter
public enum NumberOfDataEnum {

    /**
     * 根据一级分类每页数据量
     */
    ONE_CATEGORY(10, "一级分类"),

    /**
     * 最新发布每页数据量
     */
    RECENT(8, "最新发布"),

    /**
     * 最热门的每页数据量
     */
    HOT(9, "最热门"),

    /**
     * 相关文章每页数据量
     */
    RELEVANT(6, "相关文章"),

    /**
     * 每页显示用户数量
     */
    USER(8, "每页显示用户数量"),

    /**
     * 管理员界面显示文章数量
     */
    ADMIN_ARTICLE(8, "管理员界面显示文章数量"),
    ;

    /**
     * 每页数据量
     */
    private Integer number;

    /**
     * 代表信息
     */
    private String message;

    NumberOfDataEnum(Integer number, String message) {
        this.number = number;
        this.message = message;
    }
}
