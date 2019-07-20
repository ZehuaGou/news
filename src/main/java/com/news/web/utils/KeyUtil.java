package com.news.web.utils;

import java.util.UUID;

/**
 * @author Li Ersan
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     *
     */
    public static synchronized String genUniqueKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
