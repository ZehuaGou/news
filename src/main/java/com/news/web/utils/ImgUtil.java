package com.news.web.utils;

import com.news.web.enums.PathEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Paul Lee
 * <p>
 * 关于图片的工具类
 */
@Slf4j
public class ImgUtil {

    /**
     * 文件上传
     *
     * @param list
     * @param context 项目路径
     * @return
     * @throws IOException
     */
    public static List<String> upload(List<MultipartFile> list, String context) throws IOException {

        String finalPath = PathUtil.imgPath();

        File finalPathFile = new File(finalPath);
        if (!finalPathFile.exists()) {
            finalPathFile.mkdirs();
        }
        log.info("finalPath: " + finalPath);
        List<String> urlData = new ArrayList<>();
        int index = 0;
        for (MultipartFile img : list) {
            String fileName = img.getOriginalFilename();
            if ("".equals(fileName)) {
                continue;
            }

            /*
             * 为了保证文件名不一致，因此文件名称使用当前的时间戳和4位的随机数，还有原始文件名组成
             * 觉得实际的企业开发不应当使用原始文件名，否则上传者使用一些不好的名字，对于下载者就不好了．
             * 这里为了调试方便，可以加上．
             * 文件名动态部分
             * */
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String finalFileName = System.currentTimeMillis() + Math.round(Math.random() * 1000)
                    + KeyUtil.genUniqueKey() + suffix; //新文件名
            File newFile = new File(finalPath + finalFileName);
            log.info("new file path: " + newFile.getAbsolutePath());
            //保存文件到本地
            img.transferTo(newFile);

            log.info("final File Name: " + finalFileName);
            /*
             * 这里的路径是项目路径＋文件路径＋文件名称
             * 这么写不是规范的做法，项目路径应是放在前端处理，只需要发送相对路径和文件名称即可，
             * 项目路径由前端加上．
             * */
            urlData.add(context + PathEnum.IMG_UPLOAD_RELATIVE_PATH.getPath() + finalFileName);
            log.info("urlData[" + (index) + "]: " + urlData.get(index));
            index++;
        }
        return urlData;
    }

    public static boolean delete(String fileName) {

        File file = new File(fileName);
        if (!file.exists()) {
            log.error("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile()) {
                return FileUtil.deleteFile(fileName);
            } else {
                return FileUtil.deleteDirectory(fileName);
            }
        }
    }

}
