package com.news.web.utils;

import com.news.web.enums.PathEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * @author Paul Lee
 * <p>
 * 一些有关路径
 */
@Slf4j
public class PathUtil {

    /**
     * 获得图片存储路径
     *
     * @return
     * @throws FileNotFoundException
     */
    public static String imgPath() {
        try {
            String imgUploadAbsolutePath = ResourceUtils.getURL("classpath:").getPath() + "static/";
            log.info("imgUploadAbsolutePath: " + imgUploadAbsolutePath);
            //绝对路径　＋　相对路径
            return imgUploadAbsolutePath + PathEnum.IMG_UPLOAD_RELATIVE_PATH.getPath();
        } catch (FileNotFoundException e) {
            log.error("上传文件路径找不到！");
            e.printStackTrace();
        }

        return null;
    }
}
