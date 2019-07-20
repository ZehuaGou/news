package com.news.web.utils;

import com.news.web.enums.CodeEnum;

/**
 * @author Paul Lee
 */
public class EnumUtil {

    /**
     * 得到相应的枚举信息
     * <T extends CodeEnum> 静态方法对泛型进行说明
     *
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends CodeEnum> T getByCode(String code, Class<T> enumClass) {

        //getEnumConstants() 反射取出Enum所有常量的属性值
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }

        return null;
    }
}
