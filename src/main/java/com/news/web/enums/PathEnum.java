package com.news.web.enums;

import lombok.Getter;

/**
 * @author Paul Lee
 * <p>
 * 相关路径枚举
 */
@Getter
public enum PathEnum {

    /**
     * 上传图片相对路径
     */
    IMG_UPLOAD_RELATIVE_PATH("/upload/articleImg/", "上传图片相对路径"),
    ;

    /**
     * 路径
     */
    private String path;

    /**
     * 代表信息
     */
    private String message;

    PathEnum(String path, String message) {
        this.path = path;
        this.message = message;
    }
}
